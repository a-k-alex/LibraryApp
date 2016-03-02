package ua.alekseytsev.LibraryApp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.*;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.bean.UserBean;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.DBException;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides methods for manipulating with {@link User} entity
 */
public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);


    public void create(User user) throws LibraryException {
        LOG.debug("Create the user");
        LOG.trace("user ===> " + user);
        Connection connection = null;
        UserDAO userDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            userDAO = DAOFactory.getInstance().getUserDAO(connection);
            userDAO.create(user);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot create user", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB + e.getMessage(), e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public void update(User user) throws LibraryException {
        LOG.debug("Update the user");
        LOG.trace("user ===> " + user);
        Connection connection = null;
        UserDAO userDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            userDAO = DAOFactory.getInstance().getUserDAO(connection);
            userDAO.update(user);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot update user", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }


    public void update(Integer id, Role role, Timestamp isBanned) throws LibraryException {
        LOG.debug("update user role and condition");
        LOG.trace("id:" + id);
        LOG.trace("role:" + role);
        LOG.trace("isBanned:" + isBanned);

        Connection connection = null;
        UserDAO userDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            userDAO = DAOFactory.getInstance().getUserDAO(connection);
            userDAO.updateRoleAndCondition(id, role, isBanned);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot update user role and condition", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public User authentication(String email, String hashPassword) throws LibraryException {
        LOG.debug("Authenticate the user");
        LOG.trace("email ===> " + email);
        Connection connection = null;
        User user;
        UserDAO userDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            userDAO = DAOFactory.getInstance().getUserDAO(connection);
            user = userDAO.getUserByEmail(email);

            if (user == null) {
                throw new LibraryException(LibraryException.USER_NOT_EXIST);
            }
            if (!(user.getPassword().equals(hashPassword)
                    && (user.getEmail().equals(email)))) {
                throw new LibraryException(LibraryException.ERR_AUTHETICATION);
            }
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot authenticate user", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return user;
    }

    public List<User> showAll() throws LibraryException {
        LOG.debug("Show list users");
        List<User> userList;
        Connection connection = null;
        UserDAO userDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            userDAO = DAOFactory.getInstance().getUserDAO(connection);
            userList = userDAO.findAll();
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot obtain list users", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return userList;
    }

    public UserBean getMyOrders(User user) throws LibraryException {
        LOG.debug("Show user orders");
        LOG.trace("user ===>" + user);
        UserBean userBean = null;
        Connection connection = null;
        OrderDAO orderDAO;
        BookDAO bookDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            List<Order> orders = orderDAO.findAllByUserId(user.getId());
            Map<Order, Book> orderBookMap = new HashMap<>();
            for (Order order : orders) {
                bookDAO = DAOFactory.getInstance().getBookDAO(connection);
                orderBookMap.put(order, bookDAO.read(order.getBookId()));
            }
            userBean = new UserBean();
            userBean.setOrderMap(orderBookMap);
            LOG.trace(userBean.getOrderMap());
            //todo get fines

            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot obtain user orders", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return userBean;
    }

    public List<Fine> getFines(User user) throws LibraryException {
        LOG.debug("Show user fines");
        Connection connection = null;
        FineDAO fineDAO;
        List<Fine> fines = null;
        try {
            connection = DAOFactory.getInstance().createConnection();
            fineDAO = DAOFactory.getInstance().getFineDAO(connection);
            fines = fineDAO.findAllByUserId(user.getId());
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot obtain list users", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return fines;
    }
}