package ua.alekseytsev.LibraryApp.exceptions;

public class LibraryException extends Exception {


    public static final String ERR_USER_EXIST = "User with such an email is already registered";
    public static final String ERR_AUTHETICATION = "Authentication failed, check the email and password";
    public static final String USER_NOT_EXIST = "User with such login does not exist";
    public static final String ERR_OBTAIN_CONNECTION_TO_DB = "Cannot obtain the connection to the database";
    public static final String ERR_EMPTY_FIELDS = "Please fill all the gaps";
    public static final String ERR_PASSWORD_MATCHING = "Passwords doesn't match";
    public static final String ERR_BAD_EMAIL = "Email is bad";

    public static final String ERR_BOOK_NOT_IN_STOCK = "Sorry, but all books in reserve";
    public static final String ERR_NO_SUCH_COMPARATOR = "No such comparator";
    public static final String ERR_USER_BANNED = "Sorry, but your account was  blocked. Please, contact to administrator";
    public static final String ERR_BOOK_ALREADY_ORDERED = "You already have this book";
    public static final String ERR_BAD_QUANTITY_BOOKS = "In stock can not be more than the total number  of books";
    private static final long serialVersionUID = -4963454361056684568L;

    public LibraryException() {
        super();
    }

    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryException(String message) {
        super(message);
    }
}
