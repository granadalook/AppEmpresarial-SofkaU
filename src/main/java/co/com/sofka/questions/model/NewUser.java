package co.com.sofka.questions.model;


import java.util.HashSet;
import java.util.Set;

public class NewUser {

    private String idUser;
    private String username;
    private String password;

    private Set<String> roles = new HashSet<>();

    public NewUser() {

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


}