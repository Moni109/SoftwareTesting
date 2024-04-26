package com.example;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest 
{
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor je;
    @BeforeTest
    public void beforeTest() throws InterruptedException
    {
        driver = new ChromeDriver();
        driver.get("https://www.barnesandnoble.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        je = (JavascriptExecutor) driver;
    }
    @Test
    public void testCase1() throws Exception
    {
        //select All
        WebElement element1 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/a"));
        element1.click();

        //select books
        WebElement element2 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/div/a[2]"));
        element2.click();

        String path = "C:\\Users\\kskm4\\Downloads\\cc2\\src\\excelSheet\\Worksheet.xlsx";
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        
        String bookname = sheet.getRow(1).getCell(0).getStringCellValue();        

        //search given bookname
        WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]"));
        element3.sendKeys(bookname);

        //click on search button
        WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/span/button")));
        element4.click();

        String name = "Chetan Bhagat";

        WebElement element5 = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"));
        String result = element5.getText();
        if(result.equals(name)){
            System.out.println("Result found");
        }
        else{
            System.out.println("Result not found");
        }

        workbook.close();
        fis.close();

    }

    @Test
    public void testCase2() throws InterruptedException{
        
        driver.navigate().to("https://www.barnesandnoble.com/");
        Thread.sleep(3000);

        WebElement audiobooks = driver.findElement(By.name("Audiobooks"));
        actions.moveToElement(audiobooks).perform();

        //select the audiobook top 100
        WebElement element1 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]"));
        element1.click();
        Thread.sleep(3000);
        
        je.executeScript("windows.scrollby(0,250)");

        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[5]/div[2]/div/div/form/input[11]")));
        element2.click();
        
    }


}
