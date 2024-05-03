
import config.UseDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.Random;

public class UnsuccessRegisterTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private final String email = (new Random().nextInt(300) + "user@yandex.ru");
    private final String passwordLess6 = (new Random().nextInt(10) + "1234");
    private final String name = ("Ivan" + new Random().nextInt(300));

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new UseDriver().getWebDriver(false);
        homePage = new HomePage(driver);
        driver.get(Constants.PAGE_URL);//открыли сайт
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @DisplayName("Unsuccessful registering a new user with password < 6")//шаг за ГЗ: 5 символов
    @Test
    public void registrationWithPasswordLess6Unsuccess() {
        homePage.clickToButtonEnterToAccount();
        loginPage.register();
        registerPage.fillInDetails(name, email, passwordLess6);
        Assert.assertTrue(registerPage.isLoadMessageInvalidPassword());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
