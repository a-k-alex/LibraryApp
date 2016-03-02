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
import java.util.Map;

/**
 * Popular books command handler
 */
public class PopularBooksCommandHandler implements HandlerCommand {

    private static final Logger LOG = LogManager.getLogger(PopularBooksCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, LibraryException {
        LOG.debug("Command starts");
        Map<Book, Integer> books = new BookService().searchPopular(3);
        request.getSession().setAttribute("booksMap", books);
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_TOP_BOOKS);
        return Path.ACTION_FORWARD;
    }
}
