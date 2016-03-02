package ua.alekseytsev.LibraryApp.web.command.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.bean.UserBean;
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
 * My books handler
 */
public class MyBooksCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -9106893444691790692L;
    private static final Logger LOG = LogManager.getLogger(MyBooksCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("User ===>" + user);
        UserBean userBean = new UserService().getMyOrders(user);
        request.setAttribute("userBean", userBean);
        request.getSession().setAttribute("path", Path.PAGE_MY_ORDERS);
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}
