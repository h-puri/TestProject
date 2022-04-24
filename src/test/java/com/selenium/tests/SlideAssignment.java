package com.selenium.tests;

import org.testng.annotations.Test;

import com.selenium.page.SlidePage;

public class SlideAssignment extends BaseTest {

	@Test
	public void test_slideScenario() {
		driver.get("https://docs.google.com/presentation/d/1P7I1mG1Z5kK1y99gpsIq1D4JCgbmnkSpLoB_PoX-Hn4");
		SlidePage slidePage = new SlidePage(driver);
		slidePage.switchToPresentationMode();
		uiHelper.switchToTabWithoutClosingMainWindow();
		slidePage.moveToSlide(7, 32);
	}

}
