package examples;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BetweenTwoBrowsers {

	public static WebDriver driver = null;

	public static void main(String... args) {

		System.setProperty("web.chrome.driver", "\"C:\\Users\\sp369w\\Downloads\\chromedriver.exe\"");
		System.setProperty("web.gecko.driver", "\"C:\\Users\\sp369w\\Downloads\\geckodriver.exe\"");
		List<String> wh = new ArrayList<String>();
		WebDriver driver1 = new ChromeDriver();
		wh.add(driver1.getWindowHandle());
		doSomeThing(driver1);
		WebDriver driver2 = new FirefoxDriver();
		wh.add(driver2.getWindowHandle());
		doSomeThing(driver2);
		String window1 = wh.get(0).toString();
		String window2 = wh.get(1).toString();
		driver1.switchTo().window(window1);
		System.out.println("\t \t window1: " + window1);
		doSomeThing(driver1);

		driver2.switchTo().window(window2);
		System.out.println("\t \t window2: " + window2);
		doSomeThing(driver2);

		driver1.switchTo().window(window1);
		System.out.println("\t \t window1: " + window1);
		doSomeThing(driver1);

		driver2.switchTo().window(window2);
		System.out.println("\t \t window2: " + window2);
		doSomeThing(driver2);

		driver1.switchTo().window(window1);
		System.out.println("\t \t window1: " + window1);
		doSomeThing(driver1);

		driver2.switchTo().window(window2);
		System.out.println("\t \t window2: " + window2);
		doSomeThing(driver2);

		driver1.switchTo().window(window1);
		System.out.println("\t \t window1: " + window1);
		doSomeThing(driver1);

		driver2.switchTo().window(window2);
		System.out.println("\t \t window2: " + window2);
		doSomeThing(driver2);

		driver1.quit();
		driver2.quit();

	}

	public static void doSomeThing(WebDriver driver) {
		driver.manage().window().maximize();
		driver.navigate().to("http://executeautomation.com/demosite/Login.html");

		driver.findElement(By.name("UserName")).sendKeys("Rupesh");
		System.out.println("entering Rupesh for " + driver.getWindowHandle());
		driver.findElement(By.name("Password")).sendKeys("Kumar");
		System.out.println("entering kumar for " + driver.getWindowHandle());
		driver.findElement(By.name("Login")).submit();

		driver.findElement(By.name("Initial")).sendKeys("Somala");
		System.out.println("entering Somala for " + driver.getWindowHandle());
		driver.findElement(By.name("FirstName")).sendKeys("Rupesh");
		System.out.println("entering Rupesh for " + driver.getWindowHandle());
		driver.findElement(By.name("MiddleName")).sendKeys("Kumar");
		System.out.println("entering Kumar for " + driver.getWindowHandle());
		driver.findElement(By.name("Save")).click();
	}

}
