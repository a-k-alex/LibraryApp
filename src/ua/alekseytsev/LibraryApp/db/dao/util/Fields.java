package ua.alekseytsev.LibraryApp.db.dao.util;

/**
 * Contains column names from DB
 */
public final class Fields {
    //enntity
    public static final String ENTITY_ID = "id";
    //user
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_ROLE = "role";
    public static final String USER_BANNED_AT = "banned_at";
    public static final String USER_DELETE_AT = "delete_at";
    //order
    public static final String ORDER_USER_ID = "user_id";
    public static final String ORDER_BOOK_ID = "book_id";
    public static final String ORDER_CREATE_AT = "create_at";
    public static final String ORDER_RETURN_DATE = "return_date";
    public static final String ORDER_STATUS = "status";
    //category
    public static final String CATEGORY_NAME = "category_name";
    //book
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_PUBLICATION = "publication";
    public static final String BOOK_YEAR = "publication_year";
    public static final String BOOK_AMOUNT = "amount";
    public static final String BOOK_IN_STOCK = "in_stock";
    public static final String BOOK_DELETE_AT = "delete_at";
    // fine
    public static final String FINE_ID = "fine_id";
    public static final String FINE_USER_ID = "user_id";
    public static final String FINE_ORDER_ID = "order_id";
    public static final String FINE_STATUS = "status";
    public static final String FINE_CREATE_AT = "create_at";
}