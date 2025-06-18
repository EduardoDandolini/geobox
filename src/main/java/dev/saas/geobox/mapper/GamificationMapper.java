package dev.saas.geobox.mapper;

import dev.saas.geobox.dto.GamificationResponse;
import dev.saas.geobox.models.Gamification;

public class GamificationMapper {

    public static GamificationResponse toResponse(Gamification gamification) {
        return new GamificationResponse(gamification.getUser().getName(), gamification.getPoints());
    }
}
