package dev.saas.geobox.mapper;

import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.models.Delivery;

public class DeliveryMapper {

    public static DeliveryResponse toResponse(Delivery delivery) {
        return new DeliveryResponse(delivery.getLocation().getX(), delivery.getLocation().getY(), delivery.getId(), delivery.getUser().getName());
    }
}
