package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() throws InterruptedException {
        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Make Appointment']"))
        );

        // Open sidebar menu
        WebElement menuToggle = driver.findElement(By.id("menu-toggle"));
        menuToggle.click();

        // Wait for menu animation (necessary in headless)
        Thread.sleep(1000);

        // Find Logout by href (ignore text)
        List<WebElement> logoutCandidates = driver.findElements(By.xpath("//a[contains(@href, 'logout')]"));
        Assertions.assertFalse(logoutCandidates.isEmpty(), "No Logout link found!");

        WebElement logoutLink = logoutCandidates.get(0);

        // Try to click, use JS as fallback
        try {
            logoutLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", logoutLink);
        }

        // Wait for redirect to home page (either / or /index.php)
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/"),
                ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/index.php"),
                ExpectedConditions.visibilityOfElementLocated(By.id("btn-make-appointment"))
            )
        );

        String url = driver.getCurrentUrl();
        boolean urlOk = url.equals("https://katalon-demo-cura.herokuapp.com/") ||
                        url.equals("https://katalon-demo-cura.herokuapp.com/index.php");
        Assertions.assertTrue(urlOk, "Logout failed! Not redirected to home. URL: " + url);
    }
}
