package ua.alekseytsev.LibraryApp.db.dao.mySQL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.UserDAO;
import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.dao.util.extractors.UserExtractor;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alekseytsev A.
 */
public class UserDAOMySQL implements UserDAO {
    private static final Logger LOG = LogManager.getLogger(UserDAOMySQL.class);
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Statement stm;

    public UserDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws DBException {
        LOG.debug("Trying to get users from db");
        List<User> userList;

        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SQLQueries.USER_FIND_ALL);
            userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(new UserExtractor().extract(rs));
            }
            LOG.debug("Success: data have been obtained");
            LOG.trace("userList ===>" + userList);
        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(stm, rs);
        }
        return userList;
    }

    @Override
    public void create(User entity) throws DBException {
        LOG.debug("Trying to create the user");
        LOG.trace(entity);
        User user = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.USER_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
            LOG.debug("Success: user have been  created");
            LOG.trace(user);
        } catch (java.sql.BatchUpdateException e) {
            //if duplicate category
            LOG.error("ERR_DUPLICATE_ENTRY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_DUPLICATE_USER, e);

        } catch (SQLException e) {
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public User read(Integer id) throws DBException {
        LOG.debug("Trying to read user with id" + id);
        User user = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.USER_GET_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserExtractor().extract(rs);
            }
            LOG.debug("Success: category have been  read");
            LOG.trace(user);
        } catch (SQLException e) {
            LOG.error("Can not read ===>" + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return user;
    }

    @Override
    public void update(User entity) throws DBException {
        LOG.debug("Trying to update the user");
        LOG.trace(entity);
        User user = entity;
        try {
            pstmt = connection.prepareStatement(SQLQueries.USER_UPDATE);
            int k = 1;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getRole().name());
            pstmt.setTimestamp(k++, user.getBannedAt());
            pstmt.setTimestamp(k++, user.getDeleteAt());
            pstmt.setInt(k++, user.getId());
            pstmt.executeUpdate();
            LOG.debug("Success: category have been  updated");
        } catch (java.sql.BatchUpdateException e) {
            //if update on duplicate category
            LOG.error("ERR_DUPLICATE_ENTRY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_DUPLICATE_ENTRY, e);
        } catch (SQLException e) {
            LOG.error("Can not update ===> " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }

    @Override
    public void delete(User entity) throws DBException {
        User user = entity;
        user.setDeleteAt(new Timestamp(new Date().getTime()));
        update(user);
    }


    @Override
    public User getUserByEmail(String email) throws DBException {
        LOG.debug("Trying to read user  with email" + email);
        User user = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.USER_GET_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserExtractor().extract(rs);
            }
            LOG.debug("Success: user have been  read");
            LOG.trace(user);
        } catch (SQLException e) {
            LOG.error("Can not get by email ===>" + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return user;
    }

    @Override
    public String getEmailbyId(Integer id) throws DBException {
        LOG.debug("Trying to read email by user id" + id);
        String email = null;
        try {
            pstmt = connection.prepareStatement(SQLQueries.USER_GET_EMAIL_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                email = rs.getString(Fields.USER_EMAIL);
            }
            LOG.debug("Success: email have been  read");
            LOG.trace(email);
        } catch (SQLException e) {
            LOG.error("Can not get email by id===> " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
        return email;
    }

    @Override
    public void updateRoleAndCondition(Integer id, Role role, Timestamp isBanned) throws DBException {
        LOG.debug("Trying to update the role and the condition od the  the user with id ===>" + id);
        LOG.trace("role ===>" + role);
        LOG.trace("isBanned ===>" + isBanned);
        try {
            pstmt = connection.prepareStatement(SQLQueries.USER_UPDATE_ROLE_CONDITION);
            int k = 1;
            pstmt.setString(k++, role.name());
            pstmt.setTimestamp(k++, isBanned);
            pstmt.setInt(k++, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ERR_CANNOT_OBTAIN_ENTITY " + getClass().getName(), e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_ENTITY, e);
        } finally {
            MySQLDAOFactory.close(pstmt, rs);
        }
    }
}
