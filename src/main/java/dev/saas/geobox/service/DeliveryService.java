package dev.saas.geobox.service;


import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;

import java.util.List;

public interface DeliveryService {

    void save(DeliveryRequest request);

    List<DeliveryResponse> findAllLocations();
}
