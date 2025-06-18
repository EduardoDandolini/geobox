package dev.saas.geobox.dto;

import jakarta.validation.constraints.NotNull;

public record DeliveryRequest(@NotNull(message = "Latitude is required") Double latitude,
                              @NotNull(message = "Longitude is required") Double longitude,
                              @NotNull(message = "Truck ID is required") String truckPlate,
                              @NotNull(message = "User ID is required") Long userId,
                              @NotNull(message = "Box ID is required") String boxNumber,
                              @NotNull(message = "Status is required") Long status) {
}
