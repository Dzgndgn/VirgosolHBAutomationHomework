package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemInfoPage extends Driver {

    public void ClickButtonToChart(){
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//        By btnSepeteEkle = By.xpath("//button[@data-test-id='addToCart']");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test-id='title-area']")));
//        WebElement btn = webDriver.findElement(btnSepeteEkle);
//        JavascriptExecutor executor =(JavascriptExecutor) webDriver;
//        executor.executeScript("arguments[0].scrollIntoView({block:'center'});",btn);
//        wait.until(ExpectedConditions.elementToBeClickable(btnSepeteEkle));
//        btn.click();




            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            System.out.println(webDriver.getCurrentUrl());
            By btnSepeteEkle = By.xpath("//div//button[@data-test-id='addToCart']");

            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(btnSepeteEkle));

            ((JavascriptExecutor) webDriver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

            wait.until(ExpectedConditions.elementToBeClickable(btn));

            btn.click();
        By btnToChart = By.xpath("//a[@href='https://checkout.hepsiburada.com/sepetim']");
        WebElement btnTo = webDriver.findElement(btnToChart);
        ((JavascriptExecutor) webDriver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", btnTo);
        webDriver.findElement(btnToChart).click();

    }
}
