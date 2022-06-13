package intermediate;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC5_ActionClass extends BaseClass {

    Actions act;

    @BeforeMethod
    void initializeAct () {
        act = new Actions(driver);
    }

    //The Action class serves to performing mouse and keyboard events;
    @Test (priority = 1)
    void performingMouseActions () {
        //act = new Actions(driver);
        startPage("https://demoqa.com/buttons");

        //A normal click
        WebElement btnClick = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/button"));
        act.click(btnClick).build().perform();

        //Double click
        WebElement btnDouble = driver.findElement(By.id("doubleClickBtn"));
        act.doubleClick(btnDouble).build().perform();

        //Right click
        WebElement btnRight = driver.findElement(By.id("rightClickBtn"));
        act.contextClick(btnRight).build().perform();

        //Drag and drop
        driver.navigate().to("https://demoqa.com/droppable");
        WebElement squareDraggable = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        act.dragAndDrop(squareDraggable, target).build().perform();

        //Mouse Hover
        driver.navigate().to("https://demoqa.com/menu/");
        WebElement menuItem = driver.findElement(By.xpath("//*[@id='nav']/li[2]/a"));
        act.moveToElement(menuItem).build().perform();
        //Slider
        driver.navigate().to("https://demoqa.com/slider");
        WebElement inputSlider = driver.findElement(By.cssSelector("input[max='100']"));
        act.moveToElement(inputSlider, 50, 0).click().build().perform();

    }

    @Test (priority = 2)
    void performingKeyboardEvents () {
        driver.navigate().to("https://demoqa.com/text-box");
        //act = new Actions(driver);
        WebElement inputUser = driver.findElement(By.id("userName"));
        act.keyDown(inputUser, Keys.SHIFT).sendKeys("capitalized text").build().perform();

    }
}
