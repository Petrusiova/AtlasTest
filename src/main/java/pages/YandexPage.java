package pages;

import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import org.junit.jupiter.api.Assertions;

public interface YandexPage extends BasePage, WebPage {

    @FindBy("//b[text()='market.yandex.ru']/../..")
    AtlasWebElement marketLink();

    @FindBy("//button[@role=\"button\"]/span[contains(text(), '�����')]/..")
    AtlasWebElement searchButton();

    @Override
    default void open() {
        open("https://yandex.ru");
    }

    @Step("������������� �������� {0} ��� ������")
    default YandexPage setSearch(String search) {
        open();
        input("").click();
        input("").sendKeys(search);
        searchButton().click();
        return this;
    }

    @Step("��������� �� �������� ������.������")
    default void redirectToMarket() {
        Assertions.assertTrue(marketLink().isDisplayed(), "� ��������� ������ ����������� ������ �� ������.������");
        marketLink().click();
        closePreviousWindow();
    }
}
