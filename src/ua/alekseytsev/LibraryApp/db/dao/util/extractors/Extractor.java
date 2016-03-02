package ua.alekseytsev.LibraryApp.db.dao.util.extractors;

import ua.alekseytsev.LibraryApp.db.model.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Parse obtained from DB result set
 *
 * @param <T>
 */
public interface Extractor<T extends Entity> {
    T extract(ResultSet resultSet) throws SQLException;
}
