package stepdefinitions;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class TestSetup {

    public static WebDriver driver;
    public static String baseUrl = "http://localhost:";

    @Before
    public void setUp() {
        // Select Browser Driver
        driver = new EdgeDriver();

        // Go to the Best Price website
        driver.get(baseUrl);

        // Maximize window size
        driver.manage().window().maximize();
    }

    @After
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
