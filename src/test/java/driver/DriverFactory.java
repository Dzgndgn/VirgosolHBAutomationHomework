package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = createDriver();
		}
		return driver;
	}

	private static WebDriver createDriver() {
		String hubUrl = System.getProperty("hubUrl");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
	 //	options.addArguments("--headless=new");
		options.addArguments("--window-size=1920,1080");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-blink-features=AutomationControlled");
		// Ekstra stabilite argümanları
		options.addArguments("--disable-gpu");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-extensions");

//-------------------
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		try {
			if (hubUrl != null && !hubUrl.isEmpty()) {
				System.out.println("QAMaster Hub'ına bağlanılıyor: " + hubUrl);

				// RemoteWebDriver başlatılırken zaman aşımı (timeout) süresini artıralım
				RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(hubUrl), options);
				remoteDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
				remoteDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

				return remoteDriver;
			} else {
				// Yerel (Local) fallback
				return new org.openqa.selenium.chrome.ChromeDriver(options);
			}
		} catch (Exception e) {
			System.err.println("Driver oluşturulurken KRİTİK HATA: " + e.getMessage());
			// Hatanın detayını görmek için stacktrace yazdıralım
			e.printStackTrace();
			throw new RuntimeException("Driver başlatılamadı!", e);
		}
	}
}