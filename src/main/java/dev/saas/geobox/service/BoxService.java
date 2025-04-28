package dev.saas.geobox.service;

import dev.saas.geobox.dto.BoxRequest;
import dev.saas.geobox.dto.BoxResponse;

import java.util.List;

public interface BoxService {

    BoxResponse save(BoxRequest request);

    BoxResponse findById(Long id);

    List<BoxResponse> findAllBoxes();

    BoxResponse update(Long id, BoxRequest request);

    void delete(Long id);
}
