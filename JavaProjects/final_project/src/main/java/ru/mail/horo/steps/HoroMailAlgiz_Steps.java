package ru.mail.horo.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.mail.horo.pages.HoloMailGuess;
import ru.mail.horo.pages.HoroMailAlgiz;
import ru.mail.horo.pages.HoroMailAlign;
import ru.mail.horo.pages.HoroMailFeu;

import static org.junit.Assert.assertEquals;

public class HoroMailAlgiz_Steps extends BaseSteps {
    private HoroMailAlgiz horoMailAlgiz = null;
    public HoroMailFeu horoMailFeu = null;
    public HoroMailAlgiz_Steps(WebDriver driver) {
        super(driver);
        horoMailAlgiz = PageFactory.initElements(driver, HoroMailAlgiz.class);
    }
    public HoroMailFeu getHoroMailFeu(){
        horoMailFeu = PageFactory.initElements(getDriver(), HoroMailFeu.class);
        return horoMailFeu;
    }

    @Step("Получаем заголовок веб страницы")
    private void pageHeaderShouldBeSameAs(String header){
        assertEquals("Неверный заголовок", header, horoMailAlgiz.getPageHeader());
    }

    @Step("Открываем страницу {header}")
    public void open(String header){
        horoMailAlgiz.open();
       horoMailAlgiz.pageValidate();
        pageHeaderShouldBeSameAs(header);
    }

}
