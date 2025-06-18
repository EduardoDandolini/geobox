package dev.saas.geobox.controller;

import dev.saas.geobox.service.impl.PasswordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordServiceImpl passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) {
        passwordResetService.sendResetLink(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetPassword(@PathVariable String token, @RequestParam String novaSenha) {
        passwordResetService.resetPassword(token, novaSenha);
        return ResponseEntity.ok().build();
    }
}
