package HandlingCalendar.qa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {
	
public static void main(String[] args) throws ParseException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://omayo.blogspot.com/p/page6.html");
		driver.switchTo().frame("dateFrame");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		driver.findElement(By.id("datepicker")).click();
		
		String dateToBeSelected = "15/08/2025";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date formatedDate = sdf.parse(dateToBeSelected);
		String stringDay = new SimpleDateFormat("dd").format(formatedDate);
		int day = Integer.parseInt(stringDay);
		String stringMonth = new SimpleDateFormat("MM").format(formatedDate);
		int month = Integer.parseInt(stringMonth);
		String stringYear = new SimpleDateFormat("yyyy").format(formatedDate);
		int year = Integer.parseInt(stringYear);
		
		//Logic to navigate to the required year in the calendar
		while(true) {
			
			String displayedStringYear = driver.findElement(By.cssSelector("span[class='ui-datepicker-year']")).getText();
			int displayedYear = Integer.parseInt(displayedStringYear);
			if(displayedYear==year)	{
				
				break;
				
			}else if(displayedYear<year) {
				
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				
			}else if(displayedYear>year) {
				
				driver.findElement(By.xpath("//span[text()='Prev']")).click();
				
			}
			
		}
		
		//Logic to navigate to required month in calendar and selecting the date
		while(true) {
			
			String displayedMonth = driver.findElement(By.cssSelector("span[class='ui-datepicker-month']")).getText();
			SimpleDateFormat simpleDF = new SimpleDateFormat("MMMM");
			Date sFormatedDate = simpleDF.parse(displayedMonth);
			String sfStringMonth = new SimpleDateFormat("MM").format(sFormatedDate);
			int iDisplayedMonth = Integer.parseInt(sfStringMonth);
			
			if(iDisplayedMonth==month) {
				
				String dayXpath = "//a[text()='"+day+"']";
				driver.findElement(By.xpath(dayXpath)).click();
				break;			
				
			}else if(iDisplayedMonth<month) {
				
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				
			}else if(iDisplayedMonth>month) {
				
				driver.findElement(By.xpath("//span[text()='Prev']")).click();
				
			}
			
			
		}
		
		
	}

}
