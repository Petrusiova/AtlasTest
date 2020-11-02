import io.qameta.allure.Step;
import pages.YandexPage;
import util.SetUp;

public class MainSteps extends SetUp {

    @Step("������� ������ � ������� ������ ������ � ��������� �� ������")
    public void openPageRedirectAndCheck(String search) {
        onPage().onYandexPage().setSearch(search).redirectToMarket();
        onPage().onMarketPage().changeCity("���", "�����-���������");





        test();
    }

    private void test() {
        String s = "";
    }
}
