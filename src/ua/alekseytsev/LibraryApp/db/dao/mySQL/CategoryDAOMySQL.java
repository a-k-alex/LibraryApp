package ua.alekseytsev.LibraryApp.db.dao.mySQL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.CategoryDAO;
import ua.alekseytsev.LibraryApp.db.dao.util.extractors.BookExtractor;
import ua.alekseytsev.LibraryApp.db.dao.util.extractors.CategoryExtractor;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOMySQL implements CategoryDAO {
    private static final Logger LOG = LogManager.getLogger(CategoryDAOMySQL.class);
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Statement stm;

    public CategoryDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> findAll() throws DBException {
        LOG.debug("Trying to get categories from db");
        List<Category> categoryList;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SQLQueries.CATEGORY_FIND_ALL);
            categoryList = new ArrayList<>();
            while (rs.next()) {
                categoryList.add(new CategoryExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace("categoryList ===>" + categoryList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(stm, rs);
        }
        return categoryList;
    }

    @Override
    public void create(Category entity) throws DBException {
        LOG.debug("Trying to create the category");
        LOG.trace(entity);
        Category category = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.CATEGORY_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, category.getCategoryName());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    category.setId(rs.getInt(1));
                }
            }
            LOG.debug("Success: category have been  created");
            LOG.trace(category);
        } catch (java.sql.BatchUpdateException e) {
            //if duplicate category
            LOG.error("ERR_DUPLICATE_ENTRY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_DUPLICATE_ENTRY, e);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public Category read(Integer id) throws DBException {
        LOG.debug("Trying to read category with id" + id);
        Category category = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.CATEGORY_GET_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new CategoryExtractor().extract(rs);
            }
            LOG.debug("Success: category have been  read");
            LOG.trace(category);
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return category;
    }

    @Override
    public void update(Category entity) throws DBException {
        LOG.debug("Trying to update the category");
        LOG.trace(entity);
        Category category = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.CATEGORY_UPDATE);
            int k = 1;
            pstmt.setString(k++, category.getCategoryName());
            pstmt.setInt(k++, category.getId());
            pstmt.executeUpdate();
            LOG.debug("Success: category have been  updated");
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
    public List<Book> readCategoryBooks(Integer id) throws DBException {
        LOG.debug("Trying to read category content with id" + id);
        List<Book> bookList = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.CATEGORY_LIST_BOOK);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            bookList = new ArrayList<>();
            while (rs.next()) {
                bookList.add(new BookExtractor().extract(rs));
            }
            LOG.debug("Success: category content have been  read");
            LOG.trace("Book list of category with id=" + id + "\t" + bookList);
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return bookList;
    }

    @Override
    public void addBookToCategory(Integer idCategory, Integer idBook) throws DBException {
        LOG.debug("Trying to add a book into the category");
        LOG.trace("idCategory ===>" + idCategory);
        LOG.trace("bookId ===>" + idBook);
        try {
            pstmt = connection.prepareStatement(SQLQueries.CATEGORY_ADD_BOOK);
            int k = 1;
            pstmt.setInt(k++, idCategory);
            pstmt.setInt(k++, idBook);
            pstmt.execute();
            LOG.debug("Success: book have been  added  to the category");
        } catch (java.sql.BatchUpdateException e) {
            //if duplicate category
            LOG.error("ERR_DUPLICATE_ENTRY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_DUPLICATE_ENTRY, e);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public void delete(Category entity) throws DBException {
        LOG.debug("Trying to delete the category");
        LOG.info("not implemented");
    }
}
