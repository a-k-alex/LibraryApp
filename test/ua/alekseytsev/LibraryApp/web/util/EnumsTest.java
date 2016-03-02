package ua.alekseytsev.LibraryApp.web.util;

import org.junit.Test;
import ua.alekseytsev.LibraryApp.db.model.FineStatus;
import ua.alekseytsev.LibraryApp.db.model.OrderStatus;
import ua.alekseytsev.LibraryApp.db.model.Role;

public class EnumsTest {
    @Test
    public void valueFineStatus() {
        for (FineStatus f : FineStatus.values()) {
            FineStatus.valueOf(f.toString());
        }
    }

    @Test
    public void valueOrderStatus() {
        for (OrderStatus o : OrderStatus.values()) {
            OrderStatus.valueOf(o.toString());
        }
    }

    @Test
    public void valueRole() {
        for (Role r : Role.values()) {
            Role.valueOf(r.toString());
        }
    }
}
