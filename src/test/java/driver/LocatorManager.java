package driver;

import com.google.gson.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorManager extends Driver {

    private static Map<String, Locator> elementMap = new HashMap<>();
    private static JsonObject testData;

    // Locator modeli
    public static class Locator {
        String value;
        String type;

        public Locator(String value, String type) {
            this.value = value;
            this.type = type;
        }
    }

    // element.json yükleme
    public static void loadElements(String filePath) {

        try (FileReader reader = new FileReader(filePath)) {

            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            Map<String, Locator> tempMap = new HashMap<>();

            for (JsonElement element : array) {

                JsonObject obj = element.getAsJsonObject();

                String key = obj.get("key").getAsString();
                String value = obj.get("value").getAsString();
                String type = obj.get("type").getAsString();

                tempMap.put(key, new Locator(value, type));
            }

            elementMap = tempMap;

        } catch (Exception e) {
            throw new RuntimeException("element.json yüklenirken hata oluştu", e);
        }
    }

    // data.json yükleme
    public static void loadData(String filePath){

        try(FileReader reader = new FileReader(filePath)){

            testData = JsonParser.parseReader(reader).getAsJsonObject();

        }catch (Exception e){

            throw new RuntimeException("data.json okunamadı",e);
        }
    }

    // data.json'dan veri çekme
    public static String getData(String key){

        if(testData == null)
            throw new RuntimeException("data.json yüklenmedi");

        return testData.get(key).getAsString();
    }

    // Locator üretme
    public static By getBy(String key) {

        Locator locator = elementMap.get(key);

        if (locator == null)
            throw new RuntimeException("Locator bulunamadı: " + key);

        switch (locator.type) {

            case "id":
                return By.id(locator.value);

            case "xpath":
                return By.xpath(locator.value);

            case "css":
                return By.cssSelector(locator.value);

            case "name":
                return By.name(locator.value);

            default:
                throw new RuntimeException("Desteklenmeyen locator type: " + locator.type);
        }
    }

    public static void Start() {
        webDriver.get("https://www.hepsiburada.com/");
    }

    public static void AcceptCookie(String key) {
//        JavascriptExecutor js = (JavascriptExecutor) webDriver;
//        js.executeScript(
//                "document.querySelector('efilli-layout-dynamic')" +
//                        ".shadowRoot.querySelector(\"div[data-name='Accept Button']\").click()"
//        );
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//
//        wait.until(webDriver ->
//                ((JavascriptExecutor) webDriver).executeScript(
//                        "return document.querySelector('efilli-layout-dynamic') != null"
//                )
//        );
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

        wait.until(driver -> {
            Object res = ((JavascriptExecutor) driver).executeScript(
                    "let root = document.querySelector('efilli-layout-dynamic');" +
                            "if (!root) return false;" +
                            "let shadow = root.shadowRoot;" +
                            "if (!shadow) return false;" +
                            "let btn = shadow.querySelector(\"div[data-name='Accept Button']\");" +
                            "if (!btn) return false;" +
                            "btn.click(); return true;"
            );
            return Boolean.TRUE.equals(res);
        });
    }

    public static void ClickButton(String key) {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(getBy(key))
        );

        element.click();
    }

    public static void FillTheBlanks(String key, String input) {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(getBy(key))
        );

        element.sendKeys(input);
    }

    public static void setListGridControl(String key) {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement list = wait.until(
                ExpectedConditions.visibilityOfElementLocated(getBy(key))
        );

        String display = list.getCssValue("display");

        assertThat(display)
                .as("grid şeklinde değil")
                .isEqualTo("grid");
    }

    public static int calculateRow(String key) {

        List<WebElement> products = webDriver.findElements(getBy(key));

        if (products.isEmpty())
            return 0;

        int count = 0;
        int firstRow = products.get(0).getLocation().getY();

        for (WebElement product : products) {

            if (product.getLocation().getY() == firstRow)
                count++;

            if (product.getLocation().getY() != firstRow)
                break;
        }

        return count;
    }

    public static void ClickSecondRowFirstItem(String key) {

        int element = calculateRow(key);

        List<WebElement> products = webDriver.findElements(getBy(key));
        products.get(element).click();

        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
        }

        System.out.println(webDriver.getCurrentUrl());
    }

    public static void ScrollInto(String key) {

        WebElement element = webDriver.findElement(getBy(key));

        ((JavascriptExecutor) webDriver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
}