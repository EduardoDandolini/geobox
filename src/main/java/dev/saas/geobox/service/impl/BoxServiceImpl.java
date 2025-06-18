package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.BoxRequest;
import dev.saas.geobox.dto.BoxResponse;
import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.mapper.BoxMapper;
import dev.saas.geobox.models.Box;
import dev.saas.geobox.repository.BoxRepository;
import dev.saas.geobox.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;

    @Override
    public BoxResponse save(BoxRequest request) {
        Box box = boxRepository.save(Box.builder()
                .boxNumber(request.boxNumber())
                .build());
        return BoxMapper.toResponse(box);
    }

    @Override
    public BoxResponse findById(Long id) {
        Box box = boxRepository.findById(id).orElseThrow(() -> new NotFoundException("Box not found"));
        return BoxMapper.toResponse(box);
    }

    @Override
    public List<BoxResponse> findAllBoxes() {
        return boxRepository.findAll()
                .stream()
                .map(BoxMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BoxResponse update(Long id, BoxRequest request) {
        boxRepository.findById(id).orElseThrow(() -> new NotFoundException("Box not found"));
        return BoxMapper.toResponse(boxRepository.save(Box.builder()
                .boxNumber(request.boxNumber())
                .build()));
    }

    @Override
    public void delete(Long id) {
        boxRepository.findById(id).orElseThrow(() -> new NotFoundException("Box not found"));
        boxRepository.deleteById(id);
    }
}
