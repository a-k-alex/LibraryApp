package ua.alekseytsev.LibraryApp.web.util;

import org.junit.Assert;
import org.junit.Test;
import ua.alekseytsev.LibraryApp.web.command.EmptyCommandHandler;

public class CommandContainerTest {

    @Test
    public void testGetEmptyCommand() throws Exception {
        Assert.assertEquals(CommandContainer.get("").getClass(), EmptyCommandHandler.class);
    }
}