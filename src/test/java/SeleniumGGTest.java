
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

public class SeleniumGGTest {
    SeleniumGG seleniumGG;
    Logger log = Logger.getLogger(SeleniumGG.class);

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        seleniumGG = new SeleniumGG(new ChromeDriver(options));
    }

    @Test
    public void testUp(){
        seleniumGG.goPage("https://www.gittigidiyor.com/");
        if (seleniumGG.pageControl("gittigidiyor"))
            log.info("Anasayfa açıldı.");

        seleniumGG.hoverActionClass("egoSnI");
        seleniumGG.clickTextLink("Giriş Yap");
        seleniumGG.setInputId("L-UserNameField", "mibil83795@dghetian.com");
        seleniumGG.setInputId("L-PasswordField", "Testinium21313");
        seleniumGG.clickId("gg-login-enter");
        if (seleniumGG.pageControl("muhammedali599217"))
            log.info("Başarıyla giriş yapıldı.");

        seleniumGG.setCustomAtt("data-cy", "header-search-input", "bilgisayar");
        seleniumGG.clickCustomAtt("data-cy", "search-find-button");
        seleniumGG.clickTextLink("2");
        if (seleniumGG.pageControl("- 2/"))
            log.info("2.sayfa açıldı");

        Random random = new Random();
        int number = random.nextInt(10) + 1;
        seleniumGG.clickCustomAtt("product-index", Integer.toString(number));
        seleniumGG.savePrice();
        seleniumGG.clickId("add-to-basket");
        seleniumGG.hoverActionClass("basket-title");
        seleniumGG.clickTextLink("Sepete Git");
        if(seleniumGG.checkPrice())
            log.info("Ürün fiyatı ile sepet fiyatı aynı.");

        if(seleniumGG.addNumberOfProduct())
            log.info("Ürün sayısı artırıldı");

        seleniumGG.clickCustomAtt("class", "gg-icon gg-icon-bin-medium");
        if(seleniumGG.clearBasket())
            log.info("Sepet boşaltıldı.");
    }

    @After
    public void tearDown() {
        seleniumGG.pageQuit();
    }
}