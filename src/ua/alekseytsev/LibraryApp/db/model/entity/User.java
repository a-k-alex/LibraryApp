package ua.alekseytsev.LibraryApp.db.model.entity;

import ua.alekseytsev.LibraryApp.db.model.Role;

import java.sql.Timestamp;

/**
 * User entity
 */
public class User extends Entity {

    private static final long serialVersionUID = 4818542474983461195L;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Timestamp bannedAt;
    private Timestamp deleteAt;


    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public User setEmail(String email) {
        this.email = email.toLowerCase();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public Timestamp getBannedAt() {
        return bannedAt;
    }

    public User setBannedAt(Timestamp bannedAt) {
        this.bannedAt = bannedAt;
        return this;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public User setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            {
                return false;
            }
        }

        User user = (User) o;

        if (bannedAt != null ? !bannedAt.equals(user.bannedAt) : user.bannedAt != null) {
            return false;
        }
        if (deleteAt != null ? !deleteAt.equals(user.deleteAt) : user.deleteAt != null) {
            return false;
        }
        if (!email.equals(user.email)) {
            return false;
        }
        if (!firstName.equals(user.firstName)) {
            return false;
        }
        if (!lastName.equals(user.lastName)) {
            return false;
        }
        if (!password.equals(user.password)) {
            return false;
        }
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (bannedAt != null ? bannedAt.hashCode() : 0);
        result = 31 * result + (deleteAt != null ? deleteAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{id=" + getId() + " " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", bannedAt=" + bannedAt +
                ", deleteAt=" + deleteAt +
                '}';
    }
}
