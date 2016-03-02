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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sort handler
 */
public class SortCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = 593644447324500003L;
    private static final Logger LOG = LogManager.getLogger(SortCommandHandler.class);
    private static final String CRRITERIA_BOOK = "bookName";
    private static final String CRRITERIA_AUTHOR = "author";
    private static final String CRRITERIA_YEAR = "year";
    private static final String CRRITERIA_PUBLICATION = "publication";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String sortCriteria = request.getParameter("criteria");
        LOG.trace("sort criteria ===>" + sortCriteria);
        List<Book> books = (List<Book>) request.getSession().getAttribute("bookList");
        BookService bookService = new BookService();
        Comparator<Book> comparator;
        switch (sortCriteria) {
            case CRRITERIA_BOOK:
                comparator = bookService.getNameComparator();
                break;
            case CRRITERIA_AUTHOR:
                comparator = bookService.getAuthorComparator();
                break;
            case CRRITERIA_YEAR:
                comparator = bookService.getYearComparator();
                break;
            case CRRITERIA_PUBLICATION:
                comparator = bookService.getPublicationComparator();
                break;
            default:
                throw new LibraryException(LibraryException.ERR_NO_SUCH_COMPARATOR);
        }
        Collections.sort(books, comparator);
        request.getSession().setAttribute("bookList", books);
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}


