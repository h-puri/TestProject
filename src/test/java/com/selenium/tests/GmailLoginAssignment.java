package com.selenium.tests;

import org.testng.annotations.Test;

import com.selenium.page.GmailLoginPage;

public class GmailLoginAssignment extends BaseTest {

	private String userName = "seleniumappiumtests@gmail.com";

	// encrypted password
	private String password = "";

	@Test
	public void test_loginScenario() {
		driver.get("https://teacher.merlyn.org");
		uiHelper.click(GmailLoginPage.signInWithGoogleButton);
		GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
		gmailLoginPage.enterCredentials(userName, password);
		uiHelper.waitForElementToBeVisible(GmailLoginPage.MERLYN_WANTS_ACCESS_MESSAGE);
	}

}
