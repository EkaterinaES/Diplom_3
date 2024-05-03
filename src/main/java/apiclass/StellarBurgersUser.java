package apiclass;

import io.restassured.response.Response;

public interface StellarBurgersUser {
    Response createUser(User user);

    void deleteUser(String accessToken);

    Response authUser(AuthUser authUser);
}
