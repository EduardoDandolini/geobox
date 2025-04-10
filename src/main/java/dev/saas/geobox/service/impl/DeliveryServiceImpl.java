package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.models.Truck;
import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.DeliveryRepository;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository repository;
    private final TruckRepository truckRepository;
    private UserRepository userRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public Delivery save(DeliveryRequest request) {
        Point location = geometryFactory.createPoint(new Coordinate(request.longitude(), request.latitude()));
        Truck truck = truckRepository.findById(request.truckId()).orElseThrow(() -> new NotFoundException("Truck not found"));
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new NotFoundException("User not found"));
        return repository.save(Delivery.builder()
                .location(location)
                .truck(truck)
                .user(user)
                .build());
    }

    @Override
    public Page<DeliveryResponse> findAllLocations(Pageable pageable) {
        return repository.findAll(pageable)
                .map(delivery -> new DeliveryResponse(
                        delivery.getLocation().getY(),
                        delivery.getLocation().getX()
                ));
    }
}
