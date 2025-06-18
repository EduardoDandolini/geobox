package dev.saas.geobox.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TruckRequest(@NotEmpty(message = "Plate is required")
                           @NotNull(message = "Plate is required")
                           String plate,
                           @NotEmpty(message = "Truck type is required")
                           @NotNull(message = "Truck type is required")
                           String truckType) {

}
