package ma.emsi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex3TestMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            // Recherche sur Amazon
            driver.get("https://www.amazon.fr/");
            try {
                WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sp-cc-accept")));
                cookiesButton.click();
            } catch (Exception ignored) {
                System.out.println("Pas de fenêtre de cookies détectée.");
            }
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
            searchBox.sendKeys("Asus Vivobook");
            driver.findElement(By.id("nav-search-submit-button")).click();
            List<WebElement> amazonPrices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                    (By.xpath("//span[contains(@class,'a-price-whole')]")));
            List<String> pricesAmazon = extractPrices(amazonPrices);
            System.out.println("Prix trouvés sur Amazon : " + pricesAmazon);

            // Recherche sur eBay
            driver.get("https://www.ebay.fr/");
            WebElement ebaySearchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-ac")));
            ebaySearchBox.sendKeys("Asus Vivobook");
            driver.findElement(By.id("gh-btn")).click();

            List<WebElement> ebayPrices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                    (By.xpath("//span[contains(@class,'s-item__price')]")));
            List<String> pricesEbay = extractPrices(ebayPrices);

            System.out.println("Prix trouvés sur eBay : " + pricesEbay);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
    private static List<String> extractPrices(List<WebElement> priceElements) {
        List<String> prices = new ArrayList<>();
        for (WebElement price : priceElements) {
            prices.add(price.getText());
        }
        return prices;
    }
}

