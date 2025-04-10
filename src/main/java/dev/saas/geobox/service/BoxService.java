package dev.saas.geobox.service;

import dev.saas.geobox.dto.BoxRequest;
import dev.saas.geobox.dto.BoxResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoxService {

    BoxResponse save(BoxRequest request);

    BoxResponse findById(Long id);

    Page<BoxResponse> findAllBoxes(Pageable pageable);

    BoxResponse update(Long id, BoxRequest request);

    void delete(Long id);
}
