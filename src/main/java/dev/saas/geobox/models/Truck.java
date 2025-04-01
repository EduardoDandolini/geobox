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
public class Truck extends BaseEntity{

    private String plate;
    private String truckType;

    @OneToMany(mappedBy = "truck")
    private List<Delivery> delivery;

}
