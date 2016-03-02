package ua.alekseytsev.LibraryApp.web.command.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.UserService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.command.common.CategoryContentCommandHandler;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Profile handler
 */
public class ProfileCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -977495104183751127L;
    private static final Logger LOG = LogManager.getLogger(CategoryContentCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String changeInfo = request.getParameter("changeInfo");
        LOG.trace("changeInfo ===>" + changeInfo);
        if (changeInfo != null) {
            String firstName = request.getParameter("firstName");
            LOG.trace("firstName ===>" + firstName);
            String lastName = request.getParameter("lastName");
            LOG.trace("lastName ===>" + lastName);
            User user = (User) request.getSession().getAttribute("user");
            new UserService().update(user.setFirstName(firstName).
                    setLastName(lastName));
            request.setAttribute("user", user);
            LOG.trace("Set the session attribute \"user\" ===>" + user);
            LOG.info("Users has been obtained");
        }
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_PERSONAL_INFO);
        return Path.ACTION_FORWARD;
    }
}
