package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import org.hamcrest.Matchers;
import org.openqa.selenium.Keys;

import java.util.NoSuchElementException;

public interface MarketPage extends BasePage, WebPage {

    @FindBy("//*[@title=\"Регион\"]")
    AtlasWebElement region();

    @FindBy("//div[@tabindex=\"-1\"]//a")
    ElementsCollection<AtlasWebElement> cities();

    @FindBy("//*[contains(text(), '{{ text }}')]/../..")
    AtlasWebElement city(@Param("text") String text);

    default void changeCity(String firstLetters, String fullName) {
        region().waitUntil(Matchers.any(AtlasWebElement.class), 10);
        region().click();
        AtlasWebElement input = input("[@placeholder='Укажите другой регион']");
        try{
            input.isDisplayed();
        }
        catch (NoSuchElementException e){
            region().click();
        }
        input.click();
        input.sendKeys(firstLetters);
        city(fullName).click();
        region().sendKeys(Keys.ENTER);
    }

}
