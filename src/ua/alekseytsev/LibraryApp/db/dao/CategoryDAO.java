package ua.alekseytsev.LibraryApp.db.dao;

import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.util.List;

/**
 * For data manipulation in the "categories" table
 */
public interface CategoryDAO extends DAO<Category> {

    List<Book> readCategoryBooks(Integer id) throws DBException;

    void addBookToCategory(Integer idCategory, Integer idBook) throws DBException;
}