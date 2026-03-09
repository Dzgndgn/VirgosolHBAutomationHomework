package pages;

import driver.Driver;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Driver {

    public void search() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        JavascriptExecutor js =(JavascriptExecutor) webDriver;
        Thread.sleep(3000);
        By searchArea = By.xpath("//div[@role='search']");
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchArea));
        System.out.println("Kelime yazılmadı henüz3.");
        searchInput.click();
        System.out.println("Kelime yazılmadı henüz3.");
        searchInput =wait.until(ExpectedConditions.elementToBeClickable(searchArea));
        searchInput.click();
        System.out.println("Kelime yazılmadı henüz2.");

            Thread.sleep(2000);

        System.out.println("Kelime yazılmadı henüz.");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@data-test-id='search-bar-input']"))))
                .sendKeys("bilgisayar");

            System.out.println("Kelime yazıldı.");


            By suggestionLoc = By.cssSelector("div[data-test-id='search-bar-suggestions-container'][aria-label*='bilgisayar']");
            wait.until(ExpectedConditions.elementToBeClickable(suggestionLoc)).click();

    }
}
