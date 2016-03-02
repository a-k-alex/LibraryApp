package ua.alekseytsev.LibraryApp.web.command.librarian;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.FineService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * List fines handler
 */
public class ListFineCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = 5569081472224238820L;
    private static final Logger LOG = LogManager.getLogger(ListFineCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        String status = request.getParameter("status");
        LOG.trace("fine status ===>" + status);
        List<Fine> fines = new FineService().showAll(FineStatus.valueOf(status.toUpperCase()));
        request.getSession().setAttribute("fines", fines);
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_LIST_FINES);
        return Path.ACTION_FORWARD;
    }
}
