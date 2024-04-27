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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest 
{
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    JavascriptExecutor jse;
    String url = "http://dbankdemo.com/bank/login";

    @BeforeTest
    public void beforeTest() throws InterruptedException{
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        jse = (JavascriptExecutor) driver;
    }
    
    @Test
    public void testcase1() throws Exception
    {
        String path = "C:\\Users\\kskm4\\Downloads\\seleniumday8\\ex1\\src\\main\\java\\com\\example\\ExcelSpreadSheet\\WorkSheet.xlsx";

        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        String username = sheet.getRow(1).getCell(0).getStringCellValue();
        String pwd = sheet.getRow(1).getCell(1).getStringCellValue();

        WebElement userinput = driver.findElement(By.xpath("//*[@id=\'username\']"));
        userinput.sendKeys(username);

        Thread.sleep(3000);
        
        WebElement pwdinput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        pwdinput.sendKeys(pwd);
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        Thread.sleep(3000);
        
        if(driver.getCurrentUrl().contains("home")){
            System.out.println("URL contains home");
        }
        else{
            System.out.println("URL does not contains home");
        }
        Thread.sleep(3000);
        workbook.close();
        fis.close();
    }
    
    @Test
    public void testcase2() throws Exception{
        driver.navigate().to(url);
        String path = "C:\\Users\\kskm4\\Downloads\\seleniumday8\\ex1\\src\\main\\java\\com\\example\\ExcelSpreadSheet\\WorkSheet.xlsx";
        
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        
        String username = sheet.getRow(1).getCell(0).getStringCellValue();
        String pwd = sheet.getRow(1).getCell(1).getStringCellValue();
        
        WebElement userinput = driver.findElement(By.xpath("//*[@id=\'username\']"));
        userinput.sendKeys(username);
    
        Thread.sleep(3000);
        
        WebElement pwdinput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        pwdinput.sendKeys(pwd);
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        Thread.sleep(3000);
        
        WebElement deposit = driver.findElement(By.xpath("//*[@id='deposit-menu-item']"));
        deposit.click();
        
        WebElement select = driver.findElement(By.xpath("//*[@id='selectedAccount']"));
        Select dropdown = new Select(select);
        dropdown.selectByValue("434969");
        
        String amount = "5000";
        driver.findElement(By.xpath("//*[@id='amount']")).sendKeys(amount);
        
        
        WebElement submit = driver.findElement(By.cssSelector("#right-panel > div.content.mt-3 > div > div > div > div > form > div.card-footer > button.btn.btn-primary.btn-sm"));
        submit.click();
        
        String window = driver.getWindowHandle();
        for(String handle:driver.getWindowHandles()){
            if(handle.equals(window)){
                System.out.println("Page redirected");
                driver.switchTo().window(handle);
                break;
            }
            else{
                System.out.println("Page not redirected");
            }
        }
        
        Thread.sleep(3000);
        jse.executeScript("window.scrollBy(0,500)");
        
        WebElement element = driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]"));
        String result = element.getText();
        System.out.println(result);
        if(result.contains(amount)){
            System.out.println("Deposited amount is present");
        }
        else{
            System.out.println("Deposited amount is not present");
        }
        
        
        Thread.sleep(3000);
        workbook.close();
        fis.close();
    }
    
    @Test
    public void testcase3() throws Exception {
        driver.navigate().to(url);
        Thread.sleep(3000);
        String path = "C:\\Users\\kskm4\\Downloads\\seleniumday8\\ex1\\src\\main\\java\\com\\example\\ExcelSpreadSheet\\WorkSheet.xlsx";
        
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        
        String username = sheet.getRow(1).getCell(0).getStringCellValue();
        String pwd = sheet.getRow(1).getCell(1).getStringCellValue();
        
        WebElement userinput = driver.findElement(By.xpath("//*[@id=\'username\']"));
        userinput.sendKeys(username);
        
        Thread.sleep(3000);
        
        WebElement pwdinput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        pwdinput.sendKeys(pwd);
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='submit']")).click();
        
        WebElement withdraw = driver.findElement(By.xpath("//*[@id='withdraw-menu-item']"));
        withdraw.click();
        
        WebElement deposit = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/form/div[1]/div[2]/div/div/select"));
        deposit.click();
        
        WebElement select = driver.findElement(By.xpath("//*[@id='selectedAccount']"));
        Select dropdown = new Select(select);
        dropdown.selectByValue("434969");
        
        String amount = "3000";
        driver.findElement(By.xpath("//*[@id=\'amount\']")).sendKeys(amount);
        
        
        WebElement submit = driver.findElement(By.cssSelector("#right-panel > div.content.mt-3 > div > div > div > div > form > div.card-footer > button.btn.btn-primary.btn-sm"));
        submit.click();
        
        String window = driver.getWindowHandle();
        for(String handle:driver.getWindowHandles()){
            if(handle.equals(window)){
                System.out.println("Page redirected");
                driver.switchTo().window(handle);
                break;
            }
            else{
                System.out.println("Page not redirected");
            }
        }
        
        Thread.sleep(3000);
        jse.executeScript("window.scrollBy(0,500)");
        WebElement element = driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]"));
        String result = element.getText();
        System.out.println(result);
        if(result.contains(amount)){
            System.out.println("Withdrawal amount is present");
        }
        else{
            System.out.println("Withdrawal amount is not present");
        }
        
        
        Thread.sleep(3000);
        workbook.close();
        fis.close();
        
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }

}
