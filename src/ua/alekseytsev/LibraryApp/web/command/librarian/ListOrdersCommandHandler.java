package ua.alekseytsev.LibraryApp.web.command.librarian;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.bean.OrderBean;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.OrderService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * List orders handler
 */
public class ListOrdersCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -931379502374396042L;
    private static final Logger LOG = LogManager.getLogger(ListOrdersCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String status = request.getParameter("status");
        LOG.trace("order status ===>" + status);
        List<OrderBean> orderBeans = new OrderService().showAllBeans(OrderStatus.valueOf(status.toUpperCase()));
        request.getSession().setAttribute("orderBeans", orderBeans);
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_LIST_ORDERS);
        return Path.ACTION_FORWARD;
    }
}
