package io.jaylee.springcloud.function;

import io.jaylee.springcloud.model.UserDTO;
import io.jaylee.springcloud.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FunctionRegistration {

    private UserService userService;

    public FunctionRegistration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public Function<UserDTO, String> createUserFunction() {
        return user -> userService.saveUser(user);
    }

    @Bean
    public Function<String, UserDTO> getUserFunction() {
        return userId -> userService.getUser(userId);
    }
}