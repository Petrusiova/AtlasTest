package pages;

import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import org.hamcrest.Matchers;

import java.util.NoSuchElementException;

public interface MarketPage extends BasePage, WebPage {

    @FindBy("//*[@title=\"Регион\"]")
    AtlasWebElement region();

    @FindBy("//li//a[contains(text(), '{{ text }}')]")
    AtlasWebElement item(@Param("text") String text);

    @Step("Выставляем город {1} по первым трем буквам: {0}")
    default MarketPage changeCity(String firstLetters, String fullName) {
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
        element(fullName).click();
        element("Продолжить с новым регионом").click();
        return this;
    }

    @Step("Выбираем категорию: {0}")
    default MarketPage changeCategory(String category){
        element("Каталог").click();
        element(category).click();
        return this;
    }

    @Step("Выбираем раздел: {0}")
    default MarketPage changeSubCategory(String subCategory){
        item(subCategory).click();
        return this;
    }

}
