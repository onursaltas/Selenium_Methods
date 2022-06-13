package basic;

import base.BaseClass;
import org.testng.annotations.Test;


public class TC2_Navigation_Commands extends BaseClass {

    @Test
    void navigation () {
        startPage("http://shop.demoqa.com/");
        //Go to other page in the current window
        driver.navigate().to("https://demoqa.com/");
        //Refresh Page
        driver.navigate().refresh();
    }
}
