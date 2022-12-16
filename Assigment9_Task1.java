//Open https://ineuron-courses.vercel.app/signup
//Verify Sign up button is disabled
//FIll all details
//Verify Sign up button is enabled
//Click on Sign up button
//Verify user is created or not (by login with same credentails)

package assignment_9;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assigment9_Task1 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://ineuron-courses.vercel.app/signup");
		Thread.sleep(5000);
		
		WebElement submit = driver.findElement(By.className("submit-btn"));
		boolean submitStatus = submit.isEnabled();
		
		if(!submitStatus) {
			System.out.println("Submit button is disabled as details are entered");
			
			//Name
			driver.findElement(By.id("name")).sendKeys("Lakshmi");
			
			//email
			driver.findElement(By.id("email")).sendKeys("sudarshanlakshmi@gmail.com");
			
			//password
			driver.findElement(By.id("password")).sendKeys("Lakshmi@123");
			
			//interests
			List<WebElement> interests = driver.findElements(By.xpath("//div[@class='interests-div']//label"));
			for(WebElement interest:interests) {
				String actualInterest = interest.getText();
				if((actualInterest.contains("DevOps"))||(actualInterest.contains("Testing"))) {
					interest.click();
				}
			}
			
			//choose female
			WebElement gender = driver.findElement(By.xpath("//input[@value='Female']"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", gender);
			
			
			//select state
			WebElement stateField = driver.findElement(By.id("state"));
			Select state = new Select(stateField);
			
			state.selectByVisibleText("Maharashtra");
			
			//verify submit button is enabled now
			submitStatus = submit.isEnabled();
			if(submitStatus) {
				System.out.println("===================================");
				System.out.println("Submit button is enabled now and hence can click");
				js.executeScript("arguments[0].click();", submit);
				Thread.sleep(5000);
				if(driver.getCurrentUrl().contains("https://ineuron-courses.vercel.app/login")) {
					System.out.println("user is successfully logged in");
				}
				
				
			}
			
		}
		
		driver.get("https://ineuron-courses.vercel.app/login");
		Thread.sleep(5000);
		
		driver.findElement(By.name("email1")).sendKeys("sudarshanlakshmi@gmail.com");
		driver.findElement(By.name("password1")).sendKeys("Lakshmi@123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		Thread.sleep(3000);
		driver.quit();
		

	}

}
