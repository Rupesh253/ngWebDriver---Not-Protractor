package examples;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class Test1 {

	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		NgWebDriver ngWebDriver = new NgWebDriver(jse).withRootSelector("helloApp");
		driver.navigate().to("https://hello-angularjs.appspot.com/");

		ngWebDriver.waitForAngularRequestsToFinish();

		driver.findElement(By.linkText("Add a table row dynamically")).click();

		ngWebDriver.waitForAngularRequestsToFinish();

		driver.findElement(ByAngular.model("name")).sendKeys("Tufli");
		driver.findElement(ByAngular.model("employees")).sendKeys("253");
		driver.findElement(ByAngular.model("headoffice")).sendKeys("Dakota");

		driver.findElement(ByAngular.buttonText("Submit")).click();
		ngWebDriver.waitForAngularRequestsToFinish();
		Thread.sleep(2000);

		int numberOfCompanies = driver.findElements(By.xpath("//tr[@ng-repeat='company in companies']")).size();
		System.out.println(numberOfCompanies + " numberOfCompanies");

		for (int i = 1; i <= numberOfCompanies; i++) {
			int numberOfTDs = driver
					.findElements(
							By.xpath("//tr[@ng-repeat='company in companies'][" + i + "]/td[@class='ng-binding']"))
					.size();
			for (int j = 1; j <= numberOfTDs; j++) {
				System.out.print("|" + driver.findElement(By
						.xpath("//tr[@ng-repeat='company in companies'][" + i + "]/td[@class='ng-binding'][" + j + "]"))
						.getText());
			}
			System.out.print("|");
			System.out.println();

			numberOfTDs = 0;
		}

		List<WebElement> tableElementsList = driver.findElements(ByAngular.repeater("company in companies"));
		for (WebElement e : tableElementsList) {
			int numberOfTDs = e.findElements(By.xpath("./td")).size();
			for (int k = 1; k <= numberOfTDs; k++) {
				System.out.print("|" + e.findElement(By.xpath("./td[" + k + "]")).getText());
			}
			System.out.print("|");
			System.out.println();
		}

		String txt1 = driver
				.findElement(ByAngular.repeater("company in companies").row(numberOfCompanies - 1).column("name"))
				.getText();
		String txt2 = driver
				.findElement(ByAngular.repeater("company in companies").row(numberOfCompanies - 1).column("employees"))
				.getText();
		String txt3 = driver
				.findElement(ByAngular.repeater("company in companies").row(numberOfCompanies - 1).column("headoffice"))
				.getText();
		System.out.println(txt1 + " company added with " + txt2 + " employees and headoffice located at " + txt3);

		// String nameJson =
		// ngWebDriver.retrieveJson(driver.findElement(ByAngular.model("name")),
		// "company.name");
		// System.out.println(nameJson);

		driver.quit();

		/*StringSelection ss = new StringSelection("");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement e = w.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.className(""));
			}
		});*/

	}

}
