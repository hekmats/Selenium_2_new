package tests;

import base.BaseTest;
import base.ConfigLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        String url = ConfigLoader.getProperty("base.url");
        String email = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");

        driver.get(url);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        // Wait up to 30 seconds for the element with id="//span[contains(@class,'sidebar__title') and text()='Home']" to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean loggedIn = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Make Appointment']")
            ));
            loggedIn = true;
        } catch (Exception e) {
            loggedIn = false;
        }

        // Assert that the post-login element appears (meaning login succeeded)
        Assertions.assertTrue(loggedIn, "Login was NOT successful — element with id='//span[contains(@class,'sidebar__title') and text()='Home']' was not found!");

        // Optional: Assert not still on the login page
        Assertions.assertFalse(driver.getCurrentUrl().contains("/login"), "Still on login page — login failed.");
    }
}
