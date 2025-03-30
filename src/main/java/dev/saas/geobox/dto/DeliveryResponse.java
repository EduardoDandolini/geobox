package dev.saas.geobox.dto;

import dev.saas.geobox.models.Delivery;
import lombok.Data;

@Data
public class DeliveryResponse {

    private Double latitude;
    private Double longitude;

    public DeliveryResponse(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static DeliveryResponse fromEntity(Delivery delivery) {
        return new DeliveryResponse(delivery.getLocation().getX(), delivery.getLocation().getY());
    }
}
