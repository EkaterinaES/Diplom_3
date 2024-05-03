import apiclass.AuthUser;
import apiclass.StellarBurgersUser;
import apiclass.StellarBurgersUserImpl;
import config.UseDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.PersonalAccountPage;
import pageobject.RegisterPage;

import java.util.Random;

@RunWith(Parameterized.class)
public class SuccessRegisterTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private PersonalAccountPage personalAccountPage;
    private AuthUser authUser;
    private final String email;
    private final String password;
    private final String name;

    public SuccessRegisterTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new UseDriver().getWebDriver(false);
        homePage = new HomePage(driver);
        driver.get(Constants.PAGE_URL);//открыли сайт
        driver.manage().deleteAllCookies();//убрали куки
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        authUser = new AuthUser(email, password);
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {("Ivan" + new Random().nextInt(300)), (new Random().nextInt(300) + "user@yandex.ru"), (new Random().nextInt(10) + "12345")},//ГЗ: 6 символов
                {("Ivan" + new Random().nextInt(300)), (new Random().nextInt(300) + "user@yandex.ru"), (new Random().nextInt(10) + "123456")}, //шаг за ГЗ: 7 символов
        };
    }

    @DisplayName("Successful registering a new user")
    @Test
    public void registrationSuccess() {
        homePage.clickToButtonEnterToAccount();
        loginPage.register();
        registerPage.fillInDetails(name, email, password);
        loginPage.enteringDataOnLoginPage(email, password);
        homePage.clickToPersonalAccount();
        Assert.assertEquals(email, personalAccountPage.findValueLogin());
    }

    @DisplayName("Registering a new user")


    @After
    public void tearDown() {
        driver.quit();
        StellarBurgersUser userMetods = new StellarBurgersUserImpl(StellarBurgersUserImpl.requestSpecification);
        Response response = userMetods.authUser(authUser);
        StringBuilder accessTokenWithoutBearer = new StringBuilder(response.then().extract().body().path("accessToken"));
        String accessTokenForDelete = accessTokenWithoutBearer.delete(0, 7).toString();
        userMetods.deleteUser(accessTokenForDelete);
    }

}
