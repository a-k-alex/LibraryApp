package ua.alekseytsev.LibraryApp.web.command.librarian;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.bean.OrderBean;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.OrderService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ListIterator;

/**
 * Change order status handler
 */
public class ChangeOrderStatusCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -8232731422164232438L;
    private static final Logger LOG = LogManager.getLogger(ChangeOrderStatusCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        OrderStatus status = OrderStatus.valueOf(request.getParameter("status").toUpperCase());
        LOG.trace("status ===>" + status);
        OrderStatus newStatus = OrderStatus.valueOf(request.getParameter("newStatus").toUpperCase());
        LOG.trace("newStatus ===>" + newStatus);
        Integer id = Integer.valueOf(request.getParameter("id"));
        LOG.trace("id:" + id);
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        LOG.trace("bookId ===>" + bookId);
        String dateReturn = request.getParameter("returnDate");
        LOG.trace("dateReturn ===>" + dateReturn);
        if (status.equals(newStatus)) {
            throw new LibraryException("Choose the new status");
        }
        Timestamp returnDate = null;
        if (OrderStatus.BOOKED.equals(newStatus)) {
            if ("".equals(dateReturn)) {
                throw new LibraryException("Choose the return date");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                returnDate = new Timestamp(sdf.parse(dateReturn).getTime());
            } catch (ParseException e) {
                throw new LibraryException("Cannot convert data", e);
            }
        }
        Order order = new Order().setStatus(newStatus)
                .setReturnDate(returnDate)
                .setBookId(bookId);
        order.setId(id);
        new OrderService().update(order);
        List<OrderBean> orderBeans = (List<OrderBean>) request.getSession().getAttribute("orderBeans");
        for (ListIterator<OrderBean> iterator = orderBeans.listIterator(); iterator.hasNext(); ) {
            if (iterator.next().getOrder().getId().equals(id)) {
                iterator.remove();
            }
        }
        request.getSession().setAttribute("orderBeans", orderBeans);
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_LIST_ORDERS);
        return Path.ACTION_REDIRECT;
    }
}

