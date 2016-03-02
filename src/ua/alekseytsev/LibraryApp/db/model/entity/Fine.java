package ua.alekseytsev.LibraryApp.db.model.entity;

import ua.alekseytsev.LibraryApp.db.model.FineStatus;

import java.sql.Timestamp;

/**
 * Fine entity
 */
public class Fine extends Entity {

    private static final long serialVersionUID = 6928162895568526540L;
    private Integer userId;
    private Integer orderId;
    private FineStatus status;
    private Timestamp createAt;

    public Integer getUserId() {
        return userId;
    }

    public Fine setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Fine setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public FineStatus getStatus() {
        return status;
    }

    public Fine setStatus(FineStatus status) {
        this.status = status;
        return this;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public Fine setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fine)) {
            return false;
        }

        Fine fine = (Fine) o;

        if (createAt != null ? !createAt.equals(fine.createAt) : fine.createAt != null) {
            return false;
        }
        if (!orderId.equals(fine.orderId)) {
            return false;
        }
        if (status != fine.status) {
            return false;
        }
        return userId.equals(fine.userId);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "id=" + getId() +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", status=" + status +
                ", createAt=" + createAt +
                '}';
    }
}
