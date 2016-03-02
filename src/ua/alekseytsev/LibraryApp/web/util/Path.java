package ua.alekseytsev.LibraryApp.web.util;

/**
 * Contains paths to jsp pages
 */
public final class Path {
    public static final String ACTION_FORWARD = "forward";
    public static final String ACTION_REDIRECT = "redirect";

    public static final String LOCATION = "/WEB-INF/jsp/";

    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTER = "/register.jsp";
    public static final String PAGE_CATEGORY = LOCATION + "category.jsp";
    public static final String PAGE_MAIN = LOCATION + "categories.jsp";
    public static final String PAGE_BOOKS = LOCATION + "searchBooksResult.jsp";
    public static final String PAGE_ERROR_PAGE = LOCATION + "errorPage.jsp";

    public static final String PAGE_WELCOME = LOCATION + "reader/welcome.jsp";
    public static final String PAGE_PERSONAL_INFO = LOCATION + "reader/personalInfo.jsp";
    public static final String PAGE_MY_ORDERS = LOCATION + "reader/myOrders.jsp";
    public static final String PAGE_MY_FINES = LOCATION + "reader/myFines.jsp";

    public static final String PAGE_LIST_ORDERS = LOCATION + "librarian/listOrders.jsp";
    public static final String PAGE_LIST_FINES = LOCATION + "librarian/listFines.jsp";

    public static final String PAGE_BOOK = LOCATION + "admin/editBook.jsp";
    public static final String PAGE_LIST_USERS = LOCATION + "admin/listUsers.jsp";
    public static final String PAGE_TOP_BOOKS = LOCATION + "admin/topBooks.jsp";

    private Path() {
    }
}
