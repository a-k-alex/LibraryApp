package ua.alekseytsev.LibraryApp.web.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alekseytsev A.
 */
public class ViewController extends HttpServlet {
    private static final long serialVersionUID = -1023225348832416806L;
    private static final Logger LOG = LogManager.getLogger(ViewController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("In view controller method GET");
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("In view controller method POST");
        process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("In view controller process");
        String path = (String) request.getSession().getAttribute("path");
        LOG.debug("get session atribute path==>" + path);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
