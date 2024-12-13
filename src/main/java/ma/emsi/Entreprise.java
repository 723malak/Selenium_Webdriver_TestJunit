package ma.emsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Entreprise {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.pagesjaunes.fr");

            acceptCookies(driver);

            WebElement searchSector = driver.findElement(By.name("S_Activité"));
            searchSector.sendKeys("plomberie");
            WebElement searchRegion = driver.findElement(By.name("Région"));
            searchRegion.sendKeys("Lyon");

            driver.findElement(By.cssSelector("#findId")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bi-list")),
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-results"))
            ));

            if (!driver.findElements(By.cssSelector(".bi-list")).isEmpty()) {
                List<WebElement> entreprises = driver.findElements(By.cssSelector(".bi-generic"));

                System.out.println("\n--------------------------------------------");
                System.out.println("    Entreprises trouvées :");
                System.out.println("--------------------------------------------");

                for (int i = 0; i < Math.min(30, entreprises.size()); i++) {
                    WebElement entreprise = entreprises.get(i);

                    String nomEntreprise = getElementText(entreprise, ".bi-denomination", "Nom non disponible");
                    String adresse = getElementText(entreprise, ".bi-address a", "Adresse non disponible").replace("Voir le plan", "").trim();
                    String phoneNumber = getElementText(entreprise, ".number-contact .annonceur", "Non disponible");
                    String websiteUrl = getElementAttribute(entreprise, ".bi-content a[href^='http']", "href", "Non disponible");
                    String activity = getElementText(entreprise, ".bi-activity-unit", "Non disponible");
                    String rating = getElementText(entreprise, ".bi-rating", "Non disponible");

                    System.out.println("\n--------------------------------------------");
                    System.out.println("Nom de l'entreprise: " + nomEntreprise);
                    System.out.println("Adresse: " + adresse);
                    System.out.println("Numéro de téléphone: " + phoneNumber);
                    System.out.println("Site web: " + websiteUrl);
                    System.out.println("Activité: " + activity);
                    System.out.println("Évaluation: " + rating);
                    System.out.println("--------------------------------------------");
                }
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void acceptCookies(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#didomi-notice-agree-button")));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Pas de cookies à accepter.");
        }
    }

    private static String getElementText(WebElement parent, String cssSelector, String defaultText) {
        try {
            WebElement element = parent.findElement(By.cssSelector(cssSelector));
            return element.getText();
        } catch (Exception e) {
            return defaultText;
        }
    }

    private static String getElementAttribute(WebElement parent, String cssSelector, String attribute, String defaultText) {
        try {
            WebElement element = parent.findElement(By.cssSelector(cssSelector));
            return element.getAttribute(attribute);
        } catch (Exception e) {
            return defaultText;
        }
    }
}
