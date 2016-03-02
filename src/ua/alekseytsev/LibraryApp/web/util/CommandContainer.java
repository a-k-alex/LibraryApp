package ua.alekseytsev.LibraryApp.web.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.web.command.EmptyCommandHandler;
import ua.alekseytsev.LibraryApp.web.command.HandlerCommand;
import ua.alekseytsev.LibraryApp.web.command.admin.*;
import ua.alekseytsev.LibraryApp.web.command.common.*;
import ua.alekseytsev.LibraryApp.web.command.librarian.ChangeOrderStatusCommandHandler;
import ua.alekseytsev.LibraryApp.web.command.librarian.ListFineCommandHandler;
import ua.alekseytsev.LibraryApp.web.command.librarian.ListOrdersCommandHandler;
import ua.alekseytsev.LibraryApp.web.command.librarian.PaidFineCommandHandler;
import ua.alekseytsev.LibraryApp.web.command.reader.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Contains all commands
 */
public class CommandContainer {
    private static final Logger LOG = LogManager.getLogger(CommandContainer.class);
    private static Map<String, HandlerCommand> commands = new HashMap<>();

    static {
        commands.put("noCommand", new EmptyCommandHandler());

        // common commands
        commands.put("login", new LoginCommandHandler());
        commands.put("register", new RegistrationCommandHandler());
        commands.put("language", new LanguageCommandHandler());
        commands.put("search", new SearchCommandHandler());
        commands.put("showCategories", new ListCategoriesCommandHandler());
        commands.put("showCategory", new CategoryContentCommandHandler());
        commands.put("sortBooks", new SortCommandHandler());

        //reader commands
        commands.put("logout", new LogoutCommandHandler());
        commands.put("orderBook", new OrderBookCommandHandler());
        commands.put("profile", new ProfileCommandHandler());
        commands.put("myBooks", new MyBooksCommandHandler());
        commands.put("myFines", new MyFinesCommandHandler());

        //librarian commands
        commands.put("listOrders", new ListOrdersCommandHandler());
        commands.put("changeOrderStatus", new ChangeOrderStatusCommandHandler());
        commands.put("listFines", new ListFineCommandHandler());
        commands.put("paidFine", new PaidFineCommandHandler());


        // admin commands
        commands.put("listUsers", new ListUsersCommandHandler());
        commands.put("addBook", new AddBookCommandHandler());
        commands.put("bannUser", null);
        commands.put("updateUser", new UserUpdateCommandHandler());
        commands.put("editBook", new ManageBookCommandHandler());
        commands.put("topBooks", new PopularBooksCommandHandler());

        LOG.debug("Command container was successfully initialized");
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static HandlerCommand get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("No such command ===> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}