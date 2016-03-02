package ua.alekseytsev.LibraryApp.web.filters;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.web.util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Security filter
 */
public class FilterSecurity implements Filter {
    private static final Logger LOG = LogManager.getLogger(FilterSecurity.class);
    private static final String ERR_AUTHORISATION = "Unauthorized access";
    private Map<Role, Set<String>> permissionsMap = new HashMap<>();
    private Set<String> commons = new HashSet<>();

    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        LOG.debug("Filter starts");
        if (isPermiss(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            LOG.error("unauthorized access");
            request.setAttribute("errorMessage", ERR_AUTHORISATION);
            request.getRequestDispatcher(Path.PAGE_LOGIN).forward(request, response);
            LOG.debug("Filter finished");
        }
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("Filter initialization starts");
        // roles apart from admin
        String readerCommands = config.getInitParameter("reader commands");
        String librarianCommands = config.getInitParameter("librarian commands");
        permissionsMap.put(Role.READER, parseCommands(readerCommands));
        permissionsMap.put(Role.LIBRARIAN, parseCommands(readerCommands + "," + librarianCommands));
        LOG.trace("Permissions map --> " + permissionsMap);
        // commons
        commons = parseCommands(config.getInitParameter("common commands"));
        LOG.trace("Common commands --> " + commons);
        LOG.debug("Filter initialization finished");
    }

    private boolean isPermiss(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }
        if (commons.contains(commandName)) {
            return true;
        }
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return false;
        }
        if (Role.ADMINISTRATOR.equals(user.getRole())) {
            return true;
        }
        return permissionsMap.get(user.getRole()).contains(commandName);
    }

    /**
     * Extracts parameter values from string.
     *
     * @param commands parameter values string.
     * @return list of parameter values.
     */
    private Set<String> parseCommands(String commands) {
        Set<String> set = new HashSet<>();
        for (String command : commands.split(",")) {
            set.add(command);
        }
        return set;
    }
}
