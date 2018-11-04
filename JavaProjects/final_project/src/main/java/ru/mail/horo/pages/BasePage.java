package ru.mail.horo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.mail.horo.navigation.Page;
import ru.mail.horo.navigation.Url;
import ru.mail.horo.tools.WaitTools;

import static org.junit.Assert.assertTrue;

public abstract class BasePage {
    private Class<?> pageClass = super.getClass();

    private WebDriver driver = null;

    protected WaitTools waitTools;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitTools = new WaitTools(getDriver());
    }
    @FindBy(xpath = "//h1")
    private WebElement header;

    public void clickButton(WebElement button){
        button.click();
    }

    /**
     * Возвращает заголовок страницы
     * @return заголовок страницы
     */
    public String getPageHeader() {
        return header.getText();
    }

    /**
     * JavaScript Executor
     * @return executor
     */
    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    /**
     * Открывает страницу
     */
    public void open() {
        getDriver().get(getPageUrl());
    }
    /**
     * Возвращает URL страницы
     * @return URL страницы
     */
    protected String getPageUrl() {
        if(pageClass.isAnnotationPresent(Page.class))
            return System.getProperty("webdriver.base.url") + pageClass.getAnnotation(Page.class).value();

        return "";
    }

    /**
     * Проверяет URL страницы на соответствие шаблону
     */
    public void pageValidate() {
        if(pageClass.isAnnotationPresent(Url.class)) {
            String pattern = pageClass.getAnnotation(Url.class).value();
            assertTrue(String.format("URL страницы не соответствует шаблону %s", pattern),
                    driver.getCurrentUrl().matches("http[s]?.*?(?::\\d+)?/" + pattern + "/?"));
        }
    }

    /**
     * Возвращает драйвер
     * @return драйвер
     */
    protected WebDriver getDriver() {
        return driver;
    }
}

