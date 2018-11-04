package ru.mail.horo.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.mail.horo.data.HoroData;
import ru.mail.horo.pages.HoloMailGuess;
import ru.mail.horo.pages.HoroMailAlign;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HoroMailAlign_Steps extends BaseSteps {
    private HoroMailAlign horomailalign = null;
    private HoloMailGuess horoMailGuess = null;
    public HoroMailAlign_Steps(WebDriver driver) {
        super(driver);
        horomailalign = PageFactory.initElements(driver, HoroMailAlign.class);
    }
    private HoloMailGuess getHoloMailGuess(){
        horoMailGuess = PageFactory.initElements(getDriver(), HoloMailGuess.class);
        return horoMailGuess;
    }

    @Step("Получаем заголовок веб страницы")
    private void pageHeaderShouldBeSameAs(String header){
        assertEquals("Неверный заголовок", header, horomailalign.getPageHeader());
    }

    @Step("Открываем страницу {header}")
    public void open(String header){
        horomailalign.open();
        horomailalign.pageValidate();
        pageHeaderShouldBeSameAs(header);
    }

    @Step("Проверяем, что в списке по умолчанию выбрано 1")
    public void listStateDefault(){
        assertEquals("Неверное стандартное значение листа", horomailalign.listValIs(), (Integer) 1);
    }

    @Step("Печатаем текст <Вопрос> в необходимое поле")
    public void printTextInForm(){
        horomailalign.form.sendKeys("Вопрос");
    }
    @Step("Выбираем третий пункт в списке")
    public void setListValue(Integer number){
        horomailalign.isListOpen();
        horomailalign.setListVal(number);
        assertEquals("Пункт установлен неверно", horomailalign.listValIs(), number);
    }
    @Step("Кликаем на кнопку гадать")
    public void clickButton(){
        getHoloMailGuess().clickButton(horomailalign.button);
    }

    @Step("Проверяем скролл списка")
    public void checkScrollList(){
        assertTrue("Список функционирует неверно", horomailalign.scrollList());
    }


}
