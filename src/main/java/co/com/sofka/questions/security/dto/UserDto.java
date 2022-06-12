package co.com.sofka.questions.security.dto;


public class UserDto {

    private String idUser;

    private String userName;

    private String password;

    public UserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
