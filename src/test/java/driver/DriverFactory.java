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
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--incognito");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);

// Browser tipi
		String browserType = System.getProperty("browserType", "Local");

// 🔥 KRİTİK DÜZELTME: QAMASTER_HUB_URL kullan
		String hubUrl = System.getProperty("QAMASTER_HUB_URL");

		if ("Remote".equalsIgnoreCase(browserType)) {
			if (hubUrl == null || hubUrl.isEmpty()) {
				throw new RuntimeException("Hata: Remote seçili ama QAMASTER_HUB_URL boş!");
			}

			try {
				System.out.println("Using HUB URL: " + hubUrl);
				return new RemoteWebDriver(new URL(hubUrl), options);
			} catch (Exception e) {
				// 🔥 stacktrace kaybolmasın
				throw new RuntimeException("Remote WebDriver sunucuya bağlanamadı", e);
			}
		}

// Local çalıştırma
		return new ChromeDriver(options);
	}
}