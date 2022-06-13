package intermediate;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC1_Waits extends BaseClass{

    /*
        When implicitly wait is applied to a driver
        selenium will wait this time for all interactions below.
     */
    @Test(expectedExceptions = NoSuchElementException.class, priority = 1)
    void setImplicitlyWait () {
        startPage("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.tagName("button")).click();

        //This interaction needs 5 seconds to happen. If we apply less than
        //5 seconds, we get an error.
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#finish>h4"));
        //Throw an error!!
    }

    // We Could set a wait explicitly to manage our conditions.

    @Test (priority = 2)
    void setWaitExplicitly () {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.tagName("button")).click();

        //Waiting for h4
        WebElement h4 = wait.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.cssSelector("#finish"))));

        System.out.println(h4.getText());

        driver.navigate().to("https://demoqa.com/dynamic-properties");
        driver.findElement(By.cssSelector("#colorChange")).click();

        //New condition that verify if an element is become red
        ExpectedCondition<Boolean> newCondition = driver -> {
            WebElement btnChange = driver.findElement(By.cssSelector("#colorChange"));
            String colorBtn = btnChange.getCssValue("color");
            System.out.println(colorBtn); //Prints the current color

            if (colorBtn.equals("rgba(220, 53, 69, 1)")){
                return true;
            }else{
                return false;
            }
        };
        wait.until(newCondition);
    }

    //With this type of wait we could set an interval of polling and ignoring
    // exceptions that can appear;
    @Test (priority = 3)
    void setFluentWait () {
        FluentWait<WebDriver> waitFluent = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200));

        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.tagName("button")).click();

        //Every 200 milliseconds interval selenium will search the element
        waitFluent.until(ExpectedConditions
                .visibilityOf(driver.findElement(By.cssSelector("#finish"))));
    }
}
