package ua.alekseytsev.LibraryApp.db.dao;

import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.util.List;
import java.util.Map;

/**
 * For data manipulation in the "books" table
 */
public interface BookDAO extends DAO<Book> {

    List<Book> search(String bookNameOrAuthor) throws DBException;

    Map<Book, Integer> searchPopularBooks(int limit) throws DBException;
}
