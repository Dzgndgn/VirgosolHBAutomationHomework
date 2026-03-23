package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Collections;

public class DriverFactory {

	public static WebDriver getDriver() {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--incognito");
		options.addArguments("--disable-blink-features=AutomationControlled");

		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);

		try {
			String browserType = System.getProperty("browserType");
			String hubUrl = System.getProperty("hubUrl");

			// 🔴 CRITICAL FIX: Remote destek
			if ("Remote".equalsIgnoreCase(browserType)) {
				return new RemoteWebDriver(new URL(hubUrl), options);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// default local
		return new ChromeDriver(options);
	}
}