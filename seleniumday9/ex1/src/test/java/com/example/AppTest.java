package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AppTest 
{

    WebDriver driver;
    WebDriverWait wait;
    ExtentTest test;
    ExtentReports reports;
    JavascriptExecutor jse;

    @BeforeTest
    public void beforeTest() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://groww.in/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\kskm4\\Downloads\\seleniumday9\\ex1\\src\\report\\Reports.html");
        reports = new ExtentReports();
        reports.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);

        jse = (JavascriptExecutor) driver;
    }
   
    @Test
    public void testcaseforGroww() throws Exception
    {

        WebElement footer = driver.findElement(By.xpath("//*[@id='footer']/div/div[1]/div/div[1]/div[2]/div[3]/div"));
        footer.click();

        Thread.sleep(3000);
        
        jse.executeScript("window.scrollBy(0,500)");
        WebElement calc = driver.findElement(By.xpath("//*[@id='footer']/div/div[1]/div/div[1]/div[2]/div[3]/a[2]"));
        calc.click();
        Thread.sleep(3000);
        
        WebElement element =driver.findElement(By.xpath("//*[@id=\'root\']/div[2]/h1"));
        String result = element.getText();
        
        ExtentTest test1 = reports.createTest("Test case 2");
        driver.manage().timeouts().getPageLoadTimeout();
        if(result.equals("Calculators")){
            test1.log(Status.PASS, "Found!" );
        }
        else{
            test1.log(Status.FAIL, "Not Found!");
        }
        
        Thread.sleep(3000);
        
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshot.png"));

        jse.executeScript("window.scrollBy(0,500)");

        WebElement homeloan = driver.findElement(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div/p[1]"));
        homeloan.click();

        test = reports.createTest("Test case 2 ");
        String loanpage = driver.getWindowHandle();
        for(String handle:driver.getWindowHandles()){
            if(handle.equals(loanpage)){
                test.log(Status.PASS, "HomeLoan page redirected");
                driver.switchTo().window(handle);
                System.out.println("Homeloan Page");
                break;
            }
            else{
                test.log(Status.FAIL, "HomeLoan is not page redirected");
            }
        }
        
        Thread.sleep(2000);
        
        FileInputStream fis = new FileInputStream("C:\\Users\\kskm4\\Downloads\\seleniumday9\\ex1\\src\\excelSheet\\data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        
        String loan = sheet.getRow(1).getCell(0).getNumericCellValue()+"";
        String roi = sheet.getRow(1).getCell(1).getNumericCellValue()+"";
        String yr = sheet.getRow(1).getCell(1).getNumericCellValue()+"";
        
        WebElement loanAmt =driver.findElement(By.xpath("//*[@id=\'LOAN_AMOUNT\']"));
        loanAmt.sendKeys(loan);
        
        WebElement rateofinterest  = driver.findElement(By.xpath("//*[@id=\'RATE_OF_INTEREST\']"));
        rateofinterest.clear();
        rateofinterest.sendKeys(roi);
        
        WebElement year = driver.findElement(By.xpath("//*[@id=\'LOAN_TENURE\']"));
        year.clear();
        year.sendKeys(yr);
        ExtentTest test2 = reports.createTest("Test case 3 ");

        String condition = driver.findElement(By.xpath("//*[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/p")).getText();
        if(condition.contains("Your Amortization Details")){
            test2.log(Status.PASS, "Result is present");
        }
        else{
            test2.log(Status.FAIL, "Result is present");
        }
        
        Thread.sleep(3000);
        workbook.close();
        fis.close();

    }


    @AfterTest
    public void afterTest(){
        driver.close();
        reports.flush();
    }
}
