package ru.mail.horo.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.mail.horo.data.HoroData;
import ru.mail.horo.navigation.Page;
import ru.mail.horo.navigation.Url;
import ru.mail.horo.pages.HoroMailFeu;

import java.util.List;
import java.util.stream.Collectors;

import static ru.mail.horo.data.HoroData.listRuneDynamicAfter;
import static ru.mail.horo.data.HoroData.listRuneDynamicBefore;
import static ru.mail.horo.data.HoroData.listRuneStatic;
@Page("/runes/tile/15/")
@Component(xpath = "//span[text()='Все руны']")
public class RuneComponent extends BaseComponent {

    public RuneComponent(WebDriver driver) {
        super(driver);
    }
    String runesLocator = "//span[text()='Все руны']/ancestor::div[contains(@class, 'wrapper_outside')]//div[@class='p-outside-block__content%s']//span[contains(@class, 'name')]";

    @FindBy(xpath = "//div[text()='Ещё']/..")
    WebElement buttonMore;
    public String getRuneName(Integer number){
        return getDriver().findElements(byBuilder(listRuneStatic)).get(number-1).getText();
    }
    public HoroMailFeu getRunePage(Integer number){
        getDriver().findElements(byBuilder(listRuneStatic)).get(number-1).click();
        return PageFactory.initElements(getDriver(), HoroMailFeu.class);
    }
    public By byBuilder(HoroData horodata){
        return By.xpath((String.format(runesLocator, horodata.getName())));
    }
    public List<String> listBuilder(HoroData horoData){
        return getDriver().findElements(byBuilder(horoData)).stream()
                .map((s) -> s.getText()).collect(Collectors.toList());
    }
    public boolean checkRuneList(){
        waitTools.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(byBuilder(listRuneDynamicBefore)));
        List<String> runesStatic = listBuilder(listRuneStatic);
        buttonMore.click();
        waitTools.waitForCondition(ExpectedConditions.visibilityOfElementLocated(byBuilder(listRuneDynamicAfter)));
        List<String> runesDynamic = listBuilder(listRuneDynamicAfter);
        List<String> runes = runesStatic;
        runesStatic.removeAll(runesDynamic);
        return runesStatic.equals(runes);
    }
}
