package intermediate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC7_HandleSSLErrors {

    WebDriver driver;

    @Test
    void getPageError () {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        //Crash in an SSL error
        driver.get("https://expired.badssl.com/");
        String titleError = driver.getTitle();

        //Accept all insecure certs (Chrome Implementation)
        ChromeOptions optChrome = new ChromeOptions();
        optChrome.setAcceptInsecureCerts(true);

        WebDriver driverChrome = new ChromeDriver(optChrome);
        driverChrome.get("https://expired.badssl.com/");
        String titlePass = driverChrome.getTitle();

        Assert.assertFalse(titleError.equals(titlePass));

        driver.quit();
        driverChrome.quit();
    }



}
