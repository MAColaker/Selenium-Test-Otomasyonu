
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumGG {

    public ChromeDriver driver;
    private final WebDriverWait wait;
    JavascriptExecutor js;
    public String productPagePrice;
    public String bucketPrice;

    public SeleniumGG(ChromeDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
        js = driver;
    }

    public void goPage(String link) {
        driver.get(link);
    }

    public void pageQuit() {
        driver.quit();
    }

    public void hoverActionClass(String string) {
        Actions actions = new Actions(driver);
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(string)))).perform();
    }

    public boolean pageControl(String Text) {
        return driver.getPageSource().contains(Text);
    }

    public void clickTextLink(String textLink) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(textLink)));
        scrollIntoView(webElement);
    }

    public void setInputId(String Id, String input) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id))).sendKeys(input);
    }

    public void clickId(String id) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        scrollIntoView(webElement);
    }

    public WebElement customAttribute(String att, String css) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[" + att + "=\"" + css + "\"]")));
    }

    public void setCustomAtt(String att, String css, String input) {
        customAttribute(att, css).sendKeys(input);
    }

    public void clickCustomAtt(String att, String css) {
        WebElement webElement = customAttribute(att, css);
        scrollIntoView(webElement);
    }

    public void scrollIntoView(WebElement webElement) {
        js.executeScript("arguments[0].scrollIntoView();", webElement);
        webElement.click();
        /*
          Sitenin altındaki Çerez alerti buton tıklamalarında sorun çıkardığı için
          bu fonksiyon kullanılmıştır.
          */
    }

    public void savePrice() {
        try {
            productPagePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sp-price-lowPrice"))).getText();
        } catch (Exception e) {
            productPagePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sp-price-highPrice"))).getText();
        }
    }

    public boolean checkPrice() {
        bucketPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("new-price"))).getText();
        return productPagePrice.equals(bucketPrice);
    }

    public boolean addNumberOfProduct() {
        Select amount = new Select(driver.findElement(By.cssSelector("select[class='amount']")));
        amount.selectByValue("2");
        WebElement option = amount.getFirstSelectedOption();
        return option.getText().equals("2");
    }

    public boolean clearBasket() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("empty-cart-container"))).isDisplayed();
    }
}
