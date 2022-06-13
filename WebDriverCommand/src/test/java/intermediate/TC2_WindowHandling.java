package intermediate;

import base.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC2_WindowHandling extends BaseClass{

    @Test
    void manipulatingWindows () {
        driver.get("https://demoqa.com/browser-windows");
        String parentId = driver.getWindowHandle();
        driver.findElement(By.id("windowButton")).click();
        //Changing to parent window
        driver.switchTo().window(parentId);
        driver.findElement(By.id("messageWindowButton")).click();
        //Gets all pages id open
        driver.getWindowHandles().forEach(id -> {
            System.out.println(id);
        });

    }

}
