package com.selenium.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.util.UIHelper;

public class YoutubePage {

	WebDriver driver;
	UIHelper uiHelper;

	public YoutubePage(WebDriver driver) {
		this.driver = driver;
		this.uiHelper = new UIHelper(driver);
	}

	public static final By thumbNail = By.xpath("//*[@id='page-manager']//a[@id='thumbnail']");
	public static final By fullScreenButton = By
			.xpath("//*[@class='ytp-chrome-controls']//button[@title='Full screen (f)']");
	public static final By exitFullScreenButton = By
			.xpath("//*[@class='ytp-chrome-controls']//button[contains(@title,'Exit full screen')]");
	public static final By videoPlayer = By.xpath("//*[@id='ytd-player']//*[contains(@class,'video-player')]");

	public static final By skipTrialButton = By.xpath("//*[text()='Skip trial']");
	public static final By noThanksButton = By.xpath("//*[contains(text(),'No thanks')]");

	public void verifyVideoChromeButtonDisplayed(By locator) {
		displayVideoChromeButtons();
		uiHelper.waitForElementToBePresent(locator);
	}

	public void clickVideoChromeButton(By locator) {
		displayVideoChromeButtons();
		List<WebElement> list = uiHelper.getElements(locator);
		if (list.size() > 1) {
			list.get(1).click();
		} else {
			uiHelper.click(locator);
		}
	}

	public void displayVideoChromeButtons() {
		if (uiHelper.isElementVisible(skipTrialButton, 1)) {
			uiHelper.click(skipTrialButton);
		}
		if (uiHelper.isElementVisible(noThanksButton, 1)) {
			uiHelper.click(noThanksButton);
		}
		getVideoPlayerElement().click();
	}

	public Dimension getVideoPlayerElementSize() {
		Dimension dimension = null;
		for (int i = 0; i < 10; i++) {
			dimension = getVideoPlayerElement().getSize();
			if (dimension.width != 0 && dimension.height != 0) {
				break;
			}
			System.out.println("Element size is not defined. Trying again...");
			uiHelper.waitInSeconds(3);
		}

		return dimension;
	}

	private WebElement getVideoPlayerElement() {
		List<WebElement> list = uiHelper.getElements(videoPlayer);
		WebElement element = null;
		if (list.size() > 1) {
			element = list.get(1);
		} else {
			element = list.get(0);
		}
		return element;
	}
}
