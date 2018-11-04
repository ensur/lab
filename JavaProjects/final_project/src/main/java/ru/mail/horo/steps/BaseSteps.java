package ru.mail.horo.steps;

import org.openqa.selenium.WebDriver;
import ru.mail.horo.pages.BasePage;

public abstract class BaseSteps extends BasePage {

    public BaseSteps(WebDriver driver) {
        super(driver);
    }
}
