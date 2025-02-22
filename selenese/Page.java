package com.selenese;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	public static void main(String[] args) throws InterruptedException {
		
	
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	driver.get("https://www.samsung.com/in/");
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	WebElement Mobiles = driver.findElement(By.xpath("//button[@role='menuitem' and contains(text(), 'Mobile')]"));
	WebElement s25Ultra = driver.findElement(By.xpath("//p[contains(text(),'Galaxy S25 Ultra')]//parent::a[@role='menuitem']"));
	
	Actions ac = new Actions(driver);
	ac.moveToElement(Mobiles).perform();
	
	try {
		WebElement getPhone = wait.until(ExpectedConditions.visibilityOf(s25Ultra));
		System.out.println("Found S25 Ultra");
		
		js.executeScript("arguments[0].click();", getPhone);
		
		
	}catch (Exception e) {
		System.out.println("Mobile Not visible");
	}
	
	String sAi = "(//a[@href='//www.samsung.com/in/galaxy-ai/']/span[text()='Learn more about Galaxy AI'])[1]";
	
	
	Thread.sleep(3000);
	
	FluentWait<WebDriver> fluWait = new FluentWait<>(driver).
			withTimeout(Duration.ofSeconds(10)).
			ignoring(NoSuchElementException.class);
		WebElement aiClick = fluWait.until(new Function<WebDriver, WebElement>() {

		@Override
		public WebElement apply(WebDriver driver) {
			WebElement aI = driver.findElement(By.xpath(sAi));
			if(aI.isDisplayed()) {
				js.executeScript("arguments[0].click();", aI);
				return aI;
			}
			return null;
		}
		
	});
		
		aiClick.click();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	String curWin = driver.getWindowHandle();
	Set<String> allWin = driver.getWindowHandles();
	
	System.out.println(allWin);
	
	for(String win : allWin) {
		if(!win.equals(curWin)) {
			driver.switchTo().window(win);
			String title = driver.getTitle();
			System.out.println(title);
		}
	}
	
	}
	
	
	
	
	}

