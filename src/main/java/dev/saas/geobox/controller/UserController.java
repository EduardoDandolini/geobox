package dev.saas.geobox.controller;

import dev.saas.geobox.dto.UserRequest;
import dev.saas.geobox.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/save")
    public ResponseEntity<Void> createNewUser(@RequestBody UserRequest request) {
       userService.createNewUser(request);
       return ResponseEntity.ok().build();
    }

}
