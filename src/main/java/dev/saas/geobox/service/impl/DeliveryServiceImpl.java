package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.models.Box;
import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.models.Truck;
import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.BoxRepository;
import dev.saas.geobox.repository.DeliveryRepository;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository repository;
    private final TruckRepository truckRepository;
    private final UserRepository userRepository;
    private final BoxRepository boxRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public void save(DeliveryRequest request) {
        Point location = geometryFactory.createPoint(new Coordinate(request.longitude(), request.latitude()));
        Truck truck = truckRepository.findByPlate(request.truckPlate()).orElseThrow(() -> new NotFoundException("Truck not found"));
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new NotFoundException("User not found"));
        Box box = boxRepository.findByBoxNumber(request.boxNumber()).orElseThrow(() -> new NotFoundException("Box not found"));
        repository.save(Delivery.builder()
                .location(location)
                .truck(truck)
                .user(user)
                .box(box)
                .build());
    }

    @Override
    public List<DeliveryResponse> findAllLocations() {
        return repository.findAll().stream()
                .map(delivery -> new DeliveryResponse(
                        delivery.getLocation().getY(),
                        delivery.getLocation().getX()
                )).collect(Collectors.toList());
    }
}
