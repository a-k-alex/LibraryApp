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
 * Change language handler
 */
public class LanguageCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -1048043848137249980L;
    private static final Logger LOG = LogManager.getLogger(LanguageCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String language = request.getParameter("language");
        LOG.trace("parameter language ===>" + language);
        request.getSession().setAttribute("language", language);
        LOG.debug("Command finished");
        return Path.ACTION_FORWARD;
    }
}
