package dev.saas.geobox.service.impl;

import dev.saas.geobox.dto.UserRequest;
import dev.saas.geobox.models.Roles;
import dev.saas.geobox.models.User;
import dev.saas.geobox.repository.RolesRepository;
import dev.saas.geobox.repository.UserRepository;
import dev.saas.geobox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Override
    public void createNewUser(UserRequest userRequest) {
        userRepository.save(User.builder()
                        .name(userRequest.name())
                        .email(userRequest.email())
                        .password(userRequest.password())
                        .roles(findRolesById(userRequest.roleIds()))
                .build());
    }

    private List<Roles> findRolesById(List<String> ids) {
       return rolesRepository.findRolesById(ids).stream().toList();
    }

}
