package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.repository.DeliveryRepository;
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
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public Delivery save(Double latitude, Double longitude) {
        Point location = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        return repository.save(Delivery.builder()
                .location(location)
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
