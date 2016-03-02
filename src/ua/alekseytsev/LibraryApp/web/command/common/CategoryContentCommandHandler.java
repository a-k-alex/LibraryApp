package ua.alekseytsev.LibraryApp.web.command.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.CategoryService;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * List categories handler
 */
public class CategoryContentCommandHandler implements HandlerCommand {

    private static final long serialVersionUID = 3410787706681559653L;
    private static final Logger LOG = LogManager.getLogger(CategoryContentCommandHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException {
        LOG.debug("Command starts");
        LOG.trace("Extract request parameters");
        Integer id = Integer.valueOf(request.getParameter("id"));
        LOG.trace("id ===>" + id);
        Category category = new CategoryService().showContent(id);
        request.getSession().setAttribute("category", category);
        LOG.trace("Set the session attribute \"category\" ===>" + category);
        request.getSession().setAttribute("bookList", category.getBookList());
        LOG.trace("Set the session attribute \"bookList\" ===>" + category.getBookList());
        LOG.debug("Command finished");
        request.getSession().setAttribute("path", Path.PAGE_CATEGORY);
        return Path.ACTION_FORWARD;
    }
}
