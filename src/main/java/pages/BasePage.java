package pages;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import util.SetUp;

import java.util.ArrayList;

public interface BasePage extends WebPage {
    @FindBy("//*[@name='text']{{ text }}")
    AtlasWebElement input(@Param("text") String text);

//    default void checkElementOnPage(AtlasWebElement webElement){
//        webElement.waitUntil(displayed(webElement), 5);
//    }

    default void closePreviousWindow() {
        ChromeDriver chromeDriver = SetUp.getChromeDriver();
        ArrayList<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(0));
        chromeDriver.close();
        chromeDriver.switchTo().window(tabs.get(1));
        Assertions.assertEquals(1, chromeDriver.getWindowHandles().size(),
                "Закрытие предыдущей страницы не произошло");
    }
}
