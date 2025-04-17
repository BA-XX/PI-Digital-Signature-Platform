package aloui.bilal.userauthservice.service;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;

public class AuthService implements HttpService {
    @Override
    public void routing(HttpRules rules) {
        rules.
                get("/", (req, res)-> {
                    res.send("Hello From Auth");
                });
    }
}
