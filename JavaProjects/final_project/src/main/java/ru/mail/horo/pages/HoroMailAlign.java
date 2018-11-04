package ru.mail.horo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.mail.horo.data.HoroData;
import ru.mail.horo.navigation.Page;
import ru.mail.horo.navigation.Url;
import ru.mail.horo.tools.WaitTools;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Page("/runes/divination/1/")
@Url("runes/divination/1")
public class HoroMailAlign extends BasePage {

    public HoroMailAlign(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[contains(@class, 'button')][text()='Гадать']")
    public WebElement button;
    @FindBy(xpath = "//div[text()='Конкретный вопрос или предмет гадания']//..//input[contains(@class, 'input__field')]")
    public WebElement form;

String listLocator = "//div[contains(@class, 'dropdown_scrollable js-select js-module%s')]";
String listValLocator = "//div[contains(@class, 'suggest__item')][text()='%s']";
//border-color:#ff2d54;!
//button_opaque:hover{background:#ff2d54;border-color:#ff2d54;color:#fff}
    public boolean isListOpen(){
        String locator = String.format(listLocator, HoroData.listclose.getName());
        return waitTools.waitForCondition(ExpectedConditions.attributeContains(By.xpath(locator), "class", "dropdown_active"));
    }
    public String setListLocator(){
        if(isListOpen()){return String.format(listLocator, HoroData.listOpen.getName());}
        else{return String.format(listLocator, HoroData.listclose.getName());}
    }
    public Integer listValIs(){
        String locator = setListLocator();
        return Integer.parseInt(getDriver().findElement(By.xpath(locator)).getText());
    }
    public void setListVal(Integer number){
        String locator = String.format(listValLocator, number);
        if(isListOpen()){ getDriver().findElement(By.xpath(locator)).click();}
        else {
            getDriver().findElement(By.xpath(String.format(listLocator, HoroData.listclose.getName()))).click();
            getDriver().findElement(By.xpath(locator)).click(); }
    }

    public boolean scrollList(){
        getDriver().findElement(By.xpath("//div[contains(@class, 'dropdown_scrollable')]")).click();
        WebElement scrollBar = getDriver().findElement(By.xpath("//div[contains(@class, 'js-scrollbar__track__drag')]//div"));
        try
        {
            Actions dragger = new Actions(getDriver());
            // drag downwards
            int numberOfPixelsToDragTheScrollbarDown = 10;
            while (!waitTools.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'suggest__item')][text()='7']"))));
            {
                dragger.moveToElement(scrollBar).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(scrollBar).build().perform();
            }
            Thread.sleep(500);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
