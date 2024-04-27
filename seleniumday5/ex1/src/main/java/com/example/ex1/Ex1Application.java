package com.example.ex1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Ex1Application {

	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.demoblaze.com/");
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement laptop = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Laptops")));
		laptop.click();

		WebElement macbook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook air")));
		macbook.click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#tbodyid > div.row > div > a")).click();
		
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='cartur']")).click();
		Thread.sleep(2000);
		
		WebElement element1 = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]"));
		String title = element1.getText();
		
		Thread.sleep(2000);
		WebElement element2 = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[3]"));
		String price = element2.getText();
		
		System.out.println(title);
		System.out.println(price);
		Thread.sleep(2000);
		driver.close();
		SpringApplication.run(Ex1Application.class, args);
	}

}
