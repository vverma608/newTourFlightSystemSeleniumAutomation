package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class bookingFlight extends TestBase{

	@Test
	public void defaultInputBooking(){
	
		driver.findElement(By.xpath(OR.getProperty("find_flight"))).click(); //Clicking the "continue" button. 
		driver.findElement(By.xpath(OR.getProperty("flight_submit"))).click(); //Clicking the "continue" button.
		
		driver.findElement(By.xpath(OR.getProperty("Passenger1_first_name"))).sendKeys("firstUser1");
		driver.findElement(By.xpath(OR.getProperty("Passenger1_last_name"))).sendKeys("lastUser1");
		
		driver.findElement(By.xpath(OR.getProperty("buy_flights"))).click(); //Clicking the "secure purchase" button.
		
		Assert.assertTrue(isElementPresent(OR.getProperty("flight_confirm_number")));
	}
	
	
	@Test(priority =10)
	public void findingBrokenLinksOnBookingFlightPage(){
			
		// Get all the links url
		List<WebElement> ele = driver.findElements(By.tagName("a"));
        System.out.println("size:" + ele.size());
        boolean isValid = false;
        for (int i = 0; i < ele.size(); i++) {
            isValid = getResponseCode(ele.get(i).getAttribute("href"));
            
            //Just to print all the links o/p over console
            if (isValid) {
                System.out.println("ValidLinks on booking flight page:"+ ele.get(i).getAttribute("href"));
            } else {
                System.out.println("InvalidLinks on booking flight page:"+ ele.get(i).getAttribute("href"));
            }
            
            Assert.assertEquals(isValid, true);
                        
        }

	
	}

	
	
}
