package ma.emsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Ex1TestMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.google.com");
            WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            searchBar.sendKeys("youtube");
            searchBar.submit();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
            System.out.println("Les résultats de recherche sont affichés avec succès.");

        } catch (Exception e) {
            System.err.println("Une erreur s'est produite : " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

