package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.TruckRequest;
import dev.saas.geobox.dto.TruckResponse;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.mapper.TruckMapper;
import dev.saas.geobox.models.Truck;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Truck truck = truckRepository.findById(id).orElseThrow(() -> new NotFoundException("Truck not found"));
        return TruckMapper.toResponse(truck);
    }

    @Override
    public Page<TruckResponse> findAllTrucks(Pageable pageable) {
        return truckRepository.findAll(pageable)
                .map(TruckMapper::toResponse);
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
    public void delete(Long id) {
        truckRepository.findById(id).orElseThrow(() -> new NotFoundException("Truck not found"));
        truckRepository.deleteById(id);
    }
}
