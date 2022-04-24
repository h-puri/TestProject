package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selenium.util.UIHelper;

public class SlidePage {

	WebDriver driver;
	UIHelper uiHelper;

	public SlidePage(WebDriver driver) {
		this.driver = driver;
		this.uiHelper = new UIHelper(driver);
	}

	// Google Slide
	public static final By rightArrowButton = By.id("punch-start-presentation-right");
	public static final By presenterViewButton = By.xpath("//*[contains(text(),'Presenter view')]");
	public static final By slideNumber = By
			.xpath("//*[contains(@class,'goog-flat-menu-button-caption') and contains(text(),'Slide ')]");
	public static final By slideNextButton = By
			.xpath("//*[contains(@class,'page-next')]//div[contains(text(),'Next')]");

	public void switchToPresentationMode() {
		uiHelper.click(rightArrowButton);
		uiHelper.click(presenterViewButton);
	}

	public void moveToSlide(int slideNum, int maxSlideCount) {
		boolean isSlideFound = false;
		for (int i = 0; i < maxSlideCount; i++) {
			int num = Integer.parseInt(uiHelper.getText(slideNumber).split(" ")[1]);
			if (num == slideNum) {
				isSlideFound = true;
				break;
			} else {
				uiHelper.click(slideNextButton);
				uiHelper.waitInSeconds(1);
			}
		}
		Assert.assertTrue(isSlideFound, "slide not found..");
	}
}
