package ua.alekseytsev.LibraryApp.db.model.entity;

import ua.alekseytsev.LibraryApp.db.model.OrderStatus;

import java.sql.Timestamp;

/**
 * Order entity
 */
public class Order extends Entity {

    private static final long serialVersionUID = 8923213865324666472L;
    private Integer userId;
    private Integer bookId;
    private Timestamp createAt;
    private Timestamp returnDate;
    private OrderStatus status;

    public Integer getUserId() {
        return userId;
    }

    public Order setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }


    public Integer getBookId() {
        return bookId;
    }

    public Order setBookId(Integer bookId) {
        this.bookId = bookId;
        return this;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public Order setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
        return this;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public Order setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        if (!bookId.equals(order.bookId)) {
            return false;
        }
        if (createAt != null ? !createAt.equals(order.createAt) : order.createAt != null) {
            return false;
        }
        if (returnDate != null ? !returnDate.equals(order.returnDate) : order.returnDate != null) {
            return false;
        }
        if (status != order.status) {
            return false;
        }
        return userId.equals(order.userId);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + bookId.hashCode();
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{id=" + getId() + " " +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", createAt=" + createAt +
                ", returnDate=" + returnDate +
                ", status=" + status +
                '}';
    }
}


