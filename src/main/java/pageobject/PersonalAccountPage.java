package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    WebDriver driver;
    private final By fieldLogin = By.xpath(".//label[text()='Логин']/ancestor::*[contains(@class, 'input pr-6')]//*[@name='name']");
    private final By textProfile = By.xpath(".//*[@href='/account/profile']");
    private final By exit = By.xpath(".//*[@class = 'Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get the value of the login field")
    public String findValueLogin() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textProfile));
        return driver.findElement(fieldLogin).getAttribute("value");
    }

    @Step("Click the Exit on personal account page")
    public void exitFromAccount() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textProfile));
        driver.findElement(exit).click();
    }
}
