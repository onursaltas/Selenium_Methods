package intermediate;

import base.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC4_Iframes extends BaseClass{

    /*
        An iframe is a space on the web page that embeds different
        kinds of media like images, documents, videos inside the main web page.
     */
    @Test
    void manipulatingIframes () {
        startPage("https://demoqa.com/nestedframes");

        String textParent = driver.switchTo().frame("frame1")
                .findElement(By.tagName("body")).getText();
        Assert.assertEquals("Parent frame", textParent);

        String textFrameChild = driver.switchTo().frame(0)
                .findElement(By.tagName("p")).getText();
        Assert.assertEquals("Child Iframe", textFrameChild);
    }
}
