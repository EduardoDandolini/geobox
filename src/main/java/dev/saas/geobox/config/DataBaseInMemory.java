package dev.saas.geobox.config;

import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.BoxRepository;
import dev.saas.geobox.repository.TruckRepository;
import dev.saas.geobox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBaseInMemory implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TruckRepository truckRepository;
    private final BoxRepository boxRepository;
    private final PasswordEncoder encoder;
    private static final String PASSWORD = "123456";

    @Override
    public void run(String... args) throws Exception {
//        if (userRepository.existsByEmail("admin@gmail.com")) {
//            return;
//        }

        userRepository.save(User.builder()
                        .name("teste12345@gmail.com")
                        .email("teste12345@gmail.com")
                        .password(encoder.encode(PASSWORD))
                .build());

//        createTrucks();
//        createBoxes();
    }

//    private void createTrucks() {
//        Truck truck1 = Truck.builder()
//                .plate("ABC 1234")
//                .truckType("Caminhão de Carga")
//                .build();
//
//        truckRepository.saveAll(Collections.singletonList(truck1));
//    }
//
//    private void createBoxes() {
//        Box box1 = Box.builder()
//                .boxNumber("BOX007")
//                .build();
//
//        boxRepository.saveAll(Collections.singletonList(box1));
//    }

}

