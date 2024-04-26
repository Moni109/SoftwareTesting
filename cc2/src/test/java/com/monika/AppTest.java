package com.monika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
    Actions actions;
    ExtentTest test,test2,test3;
    ExtentSparkReporter spark;
    ExtentReports reports;
    JavascriptExecutor je;
    Logger logger=Logger.getLogger(AppTest.class);

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

        String path = "C:\\Users\\Lenovo\\Desktop\\cc2\\src\\Report\\report.html";
        spark = new ExtentSparkReporter(path);
        reports = new ExtentReports();
        reports.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        test = reports.createTest("TestCase");
    }

    @Test(priority = 1)
    public void testcase1() throws Exception
    {
        FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\cc2\\src\\Excel\\Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        //select All
        WebElement element1 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/a"));
        element1.click();

        //select books
        WebElement element2 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/div/a[2]"));
        element2.click();
        
        Thread.sleep(10000);
        String bookname = sheet.getRow(0).getCell(0).getStringCellValue();    
        
        Thread.sleep(3000);
        //search given bookname
        WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]"));
        element3.sendKeys(bookname);
        
        Thread.sleep(2000);
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

    @Test(priority = 2)
     public void Addtocart() throws InterruptedException, IOException{

         WebElement audio =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a")));
        actions.moveToElement(audio);
        actions.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Audiobooks Top 100"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"resolve_1\"]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"otherAvailFormats\"]/div/div/div[3]/a"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"prodInfoContainer\"]/div[3]/form[1]/input[11]"))).submit();

        try{
            String d = driver.switchTo().alert().getText();
            if (d.contains("Item Successfully Added To Your Cart")) {
                test.log(Status.PASS,"Successfully inserted into the cart");
            } else {
                test.log(Status.FAIL,"Item not inserted into the cart");
            }

        }
        catch(Exception e){

            test.log(Status.FAIL, e);
            TakesScreenshot ts=(TakesScreenshot)driver;
		    File file=ts.getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(file, new File("./screenshots/screenshot.png"));
        }
     }
     @Test(priority = 3)
    public void BSI()
    {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\\\"footer\\\"]/div/dd/div/div/div[1]/div/a[5]"))).click(); 

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='rewards-modal-link']"))).click();

        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dialog-title']")));

        if(text.getText().contains("Sign in or Create an Account"))
        {
            test3.log(Status.PASS,"Pop up has arrived");
        }
        else{
            test3.log(Status.FAIL,"Pop up does not arrived");

        }
    }

    @AfterTest
    public void after() throws InterruptedException{

        reports.flush();
        Thread.sleep(10000);
        driver.quit();
    }

}