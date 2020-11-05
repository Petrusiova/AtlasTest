package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public interface BasePage extends WebPage {

    @FindBy("//*[@name='text']{{ text }}")
    AtlasWebElement input(@Param("text") String text);

    @FindBy("//*[contains(text(), '{{ text }}')]/../..")
    AtlasWebElement element(@Param("text") String text);

    default void closePreviousWindow() {
        WebDriver chromeDriver = getWrappedDriver();
        ArrayList<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(0));
        chromeDriver.close();
        chromeDriver.switchTo().window(tabs.get(1));
        Assertions.assertEquals(1, chromeDriver.getWindowHandles().size(),
                "Закрытие предыдущей страницы не произошло");
    }
}
