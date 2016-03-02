package ua.alekseytsev.LibraryApp.db.dao.util.extractors;

import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.model.Role;
import ua.alekseytsev.LibraryApp.db.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link ua.alekseytsev.LibraryApp.db.model.entity.User} from obtained ResultSet
 */
public class UserExtractor implements Extractor<User> {
    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    @Override
    public User extract(ResultSet rs) throws SQLException {
        User user = new User();
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME))
                .setLastName(rs.getString(Fields.USER_LAST_NAME))
                .setEmail(rs.getString(Fields.USER_EMAIL))
                .setPassword(rs.getString(Fields.USER_PASSWORD))
                .setRole(Role.valueOf(rs.getString(Fields.USER_ROLE).toUpperCase()))
                .setBannedAt(rs.getTimestamp(Fields.USER_BANNED_AT))
                .setDeleteAt(rs.getTimestamp(Fields.USER_DELETE_AT))
                .setId(rs.getInt(Fields.ENTITY_ID));
        return user;
    }
}
