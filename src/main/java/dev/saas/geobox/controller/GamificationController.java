package dev.saas.geobox.controller;

import dev.saas.geobox.dto.GamificationResponse;
import dev.saas.geobox.service.impl.GamificationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/gamification")
@RequiredArgsConstructor
@Tag(name = "Gamificação", description = "Dados de gamificação do usuário")
public class GamificationController {

    private final GamificationServiceImpl gamificationService;

    @GetMapping("/points")
    public ResponseEntity<List<GamificationResponse>> findAllPoints() {
        return ResponseEntity.ok(gamificationService.findAllPoints());
    }
}
