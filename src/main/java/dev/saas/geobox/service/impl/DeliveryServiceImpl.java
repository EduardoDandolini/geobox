package dev.saas.geobox.service.impl;

import dev.saas.geobox.models.Delivery;
import dev.saas.geobox.repository.DeliveryRepository;
import dev.saas.geobox.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

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
}
