package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class ListingPage extends Driver {
    WebElement productList = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'productListContent')]"));
    WebElement productRow = webDriver.findElement(By.id("i0"));
    List<WebElement> Products = webDriver.findElements(By.xpath("//ul[@id='1']//li[starts-with(@id,'i')]"));
    public void setListGridControl() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(productList));
        String display =  productList.getCssValue("display");
        assertThat(display).as("grid şeklinde değil").isEqualTo("grid");

    }
    public int calculateRow(){
        int count = 0;
        int firstRow = Products.get(0).getLocation().getY();
        if(Products.isEmpty())
            return 0;
        for (WebElement product : Products){
            if(product.getLocation().getY() == firstRow)
                count++;
            if(product.getLocation().getY() != firstRow)
                break;
        }
        return count;
    }
    public void ClickSecondRowFirstItem(){
    int element = calculateRow();
    Products.get(element).click();
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
        }

        System.out.println(webDriver.getCurrentUrl());
    }
}
