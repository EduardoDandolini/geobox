package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;
import dev.saas.geobox.mapper.TruckMapper;
import dev.saas.geobox.models.Truck;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    @Override
    @Transactional
    public TruckResponse save(TruckRequest request) {
        Truck truck = truckRepository.save(Truck.builder()
                .truckType(request.truckType())
                .plate(request.plate())
                .build());

        return TruckMapper.toResponse(truck);
    }

    @Override
    public TruckResponse findById(Long id) {
        Truck truck = truckRepository.findById(id).orElseThrow(() -> new RuntimeException("Truck not found"));
        return TruckMapper.toResponse(truck);
    }

    @Override
    public TruckResponse findAllTrucks() {
        return null;
    }
}
