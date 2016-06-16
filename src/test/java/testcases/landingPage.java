package testcases;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class landingPage extends TestBase {
	
	@Test(priority =1)
	public void todayDate(){
		
		WebElement element= driver.findElement(By.xpath(OR.getProperty("today_date")));
		String actual_date = element.getText();
		
		getCurrentDateMonthAndYear();
		String expected_date=formatMonth(currentMonth).substring(0, 3)+" "+currentDay+","+" "+currentYear;
					
		Assert.assertEquals(actual_date, expected_date);
		
	}
	
		
	@Test(priority =3)
	public void dologin(){
		
		driver.findElement(By.xpath(OR.getProperty("cust_user_name"))).sendKeys(Config.getProperty("custusername"));
		driver.findElement(By.xpath(OR.getProperty("cust_passwd"))).sendKeys(Config.getProperty("custpasswd"));
		driver.findElement(By.xpath(OR.getProperty("sign_in"))).click();
		
		Assert.assertTrue(isElementPresent(OR.getProperty("trip_round")));
	}
	
	
	
	//public void Finding Broken links 
	@Test(priority =10)
	public void findingBrokenLinksOnLandingPage(){

		//getResponseCode(String urlString);
		
		// Get all the links url
		List<WebElement> ele = driver.findElements(By.tagName("a"));
        System.out.println("size:" + ele.size());
        boolean isValid = false;
        for (int i = 0; i < ele.size(); i++) {
           isValid = getResponseCode(ele.get(i).getAttribute("href"));
            
            //Just to print all the links o/p over console
            if (isValid) {
                System.out.println("ValidLinks on landing page:"+ ele.get(i).getAttribute("href"));
            } else {
                System.out.println("InvalidLinks on landing page:"+ ele.get(i).getAttribute("href"));
            }
            
            Assert.assertEquals(isValid, true);
                        
        }
        
        }
	
	}	
	

