package ua.alekseytsev.LibraryApp.db.dao.mySQL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.OrderDAO;
import ua.alekseytsev.LibraryApp.db.dao.util.extractors.OrderExtractor;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAOMySQL implements OrderDAO {
    private static final Logger LOG = LogManager.getLogger(OrderDAOMySQL.class);
    private Connection connection;
    private PreparedStatement pstmt;
    private Statement stm;
    private ResultSet rs;

    public OrderDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAll() throws DBException {
        LOG.debug("Trying to get orders from db");
        List<Order> orderList;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SQLQueries.ORDER_FIND_ALL);
            orderList = new ArrayList<>();
            while (rs.next()) {
                orderList.add(new OrderExtractor().extract(rs));
            }
            LOG.debug("Success: data is obtained");
            LOG.trace(orderList);
        } catch (SQLException e) {
            LOG.error(getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return orderList;
    }

    @Override
    public void create(Order entity) throws DBException {
        LOG.debug("Trying to create the order");
        LOG.trace(entity);
        Order order = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setInt(k++, order.getUserId());
            pstmt.setInt(k++, order.getBookId());
            pstmt.setTimestamp(k++, order.getCreateAt());
            pstmt.setTimestamp(k++, order.getReturnDate());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
            }
            LOG.debug("Success: order have been  created");
            LOG.trace(order);
        } catch (SQLException e) {
            LOG.error(getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public Order read(Integer id) throws DBException {
        LOG.debug("Trying to read order with id" + id);
        Order order = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_GET_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new OrderExtractor().extract(rs);
            }
            LOG.debug("Success: order have been  read");
            LOG.trace(order);
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return order;
    }

    @Override
    public void update(Order entity) throws DBException {
        LOG.debug("Trying to update the order");
        LOG.trace(entity);
        Order order = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_UPDATE);
            int k = 1;
            pstmt.setInt(k++, order.getUserId());
            pstmt.setInt(k++, order.getBookId());
            pstmt.setTimestamp(k++, order.getCreateAt());
            pstmt.setTimestamp(k++, order.getReturnDate());
            pstmt.setString(k++, order.getStatus().name());
            pstmt.setInt(k++, order.getId());
            pstmt.executeUpdate();
            LOG.debug("Success: order have been updated");
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }


    public void updateStatus(Order entity) throws DBException {
        LOG.debug("Trying to change order status");
        LOG.debug(entity);
        Order order = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_UPDATE_STATUS);
            int k = 1;
            pstmt.setTimestamp(k++, order.getReturnDate());
            pstmt.setString(k++, order.getStatus().name());
            pstmt.setInt(k++, order.getId());
            pstmt.executeUpdate();
            LOG.debug("Success: status have been changed");
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public List<Order> findAllByUserId(Integer userId) throws DBException {
        LOG.debug("Trying to get  rows by user id ===>" + userId);
        List<Order> orderList;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_FIND_ALL_BY_USER_ID);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            orderList = new ArrayList<>();
            while (rs.next()) {
                orderList.add(new OrderExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace(orderList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return orderList;
    }

    @Override
    public List<Order> findAllUnregisteredOverdueOrders() throws DBException {
        LOG.debug("Trying to get unregistered overdue orders");
        List<Order> orderList;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SQLQueries.ORDER_FIND_UNRGSTR_OVERDUE);
            orderList = new ArrayList<>();
            while (rs.next()) {
                orderList.add(new OrderExtractor().extract(rs));
            }
            LOG.debug("Success: have been obtained");
            LOG.trace(orderList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return orderList;
    }

    @Override
    public List<Order> findAll(OrderStatus status) throws DBException {
        LOG.debug("Trying to get  rows by order status ===>" + status);
        List<Order> orderList;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_FIND_ALL_BY_STATUS);
            pstmt.setString(1, status.name());
            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
            orderList = new ArrayList<>();
            while (rs.next()) {
                orderList.add(new OrderExtractor().extract(rs));
            }
            LOG.debug("Success: have been obtained");
            LOG.trace(orderList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return orderList;
    }

    @Override
    public List<Order> findAllOverdue(OrderStatus status) throws DBException {
        LOG.debug("Trying to get all overdue orders by status" + status);
        List<Order> orderList;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_FIND_ALL_OVERDUE);
            pstmt.setString(1, status.name());
            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
            orderList = new ArrayList<>();
            while (rs.next()) {
                orderList.add(new OrderExtractor().extract(rs));
            }
            LOG.debug("Success: have been obtained");
            LOG.trace(orderList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return orderList;
    }

    @Override
    public Integer checkDuplicateOrder(Integer userId, Integer bookId) throws DBException {
        LOG.debug("Trying to get  rows");
        LOG.debug("userId" + userId);
        LOG.debug("bookId" + bookId);
        Integer amount = 0;
        try {
            pstmt = connection.prepareStatement(SQLQueries.ORDER_CHECK_DUPLICATE);
            int k = 1;
            pstmt.setInt(k++, userId);
            pstmt.setInt(k++, bookId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                amount = rs.getInt("total");
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace("amount ===>" + amount);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return amount;
    }

    @Override
    public void delete(Order entity) {
        LOG.debug("Trying to delete the category");
        LOG.info("not implemented");
    }
}

