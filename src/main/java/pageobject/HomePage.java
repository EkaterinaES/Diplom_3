package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By buttonEnterToAccount = By.xpath(".//*[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By textMakeBurger = By.xpath(".//*[@class = 'text text_type_main-large mb-5 mt-10']");
    private final By buttonPersonalAccountOnHomePage = By.xpath(".//*[@href='/account']");
    private final By designer = By.xpath(".//*[@class = 'AppHeader_header__linkText__3q_va ml-2' and text() ='Конструктор']");
    private final By buns = By.xpath(".//*[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By sauces = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillings = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By bunSection = By.xpath(".//*[@class= 'text text_type_main-medium mb-6 mt-10' and text()='Булки']");
    private final By saucesSection = By.xpath(".//*[@class= 'text text_type_main-medium mb-6 mt-10' and text()='Соусы']");
    private final By fillingsSection = By.xpath(".//*[@class= 'text text_type_main-medium mb-6 mt-10' and text()='Начинки']");
    private final By logo = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");

    public void clickToButtonEnterToAccount() {
        driver.findElement(buttonEnterToAccount).click();
    }

    @Step("Click the personal account button")
    public void clickToPersonalAccount() {//клик на кнопку "Личный кабинет"
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textMakeBurger));
        driver.findElement(buttonPersonalAccountOnHomePage).click();
    }

    @Step("Click the designer on the home page")
    public boolean clickOnDesigner() {
        driver.findElement(designer).click();
        return driver.findElement(textMakeBurger).isDisplayed();
    }

    @Step("Click the logo on the home page")
    public boolean clickOnLogo() {
        driver.findElement(logo).click();
        return driver.findElement(textMakeBurger).isDisplayed();
    }

    @Step("Click on the logon button on the home page")
    public void clickOnTheLoginButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textMakeBurger));
        driver.findElement(buttonEnterToAccount).click();
    }

    @Step("The section of the buns is visible on the display")
    public boolean isLoadBuns() {
        driver.findElement(buns);
        return driver.findElement(bunSection).isDisplayed();

    }

    @Step("The section of the sauces is visible on the display")
    public boolean isLoadSauces() {
        driver.findElement(sauces);
        return driver.findElement(saucesSection).isDisplayed();

    }

    @Step("The section of the fillings is visible on the display")
    public boolean isLoadFillings() {
        driver.findElement(fillings);
        return driver.findElement(fillingsSection).isDisplayed();

    }

}
