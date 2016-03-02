package ua.alekseytsev.LibraryApp.web.command.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.OrderService;
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
 * Order handler
 */
public class OrderBookCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -4754194758190108906L;
    private static final Logger LOG = LogManager.getLogger(OrderBookCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        Integer bookId = Integer.valueOf(request.getParameter("id"));
        LOG.trace("bookId ===>:" + bookId);
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("User ===>" + user);
        Order order = new Order();
        order.setUserId(user.getId()).setBookId(bookId).setCreateAt(new Timestamp(new Date().getTime()));
        new OrderService().create(order);
        List<Book> books = (List<Book>) request.getSession().getAttribute("bookList");
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                b.setInStock(b.getInStock() - 1);
            }
        }
        request.getSession().setAttribute("bookList", books);
        LOG.debug("Command finished");
        return Path.ACTION_REDIRECT;
    }
}
