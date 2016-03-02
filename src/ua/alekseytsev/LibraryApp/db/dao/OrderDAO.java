package ua.alekseytsev.LibraryApp.db.dao;

import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.util.List;

/**
 * For data manipulation in the "orders" table
 */
public interface OrderDAO extends DAO<Order> {

    List<Order> findAll(OrderStatus status) throws DBException;

    List<Order> findAllOverdue(OrderStatus status) throws DBException;

    void updateStatus(Order entity) throws DBException;

    List<Order> findAllByUserId(Integer userId) throws DBException;

    List<Order> findAllUnregisteredOverdueOrders() throws DBException;

    Integer checkDuplicateOrder(Integer userId, Integer bookId) throws DBException;

}
