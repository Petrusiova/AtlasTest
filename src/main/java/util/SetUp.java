package util;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import io.qameta.atlas.webdriver.WebPage;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SiteList;

import java.io.File;

public class SetUp {
    private static ChromeDriver chromeDriver;
    Atlas atlas;

    public SetUp() {
        if (chromeDriver == null) {
            File chromeDriverFile = new File(PropertyManager.getInstance().getDriverPath());
            System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
            chromeDriver = new ChromeDriver();
        }
        if (atlas == null) {
            atlas = new Atlas(new WebDriverConfiguration(chromeDriver, "https://yandex.ru")).listener(new AllureLogger());
        }
    }

    public static ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

    public  SiteList onPage() {
        return atlas.create(chromeDriver, SiteList.class);
    }

    @AfterAll
    public static void stopDriver() throws Exception {
        chromeDriver.close();
    }
}
