package dev.saas.geobox.controller;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
@Tag(name = "Delivery", description = "Gerenciamento de entregas")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    @Operation(summary = "Salvar entrega", description = "Cria uma nova entrega e retorna os detalhes salvos")
    public ResponseEntity<DeliveryResponse> save(@RequestBody DeliveryRequest request) {
        Delivery savedDelivery = deliveryService.save(request);
        return ResponseEntity.ok(DeliveryResponse.fromEntity(savedDelivery));
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryResponse>> findAllLocations(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(deliveryService.findAllLocations(pageable));
    }

}
