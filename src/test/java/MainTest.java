import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest extends MainSteps {

    @Test
    public void simpleTest() {
        String filePath = "parsing.xml";
        String commonMaxPrice = searchElements(filePath, "Price").get(0).trim();
        List<String> excludedVendors = searchElements(filePath, "Vendor");

        openPageRedirectAndCheck("Яндекс Маркет");
        openMarketPageWithCategoryAndSubCategory(
                "сан",
                "Санкт-Петербург",
                "Компьютерная техника",
                "Ноутбуки");

        List<String> manufacturers = searchElements(filePath, "Name");
        manufacturers.forEach(manufacturer -> {
                    String minPrice = searchPriceValue(filePath, "Name", manufacturer, "Min");
                    String maxPrice = searchPriceValue(filePath, "Name", manufacturer, "Max");

                    if (Long.parseLong(maxPrice) > Long.parseLong(commonMaxPrice)) {
                        maxPrice = commonMaxPrice;
                    }
                    chooseCharacteristics(manufacturer, minPrice, maxPrice, "12", excludedVendors);
                }
        );
    }
}
