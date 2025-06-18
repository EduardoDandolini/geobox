package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.GamificationRequest;
import dev.saas.geobox.dto.GamificationResponse;
import dev.saas.geobox.mapper.GamificationMapper;
import dev.saas.geobox.models.Gamification;
import dev.saas.geobox.repository.GamificationRepository;
import dev.saas.geobox.service.GamificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamificationServiceImpl implements GamificationService {

    private final GamificationRepository repository;

    @Override
    public void addPointsToUser(GamificationRequest request) {
        Gamification existingGamification = repository.findByUser(request.user())
                .orElse(null);

        if (Objects.nonNull(existingGamification)) {
            existingGamification.setPoints(existingGamification.getPoints() + 1.0);
            repository.save(existingGamification);
        } else {
            repository.save(Gamification.builder()
                    .delivery(request.delivery())
                    .user(request.user())
                    .points(1.0)
                    .build());
        }
    }

    @Override
    public List<GamificationResponse> findAllPoints() {
        return repository.findAllPoints().stream()
                .map(GamificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
