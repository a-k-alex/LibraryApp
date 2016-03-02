package ua.alekseytsev.LibraryApp.web.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.CommandContainer;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alekseytsev A.
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 8989571847967207989L;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Controller starts");
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command ==>" + commandName);
        HandlerCommand command = CommandContainer.get(commandName);
        LOG.trace("Obtained command ==>" + command);
        String action;
        try {
            action = command.execute(request, response);
            LOG.debug("Controller finished, now go to  address==> " + request.getSession().getAttribute("path"));

            if (action.equals(Path.ACTION_FORWARD)) {
                request.getSession().setAttribute("lastCommand", commandName);
                request.getRequestDispatcher("/view").forward(request, response);
            }
            if (action.equals(Path.ACTION_REDIRECT)) {
                response.sendRedirect("/view");
            }
        } catch (LibraryException e) {
            LOG.error(e.getMessage(), e);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }
}
