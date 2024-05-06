package com.example;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AppTest 
{
    WebDriver driver;
    String url = "https://www.mayoclinic.org/";
    JavascriptExecutor je;
    ExtentReports reports;
    
    @BeforeTest
    public void beforeTest() throws Exception{
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        je = (JavascriptExecutor)driver;
        reports = new ExtentReports();
    }

    @Test
    public void testcase() throws IOException, Exception
    {
        try{
            WebElement element1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div/header/div[2]/div/nav/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/ul/li[5]/div[1]/div/button/span/span[1]"));
            element1.click();
            Thread.sleep(3000);
    
            driver.findElement(By.xpath("//*[@id='button-d87139392b']/span/span/span[1]/span")).click();
            Thread.sleep(3000);
            
            driver.findElement(By.xpath("//*[@id='amounts']/label[3]")).click();
            Thread.sleep(2000);
            
            driver.findElement(By.xpath("//*[@id='designation']")).click();
            Thread.sleep(3000);        

            Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='designation']")));
            dropdown.selectByVisibleText("Medical Education");

            Thread.sleep(1000);
            
            
            driver.findElement(By.xpath("//*[@id=\"adfWrapper\"]/fieldset[2]/div[1]/label")).click();
            Thread.sleep(1000);

            je.executeScript("window.scrollBy(0,250)");

            Thread.sleep(1000);

            Select element2  = new Select(driver.findElement(By.xpath("//*[@id='personalTitle']")));
            element2.selectByVisibleText("Mr. ");

            Thread.sleep(1000);

            String path = "C:\\Users\\kskm4\\Downloads\\modellab\\src\\excelSheet\\data.xlsx";
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            String fname = sheet.getRow(1).getCell(0).getStringCellValue();
            String lname = sheet.getRow(1).getCell(1).getStringCellValue();
            WebElement firstName = driver.findElement(By.xpath("//*[@id='personalFirstName']"));
            firstName.sendKeys(fname);
            Thread.sleep(1000);
            
            WebElement lastName = driver.findElement(By.xpath("//*[@id=\'personalLastName\']"));
            lastName.sendKeys(lname);
            Thread.sleep(1000);
            
            
            Select dropdown3 = new Select(driver.findElement(By.xpath("//*[@id='personalCountry']")));
            dropdown3.selectByVisibleText("India");
            Thread.sleep(1000);
            
            Select dropdown4 = new Select(driver.findElement(By.xpath("//*[@id=\'personalState\']")));
            dropdown4.selectByVisibleText("NA");
            Thread.sleep(1000);
            
            
            String address = sheet.getRow(1).getCell(2).getStringCellValue();
            driver.findElement(By.xpath("//*[@id='personalAddress']")).sendKeys(address);
            Thread.sleep(1000);
            
            String city  = sheet.getRow(1).getCell(3).getStringCellValue();
            driver.findElement(By.xpath("/html/body/form[2]/div[3]/main/div/div/div[2]/div[1]/div/div/div[1]/fieldset[3]/div[3]/div[2]/input")).sendKeys(city);
            Thread.sleep(1000);
            
            String postalcode = "641056";
            driver.findElement(By.xpath("/html/body/form[2]/div[3]/main/div/div/div[2]/div[1]/div/div/div[1]/fieldset[3]/div[3]/div[3]/input")).sendKeys(postalcode);
            Thread.sleep(1000);
            
            String phnum = "1234567890";
            driver.findElement(By.xpath("/html/body/form[2]/div[3]/main/div/div/div[2]/div[1]/div/div/div[1]/fieldset[3]/div[4]/div[1]/input")).sendKeys(phnum);
            Thread.sleep(1000);

            String email= sheet.getRow(1).getCell(6).getStringCellValue();
            driver.findElement(By.xpath("/html/body/form[2]/div[3]/main/div/div/div[2]/div[1]/div/div/div[1]/fieldset[3]/div[4]/div[2]/input")).sendKeys(email);
            Thread.sleep(3000);
            

            WebElement submit = driver.findElement(By.cssSelector("#adfSubmit"));
            submit.click();

            ExtentTest test = reports.createTest("Test Case-1");

            if(submit.isSelected()){
                test.log(Status.PASS,"Gift Prepared");
            }
            else{
                test.log(Status.FAIL,"Gift Not Prepared");
            }

            Thread.sleep(3000);

            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("screenshot.png"));

            workbook.close();
            fis.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @AfterTest
    public void afterTest(){
        reports.flush();
        driver.close();
    }
}
