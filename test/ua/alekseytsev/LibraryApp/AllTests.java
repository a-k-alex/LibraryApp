package ua.alekseytsev.LibraryApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.alekseytsev.LibraryApp.db.dao.DAOFactoryTest;
import ua.alekseytsev.LibraryApp.web.util.CommandContainerTest;
import ua.alekseytsev.LibraryApp.web.util.EnumsTest;
import ua.alekseytsev.LibraryApp.web.util.ValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ValidatorTest.class, CommandContainerTest.class, EnumsTest.class, DAOFactoryTest.class})
public class AllTests {
}
