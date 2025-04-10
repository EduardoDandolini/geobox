package dev.saas.geobox.service;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TruckService {

    TruckResponse save(TruckRequest request);

    TruckResponse findById(Long id);

    Page<TruckResponse> findAllTrucks(Pageable pageable);

    TruckResponse update(Long id, TruckRequest request);

    void delete(Long id);
}
