package dev.saas.geobox.service.impl;

import dev.saas.geobox.exception.NotFoundException;
import dev.saas.geobox.models.PasswordResetToken;
import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.PasswordResetTokenRepository;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendResetLink(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = tokenRepository.findByUser(user)
                .orElse(PasswordResetToken.builder()
                        .user(user)
                        .build());

        resetToken.setToken(token);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1));
        resetToken.setUsed(false);
        resetToken.setCreatedAt(LocalDateTime.now());
        resetToken.setModifiedAt(null);

        tokenRepository.save(resetToken);

        String link = "http://localhost:5173/redefinir-senha?token=" + token;
        sendEmail(user.getEmail(), link);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token inválido"));

        if (tokenEntity.isUsed() || tokenEntity.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado ou já utilizado");
        }

        User user = tokenEntity.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
        tokenEntity.setUsed(true);
        tokenRepository.save(tokenEntity);
    }

    private void sendEmail(String destination, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destination);
        message.setSubject("Recuperação de senha");
        message.setText("Clique no link para redefinir sua senha: " + link);
        mailSender.send(message);
    }
}
