package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.selenium.util.UIHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected UIHelper uiHelper;

	@BeforeMethod
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		uiHelper = new UIHelper(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
