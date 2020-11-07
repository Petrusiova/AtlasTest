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

    @Step("��������� ������� ������������� � ������: {0} � �������� ���")
    default CompTechPage setProducer(String mnfcr) {
        showAll("�������������", "button").click();

        AtlasWebElement input = getInputByName("���� ������");
        input.click();
        input.sendKeys(mnfcr);

        AtlasWebElement producer = liWithText(mnfcr);
        assertAll("� ������ �� ������ ������������� � ������ " + mnfcr,
                () -> assertTrue(producer.isDisplayed())
        );
        if (producer.isDisplayed()) {
            producer.click();
        }
        return this;
    }

    @Step("���������� ������� �������� ��� �������������: {0}")
    default CompTechPage setPrice(String minPrice, String maxPrice) {
        AtlasWebElement startPrice = getInputByName("���� ��");
        startPrice.click();
        startPrice.sendKeys(minPrice);

        AtlasWebElement finishPrice = getInputByName("���� ��");
        finishPrice.click();
        finishPrice.sendKeys(maxPrice);

        return this;
    }

    @Step("���������� ���������� ��������� � ��������� ������: {0}")
    default CompTechPage setResultsCountOnPage(String count) {
        quantity().click();
        getResultsCount()
                .filter(item -> item.getText().contains(count))
                .get(0)
                .click();
        return this;
    }

    @Step("��������� ������������� ��������: {0}")
    default CompTechPage excludeVendors(List<String> excludedVendors) {

        showAll("��������", "button").click();

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
