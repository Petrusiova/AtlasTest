import io.qameta.allure.Step;
import pages.YandexPage;
import util.SetUp;

public class MainSteps extends SetUp {

    @Step("Находим Маркет с помощью поиска Яндекс и переходим по ссылке")
    public void openPageRedirectAndCheck(String search) {
        onPage().onYandexPage().setSearch(search).redirectToMarket();
        onPage().onMarketPage().changeCity("сан", "Санкт-Петербург");





        test();
    }

    private void test() {
        String s = "";
    }
}
