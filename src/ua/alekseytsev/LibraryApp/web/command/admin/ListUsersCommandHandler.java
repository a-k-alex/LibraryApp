package ua.alekseytsev.LibraryApp.web.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.UserService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * List users handler
 */
public class ListUsersCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = 4404447002871287184L;
    private static final Logger LOG = LogManager.getLogger(ListUsersCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        List<User> users = new UserService().showAll();
        request.getSession().setAttribute("users", users);
        LOG.trace("Set the session attribute \"users\" ===>" + users);
        LOG.info("Users has been obtained");
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_LIST_USERS);
        return Path.ACTION_FORWARD;
    }
}
