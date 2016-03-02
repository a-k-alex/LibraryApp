package ua.alekseytsev.LibraryApp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.BookDAO;
import ua.alekseytsev.LibraryApp.db.dao.CategoryDAO;
import ua.alekseytsev.LibraryApp.db.dao.DAOFactory;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.exceptions.DBException;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Provides methods for manipulating with {@link Book} entity
 */
public class BookService {
    private static final Logger LOG = LogManager.getLogger(CategoryService.class);
    private static final Comparator<Book> NAME_COMPARATOR = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getBookName().compareTo(o2.getBookName());
        }
    };
    private static final Comparator<Book> YEAR_COMPARATOR = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPublicationYear().compareTo(o2.getPublicationYear());
        }
    };
    private static final Comparator<Book> AUTHOR_COMPARATOR = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };
    private static final Comparator<Book> PUBLICATION_COMPARATOR = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPublication().compareTo(o2.getPublication());
        }
    };

    public void create(Book book, Integer categoryId) throws LibraryException {
        LOG.debug("Create the book");
        LOG.trace("book ===> " + book);
        Connection connection = null;
        BookDAO bookDAO;
        CategoryDAO categoryDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            bookDAO = DAOFactory.getInstance().getBookDAO(connection);
            bookDAO.create(book);
            categoryDAO = DAOFactory.getInstance().getCategoryDAO(connection);
            categoryDAO.addBookToCategory(categoryId, book.getId());
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot create the book", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public Book read(Integer id) throws LibraryException {
        LOG.debug("Read the book with id ===>" + id);
        Connection connection = null;
        BookDAO bookDAO;
        Book book;
        try {
            connection = DAOFactory.getInstance().createConnection();
            bookDAO = DAOFactory.getInstance().getBookDAO(connection);
            book = bookDAO.read(id);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot read the book", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return book;
    }

    public void update(Book book) throws LibraryException {
        LOG.debug("Update the book");
        LOG.trace("book ===>" + book);
        Connection connection = null;
        BookDAO bookDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            bookDAO = DAOFactory.getInstance().getBookDAO(connection);
            bookDAO.update(book);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot update the book", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public List<Book> search(String bookNameOrAuthor) throws LibraryException {
        LOG.debug("Search books");
        LOG.trace("bookNameOrAuthor ===>" + bookNameOrAuthor);
        Connection connection = null;
        BookDAO bookDAO;
        List<Book> books;
        try {
            connection = DAOFactory.getInstance().createConnection();
            bookDAO = DAOFactory.getInstance().getBookDAO(connection);
            books = bookDAO.search(bookNameOrAuthor);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot find books", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return books;
    }

    public Comparator<Book> getNameComparator() {
        return NAME_COMPARATOR;
    }

    public Comparator<Book> getYearComparator() {
        return YEAR_COMPARATOR;
    }

    public Comparator<Book> getAuthorComparator() {
        return AUTHOR_COMPARATOR;
    }

    public Comparator<Book> getPublicationComparator() {
        return PUBLICATION_COMPARATOR;
    }


    public Map<Book, Integer> searchPopular(int limit) throws LibraryException {
        LOG.debug("Search popular books");
        BookDAO bookDAO;
        Connection connection = null;
        Map<Book, Integer> bookIntegerMap = null;
        try {
            connection = DAOFactory.getInstance().createConnection();
            bookDAO = DAOFactory.getInstance().getBookDAO(connection);
            bookIntegerMap = bookDAO.searchPopularBooks(limit);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot update the book", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return bookIntegerMap;
    }
}
