package ua.alekseytsev.LibraryApp.web.command.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout handler
 */
public class LogoutCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = 2975895430329038001L;
    private static final Logger LOG = LogManager.getLogger(LogoutCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        LOG.trace("Invalidate the session:" + session.getId());
        session.invalidate();
        LOG.info("Session has been invalidate");
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_LOGIN);
        return Path.ACTION_REDIRECT;
    }
}
