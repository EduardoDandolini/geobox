package dev.saas.geobox.controller;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryResponse> save(@RequestBody DeliveryRequest request) {
        Delivery savedDelivery = deliveryService.save(request);
        return ResponseEntity.ok(DeliveryResponse.fromEntity(savedDelivery));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> findAllLocations() {
        return ResponseEntity.ok(deliveryService.findAllLocations());
    }
}
