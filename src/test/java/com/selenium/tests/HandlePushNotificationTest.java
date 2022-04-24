package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.selenium.util.UIHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlePushNotificationTest {
	private WebDriver driver;
	private By sendNotificationButton = By.xpath("//*[text()='Send notification']");

	/**
	 * Push notification can be enabled/Disabled by selenium using Chrome options as
	 * selenium click events cannot detect it as it is not a part of DOM.
	 * 
	 * In below code, I have disabled the push notification as it will not appear if
	 * you click on Send Notification button
	 * 
	 */

	@Test
	public void test_handlePushNotification() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://push.gravitec.net/demo?lang=en");
		UIHelper uiHelper = new UIHelper(driver);
		uiHelper.click(sendNotificationButton);
		uiHelper.waitInSeconds(4);
		driver.quit();
	}

}
