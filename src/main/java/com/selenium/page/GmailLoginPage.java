package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium.util.CryptoUtil;
import com.selenium.util.UIHelper;

public class GmailLoginPage {
	WebDriver driver;
	UIHelper uiHelper;

	public GmailLoginPage(WebDriver driver) {
		this.driver = driver;
		this.uiHelper = new UIHelper(driver);
	}

	public static final By signInWithGoogleButton = By.xpath("//button[@id='btn-google']");
	public static final By emailTextBox = By.xpath("//input[@type='email']");
	public static final By nextButton = By.xpath("//span[contains(text(),'Next')]");
	public static final By showPassword = By.xpath("//div[contains(text(),'Show password')]");
	public static final By passwordTextBox = By.xpath("//input[@type='password']");
	public static final String MERLYN_WANTS_ACCESS_MESSAGE = "Merlyn Mind wants access to your Google Account";
	
	public void enterCredentials(String userName, String password) {
		uiHelper.type(GmailLoginPage.emailTextBox, userName);
		uiHelper.click(GmailLoginPage.nextButton);
		uiHelper.waitForElementToBeVisible(GmailLoginPage.showPassword);
		uiHelper.type(GmailLoginPage.passwordTextBox, new CryptoUtil().decrypt(password));
		uiHelper.click(GmailLoginPage.nextButton);
	}

}
