package ua.alekseytsev.LibraryApp.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 08.02.2016.
 */
public class EmptyCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -4203422238446134548L;
    private static final Logger LOG = LogManager.getLogger(EmptyCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Command starts");
        request.setAttribute("errorMessage", "404");
        request.getSession().setAttribute("path", Path.PAGE_ERROR_PAGE);
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}
