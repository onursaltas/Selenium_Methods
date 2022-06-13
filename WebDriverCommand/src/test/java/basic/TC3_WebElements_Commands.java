package basic;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class TC3_WebElements_Commands extends BaseClass {

    WebElement doubleClickBtn;

    @Test (priority = 1)
    void inputAndClick () {
        startPage("https://demoqa.com/text-box");
        WebElement inputUser = driver.findElement(By.id("userName"));
        //If input is filled, this command will clean it up
        inputUser.clear();
        inputUser.sendKeys("Test with Inputs");
    }

    @Test (priority = 2)
    void clicks () {
        driver.navigate().to("https://demoqa.com/buttons");
        Actions act = new Actions(driver);

        //Performing double click's
        doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        act.doubleClick(doubleClickBtn).build().perform();

        //Performing right click's
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        act.contextClick(rightClickBtn).build().perform();

        //Normal Click's (By the Actions class and WebElement Methods
        WebElement btnClick = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/button"));
        btnClick.click();
        act.click(btnClick).build().perform();
    }

    @Test (priority = 3)
    void verifyingConditionOfElements () {
        boolean [] conditions = new boolean[3];
        //Should return true, because we can perform actions within this button
        conditions[0] = doubleClickBtn.isDisplayed();
        conditions[1] = doubleClickBtn.isEnabled();

        /*Should return false, because the current WebElement is a button
          not a checkbox or radiobutton
        */
        conditions[2] = doubleClickBtn.isSelected();

        assertTrue(conditions[0]);
        assertTrue(conditions[1]);
        assertFalse(conditions[2]);
    }

    @Test (priority = 4)
    void getInformationOfElements () {
        String textBtn = doubleClickBtn.getText();
        System.out.println("The text button is "+textBtn);

        String colorBtn = doubleClickBtn.getCssValue("cursor");
        System.out.println(colorBtn);
    }
}
