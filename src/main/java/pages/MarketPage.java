package pages;

import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import org.hamcrest.Matchers;

import java.util.NoSuchElementException;

public interface MarketPage extends BasePage, WebPage {

    @FindBy("//*[@title=\"������\"]")
    AtlasWebElement region();

    @Step("���������� ����� {1} �� ������ ���� ������: {0}")
    default MarketPage changeCity(String firstLetters, String fullName) {
        region().waitUntil(Matchers.any(AtlasWebElement.class), 10);
        region().click();
        AtlasWebElement input = input("[@placeholder='������� ������ ������']");
        try{
            input.isDisplayed();
        }
        catch (NoSuchElementException e){
            region().click();
        }
        input.click();
        input.sendKeys(firstLetters);
        getParentByElementText(fullName).click();
        getParentByElementText("���������� � ����� ��������").click();
        return this;
    }

    @Step("�������� ���������: {0}")
    default MarketPage changeCategory(String category){
        getParentByElementText("�������").click();
        getParentByElementText(category).click();
        return this;
    }

    @Step("�������� ������: {0}")
    default MarketPage changeSubCategory(String subCategory){
        liWithText(subCategory).click();
        return this;
    }

}
