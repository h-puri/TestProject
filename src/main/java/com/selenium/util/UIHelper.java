package com.selenium.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIHelper {

	private WebDriver driver;
	private static final int SELENIUM_TIMEOUT_IN_SEC = 20;
	private static final String STRING = "String";
	private static final String WEBELEMENT = "WebElement";

	public UIHelper(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> switchToTabWithoutClosingMainWindow() {
		boolean flag = true;
		Set<String> handles = null;
		for (int i = 0; i <= 3; i++) {
			handles = driver.getWindowHandles();
			if (handles.size() == 2) {
				flag = false;
				break;
			}
			System.out.println("Waiting for new tab to open..");
			waitInSeconds(3);
		}
		if (flag) {
			throw new WebDriverException("Tab window is not opened");
		} else {
			System.out.println("Moving ahead on new tab");
		}

		String mainWindow = driver.getWindowHandle();
		Object[] hndlArr = handles.toArray();
		String valComp = (String) hndlArr[0];
		String tabWindow = null;
		if (valComp.equalsIgnoreCase(mainWindow)) {
			tabWindow = (String) hndlArr[1];
		} else {
			tabWindow = valComp;
		}
		driver.switchTo().window(tabWindow);

		List<String> windowsId = new ArrayList<String>();
		windowsId.add(mainWindow);
		windowsId.add(tabWindow);
		return windowsId;

	}

	public <T> WebElement waitForElementToBeVisible(final T locator) {
		return waitForElementToBeVisible(locator, SELENIUM_TIMEOUT_IN_SEC);
	}

	public <T> WebElement waitForElementToBeVisible(final T locator, int timeOut) {
		System.out.println("Wait for element to be visible on web page: " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			WebElement element = null;
			if (locator.getClass().getName().contains("By")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated((By) locator));
			} else if (locator.getClass().getName().contains(STRING)) {
				element = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getXpath((String) locator))));
			} else if (locator.getClass().getName().contains(WEBELEMENT)) {
				element = wait.until(ExpectedConditions.visibilityOf((WebElement) locator));
			}
			return element;
		} catch (StaleElementReferenceException s) {
			System.out.println(s);
			if (locator.getClass().getName().contains(WEBELEMENT)) {
				throw new StaleElementReferenceException(s.getAdditionalInformation());
			}
			waitInSeconds(2);
			waitForElementToBeVisible(locator, timeOut);
		}
		return null;
	}

	/**
	 * It will type the test data into an input box
	 *
	 * @param locator
	 * @param testData
	 * @throws Exception
	 */
	public void type(By locator, CharSequence... testData) {
		try {
			String logText = "";
			for (int i = 0; i < testData.length; i++) {
				logText = logText + testData[i];
			}
			WebDriverWait wait = new WebDriverWait(driver, SELENIUM_TIMEOUT_IN_SEC);
			WebElement element = null;
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			try {
				if (element.isDisplayed()) {
					element.click();
				}
			} catch (WebDriverException e) {
				System.err.println(e);
			}
			try {
				if (element.isDisplayed()) {
					element.clear();
				}
			} catch (WebDriverException e) {
				System.err.println(e);
			}
			element.sendKeys(testData);
		} catch (StaleElementReferenceException e) {
			System.err.println(e);
			type(locator, testData);
		}

	}

	/**
	 * It clicks on web element
	 *
	 * @param locator
	 * @throws Exception
	 */
	public void click(By locator) {
		System.out.println("Click : " + locator);
		try {
			waitForElementToBeVisible(locator).click();
		} catch (StaleElementReferenceException s) {
			System.out.println(s);
			waitInSeconds(2);
			clickUsingJavaScript(locator);
		} catch (WebDriverException e) {
			if (isElementPresent(locator, 5)) {
				clickUsingJavaScript(locator);
			}
		}

	}

	public boolean isElementPresent(By locator, int waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (WebDriverException e) {
			return false;
		}
	}

	public void clickUsingJavaScript(By locator) {
		System.out.println("clickUsingJavaScript: " + locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = waitForElementToBePresent(locator);
		js.executeScript("arguments[0].click();", element);
	}

	public String getText(By locator) {
		try {
			return waitForElementToBePresent(locator).getText().trim();
		} catch (StaleElementReferenceException s) {
			System.out.println(s);
			waitInSeconds(2);
			getText(locator);
		}
		return null;
	}

	public <T> WebElement waitForElementToBePresent(final T locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, SELENIUM_TIMEOUT_IN_SEC);
			WebElement element = null;
			if (locator.getClass().getName().contains("By")) {
				element = wait.until(ExpectedConditions.presenceOfElementLocated((By) locator));
			} else if (locator.getClass().getName().contains(STRING)) {
				element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getXpath((String) locator))));
			}
			return element;
		} catch (StaleElementReferenceException s) {
			System.out.println(s);
			waitInSeconds(2);
			waitForElementToBePresent(locator);
		}
		return null;
	}

	public boolean isElementVisible(By locator, int waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (WebDriverException e) {
			return false;
		}

	}

	public void waitInSeconds(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public String getXpath(String text) {
		return "//*[contains(text(),'" + text + "')]";
	}

	public List<WebElement> getElements(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, SELENIUM_TIMEOUT_IN_SEC);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

}
