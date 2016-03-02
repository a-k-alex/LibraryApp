package ua.alekseytsev.LibraryApp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.CategoryDAO;
import ua.alekseytsev.LibraryApp.db.dao.DAOFactory;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;
import ua.alekseytsev.LibraryApp.exceptions.DBException;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides methods for manipulating with {@link Category} entity
 */
public class CategoryService {
    private static final Logger LOG = LogManager.getLogger(CategoryService.class);

    public void create(Category category) throws LibraryException {
        LOG.debug("Create the category");
        LOG.trace("category ===> " + category);
        Connection connection = null;
        CategoryDAO categoryDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            categoryDAO = DAOFactory.getInstance().getCategoryDAO(connection);
            categoryDAO.create(category);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot create the category", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public List<Category> showAll() throws LibraryException {
        LOG.debug("Show categories");
        List<Category> categoryList;
        Connection connection = null;
        CategoryDAO categoryDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            categoryDAO = DAOFactory.getInstance().getCategoryDAO(connection);
            categoryList = categoryDAO.findAll();
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot obtain list categories", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return categoryList;
    }

    public Category showContent(Integer id) throws LibraryException {
        LOG.debug("Show books in the category with id ===>" + id);
        Category category;
        Connection connection = null;
        CategoryDAO categoryDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            categoryDAO = DAOFactory.getInstance().getCategoryDAO(connection);
            category = categoryDAO.read(id);
            category.setBookList(categoryDAO.readCategoryBooks(id));
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error(e.getMessage());
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return category;
    }
}
