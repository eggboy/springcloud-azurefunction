package io.jaylee.springcloud.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import io.jaylee.springcloud.model.UserDTO;
import io.jaylee.springcloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FunctionHandler {

    private final UserService userService;

    @FunctionName("createUserFunction")
    public HttpResponseMessage createUser(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<UserDTO>> request,
            ExecutionContext context) {

        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        context.getLogger()
               .info(request.getBody()
                             .map(UserDTO::toString).orElse(""));
        userService.saveUser(request.getBody().orElseThrow());

        return request
                .createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("getUserFunction")
    public HttpResponseMessage getUser(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {
        String userId = request.getQueryParameters().get("userId");

        if (userId == null || "".equals(userId)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        context.getLogger().info(userId);
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(userService.getUser(userId))
                .header("Content-Type", "application/json")
                .build();
    }
}
