package dev.saas.geobox.service;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;

import java.util.List;

public interface TruckService {

    TruckResponse save(TruckRequest request);

    TruckResponse findById(Long id);

    List<TruckResponse> findAllTrucks();

    TruckResponse update(Long id, TruckRequest request);

    TruckResponse delete(Long id);
}
