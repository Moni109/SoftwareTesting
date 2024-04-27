package com.example.pah2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootApplication
public class Pah2Application {

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();

		String url = "https://www.demoblaze.com/#";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		Thread.sleep(10000);
		
		//select phone
		WebElement phone = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[2]"));
		phone.click();
		
		
		WebElement firstElement = driver.findElement(By.xpath("//*[@id='tbodyid']//h4/a"));

		String element = firstElement.getText();
		System.out.println(element);
		firstElement.click();
		Thread.sleep(3000);
		
		String phonename = "Samsung galaxy s6";
		if(element.equals(phonename)){
			System.out.println("It is present");
		}
		else{
			System.out.println("It is not present");
		}
		
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor)driver;
		Long length = (Long) js.executeScript("return document.body.scrollHeight;");
		System.out.println("Title of the page "+driver.getTitle());
		System.out.println("Length of the page"+length);

		Thread.sleep(3000);
		driver.close();

		SpringApplication.run(Pah2Application.class, args);
	}

}
