package pages;

import io.qameta.atlas.webdriver.WebSite;
import io.qameta.atlas.webdriver.extension.Page;

public interface SiteList extends WebSite {

    @Page
    BasePage onBasePage();

    @Page
    YandexPage onYandexPage();

    @Page
    MarketPage onMarketPage();

    @Page
    CompTechPage onCompTechPage();

}
