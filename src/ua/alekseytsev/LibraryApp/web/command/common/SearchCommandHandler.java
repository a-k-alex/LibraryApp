package ua.alekseytsev.LibraryApp.web.command.common;

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
import java.util.List;

/**
 * Created by alex on 08.02.2016.
 */
public class SearchCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -8171466764021578515L;
    private static final Logger LOG = LogManager.getLogger(SearchCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String bookNameOrAuthor = request.getParameter("bookNameOrAuthor");
        LOG.trace("searching value ===>" + bookNameOrAuthor);
        request.getSession().setAttribute("bookNameOrAuthor", bookNameOrAuthor);
        request.getSession().setAttribute("category", null);
        List<Book> books = new BookService().search(bookNameOrAuthor);
        request.getSession().setAttribute("bookList", books);
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_BOOKS);
        return Path.ACTION_FORWARD;
    }
}
