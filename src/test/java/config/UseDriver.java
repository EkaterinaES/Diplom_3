package config;

import org.openqa.selenium.chrome.ChromeDriver;

public class UseDriver {
    ChromeDriver chromeDriver = new ChromeDriver();

    public ChromeDriver getWebDriver(boolean isYandexBrowser) {
        if (isYandexBrowser) {
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver-24.1.0.2570-win64\\yandexdriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
        }
        return chromeDriver;
    }
}
