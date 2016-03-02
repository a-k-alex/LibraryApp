package ua.alekseytsev.LibraryApp.web.command.reader;

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

/**
 * My fines handler
 */
public class MyFinesCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -7610810482334253640L;
    private static final Logger LOG = LogManager.getLogger(MyFinesCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("User ===>" + user);
        request.setAttribute("fines", new UserService().getFines(user));
        request.getSession().setAttribute("path", Path.PAGE_MY_FINES);
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}
