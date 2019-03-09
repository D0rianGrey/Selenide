import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

    private By heading = By.xpath("//div[@class='setup-wrapper']//h1");
    private By userNameField = By.xpath(".//*[@id='user_login']");
    private By emailField = By.xpath(".//*[@id='user_email']");
    private By passwordField = By.xpath(".//*[@id='user_password']");
    private By signUpButton = By.xpath(".//*[@id='signup_button']");
    private By mainError = By.xpath(".//*[@id='user_login']/ancestor::dl/preceding-sibling::div[contains(@class, 'flash-error')]");
    private By userNameError = By.xpath("//input[@id='user_login']/ancestor::dd/following-sibling::dd");
    private By emailError = By.xpath(".//*[@id='user_email']/ancestor::dd/following-sibling::dd");
    private By passwordError = By.xpath(".//*[@id='user_password']/ancestor::dd/following-sibling::dd");

    public SignUpPage open(){
        Selenide.open("/");
        return this;
    }

    public SignUpPage typeUserName(String username){
        $(userNameField).sendKeys(username);
        return this;
    }

    public SignUpPage typePassword(String password){
        $(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeEmail(String email){
        $(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage registerWithInvalidCreds(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        $(signUpButton).click();
        return new SignUpPage();
    }

    public String getHeadingText(){
        return $(heading).getText();
    }

    public String getMainErrorText(){
        return $(mainError).getText();
    }

    public String getUsernameErrorText(){
        return $(userNameError).getText();
    }

    public String getPasswrodErrorText(){
        return $(passwordError).getText();
    }

    public String getEmailErrorText(){
        return $(emailError).getText();
    }

}

