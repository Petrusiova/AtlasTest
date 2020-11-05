import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest extends MainSteps {

    @Test
    public void simpleTest() {
        openPageRedirectAndCheck("������ ������");
        openMarketPageWithCategoryAndSubCategory("���", "�����-���������", "������������ �������", "��������");

        String filePath = "parsing.xml";

        List<String> manufacturers = searchElements(filePath, "Name");
        manufacturers.forEach(manufacturer ->
                        chooseManufacturer(manufacturer));
    }
}
