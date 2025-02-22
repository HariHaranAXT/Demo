package com.selenese;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.get("https://www.volvocars.com/in/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
//		try {
//		Alert al = driver.switchTo().alert();
//		al.wait(5);
//		al.accept();
//		driver.switchTo().defaultContent();
//			
//		WebElement accept = driver.findElement(By.xpath("Accept "));
//		WebElement acc = wait.until(ExpectedConditions.visibilityOf(accept));
//		acc.click();
//		
//		}catch (Exception e) {
//			System.out.println("No Cookie  Box");
//		}
		Thread.sleep(3000);
		Actions ac = new Actions(driver); 
		ac.sendKeys(Keys.TAB,Keys.ENTER).build().perform();
		 
		JavascriptExecutor js = (JavascriptExecutor) driver; // DownCasting 
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
		try {
			
		String xpath = "//h2[text()='EX40']";
		
		
		WebElement car = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		js.executeScript("arguments[0].scrollIntoView(true);", car);
		js.executeScript("arguments[0].click();", car);
		System.out.println("Car has been clicked");
		}catch (Exception e) {
			System.out.println("Element is Not Clickable");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		
		WebElement spec = driver.findElement(By.xpath("//div[text()='Specifications']"));
		System.out.println("Specification Clicked");
		WebElement clic = wait.until(ExpectedConditions.elementToBeClickable(spec));
		
		js.executeScript("arguments[0].click();", clic);
		System.out.println("Specification Clicked");
		
		
		String nedc = "Electric energy consumption combined (NEDC)";
		String consump = "17.6 kWh/100km";
		
		List<String> data = new ArrayList<String>();
		data.add(nedc);
		data.add(consump);
		
		for(String text : data) {
			
			WebElement specs = driver.findElement(By.xpath("//div[@data-cell-id='0-2-0']/p[text()='"+text+"']"));
			String out = specs.getText();
			System.out.println(out);
		}
		
		 
		
		
		
	}

}
