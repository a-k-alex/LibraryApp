package ua.alekseytsev.LibraryApp.db.dao.util.extractors;

import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link Fine} from obtained ResultSet
 */
public class FineExtractor implements Extractor<Fine> {
    /**
     * Extracts a Fine entity from the result set.
     *
     * @param rs Result set from which a Fine entity will be extracted.
     * @return Fine entity
     */
    @Override
    public Fine extract(ResultSet rs) throws SQLException {
        Fine fine = new Fine();
        fine.setUserId(rs.getInt(Fields.FINE_USER_ID))
                .setOrderId(rs.getInt(Fields.FINE_ORDER_ID))
                .setStatus(FineStatus.valueOf(rs.getString(Fields.FINE_STATUS).toUpperCase()))
                .setCreateAt(rs.getTimestamp(Fields.FINE_CREATE_AT))
                .setId(rs.getInt(Fields.ENTITY_ID));
        return fine;
    }
}
