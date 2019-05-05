package Selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {

    private By emailField = By.xpath("//*[@id=\"register-email\"]");
    private By confirmEmailField = By.xpath("//*[@id=\"register-confirm-email\"]");
    private By passwordField = By.xpath("//*[@id=\"register-password\"]");
    private By nameField = By.xpath("//*[@id=\"register-displayname\"]");
    private By monthDropDown = By.xpath("//*[@id=\"register-dob-month\"]");
    private String monthDropDownOption = "//select[@id='register-dob-month']/option[text()='%s']";
    private By dayField = By.xpath("//*[@id=\"register-dob-day\"]");
    private By yearField = By.xpath("//*[@id=\"register-dob-year\"]");
    private String sexRadioButton = "//li[@id='li-gender']//label[normalize-space()='Male']/input";
    private By shareCheckbox = By.xpath("//*[@id=\"register-thirdparty\"]");
    private By registerButton = By.xpath("//*[@id=\"register-button-email-submit\"]");
    private By errorLabel = By.xpath("//label[@class=\"has-error\" and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";


    public SignUpPage open() {
        Selenide.open("us/signup/");
        return this;
    }

    public SignUpPage typeEmail(String email) {
        $(emailField).setValue(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        $(confirmEmailField).setValue(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        $(passwordField).setValue(password);
        return this;
    }

    public SignUpPage typeName(String name) {
        $(nameField).setValue(name);
        return this;
    }

    public SignUpPage setMonth(String month) {
        $(monthDropDown).selectOption(month);
        return this;
    }

    public SignUpPage typeDay(String day) {
        $(dayField).setValue(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        $(yearField).setValue(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        $(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) { // есть true - чекаем, если false - то анчекаем
        $(shareCheckbox).setSelected(value);
        return this;
    }

    public void clickSignUpButton() {
        $(registerButton).click();
    }

    public ElementsCollection getErrors() {
        return $$(errorLabel);
    }

    public SelenideElement getErrorByNumber(int number) {
        return getErrors().get(number - 1);
    }

    public SelenideElement getError(String message) {
        return $(By.xpath(String.format(errorByText, message)));
    }


}
