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
import java.util.ListIterator;

/**
 * Paid fine handler
 */
public class PaidFineCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = -8632111272440894025L;
    private static final Logger LOG = LogManager.getLogger(PaidFineCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        Integer id = Integer.valueOf(request.getParameter("id"));
        LOG.trace("id ===>" + id);
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        LOG.trace("userId ===>" + userId);
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        LOG.trace("orderId ===>" + orderId);
        Fine fine = new Fine().setStatus(FineStatus.CLOSED)
                .setUserId(userId)
                .setOrderId(orderId);
        fine.setId(id);
        new FineService().update(fine);
        List<Fine> fines = (List<Fine>) request.getSession().getAttribute("fines");
        for (ListIterator<Fine> iterator = fines.listIterator(); iterator.hasNext(); ) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
            }
        }
        request.getSession().setAttribute("path", Path.PAGE_LIST_FINES);
        LOG.debug("Command finished");
        return Path.ACTION_REDIRECT;
    }
}
