package dev.saas.geobox.service;


import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.models.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {

    Delivery save(DeliveryRequest request);

    Page<DeliveryResponse> findAllLocations(Pageable pageable);
}
