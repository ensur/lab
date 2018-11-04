package ru.mail.horo.steps.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.mail.horo.components.RuneComponent;
import ru.mail.horo.pages.HoroMailFeu;
import ru.mail.horo.steps.BaseSteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RuneComponent_Steps extends BaseSteps {
private RuneComponent runecomponent = null;
private HoroMailFeu horoMailFeu = null;
    public RuneComponent_Steps(WebDriver driver) {
        super(driver);
        runecomponent = PageFactory.initElements(driver, RuneComponent.class);
    }
    private HoroMailFeu getHoroMailFeu(){
        horoMailFeu = PageFactory.initElements(getDriver(), HoroMailFeu.class);
        return horoMailFeu;
    }

    @Step("Кликаем по первой руне в списке и проверяем, что открылась нужная страница")
    public void clickFirstRune(){
        runecomponent.getRunePage(1);
        assertEquals("Заголовок не соответствует нужной руне", getHoroMailFeu().getPageHeader(), runecomponent.getRuneName(1));
    }

    @Step("Получаем имя первой руны")
    public String getFirstRuneName(){
        return runecomponent.getRuneName(1);
    }

    @Step("Кликаем по кнопке еще и проверяем актуальность открывшегося списка")
    public void checkRuneList(){
        assertTrue("Список функционирует неверно" ,runecomponent.checkRuneList());
    }
}
