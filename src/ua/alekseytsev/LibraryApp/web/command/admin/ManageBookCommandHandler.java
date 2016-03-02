package ua.alekseytsev.LibraryApp.web.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.BookService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Manage book handler
 */
public class ManageBookCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -8584220671286587143L;
    private static final Logger LOG = LogManager.getLogger(ManageBookCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        if (request.getParameter("bookId") != null) {
            Integer id = Integer.valueOf(request.getParameter("bookId"));
            Book book = new BookService().read(id);
            request.getSession().setAttribute("book", book);
        }
        request.getSession().setAttribute("path", Path.PAGE_BOOK);
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}
