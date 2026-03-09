package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends Driver {

    public void FillTheBlanks() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;


        String script = "return document.querySelector('efilli-layout-dynamic').shadowRoot" +
                ".querySelector('div[data-name=\"Accept Button\"]')";


        try {

            Thread.sleep(2000);

            WebElement acceptBtn = (WebElement) js.executeScript(script);

            if (acceptBtn != null) {
                js.executeScript("arguments[0].click();", acceptBtn);
                System.out.println("Çerez onay butonu başarıyla tıklandı.");
            } else {
                System.out.println("Buton Shadow DOM içinde bulunamadı.");
            }
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
        webDriver.findElement(By.id("txtUserName"))
                .sendKeys("duzgundogan0@gmail.com");

        webDriver.findElement(By.id("txtPassword"))
                .sendKeys("Dzgn2323.");

        webDriver.findElement(By.id("btnLogin"))
                .click();
    }
}