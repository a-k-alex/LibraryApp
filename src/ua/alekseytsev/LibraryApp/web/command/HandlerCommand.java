package ua.alekseytsev.LibraryApp.web.command;

import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Handler Command
 */
public interface HandlerCommand extends Serializable {
    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, LibraryException;
}
