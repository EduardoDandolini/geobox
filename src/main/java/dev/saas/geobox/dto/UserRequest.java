package dev.saas.geobox.dto;

import java.util.List;

public record UserRequest(String name, String email, String password, List<String> roleIds) {

}
