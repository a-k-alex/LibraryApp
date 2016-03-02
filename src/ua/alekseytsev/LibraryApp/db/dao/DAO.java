package ua.alekseytsev.LibraryApp.db.dao;

import ua.alekseytsev.LibraryApp.db.model.entity.Entity;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.util.List;

/**
 * Declared CRUD method for Entities
 *
 * @param <T>
 */
public interface DAO<T extends Entity> {

    List<T> findAll() throws DBException;

    void create(T entity) throws DBException;

    T read(Integer id) throws DBException;

    void update(T entity) throws DBException;

    void delete(T entity) throws DBException;
}
