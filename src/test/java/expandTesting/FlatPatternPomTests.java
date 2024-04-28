package expandTesting;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FlatPatternPomTests {
    /**
     * HW07
     * <br> - navigate to this url <a href="http://demo.automationtesting.in/Alerts.html">Alerts</a>
     * <br> - Click "Alert with OK" and click 'click the button to display an alert box:’
     * <br> - Accept Alert(I am an alert box!) and print alert on console.
     */
    WebDriver driver;
    Wait<WebDriver> wait;

    @Test
    public void test(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo.automationtesting.in/Alerts.html");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    By ClickAlerBox = By.cssSelector("button[onclick='alertbox()']");
    driver.findElement(ClickAlerBox).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alerText = alert.getText();
        alert.accept();
        System.out.println(alerText);
    }

    @Test
    public void test2(){
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = driver.getTitle();
        Assert.assertEquals("Web form",title);



    }



}