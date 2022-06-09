package co.com.sofka.questions.security.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Document(collection = "roles")
public class Rol{
    @Id
    private String id;
    @NotNull
    private String rolName;

    public Rol(String rolName) {
        this.rolName = rolName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
}
