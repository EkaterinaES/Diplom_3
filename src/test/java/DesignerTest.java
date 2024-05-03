
import config.UseDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.*;


public class DesignerTest {
    private WebDriver driver;
    private HomePage homePage;


    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new UseDriver().getWebDriver(false);
        homePage = new HomePage(driver);
        driver.get(Constants.PAGE_URL);//открыли сайт
    }


    @DisplayName("Go to the buns section")
    @Test
    public void bunsSection() {
        Assert.assertTrue(homePage.isLoadBuns());
    }

    @DisplayName("Go to the sauce section")
    @Test
    public void sauceSection() {
        Assert.assertTrue(homePage.isLoadSauces());
    }

    @DisplayName("Go to the fillings section")
    @Test
    public void fillingsSection() {
        Assert.assertTrue(homePage.isLoadFillings());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}

