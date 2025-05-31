package dev.saas.geobox.service.impl;

import dev.saas.geobox.config.TokenProvider;
import dev.saas.geobox.dto.LoginRequest;
import dev.saas.geobox.dto.TokenResponse;
import dev.saas.geobox.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final HttpSession session;

    @Override
    public TokenResponse login(LoginRequest request) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            session.setAttribute("user", authentication.getPrincipal());
            return tokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new Exception("Credenciais inválidas: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Erro ao autenticar: " + e.getMessage());
        }
    }
}
