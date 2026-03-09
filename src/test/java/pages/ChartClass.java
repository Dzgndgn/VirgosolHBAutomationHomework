package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ChartClass extends Driver {
    public void Checking(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        By element = By.xpath("//a[contains(@href, 'bilgisayar')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
