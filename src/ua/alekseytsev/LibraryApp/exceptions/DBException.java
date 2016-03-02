package ua.alekseytsev.LibraryApp.exceptions;

public class DBException extends Exception {
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
    public static final String ERR_CANNOT_OBTAIN_ENTITY = "Cannot obtain entity from DB";
    public static final String ERR_DUPLICATE_ENTRY = "Row with such fields already exists";
    public static final String ERR_OBTAIN_CONNECTION_TO_DB = "Cannot obtain the connection to the database";
    public static final String ERR_CANNOT_ROLLBACK = "Cannot rollback transaction";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close the result set";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close the statement";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close the connection";
    public static final String ERR_DUPLICATE_USER = "User with such email already exists";

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(String message) {
        super(message);
    }
}
