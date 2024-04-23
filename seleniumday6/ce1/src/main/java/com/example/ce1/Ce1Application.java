package com.example.ce1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ce1Application {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		Actions actions = new Actions(driver);
		driver.get("https://economictimes.indiatimes.com/et-now/results/");
		driver.findElement(By.linkText("Mutual Funds")).click();
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
		actions.scrollFromOrigin(scrollOrigin, 0, 500).perform();
		Thread.sleep(1000);
		WebElement test1dropdown = driver.findElement(By.xpath("//*[@id='amcSelection']"));
		Select dropdown1 = new Select(test1dropdown);
		dropdown1.selectByVisibleText("Canara Robeco");
		Thread.sleep(1000);
		WebElement test2dropdown = driver.findElement(By.id("schemenm"));
		Select dropdown2 = new Select(test2dropdown);
		dropdown2.selectByValue("15765");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='getDetails']")).click();
		Thread.sleep(10000);
		String originalwindow = driver.getWindowHandle();
		driver.getWindowHandles();
		for(String handle:driver.getWindowHandles()){
			if(!originalwindow.contentEquals(handle)){
				System.out.println(handle.toString());
				driver.switchTo().window(handle);
				break;
			}
		}
		actions.scrollFromOrigin(scrollOrigin, 0, 200).perform();
		Thread.sleep(1000);
		WebElement test3dropdown = driver.findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[2]/ul"));
		test3dropdown.click();
		driver.findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[2]/ul/li/ul/li[1]/span")).click();
		Thread.sleep(1000);
		WebElement test4dropdown = driver.findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[3]/ul"));
		test4dropdown.click();
		driver.findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[3]/ul/li/ul/li[3]/span")).click();;
		Thread.sleep(1000);
		WebElement test5dropdown = driver.findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[4]/ul"));
		test5dropdown.click();
		driver.findElement(By.xpath("/html/body/main/div[10]/section[1]/div/div[2]/div[1]/div[4]/ul/li/ul/li[3]/span")).click();
		Thread.sleep(1000);
		actions.scrollFromOrigin(scrollOrigin, 0, 500).perform();
		driver.findElement(By.xpath("/html/body/main/div[10]/section[3]/div/ul/li[2]")).click();
		WebElement returns = driver.findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]"));
		System.out.println(returns.getText());
		
		SpringApplication.run(Ce1Application.class, args);
	}

}
