package dev.saas.geobox.models;

import jakarta.persistence.Entity;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Delivery extends BaseEntity {

    private Point location;

}
