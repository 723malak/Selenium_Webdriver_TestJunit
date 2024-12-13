package ma.emsi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class Ex4TestMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://charika.ma");
            driver.manage().window().maximize();
            WebElement regionDropdown = driver.findElement(By.xpath
                    ("//*[@id='national']/form/div/div[2]/div/div/div/button/div/div/div"));
            regionDropdown.click();
            WebElement regionOption = driver.findElement(By.xpath
                    ("//span[text()='Marrakech - Safi']"));
            regionOption.click();
            WebElement activityDropdown = driver.findElement(By.xpath("//*[@id='national']/form/div/div[3]/div/div/div/button/div/div/div"));
            activityDropdown.click();
            WebElement activityOption = driver.findElement(By.xpath("//span[text()='Bâtiment et travaux publics']"));
            activityOption.click();
            WebElement searchButton = driver.findElement(By.xpath("//*[@id='national']/form/div/div[4]/div/button"));
            searchButton.click();
            Thread.sleep(5000); // Pause de 5 secondes (adapter si nécessaire)
            List<WebElement> offers = driver.findElements(By.xpath("//div[contains(@class,'ligne-tfmw')]//label"));
            System.out.println("Adresses des entreprises trouvées :");
            for (WebElement offer : offers) {
                System.out.println(offer.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }}}


