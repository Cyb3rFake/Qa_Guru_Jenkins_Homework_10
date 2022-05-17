package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {


    final String firstName = "Jora",
            lastName = "Kirkorov",
            email = "test@yandex.ru",
            mobile = "9042901111",
            dateOfBirth = "02 Apr 2022",
            CurrentAddress = "45 h, Some st, Some city";

    @BeforeAll
    static void setUP() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    static void closeAll() throws InterruptedException {
        Thread.sleep(5000);
        closeWindow();
        closeWebDriver();
    }

    @Test
    void fillFieldsTest() {
        open("automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("2002");
        $("[aria-label$='December 17th, 2002']").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("img/jdun.jpg");
        $("#currentAddress").setValue(CurrentAddress);

        $("#state").click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Gurgaon")).click();

        sleep(2000);
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Jora Kirkorov"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("test@yandex.ru"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9042901111"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("17 December,2002"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths, Computer Science"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("Jdun.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("45 h, Some st, Some city"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Gurgaon"));

    }

}
