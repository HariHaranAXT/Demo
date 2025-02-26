package com.Demo.selenese;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils; 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkShop {
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		
	
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	TakesScreenshot ts = (TakesScreenshot) driver; 
	
	driver.get("https://www.samsung.com/in/");
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	WebElement Mobiles = driver.findElement(By.xpath("//button[@role='menuitem' and contains(text(), 'Mobile')]"));
	WebElement s25Ultra = driver.findElement(By.xpath("//p[contains(text(),'Galaxy S25 Ultra')]//parent::a[@role='menuitem']"));
	
	Actions ac = new Actions(driver);
	ac.moveToElement(Mobiles).perform();
	System.out.println("Moved to element Mobiles");
	
	try {
		WebElement getPhone = wait.until(ExpectedConditions.visibilityOf(s25Ultra));
		System.out.println("Found S25 Ultra");
		
		js.executeScript("arguments[0].click();", getPhone);
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	

		WebElement buy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Buy now')]")));
		js.executeScript("arguments[0].click();", buy);
		System.out.println("Buy Now Clicked");
	
	//256 GB｜12 GB
	//512 GB｜12 GB
    //1 TB｜12 GB
	}catch (Exception e) {
		System.out.println("Mobile Not visible");
	}
	
	
	List<WebElement> variant = driver.findElements(By.xpath("//span[@class='s-label']//span//span"));
	
	try {
		
	for(WebElement storage : variant) {
		
		String phone = storage.getText();
		String trim = phone.trim(); 
		
		if(trim.equals("1 TB｜12 GB")) {
			
			js.executeScript("arguments[0].click();", storage);
			System.out.println("Selected 1TB Variant");
		}
		
			
		}System.out.println("Fetched Phone");
	}catch (Exception e) {
		System.out.println("Element not Clickable");
	}
	Thread.sleep(3000);
	
	
	File content = ts.getScreenshotAs(OutputType.FILE);
	
	File des = new File("target/output/S25 Ultra.png");
	
	FileUtils.copyFile(content, des); 
	
		
	}
	  
}
