package intermediate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/***
 * A headless browser is a term used to define browser simulation
 * programs that do not have a GUI. These programs execute like any
 * other browser but do not display any UI.
 *
 * Some Advantages of using headless browsers:
 *
 * Useful in CI pipeline;
 * Beneficial in web scraping (Data extractor);
 * Support for multiple browser versions;
 * Faster automation test execution;
 * Multi-Tasking;
 *
 * @author Ezequias Ferreira
 */
public class TC6_HeadlessBrowser {

    HtmlUnitDriver driverHeadless = new HtmlUnitDriver(true);

    @Test
    void someHeadlessOperations () {
        driverHeadless.get("https://demoqa.com/");
        String titlePage = driverHeadless.getTitle();
        Assert.assertEquals("ToolsQA", titlePage);
    }

    @Test
    void usingChromeHeadless () {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        //Setting the headless mode
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("headless");

        //Instance a driver with the headless option
        WebDriver driver = new ChromeDriver(opt);
        driver.get("https://demoqa.com/");
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
    }
}
