package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.LoginDTO;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository repository;

    public boolean login(LoginDTO dto) {
       return repository.existsByEmailAndPassword(dto.email(), dto.password());
    }

}
