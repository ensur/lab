package ru.mail.horo;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import ru.mail.horo.data.HoroData;
import ru.mail.horo.driver.DriverProvider;
import ru.mail.horo.pages.HoloMailGuess;
import ru.mail.horo.pages.HoroMailAlgiz;
import ru.mail.horo.pages.HoroMailAlign;
import ru.mail.horo.pages.HoroMailFeu;
import ru.mail.horo.steps.HoroMailAlgiz_Steps;
import ru.mail.horo.steps.HoroMailAlign_Steps;
import ru.mail.horo.steps.components.RuneComponent_Steps;

import static org.junit.Assert.assertEquals;

public class HoroMailAlign_Test {
    private DriverProvider driverProvider = null;

    @Before
    public void initDriver() {
        driverProvider = new DriverProvider();
    }

    @After
    public void closeDriver() {
        driverProvider.closeDriver();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void Test() {

        //открываем страницу Расклад «Шаг в будущее»
        HoroMailAlign_Steps horoMailAlign_Steps = new HoroMailAlign_Steps(driverProvider.getDriver());
        horoMailAlign_Steps.open("Расклад «Шаг в будущее»");

        //проверяем, что в списке по умолчанию выбрано 1
        horoMailAlign_Steps.checkScrollList();

        //Печатаем текст вопрос в поле
        horoMailAlign_Steps.printTextInForm();

        //Проверяем скролл списка
        horoMailAlign_Steps.checkScrollList();

        //Выбираем третий пункт в списке
        horoMailAlign_Steps.setListValue(3);

       //Кликаем на кнопку гадать
        horoMailAlign_Steps.clickButton();
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void Test2() {
        //открываем страницу Альгиз
        HoroMailAlgiz_Steps horoMailAlgiz_steps = new HoroMailAlgiz_Steps(driverProvider.getDriver());
        horoMailAlgiz_steps.open("Альгиз");

        //Кликаем по кнопке еще и проверяем актуальность списка
        RuneComponent_Steps runeComponent_steps = new RuneComponent_Steps(driverProvider.getDriver());
        runeComponent_steps.checkRuneList();

        //Кликаем по первой руне и проверяем открывшуюся страницу
        horoMailAlgiz_steps.horoMailFeu = horoMailAlgiz_steps.getHoroMailFeu();
        runeComponent_steps.clickFirstRune();
        assertEquals("Открылась неверная руна",horoMailAlgiz_steps.horoMailFeu.getPageHeader(), runeComponent_steps.getFirstRuneName());
    }
}
