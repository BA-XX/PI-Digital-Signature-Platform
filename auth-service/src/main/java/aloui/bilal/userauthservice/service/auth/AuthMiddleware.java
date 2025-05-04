package aloui.bilal.userauthservice.service.auth;

import aloui.bilal.userauthservice.security.JwtUtil;
import io.helidon.http.HeaderNames;
import io.helidon.webserver.http.Handler;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.Json;

public class AuthMiddleware implements Handler {

    @Override
    public void handle(ServerRequest req, ServerResponse res) throws Exception {
        String authHeader = req.headers().first(HeaderNames.create("Authorization")).orElse(null);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.status(401).send(Json.createObjectBuilder()
                    .add("status", "error")
                    .add("message", "Unauthorized: Missing or invalid Authorization header")
                    .build());
            return;
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix

        boolean isValid = JwtUtil.verifyToken(token);

        if (!isValid) {
            res.status(401).send(Json.createObjectBuilder()
                    .add("status", "error")
                    .add("message", "Unauthorized: Invalid or expired token")
                    .build());
            return;
        }

        res.next(); // Proceed to next handler
    }
}