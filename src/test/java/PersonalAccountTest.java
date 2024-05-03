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

public class PersonalAccountTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;
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
        personalAccountPage = new PersonalAccountPage(driver);
        authUser = new AuthUser(email, password);
        User user = new User(email, password, name);
        userMetods = new StellarBurgersUserImpl(StellarBurgersUserImpl.requestSpecification);
        userMetods.createUser(user);//создали пользователя
        homePage.clickToPersonalAccount();
        loginPage.enteringDataOnLoginPage(email, password);//заполнить данные для входа
    }

    @DisplayName("Go to personal account by clicking Personal account")
    @Test
    public void loginButtonPersonalAccount() {//тест такой же, как и при входе на любую из кнопок. Ьак как в них мы проверяли, что в итоге в личном кабинете указан логин.
        homePage.clickToPersonalAccount();
        Assert.assertEquals(email, personalAccountPage.findValueLogin());
    }

    @DisplayName("Transition from personal account to the designer by clicking on the Designer")
    @Test
    public void FromPersonalAccountToDesignerByDesigner() {
        homePage.clickToPersonalAccount();
        Assert.assertTrue(homePage.clickOnDesigner());
    }
    @DisplayName("Transition from personal account to the designer by clicking on the Stellar Burgers")
    @Test
    public void FromPersonalAccountToDesignerByLogo() {
        homePage.clickToPersonalAccount();
        Assert.assertTrue(homePage.clickOnLogo());
    }

    @DisplayName("Log of from account")
    @Test
    public void exitFromPersonalAccount() {
        homePage.clickToPersonalAccount();
        personalAccountPage.exitFromAccount();
        Assert.assertTrue(loginPage.getTextEnter());
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