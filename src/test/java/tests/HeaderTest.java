package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import base.BaseTest;

public class HeaderTest extends BaseTest {

    @Test
    public void testPageTitle() {
        driver.get("https://katalon-demo-cura.herokuapp.com/");

        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        assertEquals("CURA Healthcare Service", title, "Page title should match expected value");
    }
}
