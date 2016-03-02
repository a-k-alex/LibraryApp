package ua.alekseytsev.LibraryApp.db.dao;

import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.Timestamp;
import java.util.List;

/**
 * For data manipulation in the "fines" table
 */
public interface FineDAO extends DAO<Fine> {

    void changeFineStatusById(Integer id, FineStatus status) throws DBException;

    List<Fine> findAll(FineStatus status) throws DBException;

    List<Fine> findAllByUserId(Integer id) throws DBException;

    Timestamp getCreatedDate(Integer fineId) throws DBException;
}