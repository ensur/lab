package ru.mail.horo.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.mail.horo.pages.BasePage;

import static org.junit.Assert.assertTrue;

public abstract class BaseComponent extends BasePage {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }

    {
        componentValidate();
    }

    /**
     * Валидация компонента
     */
    private void componentValidate() {
        Class<?> componentClass = super.getClass();
        if(componentClass.isAnnotationPresent(Component.class)) {

            Component component = componentClass.getAnnotation(Component.class);

            assertTrue(String.format("Компонент %s отсутствует на странице", componentClass.getName()),
                    waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(component.xpath()))));
        }
    }
}