package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	/*
	 * WebDrive Initilised
	 * Log
	 * OR, Config Properties
	 * Browser via WebDriver
	 * 
	 */

	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	
	
	@BeforeSuite
	public void setUp(){
	
	 if (driver==null){
		
	//Loading the Config file 
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.debug("Config file loaded!!!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//Loading the OR file
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded!!!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
			if(Config.getProperty("browser").equals("firefox")){
				
				driver = new FirefoxDriver();
				log.debug("Firefox browser launched!!!");
				
			} else if(Config.getProperty("browser").equals("chrome")){
				
				System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome browser launched!!!");
				
			} else if(Config.getProperty("browser").equals("ie")){
				
				System.setProperty("WebDriver.ie.driver", "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE browser launched!!!");
			}
	 
	 }
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	}
	
	//Defining a user defined method to Check is a given WebElement is present?
	public static boolean isElementPresent(String xpath){
		
		try {
			
			driver.findElement(By.xpath(xpath));
			return true;
			
		}catch(Throwable t){
			
			return false;
		}
	}
		
	//Defining a method to get the current date.
		public static int currentDay = 0,
				currentMonth = 0,
				currentYear = 0;
	
		public static void getCurrentDateMonthAndYear(){
		
		Calendar cal = Calendar.getInstance();
		
		currentDay = cal.get(Calendar.DAY_OF_MONTH);	
		currentMonth = cal.get(Calendar.MONTH)+1;
		currentYear = cal.get(Calendar.YEAR);
		
	}
	
	//Defining a method to convert, Month in num to respective Month Name .		
		
		public static String formatMonth(int month) {
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		String[] monthNames = symbols.getMonths();
		return monthNames[month - 1];

		}

	//	Defining a method to validate if each URL is valid.
    public static boolean getResponseCode(String urlString) {
        boolean isValid = false;
        try {
            URL u = new URL(urlString);
            HttpURLConnection h = (HttpURLConnection) u.openConnection();
            h.setRequestMethod("GET");
            h.connect();
            System.out.println(h.getResponseCode());
            if (h.getResponseCode() != 404) {
                isValid = true;
            }
        } catch (Exception e) {

        }
        return isValid;
    }
			
       
		
	@AfterSuite
	public void tearDown(){
	
		driver.quit();
	}
}
