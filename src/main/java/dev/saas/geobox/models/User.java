package dev.saas.geobox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String name;
    private String email;
    private String password;
    private Long role;

    @OneToMany(mappedBy = "user")
    private List<Delivery> delivery;

}
