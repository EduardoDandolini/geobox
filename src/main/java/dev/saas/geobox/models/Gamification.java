package dev.saas.geobox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Gamification extends BaseEntity {

    private Double points;

    @OneToOne
    @ToString.Exclude
    private User user;

    @OneToOne
    @ToString.Exclude
    private Delivery delivery;

}
