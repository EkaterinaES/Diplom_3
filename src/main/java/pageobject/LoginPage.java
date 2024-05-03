package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By buttonEnter = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By linkRegister = By.xpath(".//*[@href='/register']");
    private final By textEnter = By.xpath(".//*[text()='Вход']");
    private final By fieldEmail = By.xpath(".//label[contains(text(), 'Email')]/ancestor::*[contains(@class, 'input pr')]//*[contains(@name, 'name')]");
    private final By fieldPassword = By.xpath(".//label[contains(text(), 'Пароль')]/ancestor::*[contains(@class, 'input pr')]//*[contains(@name, 'Пароль')]");

    private final By linkPasswordRecovery = By.xpath(".//*[@href='/forgot-password']");

    @Step("Click the register on the login page")
    public void register() {
        WebElement element = driver.findElement(linkRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(linkRegister).click();
    }

    @Step("Fill in the email and the password fields")
    public void enteringDataOnLoginPage(String email, String password) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(fieldEmail));
        driver.findElement(fieldEmail).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(fieldEmail));
        driver.findElement(fieldEmail).clear();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(fieldEmail));
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).clear();
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonEnter).click();
    }

    @Step("Click the password recovery")
    public void clickLinkPasswordRecovery() {
        WebElement element = driver.findElement(linkPasswordRecovery);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(linkPasswordRecovery).click();
    }

    @Step("The text Enter is visible on the display")
    public boolean getTextEnter() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textEnter));
        return driver.findElement(textEnter).isDisplayed();
    }

}
