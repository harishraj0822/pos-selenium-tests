package com.framework.selenium.api.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DriverInstance{
	private static final ThreadLocal<RemoteWebDriver> remoteWebdriver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<WebDriverWait> wait = new  ThreadLocal<WebDriverWait>();

	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(10)));
	}

	public WebDriverWait getWait() {
		return wait.get();
	}

	public void setDriver(String browser, boolean headless) throws MalformedURLException {		
		switch (browser) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); 
			options.addArguments("--disable-notifications"); 
			options.addArguments("--incognito");
		  	DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName("chrome");
			dc.setPlatform(Platform.LINUX);
			options.merge(dc);

			remoteWebdriver.set(new RemoteWebDriver(new URL("http://20.40.53.202:4444/wd/hub"), options));
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			DesiredCapabilities desiredCap = new DesiredCapabilities();
			desiredCap.setBrowserName("firefox");
			desiredCap.setPlatform(Platform.LINUX);
			firefoxOptions.merge(desiredCap);
			remoteWebdriver.set(new RemoteWebDriver(new URL("http://20.40.53.202:4444/wd/hub"), firefoxOptions));
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			DesiredCapabilities desiredCapEdge = new DesiredCapabilities();
			desiredCapEdge.setBrowserName("MicrosoftEdge");
			desiredCapEdge.setPlatform(Platform.LINUX);
			edgeOptions.merge(desiredCapEdge);
			remoteWebdriver.set(new RemoteWebDriver(new URL("http://20.40.53.202:4444/wd/hub"), edgeOptions));
			break;	
		case "ie":
			remoteWebdriver.set(new InternetExplorerDriver());
		default:
			break;
		
		}
	}
	public RemoteWebDriver getDriver() {
		return remoteWebdriver.get();
	}
	
}
