package aloui.bilal.userauthservice.service.auth;

import aloui.bilal.userauthservice.dao.ILoginHistoryDao;
import aloui.bilal.userauthservice.dao.ILoginHistoryDaoImpl;
import aloui.bilal.userauthservice.dao.IUserDao;
import aloui.bilal.userauthservice.dao.UserDaoImpl;
import aloui.bilal.userauthservice.service.auth.handlers.*;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;

import java.sql.SQLException;

public class AuthService implements HttpService {

    private IUserDao userDao;
    private ILoginHistoryDao loginHistoryDao;

    {
        try {
            userDao = new UserDaoImpl();
            loginHistoryDao = new ILoginHistoryDaoImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void routing(HttpRules rules) {
        rules
                .post("/register", new RegisterHandler(userDao))
                .post("/login", new LoginHandler(userDao))

                .get("/profile", new AuthMiddleware(), new ProfileHandler(userDao))
                .get("/login/history", new AuthMiddleware(), new LoginHistoryHandler(loginHistoryDao))

                .put("/update", new AuthMiddleware(), new UpdateProfileHandler(userDao))
                .put("/update-password", new AuthMiddleware(), new UpdatePasswordHandler(userDao));

    }

}
