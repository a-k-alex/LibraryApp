package ua.alekseytsev.LibraryApp.db.model.bean;

import ua.alekseytsev.LibraryApp.db.model.entity.Book;
import ua.alekseytsev.LibraryApp.db.model.entity.Fine;
import ua.alekseytsev.LibraryApp.db.model.entity.Order;
import ua.alekseytsev.LibraryApp.db.model.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserBean implements Serializable {
    private static final long serialVersionUID = 7696377278067441498L;
    private User user;
    private Map<Order, Book> orderMap;
    private List<Fine> orderFines;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Order, Book> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Order, Book> orderMap) {
        this.orderMap = orderMap;
    }

    public List<Fine> getOrderFines() {
        return orderFines;
    }

    public void setOrderFines(List<Fine> orderFines) {
        this.orderFines = orderFines;
    }
}
