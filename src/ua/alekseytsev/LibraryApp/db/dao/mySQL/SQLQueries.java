package ua.alekseytsev.LibraryApp.db.dao.mySQL;

/**
 * Contains queries for access objects in the DB
 */
public final class SQLQueries {
    //Category
    public static final String CATEGORY_FIND_ALL = "SELECT * FROM categories";
    public static final String CATEGORY_GET_BY_ID = "SELECT * FROM categories WHERE id=?";
    public static final String CATEGORY_CREATE = "INSERT INTO  categories (category_name) VALUE (?)";
    public static final String CATEGORY_UPDATE = "UPDATE categories SET category_name=? WHERE id=?";
    //Book
    public static final String BOOK_FIND_ALL = "SELECT * FROM books";
    public static final String BOOK_GET_BY_ID = "SELECT * FROM books WHERE id=?";
    public static final String BOOK_CREATE = "INSERT  INTO books (book_name, author, publication, publication_year," +
            "amount,in_stock) VALUES (?,?,?,?,?,?) ";
    public static final String BOOK_UPDATE = "UPDATE books SET book_name = ?, author = ?, publication = ?, " +
            "publication_year = ?, amount = ?, in_stock = ?, delete_at =?  WHERE id = ?";
    public static final String BOOK_SEARCH = "SELECT * FROM books WHERE  author LIKE ? OR book_name LIKE ?";
    //User
    public static final String USER_FIND_ALL = "SELECT * FROM users";
    public static final String USER_GET_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String USER_CREATE = "INSERT INTO users(first_name, last_name, email, password) " +
            "VALUES (?,?,?,?)";
    public static final String USER_UPDATE = "UPDATE users SET first_name=?,last_name=?,password=?," +
            "role=?, banned_at=?, delete_at=? WHERE id=?";

    public static final String USER_GET_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    public static final String USER_UPDATE_ROLE_CONDITION = "UPDATE users SET role=?,banned_at = ?  WHERE id=?";
    //Order
    public static final String ORDER_FIND_ALL = "SELECT * FROM orders";
    public static final String ORDER_FIND_ALL_BY_STATUS = "SELECT * FROM orders WHERE status=?";
    public static final String ORDER_FIND_ALL_BY_USER_ID = "SELECT * FROM orders WHERE user_id=?";
    public static final String ORDER_RETURN_DATE = "SELECT return_date FROM orders WHERE id=? ";

    public static final String ORDER_GET_BY_ID = "SELECT * FROM orders WHERE id=?";
    public static final String ORDER_CREATE = "INSERT INTO orders(user_id, book_id, create_at, " +
            "return_date) VALUE (?,?,?,?)";
    public static final String ORDER_UPDATE = "UPDATE orders SET user_id=?,book_id=?,create_at=?,return_date=?," +
            "status=? WHERE id=?";
    public static final String ORDER_UPDATE_STATUS = "UPDATE orders SET return_date=?,status=? WHERE id=?";
    public static final String ORDER_FIND_ALL_OVERDUE = "SELECT * FROM orders WHERE return_date <TIMESTAMP (NOW()) " +
            "AND status= ?";
    public static final String ORDER_FIND_UNRGSTR_OVERDUE = "SELECT * FROM orders O WHERE " +
            "O.return_date<TIMESTAMP(NOW()) AND O.status ='BOOKED' AND O.id  NOT IN (SELECT order_id FROM fines)";
    public static final String ORDER_CHECK_DUPLICATE = "SELECT COUNT(*) AS total FROM orders WHERE user_id=? " +
            "and book_id=? AND orders.status!='DONE';";

    //Fine
    public static final String FINE_FIND_ALL = "SELECT * FROM fines";
    public static final String FINE_FIND_ALL_BY_STATUS = "SELECT * FROM fines WHERE status=?";
    public static final String FINE_GET_BY_ID = "SELECT * FROM fines WHERE id=?";
    public static final String FINE_CREATE = "INSERT INTO fines(user_id,order_id) VALUES (?,?)";
    public static final String FINE_UPDATE = "UPDATE fines SET user_id=?,order_id=?,status=? WHERE id=?";
    public static final String FINE_UPDATE_STATUS = "UPDATE fines SET status=? WHERE id=?";
    public static final String FINE_FIND_ALL_BY_USER_ID = "SELECT * FROM fines WHERE user_id=?";
    public static final String FINE_CREATE_AT = "SELECT create_at FROM fines WHERE id=? ";
    //Beans
    public static final String CATEGORY_LIST_BOOK = "SELECT * FROM books WHERE id IN " +
            "(SELECT book_id FROM categories_books WHERE category_id=?)";
    public static final String CATEGORY_LIST_BOOK_JOIN = "SELECT * FROM books JOIN  categories_books " +
            "ON books.id = categories_books.book_id WHERE category_id =?";
    public static final String CATEGORY_ADD_BOOK = "INSERT INTO categories_books VALUES (?,?)";
    public static final String USER_GET_EMAIL_BY_ID = "SELECT email FROM users WHERE id=?";
    public static final String BOOK_SEARCH_TOP = "SELECT books.id,book_name,author,publication,publication_year," +
            "in_stock,amount,delete_at ,count(*) AS quantity FROM books INNER JOIN orders ON books.id = orders.book_id " +
            "GROUP BY books.id,book_name,author,publication,publication_year,in_stock,amount,delete_at " +
            "ORDER BY count(*) DESC LIMIT ?";

    private SQLQueries() {
    }
}
