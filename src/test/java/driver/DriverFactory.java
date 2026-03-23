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

		options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--incognito");

		String browserType = System.getProperty("browserType", "Local");
		String hubUrl = System.getProperty("QAMASTER_HUB_URL");

// 🔥 CRITICAL FIX
		if (hubUrl != null && hubUrl.contains("localhost")) {
			System.out.println("Fixing localhost → host.docker.internal");
			hubUrl = hubUrl.replace("localhost", "host.docker.internal");
		}

		if ("Remote".equalsIgnoreCase(browserType)) {
			if (hubUrl == null || hubUrl.isEmpty()) {
				throw new RuntimeException("HUB URL boş!");
			}

			try {
				System.out.println("Using HUB URL: " + hubUrl);
				return new RemoteWebDriver(new URL(hubUrl), options);
			} catch (Exception e) {
				throw new RuntimeException("Remote WebDriver bağlanamadı", e);
			}
		}

		return new ChromeDriver(options);
	}
}