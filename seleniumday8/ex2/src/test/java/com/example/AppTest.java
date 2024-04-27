package com.example;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @BeforeTest
    public void shouldTestWebsite() throws InterruptedException{
		driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
		Thread.sleep(3000);
        
    }
    @Test
    public void testcase1forDemoBlaze() throws InterruptedException
    {
		WebElement laptop = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]")));
        laptop.click();

		WebElement macbook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook air")));
        macbook.click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
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
		Thread.sleep(3000);
		
    }

    @Test
    public void testcase2forDemoBlaze() throws Exception{
        this.driver.navigate().to("https://www.demoblaze.com/");

		String path = "C:\\Users\\kskm4\\Downloads\\seleniumday8\\ex2\\src\\excelSheet\\New XLSX Worksheet.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		String username = sheet.getRow(1).getCell(0).getStringCellValue();
		String password = sheet.getRow(1).getCell(1).getStringCellValue();

		driver.findElement(By.cssSelector("#login2")).click();
		WebElement uname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        uname.sendKeys(username);

		WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));
        pass.sendKeys(password);
        
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
		Thread.sleep(3000);

		WebElement element = driver.findElement(By.xpath("//*[@id='nameofuser']"));
		String result = element.getText();
		if(result.equals("Welcome Testalpha")){
			System.out.println("Checked!");
		}
		else{
			System.out.println("Not Logged In");

		}
		Thread.sleep(3000);
		workbook.close();
		fis.close();
    }

	@AfterTest
	public void testAfter(){
		driver.close();
	}
	
}
