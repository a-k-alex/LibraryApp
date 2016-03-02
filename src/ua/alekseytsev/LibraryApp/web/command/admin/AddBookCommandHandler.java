package ua.alekseytsev.LibraryApp.web.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.BookService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;
import ua.alekseytsev.LibraryApp.web.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 * Add Book handler
 */
public class AddBookCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -6576452266662462970L;
    private static final Logger LOG = LogManager.getLogger(AddBookCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String id = request.getParameter("id");
        LOG.trace("id ===>" + id);
        String bookName = request.getParameter("bookName");
        LOG.trace("bookName ===>" + bookName);
        String author = request.getParameter("author");
        LOG.trace("author ===>" + author);
        String publication = request.getParameter("publication");
        LOG.trace("publication ===>" + publication);
        Integer publicationYear = Integer.valueOf(request.getParameter("publicationYear"));
        LOG.trace("publicationYear ===>" + publicationYear);
        Integer amount = Integer.valueOf(request.getParameter("amount"));
        LOG.trace("amount ===>" + amount);
        Integer inStock = Integer.valueOf(request.getParameter("inStock"));
        LOG.trace("inStock ===>" + inStock);
        LOG.trace("inStock ===>" + inStock);
        List<Book> books = (List<Book>) request.getSession().getAttribute("bookList");
        Category category = ((Category) request.getSession().getAttribute("category"));

        try {
            Validator.validateAmountBooks(amount, inStock);
            Book book = new Book();
            book.setBookName(bookName)
                    .setAuthor(author)
                    .setPublication(publication)
                    .setPublicationYear(publicationYear)
                    .setAmount(amount)
                    .setInStock(inStock);
            //if update book
            if (!"".equals(id)) {
                book.setId(Integer.valueOf(id));
                new BookService().update(book);
                LOG.trace("Book was updated ===>" + book);
                for (ListIterator<Book> listIterator = books.listIterator(); listIterator.hasNext(); ) {
                    if (listIterator.next().getId().equals(book.getId())) {
                        listIterator.set(book);
                        break;
                    }
                }
            } else { //if create a new book
                Integer categoryId = ((Category) request.getSession().getAttribute("category")).getId();
                new BookService().create(book, categoryId);
                LOG.trace("Book was created ===>" + book);
                books.add(book);
            }
            request.getSession().setAttribute("bookList", books);
            //if action was from category
            if (category != null) {
                request.getSession().setAttribute("path", Path.PAGE_CATEGORY);
            } else {
                request.getSession().setAttribute("path", Path.PAGE_BOOKS);
            }

        } catch (LibraryException e) {
            LOG.error("Can not create book", e);
            request.setAttribute("errorMessage", e.getMessage());
            request.getSession().setAttribute("path", Path.PAGE_BOOK);
            LOG.debug("Command finished");
            return Path.ACTION_FORWARD;
        }
        request.getSession().setAttribute("book", null);
        LOG.debug("Command finished");
        return Path.ACTION_REDIRECT;
    }
}
