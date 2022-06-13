package intermediate;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TC8_FindBrokenLinks extends BaseClass {

    List<WebElement> listLinks;

    @BeforeMethod
    void initializeList () {
        driver.get("https://demoqa.com/broken");
        listLinks = driver.findElements(By.tagName("a"));
    }

    @Test
    void findAllLinks () {
        System.out.println("Number of links:"+ listLinks.size());
        listLinks.stream().forEach(link -> {
            System.out.println(link.getText());
        });
    }

    @Test
    void findBrokenImages () {
        WebElement coolImg = driver.findElement(By.cssSelector("img[src='/images/Toolsqa.jpg']"));
        WebElement brokenImg = driver.findElement(By.cssSelector("img[src='/images/Toolsqa_1.jpg']"));

       String linkBroken = brokenImg.getAttribute("src");
       assertTrue(isLinkBroken(linkBroken, brokenImg));

        String linkOk = coolImg.getAttribute("src");
        assertFalse(isLinkBroken(linkOk, coolImg));
    }

    private boolean isLinkBroken (String url, WebElement img) {
        try {
            URL urlConnect = new URL(url);

            //Creating an url connection
            HttpURLConnection httpConnect = (HttpURLConnection) urlConnect.openConnection();
            httpConnect.setConnectTimeout(5000);
            httpConnect.connect();

            if (httpConnect.getResponseCode()>=400||(!verifyIsDisplayed(img))) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean verifyIsDisplayed (WebElement img) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return arguments[0].naturalWidth !='undefined' && arguments[0].naturalWidth>0", img);
    }
}
