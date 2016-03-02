package ua.alekseytsev.LibraryApp.web.command.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.UserService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.MailService;
import ua.alekseytsev.LibraryApp.web.util.PasswordHash;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.alekseytsev.LibraryApp.web.util.Validator.*;

/**
 * Registration handler
 */
public class RegistrationCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -782590122799864724L;
    private static final Logger LOG = LogManager.getLogger(RegistrationCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String firstName = request.getParameter("firstName");
        LOG.trace("firstName ===>" + firstName);
        String lastName = request.getParameter("lastName");
        LOG.trace("lastName ===>" + lastName);
        String email = request.getParameter("email");
        LOG.trace("email ===>" + email);
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        User user;
        try {
            validateFields(firstName, lastName, email, password, confirmPassword);
            validateEmail(email);
            validatePasswords(password, confirmPassword);
            user = new User();
            user.setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setPassword(PasswordHash.hash(password))
                    .setRole(Role.READER);
            new UserService().create(user);
            LOG.trace("Register user ===>" + user);
            //sending mail
            MailService.sendMail(email, "Welcome", MailService.MSG_REGISTRATION);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("path", Path.PAGE_WELCOME);
            LOG.debug("Command finished");
            return Path.ACTION_REDIRECT;

        } catch (LibraryException e) {
            LOG.error("Can not create user", e);
            request.setAttribute("errorMessage", e.getMessage());
            request.getSession().setAttribute("path", Path.PAGE_REGISTER);
        }
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}