package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = createDriver();
		}
		return driver;
	}

	private static WebDriver createDriver() {
		// QAMaster'ın gönderdiği parametreleri alıyoruz
		String browserType = System.getProperty("browserType", "Local");
		String hubUrl = System.getProperty("hubUrl");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--headless=new"); // Sunucu için şart
		options.addArguments("--window-size=1920,1080");

		try {
			// Eğer browserType Remote ise veya bir hubUrl varsa RemoteWebDriver kullan
			if ("Remote".equalsIgnoreCase(browserType) || (hubUrl != null && !hubUrl.isEmpty())) {
				System.out.println("QAMaster Hub'ına bağlanılıyor: " + hubUrl);
				return new RemoteWebDriver(new URL(hubUrl), options);
			} else {
				// Sadece localde çalışırken buraya girer
				System.out.println("Yerel ChromeDriver başlatılıyor...");
				return new ChromeDriver(options);
			}
		} catch (Exception e) {
			System.err.println("Driver oluşturulurken hata: " + e.getMessage());
			throw new RuntimeException("Driver başlatılamadı!", e);
		}
	}
}