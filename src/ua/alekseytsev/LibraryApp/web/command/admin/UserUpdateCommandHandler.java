package ua.alekseytsev.LibraryApp.web.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.UserService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Update user role or condition
 */
public class UserUpdateCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -5273406949369483017L;
    private static final Logger LOG = LogManager.getLogger(UserUpdateCommandHandler.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        Integer id = Integer.valueOf(request.getParameter("id"));
        LOG.trace("id ===>" + id);
        String role = request.getParameter("role");
        LOG.trace("role ===>" + role);
        String banned = request.getParameter("isBanned");
        LOG.trace("isBanned ===>" + banned);
        Timestamp isBanned = null;
        if ("Ban".equals(banned)) {
            isBanned = new Timestamp(new Date().getTime());
        }
        new UserService().update(id, Role.valueOf(role), isBanned);
        List<User> users = (List<User>) request.getSession().getAttribute("users");
        for (User u : users) {
            if (u.getId().equals(id)) {
                u.setRole(Role.valueOf(role.toUpperCase()));
                u.setBannedAt(isBanned);
                break;
            }
        }
        request.getSession().setAttribute("users", users);
        request.getSession().setAttribute("path", Path.PAGE_LIST_USERS);
        LOG.debug("Command finished");
        return Path.ACTION_REDIRECT;
    }
}
