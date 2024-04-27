package com.example.ex1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex1Application {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.shoppersstop.com/");
		SpringApplication.run(Ex1Application.class, args);
	}

}
