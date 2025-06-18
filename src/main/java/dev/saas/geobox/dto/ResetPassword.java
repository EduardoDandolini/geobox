package dev.saas.geobox.dto;

public record ResetPassword(String token, String newPassword) {
}
