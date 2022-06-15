package com.qa;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestVivaPage {

	private WebDriver chromedriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers-mac/chromedriver");
		chromedriver = new ChromeDriver();
		chromedriver.manage().window().maximize();
		chromedriver.get("https://www.vivaair.com/#/co/es");
		chromedriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test
	public void LlenarFormulario() throws InterruptedException {
		String month = "Junio";
		WebDriverWait wait = new WebDriverWait(chromedriver, 60);

		WebElement VueloOrigen = chromedriver.findElement(By.id("station"));
		VueloOrigen.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='station__popover__wrapper']")));
		WebElement VueloOrigenFilter = chromedriver.findElement(By.id("filter-station"));
		VueloOrigenFilter.sendKeys("Bogota");
		WebElement ciudadOrigen = chromedriver.findElement(By.xpath("//span[@class='station__name'][1]/div"));
		ciudadOrigen.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='station__popover__wrapper']")));
		WebElement VueloDestinoFilter = chromedriver.findElement(By.id("filter-station-second"));
		VueloDestinoFilter.sendKeys("Cali");
		WebElement ciudadDestino = chromedriver.findElement(By.xpath("//span[@class='station__name'][1]/div"));
		ciudadDestino.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendar__date_picker']")));
		WebElement monthCalendar = chromedriver.findElement(By.xpath("(//div[@class='month']/div[1]/span[1])[5]"));
		String monthVisible = monthCalendar.getText();
		assertEquals(month, monthVisible);

		Thread.sleep(10000);
	}

	@After
	public void tearDown() {
		chromedriver.quit();
	}
}
