package dev.saas.geobox.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BoxRequest(@NotEmpty(message = "Box number is required")
                         @NotNull(message = "Box number is required") String boxNumber) {
}
