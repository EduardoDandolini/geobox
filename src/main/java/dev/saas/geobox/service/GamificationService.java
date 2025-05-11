package dev.saas.geobox.service;

import dev.saas.geobox.dto.GamificationRequest;
import dev.saas.geobox.dto.GamificationResponse;

import java.util.List;

public interface GamificationService {

    void addPointsToUser(GamificationRequest request);

    List<GamificationResponse> findAllPoints();
}
