package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class flightFinderDefaultValidation extends TestBase{

	//Validating the default trip type.
	@Test(priority =1)
	public void defaultTripType(){
	
	List<WebElement> trip_type = driver.findElements(By.name("tripType"));
	
	Assert.assertEquals("roundtrip", trip_type.get(0).getAttribute("value"));
	Assert.assertTrue(trip_type.get(0).isSelected());
	
	}
	
	//Validating the default number of passenger selected.
	@Test(priority =2)
	public void defaultPassangerCount(){
		
		Select selectPassNum = new Select(driver.findElement(By.xpath(OR.getProperty("pass_count"))));
		List<WebElement> passNum = selectPassNum.getOptions();
		
		Assert.assertEquals("1", passNum.get(0).getText());	
	}
	
	//Validating the default departure date.
	@Test(priority =3)
	public void defaultDepartureDate(){
		Select selectMonth = new Select(driver.findElement(By.xpath(OR.getProperty("depart_month"))));
		String actualDefaultMonth = selectMonth.getFirstSelectedOption().getText();
					
		Select selectDate = new Select(driver.findElement(By.xpath(OR.getProperty("depart_date"))));
		String actualDefaultDate = selectDate.getFirstSelectedOption().getText();
		
			
		getCurrentDateMonthAndYear(); //Calling the base class method.
			String expectedDefaultMonth=formatMonth(currentMonth); //converting the num month value to respective month name.
			int expectedDate=currentDay;
			String expectedDefaultDate = Integer.toString(expectedDate); //converting the int date to String. 
		
		Assert.assertEquals(actualDefaultMonth, expectedDefaultMonth);
		Assert.assertEquals(actualDefaultDate, expectedDefaultDate);
						
	}
	
	//Validating the arrival city.
		@Test(priority =4)
		public void defaultArrivalCity(){
			Select selectArrivalCity = new Select(driver.findElement(By.xpath(OR.getProperty("arrival_city"))));
			String actualDefaultArrivalCity = selectArrivalCity.getFirstSelectedOption().getText();
			System.out.println(actualDefaultArrivalCity);
			
			Assert.assertEquals("Acapulco", actualDefaultArrivalCity);
				
			}
		
	
	
	
	
	//Validating the default arrival date.
		@Test(priority =5)
		public void defaultArrivalDate(){
			Select selectArrMonth = new Select(driver.findElement(By.xpath(OR.getProperty("arrival_month"))));
			String actualDefaultArrivalMonth = selectArrMonth.getFirstSelectedOption().getText();
						
			Select selectArrDate = new Select(driver.findElement(By.xpath(OR.getProperty("arrival_date"))));
			String actualDefaultArrialDate = selectArrDate.getFirstSelectedOption().getText();
			
				
			getCurrentDateMonthAndYear(); //Calling the base class method.
				String expectedDefaultArrivalMonth=formatMonth(currentMonth); //converting the num month value to respective month name.
				int expectedArrivalDate=currentDay;
				String expectedDefaultArrivalDate = Integer.toString(expectedArrivalDate); //converting the int date format to String. 
			
			Assert.assertEquals(actualDefaultArrivalMonth, expectedDefaultArrivalMonth);
			Assert.assertEquals(actualDefaultArrialDate, expectedDefaultArrivalDate);
							
		}
		
	
	
	@Test(priority =10)
	public void findingBrokenLinksOnFlightFinderPage(){
			
		// Get all the links url
		List<WebElement> ele = driver.findElements(By.tagName("a"));
        System.out.println("size:" + ele.size());
        boolean isValid = false;
        for (int i = 0; i < ele.size(); i++) {
            isValid = getResponseCode(ele.get(i).getAttribute("href"));
            
            //Just to print all the links o/p over console
            if (isValid) {
                System.out.println("ValidLinks on flight finder page:"+ ele.get(i).getAttribute("href"));
            } else {
                System.out.println("InvalidLinks on flight finder page:"+ ele.get(i).getAttribute("href"));
            }
            
            Assert.assertEquals(isValid, true);
                        
        }

	
	}
}
