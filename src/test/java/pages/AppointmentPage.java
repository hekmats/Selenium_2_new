package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentPage {
    private WebDriver driver;

    // Locators
    private By facilityDropdown = By.id("combo_facility");
    private By visitDateField   = By.id("txt_visit_date");
    private By commentField     = By.id("txt_comment");
    private By bookButton       = By.id("btn-book-appointment"); // The submit button

    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFacility(String facility) {
        driver.findElement(facilityDropdown).sendKeys(facility); // or use Select class
    }

    public void setVisitDate(String date) {
        driver.findElement(visitDateField).clear();
        driver.findElement(visitDateField).sendKeys(date);
    }

    public void setComment(String comment) {
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }

    public void bookAppointment() {
        driver.findElement(bookButton).click();
    }
}
