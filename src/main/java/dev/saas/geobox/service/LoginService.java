package dev.saas.geobox.service;

import dev.saas.geobox.dto.LoginDTO;

public interface LoginService {

    boolean login(LoginDTO dto);

}
