package dev.saas.geobox.dto;

import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.models.User;

public record GamificationRequest(User user, Delivery delivery) {
}
