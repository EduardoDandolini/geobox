package dev.saas.geobox.service;

import dev.saas.geobox.dto.LoginRequest;

public interface LoginService {

    boolean login(LoginRequest dto);

}
