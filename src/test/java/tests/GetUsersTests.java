package tests;

import common.assertions.Assert;
import common.endpoints.UsersApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUsersTests {

    static Integer userId;
    final String
            NAME = "Dmitry",
            JOB = "QA Automation";

    @BeforeAll
    public static void init() {
        userId = UsersApi.getUsers().jsonPath().get("data[0].id");
    }

    @Test
    @DisplayName("Успешный ответ на запрос получения списка пользователей")
    void getResponseOk() {
        Response response = UsersApi.getUsers();

        Assert.assertThat(response)
                .hasStatusCode(200);
    }

    @Test
    @DisplayName("Проверка количества объектов в data[]")
    void checkSize() {
        Response response = UsersApi.getUsers();

        Assert.assertThat(response)
                .checkSize("data", 6);
    }

    @Test
    @DisplayName("Проверка JSON схемы")
    void checkJsonSchema() {
        Response response = UsersApi.getUser(userId);

        Assert.assertThat(response)
                .checkJsonSchema("Users/schema_get_users_id.json");
    }

    @Test
    @DisplayName("Проверка идентичности значения входящего body-параметра id и data[].id")
    void checkIdsEqual() {
        Response response = UsersApi.getUser(userId);

        Assert.assertThat(response)
                .containsMessage("data.id", String.valueOf(userId));
    }

    @Test
    @DisplayName("Проверка присваивания идентификатора и даты создания новому пользователю")
    void checkResponseParametersOfNewUser() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", NAME);
        requestBody.put("job", JOB);
        Response response = UsersApi.postUsers(requestBody);

        Assert.assertThat(response)
                .containsMessage("name", NAME)
                .containsMessage("job", JOB)
                .containsField("id")
                .containsField("createdAt");
    }
}