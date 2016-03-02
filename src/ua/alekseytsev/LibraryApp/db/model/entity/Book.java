package ua.alekseytsev.LibraryApp.db.model.entity;

import java.sql.Timestamp;

/**
 * Book entity
 */
public class Book extends Entity {

    private static final long serialVersionUID = 1165886047031721945L;
    private String bookName;
    private String author;
    private String publication;
    private Integer publicationYear;
    private Integer inStock;
    private Integer amount;
    private Timestamp deleteAt;


    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }


    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublication() {
        return publication;
    }

    public Book setPublication(String publication) {
        this.publication = publication;
        return this;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Book setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public Integer getInStock() {
        return inStock;
    }

    public Book setInStock(Integer inStock) {
        this.inStock = inStock;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Book setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public Book setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Book book = (Book) o;

        if (!amount.equals(book.amount)) {
            return false;
        }
        if (!author.equals(book.author)) {
            return false;
        }
        if (!bookName.equals(book.bookName)) {
            return false;
        }
        if (deleteAt != null ? !deleteAt.equals(book.deleteAt) : book.deleteAt != null) {
            return false;
        }
        if (!inStock.equals(book.inStock)) {
            return false;
        }
        if (!publication.equals(book.publication)) {
            return false;
        }
        return publicationYear.equals(book.publicationYear);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + bookName.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + publication.hashCode();
        result = 31 * result + publicationYear.hashCode();
        result = 31 * result + inStock.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + (deleteAt != null ? deleteAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{id=" + getId() + " " +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publication='" + publication + '\'' +
                ", publicationYear=" + publicationYear +
                ", inStock=" + inStock +
                ", amount=" + amount +
                ", deleteAt=" + deleteAt +
                '}';
    }
}
