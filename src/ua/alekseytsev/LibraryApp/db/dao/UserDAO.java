package ua.alekseytsev.LibraryApp.db.dao;

import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.User;
import ua.alekseytsev.LibraryApp.exceptions.DBException;

import java.sql.Timestamp;

/**
 * For data manipulation in the "users" table
 */
public interface UserDAO extends DAO<User> {

    User getUserByEmail(String email) throws DBException;

    String getEmailbyId(Integer id) throws DBException;

    void updateRoleAndCondition(Integer id, Role role, Timestamp isBanned) throws DBException;
}
