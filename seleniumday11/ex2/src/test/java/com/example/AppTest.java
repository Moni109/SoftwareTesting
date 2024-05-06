package com.example;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AppTest 
{
    WebDriver driver;
    Test test;
    WebDriverWait wait;
    ExtentReports reports;
    ExtentSparkReporter spark;
    @BeforeTest
    public void beforeTest()
    {
        driver = new ChromeDriver();
        driver.get("https://www.ixigo.com/");
        driver.manage().window().maximize();
        wait  = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @Test
    public void testcase1forixigo() throws InterruptedException
    {
        try{
            String url = "C:\\Users\\kskm4\\Downloads\\seleniumday11\\ex2\\src\\excelSheet\\Data.xlsx";
            FileInputStream fis = new FileInputStream(url);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Booking Details");

            String from = sheet.getRow(1).getCell(0).getStringCellValue();
            String to = sheet.getRow(1).getCell(1).getStringCellValue();

            System.out.print(from);
            WebElement a = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]")));
            a.click();

            // driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]")).click();
            Thread.sleep(3000);
            
            // WebElement Frominput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div")));
            // Frominput.sendKeys(from);
            // // System.out.print("Hiiiiii");
            // Thread.sleep(3000);
            // System.out.println(from);
            
            WebElement Toinput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div")));
            Toinput.sendKeys(to);
            System.out.println(to);
            
            Thread.sleep(3000);
                driver.close();
            
            

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // @AfterTest
    // public void afterTest{
    // }
}
