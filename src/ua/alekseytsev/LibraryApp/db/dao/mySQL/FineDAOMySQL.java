package ua.alekseytsev.LibraryApp.db.dao.mySQL;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.FineDAO;
import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.dao.util.extractors.FineExtractor;
import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FineDAOMySQL implements FineDAO {
    private static final Logger LOG = LogManager.getLogger(FineDAOMySQL.class);
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Statement stm;

    public FineDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Fine> findAll(FineStatus status) throws DBException {
        LOG.debug("Trying to get  rows by status ===>" + status);
        List<Fine> fineList;
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_FIND_ALL_BY_STATUS);
            pstmt.setString(1, status.name());
            rs = pstmt.executeQuery();
            fineList = new ArrayList<>();
            while (rs.next()) {
                fineList.add(new FineExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace(fineList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return fineList;
    }

    @Override
    public List<Fine> findAllByUserId(Integer userId) throws DBException {
        LOG.debug("Tryinging to get  rows by user id ===>" + userId);
        List<Fine> fineList;
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_FIND_ALL_BY_USER_ID);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            fineList = new ArrayList<>();
            while (rs.next()) {
                fineList.add(new FineExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace(fineList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return fineList;
    }

    @Override
    public Timestamp getCreatedDate(Integer fineId) throws DBException {
        LOG.debug("Trying to get date of creation by fineId ===>" + fineId);
        Timestamp createAt = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_CREATE_AT);
            pstmt.setInt(1, fineId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                createAt = rs.getTimestamp(Fields.FINE_CREATE_AT);
            }
            LOG.debug("Success: date have been obtained");
            LOG.trace("createAt ===>" + createAt);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return createAt;
    }


    @Override
    public List<Fine> findAll() throws DBException {
        LOG.debug("Trying to get fines from db");
        List<Fine> fineList;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SQLQueries.FINE_FIND_ALL);
            fineList = new ArrayList<>();
            while (rs.next()) {
                fineList.add(new FineExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace(fineList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(stm, rs);
        }
        return fineList;
    }

    @Override
    public void create(Fine entity) throws DBException {
        LOG.debug("Trying to create the fine");
        LOG.trace(entity);
        Fine fine = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setInt(k++, fine.getUserId());
            pstmt.setInt(k++, fine.getOrderId());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    fine.setId(rs.getInt(1));
                }
            }
            LOG.debug("Success: fine have been  created");
            LOG.trace(fine);
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public Fine read(Integer id) throws DBException {
        LOG.debug("Trying to read fine with id" + id);
        Fine fine = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_GET_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                fine = new FineExtractor().extract(rs);
            }
            LOG.debug("Success: fine have been  read");
            LOG.trace(fine);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return fine;
    }

    @Override
    public void update(Fine entity) throws DBException {
        LOG.debug("Trying to update the category");
        LOG.trace(entity);
        Fine fine = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_UPDATE);
            int k = 1;
            pstmt.setInt(k++, fine.getUserId());
            pstmt.setInt(k++, fine.getOrderId());
            pstmt.setString(k++, fine.getStatus().name());
            pstmt.setInt(k++, fine.getId());
            pstmt.executeUpdate();
            LOG.debug("Success: fine have been  updated");
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public void changeFineStatusById(Integer id, FineStatus status) throws DBException {
        LOG.debug("Trying to change fine status with id" + id);
        LOG.debug("status ===>" + status);
        try {
            pstmt = connection.prepareStatement(SQLQueries.FINE_UPDATE_STATUS);
            int k = 1;
            pstmt.setString(k++, status.name());
            pstmt.setInt(k++, id);
            pstmt.executeUpdate();
            LOG.debug("Success: staus have been  changed");
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public void delete(Fine entity) {
        LOG.debug("Trying to delete the fine");
        LOG.info("not implemented");
    }
}
