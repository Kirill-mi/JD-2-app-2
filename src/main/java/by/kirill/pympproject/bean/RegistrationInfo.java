package by.kirill.pympproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String pass;
    private String controlPass;


    public RegistrationInfo() {

    }

    public RegistrationInfo(String name, String email, String pass, String controlPass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.controlPass = controlPass;
    }

    public RegistrationInfo(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getControlPass() {
        return controlPass;
    }

    public void setControlPass(String controlPass) {
        this.controlPass = controlPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationInfo that = (RegistrationInfo) o;
        return name.equals(that.name) &&
                email.equals(that.email) &&
                pass.equals(that.pass) &&
                controlPass.equals(that.controlPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, pass, controlPass);
    }
}
