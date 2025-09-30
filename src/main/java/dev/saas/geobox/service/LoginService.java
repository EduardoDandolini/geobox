package dev.saas.geobox.service;

import dev.saas.geobox.dto.JwtResponse;
import dev.saas.geobox.dto.LoginRequest;

public interface LoginService {

    JwtResponse login(LoginRequest dto) throws Exception;

}
