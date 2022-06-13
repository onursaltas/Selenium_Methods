package basic;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TC4_RadioCheckCombo_Commands extends BaseClass {

    @Test (priority = 1)
    void manipulatingCheck () {
        startPage("https://demoqa.com/checkbox");

        WebElement labelCheck = driver.findElement(By.cssSelector("label[for='tree-node-home']"));
        labelCheck.click();
        assertTrue(driver.findElement(By.id("tree-node-home")).isSelected());
    }

    @Test (priority = 2)
    void manipulatingRadio () {
        driver.navigate().to("https://demoqa.com/radio-button");

        WebElement radioYes = driver.findElement(By.id("yesRadio"));
        driver.findElement(By.cssSelector("label[for='yesRadio']")).click();
        assertTrue(radioYes.isSelected());

        WebElement radioDisabled = driver.findElement(By.id("noRadio"));
        assertFalse(radioDisabled.isEnabled());
    }

    @Test
    void manipulatingComboBoxes () {
        driver.navigate().to("https://demoqa.com/select-menu");

        WebElement aSelect = driver.findElement(By.id("oldSelectMenu"));
        //We have this class to manipulate combos
        Select selectOld = new Select(aSelect);

        selectOld.selectByIndex(3);
        assertEquals("Yellow",selectOld.getFirstSelectedOption().getText());

        selectOld.selectByValue("5");
        assertEquals("Black",selectOld.getFirstSelectedOption().getText());
        //
        //Example of multiple selection in a combo
        WebElement selectMulti = driver.findElement(By.id("cars"));
        Select multiple = new Select(selectMulti);

        multiple.selectByValue("volvo");
        multiple.selectByValue("saab");
        List<WebElement> optionsSelected = multiple.getAllSelectedOptions();

        String option1 = optionsSelected.get(0).getText();
        String option2 = optionsSelected.get(1).getText();

        assertEquals("Volvo", option1);
        assertEquals("Saab", option2);

        System.out.println("A opção 1 é:"+option1);
        System.out.println("A opção 2 é:"+option2);
    }
}
