package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.DeliveryRequest;
import dev.saas.geobox.dto.DeliveryResponse;
import dev.saas.geobox.dto.GamificationRequest;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.mapper.DeliveryMapper;
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
    private final GamificationServiceImpl gamificationService;

    @Override
    public void save(DeliveryRequest request) {
        Truck truck = validateTruck(request.truckPlate());
        User user = validateUser(request.userId());
        Box box = validateBox(request.boxNumber());
        Delivery delivery = createDelivery(request, truck, user, box);

        GamificationRequest gamificationRequest = buildGamificationRequest(user, delivery);
        gamificationService.addPointsToUser(gamificationRequest);
    }

    private Truck validateTruck(String truckPlate) {
        return truckRepository.findByPlate(truckPlate)
                .orElseThrow(() -> new NotFoundException("Truck not found"));
    }

    private User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Box validateBox(String boxNumber) {
        return boxRepository.findByBoxNumber(boxNumber)
                .orElseThrow(() -> new NotFoundException("Box not found"));
    }

    private Delivery createDelivery(DeliveryRequest request, Truck truck, User user, Box box) {
        Point location = geometryFactory.createPoint(new Coordinate(request.longitude(), request.latitude()));
        return repository.save(Delivery.builder()
                .location(location)
                .truck(truck)
                .user(user)
                .box(box)
                .build());
    }

    private GamificationRequest buildGamificationRequest(User user, Delivery delivery) {
        return new GamificationRequest(user, delivery);
    }

    @Override
    public List<DeliveryResponse> findAllLocations() {
        return repository.findAll().stream()
                .map(DeliveryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Delivery findDeliveryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Delivery not found"));
    }

}
