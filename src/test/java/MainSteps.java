import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import util.SetUp;
import util.XmlDOMParser;

import java.util.List;

public class MainSteps extends SetUp {

    @Step("Находим Маркет с помощью поиска Яндекс и переходим по ссылке")
    public void openPageRedirectAndCheck(String search) {
        onPage().onYandexPage()
                .setSearch(search)
                .redirectToMarket();
    }

    @Step("Производим поиск товаров: {3} для города: {1}")
    public void openMarketPageWithCategoryAndSubCategory(String cityFirstLetters, String cityFullName, String category, String subCategory){
        onPage().onMarketPage()
                .changeCity(cityFirstLetters, cityFullName)
                .changeCategory(category)
                .changeSubCategory(subCategory);
    }

    @Step("Выбираем заданные характеристики: \nпроизводитель: {0}, \nминимальная цена: {1}, \nмаксимальная цена: {2}")
    public void chooseCharacteristics(String manufacturer, String minPrice, String maxPrice, String resultCount, List<String> excludedVendors){
        onPage().onCompTechPage()
                .setProducer(manufacturer)
                .setPrice(minPrice, maxPrice)
                .setResultsCountOnPage(resultCount)
                .excludeVendors(excludedVendors);
    }

    @Step("Находим производителя в xml-файле")
    public List<String> searchElements(String filePath, String tagName){
        List<String> elements = new XmlDOMParser(filePath).getListElementsByTagName(tagName);
        Assertions.assertFalse(elements.isEmpty(), "Список значений тегов " + tagName + " пустой");
        return elements;
    }

    @Step("Находим цену в xml-файле")
    public static String searchPriceValue(String filePath, String tagName, String tagValue, String childTagName){
        String element = new XmlDOMParser(filePath).getStringByNeighbourValue(tagName, tagValue, childTagName);
        Assertions.assertFalse(element.isEmpty(), "Список значений тегов " + tagName + " пустой");
        return element;
    }
}
