package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest 
{
    WebDriver driver;
    @BeforeTest
    public void shouldbetestedbefore(){
        driver=new ChromeDriver();
    }
    @Test
    public void shouldbetested() throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get("https://www.moneycontrol.com/");

		driver.manage().window().maximize();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_str")));
		
		driver.findElement(By.id("search_str")).sendKeys("Reliance Industries");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/div[2]/div[2]/ul/li[1]")));
		
		driver.findElement(By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/div[2]/div[2]/ul/li[1]")).click();

		js.executeScript("window.scrollBy(0,500)");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[14]/div[2]/div[2]/div[2]/div[1]/div/h1")));
		WebElement reliance = driver.findElement(By.linkText("Reliance Industries Ltd."));
		
		if(reliance.isDisplayed()){
				System.out.println("Reliance Industries is present in the page");
		}
		else{
			System.out.println("Reliance Industries is not present in the page");
		}

		driver.findElement(By.linkText("Mutual Funds")).click();
		wait.withTimeout(Duration.ofSeconds(30));

		js.executeScript("window.scrollBy(0,500)");

		driver.findElement(By.xpath("/html/body/section/section[1]/div/div/div[1]/div[5]/div/div/div/div[1]/div/div[3]/div[1]/div[1]/ul/li[2]/a")).click();

		Thread.sleep(1000);

    }
    @AfterTest
    public void shouldbetestedafter(){
        driver.quit();
    }
}
