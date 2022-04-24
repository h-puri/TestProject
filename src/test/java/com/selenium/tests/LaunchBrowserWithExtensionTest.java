package com.selenium.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowserWithExtensionTest {
	private WebDriver driver;
	
	@Test
	public void test_launchWithExtenstion() {
		launchBrowserWithExtension();
		driver.quit();
	}
	
	public void launchBrowserWithExtension() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File(System.getProperty("user.dir") + "/ext/extension.zip"));
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

}
