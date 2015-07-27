package com.springer.materials;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpringerMaterial {
	static final Logger logger = Logger.getLogger(SpringerMaterial.class);
	public static void main(String[] args) {
		BasicConfigurator.configure();
	    
		WebDriver wd = applicationLaunch();
		WebDriverWait wdw=new WebDriverWait(wd, 30);
		ackImproveButton(wd, wdw);
		enterSearchElement(wd, wdw);
		clickSearch(wd, wdw);
		selectElementFromSearchResult(wd);
		lookInteractiveStructure(wd, wdw);
		applicationExit(wd);
		
	}

	private static void applicationExit(WebDriver wd) {
		logger.debug("applicationExit");
		wd.quit();
	}

	private static void lookInteractiveStructure(WebDriver wd, WebDriverWait wdw) {
		logger.debug("lookInteractiveStructure");
		wdw.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='look-inside__badge interactive-structure-badge']")));
		wd.findElement(By.xpath("//*[@class='look-inside__badge interactive-structure-badge']")).click();
	}

	private static void selectElementFromSearchResult(WebDriver wd) {
		logger.debug("selectElementFromSearchResult");
		List<WebElement> we=wd.findElements(By.xpath("//div[@class='media__body']/h2/a/*[text()='Methanol']"));
		for (WebElement webElement : we) {
			webElement.click();
			break;
		}
	}

	private static void clickSearch(WebDriver wd, WebDriverWait wdw) {
		logger.debug("clickSearch");
		wdw.until(ExpectedConditions.elementToBeClickable(By.id("search")));
		wd.findElement(By.id("search")).click();
	}

	private static void enterSearchElement(WebDriver wd, WebDriverWait wdw) {
		logger.debug("enterSearchElement");
		wdw.until(ExpectedConditions.elementToBeClickable(By.id("searchTerm")));
		wd.findElement(By.id("searchTerm")).sendKeys("methanol");
	}

	private static void ackImproveButton(WebDriver wd, WebDriverWait wdw) {
		logger.debug("ackImproveButton");
		wdw.until(ExpectedConditions.elementToBeClickable(By.id("iAmGoodBtn")));
		wd.findElement(By.id("iAmGoodBtn")).click();
	}

	private static WebDriver applicationLaunch() {
		logger.debug("applicationLaunch");
		WebDriver wd = new FirefoxDriver();
		wd.get("http://materials.springer.com/");
		return wd;
	}
}

