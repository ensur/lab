package ru.mail.horo.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverProvider {

    private WebDriver driver = null;

    public DriverProvider() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-errors");
        System.getProperty("webdriver.chrome.driver");

        driver = new ChromeDriver(chromeOptions);
    }

    /**
     * Закрываем сессию браузера и драйвера
     */
    public void closeDriver() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    /**
     * Возвращает текущую инстансу драйвера
     * @return драйвер
     */
    public WebDriver getDriver() {
        return driver;
    }
}