import org.junit.jupiter.api.Test;

public class MainTest extends MainSteps {

    @Test
    public void simpleTest() {
        openPageRedirectAndCheck("Яндекс Маркет");
    }
}
