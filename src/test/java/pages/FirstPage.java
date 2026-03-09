package pages;

import driver.Driver;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstPage extends Driver{


    protected final String  url= "https://www.hepsiburada.com/";
    public void GoToSite(){

        System.out.println("deneme");
        webDriver.get(url);
        //Accept Button

    }
    public void clickBtn(){

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));


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

        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("myAccount"))));
        WebElement element = webDriver.findElement(By.id("myAccount"));

        element.click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("login"))));
        webDriver.findElement(By.id("login")).click();
    }
}

