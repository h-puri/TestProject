package com.selenium.tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.page.YoutubePage;

public class YoutubeAssignment extends BaseTest {

	@Test
	public void test_fullScreenVideoScenario() {
		driver.get("https://www.youtube.com/");
		// wait for thumbNail
		uiHelper.waitForElementToBeVisible(YoutubePage.thumbNail);

		// Click on first Video
		uiHelper.getElements(YoutubePage.thumbNail).get(0).click();

		// Read video player dimension before make it full screen
		YoutubePage youtubePage = new YoutubePage(driver);
		Dimension elementSizeBefore = youtubePage.getVideoPlayerElementSize();
		System.out.println("------------Before--------------------------------------");
		System.out.println(elementSizeBefore.width);
		System.out.println(elementSizeBefore.height);

		// Make it full screen
		youtubePage.clickVideoChromeButton(YoutubePage.fullScreenButton);

		// verify exit full screen button should be displayed on video Player
		youtubePage.verifyVideoChromeButtonDisplayed(YoutubePage.exitFullScreenButton);

		Dimension elementSizeAfter = youtubePage.getVideoPlayerElementSize();
		System.out.println("------------After--------------------------------------");
		System.out.println(elementSizeAfter.width);
		System.out.println(elementSizeAfter.height);

		// verify element size is increased
		Assert.assertTrue(elementSizeAfter.width > elementSizeBefore.width);
		Assert.assertTrue(elementSizeAfter.height > elementSizeBefore.height);

		// exit full screen mode
		youtubePage.clickVideoChromeButton(YoutubePage.exitFullScreenButton);

		// Verify video player switch to normal mode
		youtubePage.verifyVideoChromeButtonDisplayed(YoutubePage.fullScreenButton);

	}

}
