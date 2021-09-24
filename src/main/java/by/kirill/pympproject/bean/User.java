package by.kirill.pympproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String pass;
    private String role;
    private String email;
    private String status;

    public User() {
    }

    public User(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.status = "inactive";
        this.role = "user";
    }

    public User(String name, String pass, String email, String role) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.status = "inactive";
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                pass.equals(user.pass) &&
                role.equals(user.role) &&
                email.equals(user.email) &&
                status.equals(user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass, role, email, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
