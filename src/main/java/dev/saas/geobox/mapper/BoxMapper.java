package dev.saas.geobox.mapper;

import dev.saas.geobox.dto.BoxResponse;
import dev.saas.geobox.models.Box;

public class BoxMapper {

    public static BoxResponse toResponse(Box box) {
        return new BoxResponse(box.getBoxNumber());
    }
}
