package ua.alekseytsev.LibraryApp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.DAOFactory;
import ua.alekseytsev.LibraryApp.db.dao.FineDAO;
import ua.alekseytsev.LibraryApp.db.dao.UserDAO;
import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.exceptions.DBException;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.web.util.MailService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Provides methods for manipulating with {@link ua.alekseytsev.LibraryApp.db.model.entity.Fine} entity
 */
public class FineService {
    private static final Logger LOG = LogManager.getLogger(FineService.class);

    public void create(Fine fine) throws LibraryException {
        LOG.debug("Create the fine");
        LOG.trace("fine ===>" + fine);
        Connection connection = null;
        FineDAO fineDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            fineDAO = DAOFactory.getInstance().getFineDAO(connection);
            fineDAO.create(fine);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot create  fine", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
            sendNotification(fine.getUserId());
        }
    }

    public void update(Fine fine) throws LibraryException {
        LOG.debug("Update the fine");
        LOG.trace("update ===>" + fine);
        FineDAO fineDAO;
        Connection connection = null;
        try {
            connection = DAOFactory.getInstance().createConnection();
            fineDAO = DAOFactory.getInstance().getFineDAO(connection);
            fineDAO.update(fine);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot update fine", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }

    public void findNew() throws LibraryException {
        List<Order> overDueOrderList = new OrderService().findAllUnregisteredOverdueOrders();
        for (Order order : overDueOrderList) {
            new FineService().create(
                    new Fine().setOrderId(order.getId())
                            .setUserId(order.getUserId()));
        }
    }

    public List<Fine> showAll() throws LibraryException {
        LOG.debug("Show fines");
        List<Fine> fineList;
        Connection connection = null;
        FineDAO fineDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            fineDAO = DAOFactory.getInstance().getFineDAO(connection);
            fineList = fineDAO.findAll();
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return fineList;
    }

    public List<Fine> showAll(FineStatus status) throws LibraryException {
        LOG.debug("Show fines with status ===> " + status);
        List<Fine> fineList;
        Connection connection = null;
        FineDAO fineDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            fineDAO = DAOFactory.getInstance().getFineDAO(connection);
            fineList = fineDAO.findAll(status);
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return fineList;
    }

    public Timestamp getCreatedDate(Integer fineId) throws LibraryException {
        LOG.debug("Read the date of creation fine");
        Timestamp timestamp = null;
        Connection connection = null;
        FineDAO fineDAO;
        try {
            connection = DAOFactory.getInstance().createConnection();
            fineDAO = DAOFactory.getInstance().getFineDAO(connection);
            timestamp = fineDAO.getCreatedDate(fineId);
        } catch (Exception e) {
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
        return timestamp;
    }

    private void sendNotification(Integer idUser) throws LibraryException {
        LOG.debug("Send notification to the user with id " + idUser);
        UserDAO userDAO;
        Connection connection = null;
        try {
            connection = DAOFactory.getInstance().createConnection();
            userDAO = DAOFactory.getInstance().getUserDAO(connection);
            String email = userDAO.getEmailbyId(idUser);
            MailService.sendMail(email, "Attention", MailService.MSG_FINE);
            connection.commit();
        } catch (DBException | SQLException e) {
            LOG.error("Cannot update fine", e);
            DAOFactory.rollback(connection);
            throw new LibraryException(LibraryException.ERR_OBTAIN_CONNECTION_TO_DB, e);
        } finally {
            DAOFactory.close(connection);
        }
    }
}
