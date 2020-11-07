import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import util.SetUp;
import util.XmlDOMParser;

import java.util.List;

public class MainSteps extends SetUp {

    @Step("������� ������ � ������� ������ ������ � ��������� �� ������")
    public void openPageRedirectAndCheck(String search) {
        onPage().onYandexPage()
                .setSearch(search)
                .redirectToMarket();
    }

    @Step("���������� ����� �������: {3} ��� ������: {1}")
    public void openMarketPageWithCategoryAndSubCategory(String cityFirstLetters, String cityFullName, String category, String subCategory){
        onPage().onMarketPage()
                .changeCity(cityFirstLetters, cityFullName)
                .changeCategory(category)
                .changeSubCategory(subCategory);
    }

    @Step("�������� �������� ��������������: \n�������������: {0}, \n����������� ����: {1}, \n������������ ����: {2}")
    public void chooseCharacteristics(String manufacturer, String minPrice, String maxPrice, String resultCount, List<String> excludedVendors){
        onPage().onCompTechPage()
                .setProducer(manufacturer)
                .setPrice(minPrice, maxPrice)
                .setResultsCountOnPage(resultCount)
                .excludeVendors(excludedVendors);
    }

    @Step("������� ������������� � xml-�����")
    public List<String> searchElements(String filePath, String tagName){
        List<String> elements = new XmlDOMParser(filePath).getListElementsByTagName(tagName);
        Assertions.assertFalse(elements.isEmpty(), "������ �������� ����� " + tagName + " ������");
        return elements;
    }

    @Step("������� ���� � xml-�����")
    public static String searchPriceValue(String filePath, String tagName, String tagValue, String childTagName){
        String element = new XmlDOMParser(filePath).getStringByNeighbourValue(tagName, tagValue, childTagName);
        Assertions.assertFalse(element.isEmpty(), "������ �������� ����� " + tagName + " ������");
        return element;
    }
}
