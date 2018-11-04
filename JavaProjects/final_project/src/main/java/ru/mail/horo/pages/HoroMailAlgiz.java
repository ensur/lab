package ru.mail.horo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.mail.horo.data.HoroData;
import ru.mail.horo.navigation.Page;
import ru.mail.horo.navigation.Url;

import java.util.List;
import java.util.stream.Collectors;

import static ru.mail.horo.data.HoroData.listRuneDynamicAfter;
import static ru.mail.horo.data.HoroData.listRuneDynamicBefore;
import static ru.mail.horo.data.HoroData.listRuneStatic;

@Page("/runes/tile/15/")
@Url("runes/tile/15")
public class HoroMailAlgiz extends BasePage {

    public HoroMailAlgiz(WebDriver driver) {
        super(driver);
    }

   /* public void getCircleColour(){
        waitTools.waitForCondition(ExpectedConditions.)
        String result = (String) getJSExecutor().executeScript("return elem.querySelector(‘.p-imaged-item_rune textarea’).value");
        System.out.println(result);
    }*/


}
