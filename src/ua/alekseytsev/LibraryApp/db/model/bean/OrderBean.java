package ua.alekseytsev.LibraryApp.db.model.bean;

import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.db.model.entity.User;

import java.io.Serializable;

public class OrderBean implements Serializable {
    private static final long serialVersionUID = -2300541489438489743L;
    private User user;
    private Order order;
    private Book book;
    private Fine fine;

    public User getUser() {
        return user;
    }

    public OrderBean setUser(User user) {
        this.user = user;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderBean setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public OrderBean setBook(Book book) {
        this.book = book;
        return this;
    }

    public Fine getFine() {
        return fine;
    }

    public OrderBean setFine(Fine fine) {
        this.fine = fine;
        return this;
    }
}
