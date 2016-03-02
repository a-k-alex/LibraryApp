package ua.alekseytsev.LibraryApp.db.dao.util.extractors;

import ua.alekseytsev.LibraryApp.db.dao.util.Fields;
import ua.alekseytsev.LibraryApp.db.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract {@link ua.alekseytsev.LibraryApp.db.model.entity.Category} from obtained ResultSet
 */
public class CategoryExtractor implements Extractor<Category> {
    /**
     * Extracts a category entity from the result set.
     *
     * @param rs Result set from which category entity will be extracted.
     * @return Category entity
     */
    @Override
    public Category extract(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setCategoryName(rs.getString(Fields.CATEGORY_NAME))
                .setId(rs.getInt(Fields.ENTITY_ID));
        return category;
    }
}
