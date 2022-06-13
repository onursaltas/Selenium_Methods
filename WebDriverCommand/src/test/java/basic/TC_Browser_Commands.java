package basic;

import static org.testng.Assert.*;

import base.BaseClass;
import org.testng.annotations.Test;


import java.util.Arrays;

public class TC_Browser_Commands extends BaseClass {

    String[] contentPage;

    @Test (priority = 1)
    void openBrowser () {
        startPage("http://shop.demoqa.com/");
    }

    @Test (priority = 2)
    void titleLength () {
        contentPage = new String[4];
        //Page title
        contentPage[0] = driver.getTitle();
        // Current Url
        contentPage[1] = driver.getCurrentUrl();
        // Window Id - (Useful to change between open windows)
        contentPage[2] = driver.getWindowHandle();
        //Gets the source code of current page
        contentPage[3] = driver.getPageSource();

        Arrays.stream(contentPage).forEach(content ->{
            System.out.println(content);
        });

        assertEquals("http://shop.demoqa.com/", contentPage[1]);
    }

}
