package com.example.pah;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PahApplication {

	public static void main(String[] args) throws Exception {
		String url = "https://demowebshop.tricentis.com/";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(10000);

		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[6]/a")).click();
		String currenturl = driver.getCurrentUrl();
		if(currenturl.contains("jewelry")){
			System.out.println("Jewelry is there");
		}
		else{
			System.out.println("Jewelry is not there");
		}
		
		driver.navigate().back();
		Thread.sleep(3000);
		if(driver.getCurrentUrl().equals(url)){
			System.out.println("Matches");
		}
		else{
			System.out.println("Not Matches");
		}
		
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		driver.close();


		SpringApplication.run(PahApplication.class, args);
	}

}
