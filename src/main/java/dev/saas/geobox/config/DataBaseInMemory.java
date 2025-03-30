package dev.saas.geobox.config;

import dev.saas.geobox.enuns.Roles;
import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBaseInMemory implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private static final String PASSWORD = "123456";

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByEmail("admin")) {
            return;
        }

        userRepository.save(User.builder()
                        .role(Roles.ADMIN.getId())
                        .name("admin")
                        .email("admin")
                        .password(encoder.encode(PASSWORD))
                .build());
    }

}
