package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By fieldName = By.xpath(".//label[contains(text(), 'Имя')]/ancestor::*[contains(@class, 'input pr')]//*[contains(@name, 'name')]");//проверь локатор
    private final By fieldEmail = By.xpath(".//label[contains(text(), 'Email')]/ancestor::*[contains(@class, 'input pr')]//*[contains(@name, 'name')]");//проверь локатор
    private final By fieldPassword = By.xpath(".//label[contains(text(), 'Пароль')]/ancestor::*[contains(@class, 'input pr')]//*[contains(@name, 'Пароль')]");
    private final By registerButton = By.cssSelector("button.button_button__33qZ0");
    private final By linkLogin = By.xpath(".//*[@href='/login']");
    private final By invalidPassword = By.xpath(".//*[@class='input__error text_type_main-default']");
    @Step("Fill in name, email, password and click register button")
    public void fillInDetails(String name, String email, String password) {
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Click Login on the register page")
    public void clickOnLinkLogin() {
        driver.findElement(linkLogin).click();
    }
    @Step("Invalid password message is visible on the screen")
    public boolean isLoadMessageInvalidPassword() {
        driver.findElement(invalidPassword);
        return driver.findElement(invalidPassword).isDisplayed();
    }
}

