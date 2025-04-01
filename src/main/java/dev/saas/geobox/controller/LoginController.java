package dev.saas.geobox.controller;

import dev.saas.geobox.dto.LoginRequest;
import dev.saas.geobox.service.impl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<LoginRequest> login(LoginRequest dto){
        return loginService.login(dto) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
