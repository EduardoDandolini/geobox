package dev.saas.geobox.controller;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
@Tag(name = "Delivery", description = "Gerenciamento de entregas")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/save")
    @Operation(summary = "Salvar entrega", description = "Cria uma nova entrega e retorna os detalhes salvos")
    public void save(@RequestBody DeliveryRequest request) {
        try {
            deliveryService.save(request);
            ResponseEntity.ok();
        } catch (Exception e) {
            ResponseEntity.badRequest();
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<List<DeliveryResponse>> findAllLocations() {
        return ResponseEntity.ok(deliveryService.findAllLocations());
    }

}
