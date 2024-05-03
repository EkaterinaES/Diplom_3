package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By linkLogin = By.xpath(".//*[@href='/login']");

    @Step("Click the Login on the password recovery page")
    public void clickOnLinkLogin() {
        driver.findElement(linkLogin).click();
    }
}

