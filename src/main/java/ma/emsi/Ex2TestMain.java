package ma.emsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Ex2TestMain {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://localhost/selenium/connexion.html");
            testValidLogin(driver, wait);
            testInvalidLogin(driver, wait);

        } finally {
            driver.quit();
        }
    }

    public static void testValidLogin(WebDriver driver, WebDriverWait wait) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));

        // Fournir les bons identifiants
        usernameField.clear();
        usernameField.sendKeys("user");
        passwordField.clear();
        passwordField.sendKeys("password");
        submitButton.click();

        // Vérification du message de succès
        WebElement loginMessage = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("loginMessage")));
        if ("Connexion réussie !".equals(loginMessage.getText())) {
            System.out.println("Test de connexion valide réussi.");
        } else {
            System.out.println("Test de connexion valide échoué.");
        }
    }

    public static void testInvalidLogin(WebDriver driver, WebDriverWait wait) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));

        // Fournir des identifiants incorrects
        usernameField.clear();
        usernameField.sendKeys("wrongUser");
        passwordField.clear();
        passwordField.sendKeys("wrongPassword");
        submitButton.click();

        // Vérification du message d'erreur
        WebElement loginMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginMessage")));
        if ("Nom d'utilisateur ou mot de passe incorrect.".equals(loginMessage.getText())) {
            System.out.println("Test de connexion invalide réussi.");
        } else {
            System.out.println("Test de connexion invalide échoué.");
        }
    }
}
