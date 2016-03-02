package ua.alekseytsev.LibraryApp.db.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAOFactory {
    public static final String MY_SQL_DAO = "ua.alekseytsev.LibraryApp.db.dao.mySQL.MySQLDAOFactory";
    public static final Logger LOG = LogManager.getLogger(DAOFactory.class);
    private static DAOFactory instance;
    private static String daoFactoryFCN;

    /**
     * Method for obtain DAOFactories object.
     * The type of DAOFactory defines by factory settings.
     *
     * @return instance of a child DAOFactory, whose name is contained in {@link #daoFactoryFCN}
     * @throws ua.alekseytsev.LibraryApp.exceptions.DBException
     */
    public static synchronized DAOFactory getInstance() throws DBException {
        if (instance == null) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(DAOFactory.daoFactoryFCN);
                instance = (DAOFactory) clazz.newInstance();
            } catch (Exception e) {
                LOG.error(DBException.ERR_OBTAIN_CONNECTION_TO_DB, e);
                throw new DBException(DBException.ERR_OBTAIN_CONNECTION_TO_DB, e);
            }
        }
        return instance;
    }

    /**
     * Sets concrete DAOFactory
     *
     * @param daoFactoryFCN
     */
    public static void setDaoFactoryFCN(String daoFactoryFCN) {
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }

    /**
     * Close a connection.
     *
     * @param con Connection to be closed.
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
                LOG.debug("Closing connection success");
            } catch (SQLException e) {
                LOG.error(DBException.ERR_CANNOT_CLOSE_CONNECTION, e);
            }
        }
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error(DBException.ERR_CANNOT_ROLLBACK, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    public static void close(Statement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
                LOG.debug("Closing statement success");
            } catch (SQLException e) {
                LOG.error(DBException.ERR_CANNOT_CLOSE_STATEMENT, e);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                LOG.debug("Closing result set success");
            } catch (SQLException e) {
                LOG.error(DBException.ERR_CANNOT_CLOSE_RESULTSET, e);
            }
        }
    }

    /**
     * Closes resources.
     */
    public static void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Closes resources.
     */
    public static void close(Statement stmt, ResultSet rs) {
        LOG.debug("Trying to close statement and result set");
        close(rs);
        close(stmt);
    }

    /**
     * Obtain DAO for User entity
     *
     * @return implementation UserDAO, which defines by settings factory
     * {@link DAOFactory}.
     */
    public abstract UserDAO getUserDAO(Connection connection);

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, which defines by settings factory
     * {@link DAOFactory}.
     */
    public abstract OrderDAO getOrderDAO(Connection connection);

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, which defines by settings factory
     * {@link DAOFactory}.
     */
    public abstract BookDAO getBookDAO(Connection connection);

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, which defines by settings factory
     * {@link DAOFactory}.
     */
    public abstract CategoryDAO getCategoryDAO(Connection connection);

    /**
     * Obtain DAO for Fine entity
     *
     * @return implementation FineDAO, which defines by settings factory
     * {@link DAOFactory}.
     */
    public abstract FineDAO getFineDAO(Connection connection);

    /**
     * Create connection to the DB
     *
     * @return connection to the DB
     * @throws DBException
     */
    public abstract Connection createConnection() throws DBException;
}
