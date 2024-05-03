import apiclass.AuthUser;
import apiclass.StellarBurgersUser;
import apiclass.StellarBurgersUserImpl;
import apiclass.User;
import config.UseDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.*;

import java.util.Random;

public class LoginAccountTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private PersonalAccountPage personalAccountPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private AuthUser authUser;
    private StellarBurgersUser userMetods;
    private final String email = (new Random().nextInt(300) + "user@yandex.ru");
    private final String password = (new Random().nextInt(300) + "654765");
    private final String name = ("Ivan" + new Random().nextInt(300));

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new UseDriver().getWebDriver(false);
        homePage = new HomePage(driver);
        driver.get(Constants.PAGE_URL);//открыли сайт
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        authUser = new AuthUser(email, password);
        User user = new User(email, password, name);
        userMetods = new StellarBurgersUserImpl(StellarBurgersUserImpl.requestSpecification);
        userMetods.createUser(user);//создали пользователя
    }

    @DisplayName("Login using the login button on home page")
    @Test
    public void loginButtonOnHomePAge() {
        homePage.clickOnTheLoginButton();//нажали войти на главно странице
        loginPage.enteringDataOnLoginPage(email, password);//вели данные
        homePage.clickToPersonalAccount();//зашли в личный кабинет проверить что отражается нужный логин
        Assert.assertEquals(email, personalAccountPage.findValueLogin());
    }

    @DisplayName("Login using the personal account button")
    @Test
    public void loginButtonPersonalAccount() {
        homePage.clickToPersonalAccount();
        loginPage.enteringDataOnLoginPage(email, password);
        homePage.clickToPersonalAccount();
        Assert.assertEquals(email, personalAccountPage.findValueLogin());
    }

    @DisplayName("Login using the button in the registration form")
    @Test
    public void loginButtonLoginOnRegistrationPage() {
        homePage.clickToButtonEnterToAccount();
        loginPage.register();
        registerPage.clickOnLinkLogin();
        loginPage.enteringDataOnLoginPage(email, password);
        homePage.clickToPersonalAccount();
        Assert.assertEquals(email, personalAccountPage.findValueLogin());
    }

    @DisplayName("Login using the button in the password recovery form")
    @Test
    public void loginButtonInPasswordRecoveryForm() {
        homePage.clickToButtonEnterToAccount();
        loginPage.clickLinkPasswordRecovery();
        passwordRecoveryPage.clickOnLinkLogin();
        loginPage.enteringDataOnLoginPage(email, password);
        homePage.clickToPersonalAccount();
        Assert.assertEquals(email, personalAccountPage.findValueLogin());
    }


    @After
    public void tearDown() {
        driver.quit();
        Response response = userMetods.authUser(authUser);
        StringBuilder accessTokenWithoutBearer = new StringBuilder(response.then().extract().body().path("accessToken"));
        String accessTokenForDelete = accessTokenWithoutBearer.delete(0, 7).toString();
        userMetods.deleteUser(accessTokenForDelete);//удалили пользователя
    }

}
