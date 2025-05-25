package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoverTest extends BaseTest {

    @Test
    public void testHoverOverMakeAppointmentButton() throws InterruptedException {
        driver.get("https://katalon-demo-cura.herokuapp.com/");

        // Locate the "Make Appointment" button on the homepage
        WebElement appointmentBtn = driver.findElement(By.id("btn-make-appointment"));

        // Perform hover action
        Actions actions = new Actions(driver);
        actions.moveToElement(appointmentBtn).perform();

        // Small delay to let UI respond (not always necessary)
        Thread.sleep(1000);

        // Assert the button is displayed (and could add more checks for hover effect if needed)
        assertTrue(appointmentBtn.isDisplayed(), "Make Appointment button should be visible and hoverable");

        System.out.println("Hover over Make Appointment button successful.");
    }
}
