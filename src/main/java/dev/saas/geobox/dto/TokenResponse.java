package dev.saas.geobox.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String token, Long expiresIn, String username, Long userId, Long role) {

}
