package Selenide;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.*;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.$;

public class SignUpTest {
    private SignUpPage page;

    @BeforeClass
    public static void setUp() {
        baseUrl = "https://www.spotify.com/";
        browser = "chrome";
    }

    @Test
    public void typeInvalidYear() {
        page = new SignUpPage();
        page.open()
                .setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        page.getError("Please enter a valid year.").shouldBe(Condition.visible);
        page.getError("When were you born").shouldNotBe(Condition.visible);
    }

    @Test
    public void typeInvalidEmail() {
        page = new SignUpPage();
        page.open()
                .typeEmail("test@mail.test")
                .typeConfirmEmail("wrong@mail.test")
                .typeName("Testname")
                .clickSignUpButton();
        page.getError("Email address doesn't match.").shouldBe(Condition.visible);

    }

    @Test
    public void signUpWithEmptyPassword() {
        page = new SignUpPage();
        page.open()
                .typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("testname")
                .clickSignUpButton();
        page.getError("Please choose a password.").shouldBe(Condition.visible);
    }

    @Test
    public void typeInvalidValues() {
        page = new SignUpPage();
        page.open()
                .typeEmail("testmail")
                .typeConfirmEmail("wrong@test.mail")
                .typePassword("qwerty!123")
                .typeName("Name")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();
        page.getErrors().shouldHave(CollectionCondition.size(6));
        page.getErrorByNumber(3).shouldHave(Condition.text("Please enter your birth month."));

    }
}