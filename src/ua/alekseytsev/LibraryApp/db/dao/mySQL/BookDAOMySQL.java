package ua.alekseytsev.LibraryApp.db.dao.mySQL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.BookDAO;
import ua.alekseytsev.LibraryApp.db.dao.util.extractors.BookExtractor;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class BookDAOMySQL implements BookDAO {
    private static final Logger LOG = LogManager.getLogger(BookDAOMySQL.class);
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Statement stm;

    public BookDAOMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Book> findAll() throws DBException {
        LOG.debug("Trying to get books from db");
        List<Book> bookList;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SQLQueries.BOOK_FIND_ALL);
            bookList = new ArrayList<>();
            while (rs.next()) {
                bookList.add(new BookExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace("bookList ===>" + bookList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(stm, rs);
        }
        return bookList;
    }

    @Override
    public void create(Book entity) throws DBException {
        LOG.debug("Trying to create the book");
        LOG.trace(entity);
        Book book = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.BOOK_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, book.getBookName());
            pstmt.setString(k++, book.getAuthor());
            pstmt.setString(k++, book.getPublication());
            pstmt.setInt(k++, book.getPublicationYear());
            pstmt.setInt(k++, book.getAmount());
            pstmt.setInt(k++, book.getInStock());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }
            }
            LOG.debug("Success: book have been  created");
            LOG.trace(book);
        } catch (java.sql.BatchUpdateException e) {
            //if update on duplicate category
            LOG.error("ERR_DUPLICATE_ENTRY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_DUPLICATE_ENTRY, e);
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public Book read(Integer id) throws DBException {
        LOG.debug("Trying to read book with id" + id);
        Book book = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.BOOK_GET_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                book = new BookExtractor().extract(rs);
            }
            LOG.debug("Success: book have been  read");
            LOG.trace(book);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return book;
    }

    @Override
    public void update(Book entity) throws DBException {
        LOG.debug("Trying to update the book");
        LOG.trace(entity);
        Book book = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.BOOK_UPDATE);
            int k = 1;
            pstmt.setString(k++, book.getBookName());
            pstmt.setString(k++, book.getAuthor());
            pstmt.setString(k++, book.getPublication());
            pstmt.setInt(k++, book.getPublicationYear());
            pstmt.setInt(k++, book.getAmount());
            pstmt.setInt(k++, book.getInStock());
            pstmt.setTimestamp(k++, book.getDeleteAt());
            pstmt.setInt(k++, book.getId());
            pstmt.executeUpdate();
            LOG.debug("Success: book have been  updated");
        } catch (java.sql.BatchUpdateException e) {
            //if update on duplicate category
            LOG.error("ERR_DUPLICATE_ENTRY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_DUPLICATE_ENTRY, e);
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY" + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public List<Book> search(String bookNameOrAuthor) throws DBException {
        LOG.debug("Trying to get books from db by parameter ===>" + bookNameOrAuthor);
        List<Book> bookList = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.BOOK_SEARCH);
            int k = 1;
            //wildcards
            String param = "%" + bookNameOrAuthor + "%";
            pstmt.setString(k++, param);
            pstmt.setString(k++, param);
            rs = pstmt.executeQuery();
            bookList = new ArrayList<>();
            while (rs.next()) {
                bookList.add(new BookExtractor().extract(rs));
            }
            LOG.debug("Success: book have been  read");
            LOG.trace("books ===>" + bookList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return bookList;
    }

    @Override
    public Map<Book, Integer> searchPopularBooks(int limit) throws DBException {
        LOG.debug("Trying to get popular books from db ===>");
        Map<Book, Integer> bookIntegerMap;
        try {
            pstmt = connection.prepareStatement(SQLQueries.BOOK_SEARCH_TOP);
            pstmt.setInt(1, limit);
            rs = pstmt.executeQuery();
            bookIntegerMap = new LinkedHashMap<>();
            while (rs.next()) {
                bookIntegerMap.put(new BookExtractor().extract(rs), rs.getInt("quantity"));
            }
            LOG.debug("Success: books have been  read");
            LOG.trace("books ===>" + bookIntegerMap);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return bookIntegerMap;
    }

    @Override
    public void delete(Book entity) throws DBException {
        Book book = entity;
        book.setDeleteAt(new Timestamp(new Date().getTime()));
        update(book);
    }
}
