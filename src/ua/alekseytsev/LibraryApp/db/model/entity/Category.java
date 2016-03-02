package ua.alekseytsev.LibraryApp.db.model.entity;

import java.util.List;

/**
 * Category entity
 */
public class Category extends Entity {

    private static final long serialVersionUID = -342620092525780708L;
    private String categoryName;
    private List<Book> bookList;

    public String getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Category category = (Category) o;

        return categoryName.equals(category.categoryName);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + categoryName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{id=" + getId() + " " +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
