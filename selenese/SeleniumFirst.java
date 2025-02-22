package com.selenese;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumFirst {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		
		driver.manage().window().maximize();
		
		WebElement srcBox = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
		
		srcBox.sendKeys("mobiles",Keys.ENTER);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@class='cPHDOP col-12-12']//img[@loading='eager'][1]")).click();
		
		driver.navigate().back();
		Thread.sleep(5000);
		
		driver.navigate().refresh();
		
		Thread.sleep(5000);
		
		
		
		
	}

}
