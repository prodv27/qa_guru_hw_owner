package common.endpoints;

import common.utils.RestApiBuilder;
import io.restassured.response.Response;

import java.util.Map;

public class UsersApi {

    private UsersApi() {
    }

    public static Response getUsers() {
        return new RestApiBuilder()
                .build()
                .given()
                .when()
                .log().all()
                .get(Urls.USERS);
    }

    public static Response getUser(Integer userId) {
        return new RestApiBuilder()
                .build()
                .given()
                .pathParam("userId", userId)
                .when()
                .log().all()
                .get(Urls.USERS_ID);
    }

    public static Response postUsers(Map<String, Object> requestBody) {
        return new RestApiBuilder()
                .build()
                .given()
                .body(requestBody)
                .when()
                .log().all()
                .post(Urls.USERS);
    }
}
