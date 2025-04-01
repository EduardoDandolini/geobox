package dev.saas.geobox.service;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;

public interface TruckService {

    TruckResponse save(TruckRequest request);

    TruckResponse findById(Long id);

    TruckResponse findAllTrucks();
}
