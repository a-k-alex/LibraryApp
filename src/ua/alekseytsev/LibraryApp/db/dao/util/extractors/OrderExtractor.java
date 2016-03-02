package ua.alekseytsev.LibraryApp.db.dao.util.extractors;

import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link Order} from obtained ResultSet
 */
public class OrderExtractor implements Extractor<Order> {
    /**
     * Extracts a Order entity from the result set.
     *
     * @param rs Result set from which a Order entity will be extracted.
     * @return Order entity
     */
    @Override
    public Order extract(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setUserId(rs.getInt(Fields.ORDER_USER_ID))
                .setBookId(rs.getInt(Fields.ORDER_BOOK_ID))
                .setCreateAt(rs.getTimestamp(Fields.ORDER_CREATE_AT))
                .setReturnDate(rs.getTimestamp(Fields.ORDER_RETURN_DATE))
                .setStatus(OrderStatus.valueOf(rs.getString(Fields.ORDER_STATUS).toUpperCase()))
                .setId(rs.getInt(Fields.ENTITY_ID));
        return order;
    }
}
