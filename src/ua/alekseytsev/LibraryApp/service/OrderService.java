package ua.alekseytsev.LibraryApp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.BookDAO;
import ua.alekseytsev.LibraryApp.db.dao.DAOFactory;
import ua.alekseytsev.LibraryApp.db.dao.OrderDAO;
import ua.alekseytsev.LibraryApp.db.dao.UserDAO;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.bean.OrderBean;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.exceptions.DBException;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for manipulating with {@link Order} entity
 */
public class OrderService {
    private static final Logger LOG = LogManager.getLogger(OrderService.class);

    public void create(Order order) throws LibraryException {
        LOG.debug("create order");
        LOG.trace("order:" + order);
        Connection connection = null;
        OrderDAO orderDAO;
        BookDAO bookDAO;
        Book book;
        try {
            connection = DAOFactory.getInstance().createConnection();
            bookDAO = DAOFactory.getInstance().getBookDAO(connection);
            //Checking a books in stock
            book = bookDAO.read(order.getBookId());
            if (book.getInStock() == 0) {
                LOG.error("Cannot create order:amount=0");
                DAOFactory.rollback(connection);
                throw new LibraryException(LibraryException.ERR_BOOK_NOT_IN_STOCK);
            }
            //updating amount
            bookDAO.update(book.setInStock(book.getInStock() - 1));
            // check duplication order
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            if (orderDAO.checkDuplicateOrder(order.getUserId(), order.getBookId()) > 0) {
                LOG.error("Cannot create order:duplicate order");
                DAOFactory.rollback(connection);
                throw new LibraryException(LibraryException.ERR_BOOK_ALREADY_ORDERED);
            }
            orderDAO.create(order);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot create order", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public void update(Order order) throws LibraryException {
        LOG.debug("update order");
        LOG.trace("update:" + order);
        Connection connection = null;
        OrderDAO orderDAO;
        BookDAO bookDAO;
        Book book;
        try {
            connection = DAOFactory.getInstance().createConnection();
            //update order status
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            orderDAO.updateStatus(order);
            // if book returns to the library -> update amount
            if (order.getStatus().equals(OrderStatus.DONE)) {
                bookDAO = DAOFactory.getInstance().getBookDAO(connection);
                book = bookDAO.read(order.getBookId());
                //updating amount
                bookDAO.update(book.setInStock(book.getInStock() + 1));
            }
            connection.commit();
        } catch (DBException | SQLException e) {
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }


    public List<Order> showAll() throws LibraryException {
        LOG.debug("Show list orders");
        List<Order> orderList;
        Connection connection = null;
        OrderDAO orderDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            orderList = orderDAO.findAll();
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return orderList;
    }

    public List<Order> showAll(OrderStatus status) throws LibraryException {
        LOG.debug("Show list Orders with status:" + status);
        List<Order> orderList;
        Connection connection = null;
        OrderDAO orderDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            orderList = orderDAO.findAll(status);
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return orderList;
    }

    public List<OrderBean> showAllBeans(OrderStatus status) throws LibraryException {
        LOG.debug("Show list Orders with status:" + status);
        List<Order> orderList;
        List<OrderBean> orderBeans = null;
        Connection connection = null;
        OrderDAO orderDAO;
        UserDAO userDAO;
        BookDAO bookDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            orderList = orderDAO.findAll(status);
            orderBeans = new ArrayList<>();
            for (Order order : orderList) {
                //Get user with with id what contains in order
                userDAO = DAOFactory.getInstance().getUserDAO(connection);
                //Get user with with id what contains in order
                bookDAO = DAOFactory.getInstance().getBookDAO(connection);
                //create orderBean and put them to list
                orderBeans.add(new OrderBean().setOrder(order)
                        .setUser(userDAO.read(order.getUserId()))
                        .setBook(bookDAO.read(order.getBookId())));
            }

            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot get orders", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return orderBeans;
    }

    public List<Order> findAllOverDUE(OrderStatus status) throws LibraryException {
        LOG.debug("Show list overdue orders");
        List<Order> orderList = null;
        Connection connection = null;
        OrderDAO orderDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            orderList = orderDAO.findAllOverdue(status);
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return orderList;
    }

    public List<Order> findAllUnregisteredOverdueOrders() throws LibraryException {
        LOG.debug("Show list overdue orders");
        List<Order> orderList = null;
        Connection connection = null;
        OrderDAO orderDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            orderDAO = DAOFactory.getInstance().getOrderDAO(connection);
            orderList = orderDAO.findAllUnregisteredOverdueOrders();
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return orderList;
    }

}
