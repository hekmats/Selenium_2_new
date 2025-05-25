package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppointmentTest extends BaseTest {
    @Test
    public void testMakeAppointment() {
        // 1. Login first
        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();

        // 2. Wait for appointment page to load
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("txt_visit_date")));

        // 3. Fill ONLY the date
        driver.findElement(By.id("txt_visit_date")).sendKeys("28/05/2025");

        // 4. Click "Book Appointment"
        driver.findElement(By.id("btn-book-appointment")).click();

        // 5. Wait for any URL containing "appointment"
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("appointment"));

        // 6. Assert the URL contains "appointment"
        Assertions.assertTrue(
                driver.getCurrentUrl().contains("appointment"),
                "The current URL should contain 'appointment' after making an appointment."
        );
    }
}
