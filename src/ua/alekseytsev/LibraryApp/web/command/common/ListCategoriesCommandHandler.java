package ua.alekseytsev.LibraryApp.web.command.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * List categories handler
 */
public class ListCategoriesCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -1887559743775963482L;
    private static final Logger LOG = LogManager.getLogger(ListCategoriesCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_MAIN);
        return Path.ACTION_FORWARD;

    }
}

