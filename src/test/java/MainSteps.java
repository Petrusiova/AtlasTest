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

    @Step("������� ������������� � xml-�����")
    public List<String> searchElements(String filePath, String tagName){
        List<String> elements = new XmlDOMParser(filePath).getListElementsByTagName(tagName);
        Assertions.assertFalse(elements.isEmpty(), "������ �������� ����� " + tagName + " ������");
        return elements;
    }

    @Step("�������� �������������: {0}")
    public void chooseManufacturer(String manufacturer){
        onPage().onCompTechPage()
                .manChoice(manufacturer);
    }
}
