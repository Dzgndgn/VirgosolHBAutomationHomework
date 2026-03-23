package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

public class DriverFactory {

	public static WebDriver getDriver() {
		ChromeOptions options = new ChromeOptions();

		// Temel Ayarlar
		options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu"); // Linux için ekle
		options.addArguments("--window-size=1920,1080"); // Ekran boyutu kritik
		options.addArguments("--incognito");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);

		String browserType = System.getProperty("browserType", "Local"); // Varsayılan değer
		String hubUrl = System.getProperty("hubUrl");

		if ("Remote".equalsIgnoreCase(browserType)) {
			if (hubUrl == null || hubUrl.isEmpty()) {
				throw new RuntimeException("Hata: browserType 'Remote' seçili ama 'hubUrl' boş!");
			}
			try {
				return new RemoteWebDriver(new URL(hubUrl), options);
			} catch (Exception e) {
				// Hatayı yutma, fırlat ki nerede koptuğunu anla
				throw new RuntimeException("Remote WebDriver sunucuya bağlanamadı: " + e.getMessage());
			}
		}

		// Sadece browserType 'Remote' DEĞİLSE buraya düşer
		return new ChromeDriver(options);
	}
}