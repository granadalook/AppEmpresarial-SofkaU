package co.com.sofka.questions.collections;

import co.com.sofka.questions.model.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Document
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInto implements UserDetails {
    @Id
    private String id;

    @Getter @Setter
    private String username;
    private String password;
    private List<Role>  rol;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.rol.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
