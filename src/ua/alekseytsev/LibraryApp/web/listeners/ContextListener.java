package ua.alekseytsev.LibraryApp.web.listeners;


/**
 * Context listener
 */

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.dao.DAOFactory;
import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.CategoryService;
import ua.alekseytsev.LibraryApp.web.util.Path;
import ua.alekseytsev.LibraryApp.web.util.TaskChekingFines;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;
import java.util.Timer;

public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        sce.getServletContext().setAttribute("roles", Role.values());
        sce.getServletContext().setAttribute("orderStatuses", OrderStatus.values());
        sce.getServletContext().setAttribute("fineStatuses", FineStatus.values());
        DAOFactory.setDaoFactoryFCN(DAOFactory.MY_SQL_DAO);
        Timer timer = new Timer(true); // Instantiate Timer Object
        timer.schedule(TaskChekingFines.getInstance(), 60_000, 3_600_000); // Create Repetitively task for every 1 hour

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        try {
            List<Category> categories = new CategoryService().showAll();
            se.getSession().setAttribute("categories", categories);
            se.getSession().setAttribute("path", Path.PAGE_LOGIN);
        } catch (LibraryException e) {
            LOG.error(e);
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
