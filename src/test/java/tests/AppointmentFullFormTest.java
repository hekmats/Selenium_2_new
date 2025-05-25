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
import pages.LoginPage;
import base.ConfigLoader;

public class AppointmentFullFormTest extends BaseTest {
    @Test
    public void testMakeFullAppointment() {
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

        // Now select facility safely (don't click, use Select API)
        Select facilityDropdown = new Select(driver.findElement(By.id("combo_facility")));
        facilityDropdown.selectByVisibleText("Seoul CURA Healthcare Center");

        // Fill comment
        driver.findElement(By.id("txt_comment")).sendKeys("Test full form via Selenium!");

        // Select Medicare radio button
        WebElement medicareRadio = driver.findElement(By.id("radio_program_medicare"));
        if (!medicareRadio.isSelected()) {
            medicareRadio.click();
        }
        Assertions.assertTrue(medicareRadio.isSelected(), "Medicare radio button should be selected.");

        // Book appointment
        driver.findElement(By.id("btn-book-appointment")).click();

        // Wait for confirmation
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("appointment"));

        // Confirm
        Assertions.assertTrue(driver.getCurrentUrl().contains("appointment"),
                "The current URL should contain 'appointment' after making an appointment.");
    }
}
