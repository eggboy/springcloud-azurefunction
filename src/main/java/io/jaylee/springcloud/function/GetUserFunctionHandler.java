package io.jaylee.springcloud.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import io.jaylee.springcloud.model.UserDTO;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class GetUserFunctionHandler extends FunctionInvoker<String, UserDTO> {
    @FunctionName("getUserFunction")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {
        String userId = request.getQueryParameters().get("userId");

        context.getLogger().info(userId);
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(userId, context))
                .header("Content-Type", "application/json")
                .build();
    }
}
