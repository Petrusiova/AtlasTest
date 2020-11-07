package pages;

import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface CompTechPage extends BasePage {

    @FindBy("//legend[contains(text(), '{{ text }}')]/..//{{ element }}")
    AtlasWebElement showAll(@Param("text") String text, @Param("element") String element);

    @FindBy("//input[@name=\"{{ text }}\"]")
    AtlasWebElement getInputByName(@Param("text") String text);

    @FindBy("//button[@aria-haspopup=\"true\"]")
    AtlasWebElement quantity();

    @FindBy("//div[@aria-hidden=\"false\"]//button")
    ElementsCollection<AtlasWebElement> getResultsCount();

    @FindBy("//*[@id=\"search-prepack\"]//div[2]/ul/li[*]//span")
    ElementsCollection<AtlasWebElement> shops();

    @Step("Проверяем наличие производителя в списке: {0} и выбираем его")
    default CompTechPage setProducer(String mnfcr) {
        showAll("Производитель", "button").click();

        AtlasWebElement input = getInputByName("Поле поиска");
        input.click();
        input.sendKeys(mnfcr);

        AtlasWebElement producer = liWithText(mnfcr);
        assertAll("В списке не найден производитель с именем " + mnfcr,
                () -> assertTrue(producer.isDisplayed())
        );
        if (producer.isDisplayed()) {
            producer.click();
        }
        return this;
    }

    @Step("Выставляем ценовой диапазон для производителя: {0}")
    default CompTechPage setPrice(String minPrice, String maxPrice) {
        AtlasWebElement startPrice = getInputByName("Цена от");
        startPrice.click();
        startPrice.sendKeys(minPrice);

        AtlasWebElement finishPrice = getInputByName("Цена до");
        finishPrice.click();
        finishPrice.sendKeys(maxPrice);

        return this;
    }

    @Step("Выставляем количество элементов в поисковой выдаче: {0}")
    default CompTechPage setResultsCountOnPage(String count) {
        quantity().click();
        getResultsCount()
                .filter(item -> item.getText().contains(count))
                .get(0)
                .click();
        return this;
    }

    @Step("Исключаем нежелательные магазины: {0}")
    default CompTechPage excludeVendors(List<String> excludedVendors) {

        showAll("Магазины", "button").click();

        scrollAndClick(excludedVendors);
        return this;
    }

    default void scrollAndClick(List<String> excludedVendors) {
        shops().forEach(item -> {
            if (!excludedVendors.contains(item.getText().toLowerCase())) {
                item.click();
            }
            ((JavascriptExecutor) getWrappedDriver()).executeScript("arguments[0].scrollIntoView(true);", item);
        });
        if(shops().size()>1){
            scrollAndClick(excludedVendors);
        }
    }
}
