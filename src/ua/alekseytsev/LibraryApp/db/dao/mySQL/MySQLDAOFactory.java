package ua.alekseytsev.LibraryApp.db.dao.mySQL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.*;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation DAOFactory for MySQL DB
 */
public class MySQLDAOFactory extends DAOFactory {
    private static final Logger LOG = LogManager.getLogger(DAOFactory.class);

    /**
     * Method to create MySQL connections
     *
     * @return connection from connection pool
     */
    public Connection createConnection() throws DBException {
        LOG.debug("Try to create Connection");
        Connection connection;
        try {
            connection = DataSourceSingleton.INSTANCE.getConnection();
        } catch (SQLException e) {
            LOG.error(DBException.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
            throw new DBException(DBException.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
        }
        return connection;
    }

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, for MySQL DB
     */
    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new UserDAOMySQL(connection);
    }

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, for MySQL DB
     * {@link DAOFactory}.
     */
    @Override
    public OrderDAO getOrderDAO(Connection connection) {
        return new OrderDAOMySQL(connection);
    }

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, for MySQL DB
     */
    @Override
    public BookDAO getBookDAO(Connection connection) {
        return new BookDAOMySQL(connection);
    }

    /**
     * Obtain DAO for Category entity
     *
     * @return implementation CategoryDAO, for MySQL DB
     */
    @Override
    public CategoryDAO getCategoryDAO(Connection connection) {
        return new CategoryDAOMySQL(connection);
    }

    /**
     * Obtain DAO for Fine entity
     *
     * @return implementation FineDAO, for MySQL DB
     */
    @Override
    public FineDAO getFineDAO(Connection connection) {
        return new FineDAOMySQL(connection);
    }

    /**
     * Data source singleton
     */
    public enum DataSourceSingleton {
        INSTANCE;
        private DataSource dataSource;

        DataSourceSingleton() {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/librarydb");
            } catch (NamingException e) {
                LOG.error("Can not obtain data source", e);
            }
        }

        public Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }
    }
}

