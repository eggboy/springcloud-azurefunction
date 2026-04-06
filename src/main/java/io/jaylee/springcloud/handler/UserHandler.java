package io.jaylee.springcloud.handler;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import io.jaylee.springcloud.model.UserDTO;
import io.jaylee.springcloud.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    @FunctionName("getUser")
    public HttpResponseMessage getUser(
            @HttpTrigger(name = "request",
                    methods = {HttpMethod.GET},
                    route = "user/{userId}",
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @BindingName("userId") String userId,
            ExecutionContext context) {
        context.getLogger().info("Getting user: " + userId);
        UserDTO user = userService.getUser(userId);
        return request.createResponseBuilder(HttpStatus.OK)
                .body(user)
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("createUser")
    public HttpResponseMessage createUser(
            @HttpTrigger(name = "request",
                    methods = {HttpMethod.POST},
                    route = "user",
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<UserDTO>> request,
            ExecutionContext context) {
        context.getLogger().info("Creating user");
        UserDTO userDTO = request.getBody()
                .orElse(null);
        if (userDTO == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Request body is required")
                    .build();
        }
        String userId = userService.saveUser(userDTO);
        return request.createResponseBuilder(HttpStatus.OK)
                .body(userId)
                .header("Content-Type", "application/json")
                .build();
    }
}
