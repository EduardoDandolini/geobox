package dev.saas.geobox.mapper;

import dev.saas.geobox.dto.TruckResponse;
import dev.saas.geobox.models.Truck;

public class TruckMapper {

    public static TruckResponse toResponse(Truck truck) {
        return new TruckResponse(truck.getPlate());
    }
}
