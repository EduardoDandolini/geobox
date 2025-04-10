package dev.saas.geobox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Box extends BaseEntity {

    private String boxNumber;

    @OneToMany(mappedBy = "box")
    private List<Delivery> delivery;

}
