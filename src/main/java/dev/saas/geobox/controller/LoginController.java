package dev.saas.geobox.controller;

import dev.saas.geobox.dto.LoginRequest;
import dev.saas.geobox.service.impl.LoginServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/login")
@RequiredArgsConstructor
@Tag(name = "Login", description = "Login do usuario")
public class LoginController {

    private final LoginServiceImpl loginService;

    @PostMapping
    @Operation(summary = "Login", description = "Realiza o login do usuario")
    public ResponseEntity<LoginRequest> login(LoginRequest dto){
        return loginService.login(dto) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
