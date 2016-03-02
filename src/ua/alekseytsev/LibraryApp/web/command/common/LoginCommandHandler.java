package ua.alekseytsev.LibraryApp.web.command.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.UserService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.PasswordHash;
import ua.alekseytsev.LibraryApp.web.util.Path;
import ua.alekseytsev.LibraryApp.web.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login handler
 */
public class LoginCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -2460608359473159213L;
    private static final Logger LOG = LogManager.getLogger(LoginCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String email = request.getParameter("email").toLowerCase();
        LOG.trace("email ===>" + email);
        String password = request.getParameter("password");
        LOG.trace("password ===>" + password);
        try {
            if ("".equals(password) || "".equals(email)) {
                throw new LibraryException(LibraryException.ERR_EMPTY_FIELDS);
            }
            Validator.validateEmail(email);
            User user = new UserService().authentication(email, PasswordHash.hash(password));
            LOG.trace("Authenticate user ===>" + user);
            if (user.getBannedAt() != null) {
                throw new LibraryException(LibraryException.ERR_USER_BANNED);
            }
            request.getSession().setAttribute("user", user);
            LOG.debug("Set the session attribute \"user\" ===>" + user);
            request.getSession().setAttribute("path", Path.PAGE_WELCOME);
        } catch (LibraryException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getSession().setAttribute("path", Path.PAGE_LOGIN);
        }
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}