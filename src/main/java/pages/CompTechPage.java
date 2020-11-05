package pages;

import io.qameta.allure.Step;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import util.AutoTestException;

public interface CompTechPage extends BasePage {

    @FindBy("//legend[contains(text(), '�������������')]/..//{{ text }}")
    AtlasWebElement showAll(@Param("text") String text);

    @FindBy("//input[@name=\"���� ������\"]")
    AtlasWebElement input();

    @FindBy("//span[contains(text(), '{{ text }}')]")
    AtlasWebElement span(@Param("text") String text);



    @Step("��������� ������� ������������� � ������: {0} � �������� ���")
    default void manChoice(String mnfcr) {
        showAll("button").click();
        input().click();
        input().sendKeys(mnfcr);
        ((ElementsCollection<AtlasWebElement>)showAll("li"))
                .stream()
                .filter(item -> mnfcr.equals(item.getText()))
                .findAny()
                .orElseThrow(new AutoTestException("������ ������������� \" + mnfcr + \" ����������� � ������"))
                .click();
    }
}
