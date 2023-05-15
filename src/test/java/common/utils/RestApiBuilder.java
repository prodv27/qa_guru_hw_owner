package common.utils;

import config.Env;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {

    private final RequestSpecification requestSpecification;

    public RestApiBuilder(String url) {
        requestSpecification = given()
                .baseUri(url)
                .config(RestAssuredConfig
                        .config()
                        .httpClient(HttpClientConfig
                                .httpClientConfig()
                                .setParam("http.connection.timeout", 5000)
                        )
                )
                .contentType("application/json")
                .relaxedHTTPSValidation();
    }

    public RestApiBuilder() {
        this(Env.API.CONFIG.url());
    }

    public RequestSpecification build() {
        return requestSpecification;
    }
}
