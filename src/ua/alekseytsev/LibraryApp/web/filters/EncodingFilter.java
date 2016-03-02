package ua.alekseytsev.LibraryApp.web.filters;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 */
public class EncodingFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(EncodingFilter.class);
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("Filter initialization starts");
        encoding = config.getInitParameter("encoding");
        LOG.trace("Encoding from ua.nure.alekseytsev.SummaryTask4.web.xml --> " + encoding);
        LOG.debug("Filter initialization finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Filter starts");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }
        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }

}