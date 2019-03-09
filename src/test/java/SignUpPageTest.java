import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignUpPageTest {


    static SignUpPage signUpPage;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\bin\\geckodriver.exe");
        baseUrl = "https://github.com/join";
        signUpPage = new SignUpPage();
        browser = "marionette";
    }

    @Test
    public void signUpWithShortPass(){
        SignUpPage sp = signUpPage.typePassword("qwe");
        String error = sp.getPasswrodErrorText();
        Assert.assertEquals("Password is too short (minimum is 7 characters) and needs at least one number", error);
    }

    @Test
    public void signUpReservedUsernameTest(){
        SignUpPage sp = signUpPage.typeUserName("username");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username is a reserved word", error);
    }

    @Test
    public void signUpTakenUsername(){
        SignUpPage sp = signUpPage.typeUserName("user");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username is already taken", error);
    }

    @Test
    public void getHeadingTest(){
        String heading = signUpPage.getHeadingText();
        Assert.assertEquals("Join GitHub", heading);
    }
}
