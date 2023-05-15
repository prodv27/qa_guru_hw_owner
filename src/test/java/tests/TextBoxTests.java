package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    @Test
    void FillTextBox() {
        open("/text-box");
        $("#userName").setValue("Dmitry");
        $("#userEmail").setValue("dmitry@gmail.com");
        $("#currentAddress").setValue("Izhevsk");
        $("#permanentAddress").setValue("Izhevsk");
        $("#submit").click();
        $("p#name").shouldHave(text("Name:Dmitry"));
        $("p#email").shouldHave(text("Email:dmitry@gmail.com"));
        $("p#currentAddress").shouldHave(text("Current Address :Izhevsk"));
        $("p#permanentAddress").shouldHave(text("Permananet Address :Izhevsk"));
    }
}
