package io.jaylee.springcloud.service;

import io.jaylee.springcloud.model.User;
import io.jaylee.springcloud.model.UserDTO;
import io.jaylee.springcloud.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(UserDTO userDTO) {
        var user = User.builder()
                        .userId(userDTO.userId())
                        .lastName(userDTO.lastName())
                        .firstName(userDTO.firstName())
                        .email(userDTO.email())
                        .createdOn(ZonedDateTime.now())
                        .build();

        return userRepository.save(user).getUserId();
    }

    public UserDTO getUser(String userId) {
        var user = userRepository.findById(userId).orElse(new User());

        var userDTO = new UserDTO(user.getUserId(),
                                      user.getLastName(), user.getFirstName(), user.getEmail());

        return userDTO;
    }
}
