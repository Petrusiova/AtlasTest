import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest extends MainSteps {

    @Test
    public void simpleTest() {
        openPageRedirectAndCheck("Яндекс Маркет");
        openMarketPageWithCategoryAndSubCategory("сан", "Санкт-Петербург", "Компьютерная техника", "Ноутбуки");

        String filePath = "parsing.xml";

        List<String> manufacturers = searchElements(filePath, "Name");
        manufacturers.forEach(manufacturer ->
                        chooseManufacturer(manufacturer));
    }
}
