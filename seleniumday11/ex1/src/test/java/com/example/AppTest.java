package com.example;

import java.io.File;
import java.io.FileInputStream;

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
   JavascriptExecutor je;
   ExtentReports reports;
   ExtentSparkReporter spark;
   @BeforeTest
   public void beforeTest(){
     driver = new ChromeDriver();
     je = (JavascriptExecutor)driver;
     reports = new ExtentReports();
     spark = new ExtentSparkReporter("C:\\Users\\kskm4\\Downloads\\seleniumday11\\ex1\\src\\reports\\Reports.html");
     reports.attachReporter(spark);
     spark.config().setTheme(Theme.STANDARD);
   }
   
   @Test 
   public void pageopen1() throws InterruptedException {
      driver.get("https://www.spencers.in/");
      driver.manage().window().maximize();
      Thread.sleep(2000);
   }

   @Test(dependsOnMethods = "pageopen1")
   public void testcase1() throws Exception{
      WebElement search = driver.findElement(By.xpath("//*[@id='html-body']/div[3]/header/div[2]/div[3]"));
      search.click();

      FileInputStream fis = new FileInputStream("C:\\Users\\kskm4\\Downloads\\seleniumday11\\ex1\\src\\excelSheet\\Data.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      String product = sheet.getRow(0).getCell(0).getStringCellValue();
      driver.findElement(By.xpath("/html/body/div[3]/header/div[2]/div[3]/div[2]/form/div[1]/div/input")).sendKeys(product);
      
      WebElement go = driver.findElement(By.xpath("//*[@id='search_mini_form']/div[2]/button/span"));
      go.click();
      Thread.sleep(3000);
      
      ExtentTest test1 = reports.createTest("Test case - 1");
      if(driver.getPageSource().contains("Chocolate")){
         test1.log(Status.PASS, "Product Found!");
      }
      else{
         test1.log(Status.FAIL, "Product Not Found!");
      }
      
      driver.findElement(By.xpath("//*[@id='narrow-by-list']/div[1]/div[2]/button")).click();
      WebElement checkbox = driver.findElement(By.xpath("//*[@id='narrow-by-list']/div[1]/div[2]/form/ul/li[5]/span"));
      checkbox.click();
      
      Thread.sleep(3000);
      
      WebElement add = driver.findElement(By.xpath("//*[@id='addtocart-149448']/button/span"));
      add.click();
      Thread.sleep(2000);
      
      WebElement cart = driver.findElement(By.xpath("//*[@id='html-body']/div[3]/header/div[2]/div[2]/div[1]/a"));
      cart.click();
      Thread.sleep(2000);
      
      driver.findElement(By.partialLinkText("View Cart")).click();
      Thread.sleep(2000);
      
      ExtentTest test2 = reports.createTest("Test case - 2");
      if(driver.getPageSource().contains("My Cart (1 Item)")){
         test2.log(Status.PASS, "Got It!!!");
      }
      else{
         test2.log(Status.FAIL, "OOPS!");
      }
      
      Thread.sleep(2000);
      workbook.close();
      fis.close();
   }
   
   @Test(dependsOnMethods = "testcase1")
   public void testcase2() throws InterruptedException{
      driver.navigate().to("https://www.spencers.in/");
      Thread.sleep(2000);
      
      driver.findElement(By.partialLinkText("Groceries")).click();
      
      WebElement viewall = driver.findElement(By.xpath("//*[@id='ui-id-20']/span"));
      viewall.click();
      Thread.sleep(2000);
      
      WebElement checkbox = driver.findElement(By.xpath("//*[@id='narrow-by-list']/div[1]/div[2]/form/ul/li[2]/span"));
      checkbox.click();
      Thread.sleep(2000);
      
      ExtentTest test = reports.createTest("Testcase - 3");
      WebElement category = driver.findElement(By.xpath("//*[@id='am-shopby-container']/ol/li[1]/div"));
      String result = category.getText();
      if(result.contains("Edible Oil")){
         test.log(Status.PASS, "contains");
      }
      else{
         test.log(Status.FAIL, "does not contains");
      }
      Thread.sleep(2000);
   }
   
   @Test(dependsOnMethods = "testcase2")
   public void testcase3() throws Exception{
      driver.navigate().to("https://www.spencers.in/");
      Thread.sleep(2000);
      
      WebElement login = driver.findElement(By.xpath("//*[@id='html-body']/div[3]/header/div[2]/div[2]/div[3]/div[1]/div[1]"));
      login.click();
      Thread.sleep(2000);
      
      driver.findElement(By.cssSelector("#html-body > div.page-wrapper > header > div.header.content > div.right_header.col-sm-4.pull-right.no-padding > div.header_icons.pull-right > div.customer-wrapper.c_header_pop_wrapper.c_header_cus_pop.hidden-xs > div.c_header_cus_content.common_div > div > div.dropdown-menu.my_dropdown.drop_accounts > div.view_my_acount > div > div > a > span")).click();
      Thread.sleep(2000);
      
      FileInputStream fis = new FileInputStream("C:\\Users\\kskm4\\Downloads\\seleniumday11\\ex1\\src\\excelSheet\\Data.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      String username = sheet.getRow(1).getCell(0).getStringCellValue();
      String password = sheet.getRow(2).getCell(0).getStringCellValue();
      
      driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
      driver.findElement(By.xpath("//*[@id=\'pass\']")).sendKeys(password);
      driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[3]/div[2]/div/form/fieldset/div[4]/div[1]/button")).click();
      Thread.sleep(1000);
      ExtentTest test = reports.createTest("TestCase - 4");
      if(driver.getPageSource().contains("Invalid username or password.") || driver.getPageSource().contains("Robotic Error")){
         test.log(Status.PASS, "Yaah There is an error");
         File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         FileUtils.copyFile(file, new File("screenshot.png"));
      }
      else{
         test.log(Status.FAIL, "No error");
      }

      
      Thread.sleep(2000);
      workbook.close();
      fis.close();
   }

   @AfterTest
   public void afterTest(){
      reports.flush();
      driver.close();
   }
}
