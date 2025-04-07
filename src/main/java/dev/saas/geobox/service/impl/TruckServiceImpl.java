package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.mapper.TruckMapper;
import dev.saas.geobox.models.Truck;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TruckResponse> findAllTrucks() {
        return truckRepository.findAll().stream()
                .map(TruckMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TruckResponse update(Long id, TruckRequest request) {
        truckRepository.findById(id).orElseThrow(() -> new NotFoundException("Truck not found"));
        return TruckMapper.toResponse(truckRepository.save(Truck.builder()
                .truckType(request.truckType())
                .plate(request.plate())
                .build()));
    }

    @Override
    public TruckResponse delete(Long id) {
        return null;
    }
}
