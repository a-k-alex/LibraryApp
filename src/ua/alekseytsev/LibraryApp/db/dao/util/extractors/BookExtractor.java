package ua.alekseytsev.LibraryApp.db.dao.util.extractors;

import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link Book} from obtained ResultSet
 */
public class BookExtractor implements Extractor<Book> {
    /**
     * Extracts a Book entity from the result set.
     *
     * @param rs Result set from which a Book entity will be extracted.
     * @return Book entity
     */
    @Override
    public Book extract(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookName(rs.getString(Fields.BOOK_NAME))
                .setAuthor(rs.getString(Fields.BOOK_AUTHOR))
                .setPublication(rs.getString(Fields.BOOK_PUBLICATION))
                .setPublicationYear(rs.getInt(Fields.BOOK_YEAR))
                .setAmount(rs.getInt(Fields.BOOK_AMOUNT))
                .setInStock(rs.getInt(Fields.BOOK_IN_STOCK))
                .setDeleteAt(rs.getTimestamp(Fields.BOOK_DELETE_AT))
                .setId(rs.getInt(Fields.ENTITY_ID));
        return book;
    }
}
