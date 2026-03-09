package driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;

public class Driver {

    // Holds the WebDriver instance
    public static WebDriver webDriver;

    @BeforeScenario
    public void initializeDriver(){
        System.out.println("deneme5");
        webDriver = DriverFactory.getDriver();
        webDriver.manage().window().maximize();
    }

    // Close the webDriver instance
    @AfterScenario
    public void closeDriver(){
        webDriver.quit();
    }


    // Initialize a webDriver instance of required browser
    // Since this does not have a significance in the application's business domain, the BeforeSuite hook is used to instantiate the webDriver

}
