package com.example.day5pahselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Day5pahseleniumApplication {

	public static void main(String[] args) throws Exception{
		WebDriver driver = new ChromeDriver();
		Actions actions = new Actions(driver);
		driver.get("https://magento.softwaretestingboard.com/");
		driver.findElement(By.cssSelector("#search")).sendKeys("shoes");
		driver.findElement(By.cssSelector("#search_mini_form > div.actions > button")).click();
		actions.moveToElement(driver.findElement(By.linkText("Men"))).perform();
		actions.moveToElement(driver.findElement(By.linkText("Tops"))).perform();
		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
		actions.scrollFromOrigin(scrollOrigin, 0, 500).perform();
		driver.findElement(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]/div[1]/div/a[2]/span[2]/span[2]")).click();
		driver.findElement(By.xpath("//*[@id='maincontent']/div[3]/div[1]/div[4]/ol/li[3]/div/a/span/span/img")).click();
		actions.scrollFromOrigin(scrollOrigin, 0, 100).perform();
		driver.findElement(By.xpath("//*[@id='option-label-size-143-item-167']")).click();
		driver.findElement(By.xpath("//*[@id='option-label-color-93-item-56']")).click();
		driver.findElement(By.xpath("//*[@id='qty']")).clear();
		driver.findElement(By.xpath("//*[@id='qty']")).sendKeys("4");
		driver.findElement(By.xpath("//*[@id='product-addtocart-button']")).click();
		
		Thread.sleep(3000);
		driver.close();

		SpringApplication.run(Day5pahseleniumApplication.class, args);
	}

}
// driver.get("https://demo.automationtesting.in/Frames.html");
// 		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
// 		actions.scrollFromOrigin(scrollOrigin, 0, 300).perform();
// 		Thread.sleep(2000);
// 		driver.switchTo().frame("singleframe");
// 		driver.findElement(By.xpath("/html/body/section/div/div/div/input")).sendKeys("SKCET");
// driver.get("https://demo.automationtesting.in/Frames.html");
// 		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
// 		actions.scrollFromOrigin(scrollOrigin, 0, 300).perform();
// 		Thread.sleep(2000);
// 		driver.switchTo().frame("singleframe");
// 		driver.findElement(By.xpath("/html/body/section/div/div/div/input")).sendKeys("SKCET");