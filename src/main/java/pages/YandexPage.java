package pages;

import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import org.junit.jupiter.api.Assertions;

public interface YandexPage extends BasePage, WebPage {

    @FindBy("//b[text()='market.yandex.ru']/../..")
    AtlasWebElement marketLink();

    @FindBy("//button[@role=\"button\"]/span[contains(text(), 'Найти')]/..")
    AtlasWebElement searchButton();

    @Override
    default void open() {
        getWrappedDriver().manage().window().maximize();
        open("https://yandex.ru");
    }

    @Step("Устанавливаем значение {0} для поиска")
    default YandexPage setSearch(String search) {
        open();
        input("").click();
        input("").sendKeys(search);
        searchButton().click();
        return this;
    }

    @Step("Переходим на страницу Яндекс.Маркет")
    default void redirectToMarket() {
        Assertions.assertTrue(marketLink().isDisplayed(), "В поисковой выдаче отсутствует ссылка на Яндекс.Маркет");
        marketLink().click();
        closePreviousWindow();
    }
}
