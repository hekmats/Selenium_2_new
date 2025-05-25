package tests;
import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;
import pages.LoginPage;
import base.ConfigLoader;

public class AppointmentRandomFormTest extends BaseTest {
    @Test
    public void testMakeFullAppointmentWithRandomData() {
        String url = ConfigLoader.getProperty("base.url");
        String email = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");

        driver.get(url);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("txt_visit_date")));

         // Date is required, fill it first to avoid calendar overlay!
        driver.findElement(By.id("txt_visit_date")).sendKeys("28/05/2025");

        // Close calendar overlay by clicking comment box!
        driver.findElement(By.id("txt_comment")).click();

        // Randomly select facility
        Select facilityDropdown = new Select(driver.findElement(By.id("combo_facility")));
        String[] facilities = {"Tokyo CURA Healthcare Center", "Hongkong CURA Healthcare Center", "Seoul CURA Healthcare Center"};
        String facility = facilities[new Random().nextInt(facilities.length)];
        facilityDropdown.selectByVisibleText(facility);

        // Random comment
        String comment = "Random comment: " + new Random().nextInt(10000);
        driver.findElement(By.id("txt_comment")).sendKeys(comment);

        // Randomly select a radio button
        String[] radioIds = {"radio_program_medicare", "radio_program_medicaid", "radio_program_none"};
        String radioId = radioIds[new Random().nextInt(radioIds.length)];
        WebElement radio = driver.findElement(By.id(radioId));
        if (!radio.isSelected()) {
            radio.click();
        }
        Assertions.assertTrue(radio.isSelected(), "Selected radio should be checked.");

        // Book appointment
        driver.findElement(By.id("btn-book-appointment")).click();

        // Wait for confirmation
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("appointment"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("appointment"),
                "The current URL should contain 'appointment' after making an appointment.");
    }
}
