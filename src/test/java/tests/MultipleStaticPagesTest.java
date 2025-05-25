package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultipleStaticPagesTest extends BaseTest {

    // Array: URL + Expected Page Title
    private static final String[][] PAGE_URLS_AND_TITLES = {
            {"https://katalon-demo-cura.herokuapp.com/", "CURA Healthcare Service"},
            {"https://katalon-demo-cura.herokuapp.com/profile.php#login", "CURA Healthcare Service"},
            {"https://katalon-demo-cura.herokuapp.com/#appointment", "CURA Healthcare Service"},
            // Add more: {"URL", "Expected Title"}
    };

    @Test
    public void testMultiplePagesHaveCorrectTitle() {
        for (String[] page : PAGE_URLS_AND_TITLES) {
            String url = page[0];
            String expectedTitle = page[1];

            driver.get(url);
            String actualTitle = driver.getTitle();

            System.out.println("Visited: " + url + " | Found Title: " + actualTitle);
            Assertions.assertEquals(expectedTitle, actualTitle,
                    "Title did not match for page: " + url);
        }
    }
}
