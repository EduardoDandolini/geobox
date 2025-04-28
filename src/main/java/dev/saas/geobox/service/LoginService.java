package dev.saas.geobox.service;

import dev.saas.geobox.dto.LoginRequest;
import dev.saas.geobox.dto.TokenResponse;

public interface LoginService {

    TokenResponse login(LoginRequest dto) throws Exception;

}
