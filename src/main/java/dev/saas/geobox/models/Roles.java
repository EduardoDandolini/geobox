package dev.saas.geobox.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tb_roles")
public class Roles implements GrantedAuthority {

    @Id
    @Column(length = 36)
    private String identifier;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "{\"identifier\":\"" + identifier + "\", \"name\":\"" + name + "\"}";
    }
}
