package dev.saas.geobox.service;


import dev.saas.geobox.models.Delivery;

public interface DeliveryService {

    Delivery save(Double latitude, Double longitude);
}
