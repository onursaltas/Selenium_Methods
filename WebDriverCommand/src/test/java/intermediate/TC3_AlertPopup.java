package intermediate;

import base.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC3_AlertPopup extends BaseClass {

    @Test
    void alerts () {

        startPage("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        Alert alertPage = driver.switchTo().alert();

        //First alert that appears
        String textAlert = alertPage.getText();
        System.out.println(textAlert);
        alertPage.accept();

        //Alert that appears after 5 seconds
        WebDriverWait wait = new WebDriverWait(driver, 6);
        driver.findElement(By.id("timerAlertButton")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert unexpectedAlert = driver.switchTo().alert();
        String text = unexpectedAlert.getText();
        System.out.println(text);
        unexpectedAlert.accept();

        //Confirm alert
        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().accept();
        String confirmResponse = driver.findElement(By.id("confirmResult")).getText();
        Assert.assertEquals("You selected Ok", confirmResponse);

        //A prompt
        driver.findElement(By.id("promtButton")).click();
        alertPage.sendKeys("Prompt Test");
        alertPage.accept();
        String promptText = driver.findElement(By.id("promptResult")).getText();
        Assert.assertEquals("You entered"+" Prompt Test", promptText);
    }
}
