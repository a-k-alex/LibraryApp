package ua.alekseytsev.LibraryApp.db.model.entity;

import java.io.Serializable;

/**
 * Basic class for all entities
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = 1642618967447854360L;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Entity setId(Integer id) {
        this.id = id;
        return this;
    }
}

