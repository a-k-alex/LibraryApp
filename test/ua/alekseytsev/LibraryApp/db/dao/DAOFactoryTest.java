package ua.alekseytsev.LibraryApp.db.dao;

import org.junit.Assert;
import org.junit.Test;
import ua.alekseytsev.LibraryApp.db.dao.mySQL.*;

public class DAOFactoryTest {

    @Test
    public void testGetInstance() throws Exception {
        DAOFactory.setDaoFactoryFCN(DAOFactory.MY_SQL_DAO);
        Assert.assertEquals(DAOFactory.getInstance().getClass(), MySQLDAOFactory.getInstance().getClass());
        Assert.assertEquals(DAOFactory.getInstance().getBookDAO(null).getClass(), BookDAOMySQL.class);
        Assert.assertEquals(DAOFactory.getInstance().getUserDAO(null).getClass(), UserDAOMySQL.class);
        Assert.assertEquals(DAOFactory.getInstance().getCategoryDAO(null).getClass(), CategoryDAOMySQL.class);
        Assert.assertEquals(DAOFactory.getInstance().getOrderDAO(null).getClass(), OrderDAOMySQL.class);
        Assert.assertEquals(DAOFactory.getInstance().getFineDAO(null).getClass(), FineDAOMySQL.class);
    }
}