package dev.saas.geobox.service;


import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.models.Delivery;

import java.util.List;

public interface DeliveryService {

    Delivery save(Double latitude, Double longitude);

    List<DeliveryResponse> findAllLocations();
}
