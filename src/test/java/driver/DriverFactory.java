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
		ChromeOptions options = new ChromeOptions();

		// Sunucu ortamında (Linux/Docker) çalışması için zorunlu ayarlar
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");

		// SUNUCUDA ÇALIŞIYORSAN HEADLESS ŞARTTIR
		options.addArguments("--headless=new");

		try {
			// QAMaster veya Sistem Değişkenlerinden gelen Hub URL'ini kontrol et
			String hubUrl = System.getProperty("DQAMASTER_HUB_URL");

			if (hubUrl != null && !hubUrl.isEmpty()) {
				System.out.println("Connecting to Remote Driver at: " + hubUrl);
				return new RemoteWebDriver(new URL(hubUrl), options);
			} else {
				// Eğer hub yoksa yerel çalıştır (Local debug için)
				return new ChromeDriver(options);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Driver başlatılamadı!");
		}
	}
}