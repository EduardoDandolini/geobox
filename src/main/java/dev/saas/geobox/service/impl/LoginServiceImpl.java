package dev.saas.geobox.service.impl;

import dev.saas.geobox.config.security.TokenProvider;
import dev.saas.geobox.dto.LoginRequest;
import dev.saas.geobox.dto.TokenResponse;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(LoginRequest request) throws Exception {
        try {
            String encode = passwordEncoder.encode(request.password());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), encode)
            );
            return tokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new Exception("Credenciais inválidas: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Erro ao autenticar: " + e.getMessage());
        }
    }
}
