package co.com.sofka.questions.security.collections;


import co.com.sofka.questions.security.enums.RolName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.validation.constraints.NotNull;
import javax.persistence.Enumerated;

@Document(collection = "roles")
public class Rol {

    @Id
    private String idRol;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol(RolName roleName) {
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public RolName getRolName() {
        return rolName;
    }

    public void setRolName(RolName rolname) {
        this.rolName = rolname;
    }
}
