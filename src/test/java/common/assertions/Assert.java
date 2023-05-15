package common.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Assert extends AbstractAssert<Assert, Response> {

    public Assert(Response actual) {
        super(actual, Assert.class);
    }

    public static Assert assertThat(Response actual) {
        return new Assert(actual);
    }

    public Assert hasStatusCode(Integer statusCode) {
        Assertions.assertThat(actual.statusCode())
                .as("Response code isn't valid: " + actual.body().prettyPrint())
                .isEqualTo(statusCode);
        return this;
    }

    public Assert containsMessage(String field, String value) {
        var actualMessage = actual.body().jsonPath().getString(field);
        System.out.println("field = " + field);
        System.out.println("apiValue = " + actualMessage);
        System.out.println("expectedValue = " + value + "\n");
        Assertions.assertThat(actualMessage)
                .contains(value);
        return this;
    }

    public Assert containsField(String field) {
        var actualMessage = actual.body().jsonPath().getString("");
        Assertions.assertThat(actualMessage).contains(field);
        return this;
    }

    public Assert checkJsonSchema(String value) {
        actual.then().body(matchesJsonSchemaInClasspath(value));
        return this;
    }

    public Assert checkSize(String field, Integer value) {
        var actualMessage = actual.jsonPath().get(field + ".size()");
        System.out.println("field = " + field);
        System.out.println("actualSize = " + actualMessage);
        System.out.println("expectedSize = " + value + "\n");
        Assertions.assertThat(actualMessage).isEqualTo(value);
        return this;
    }
}
