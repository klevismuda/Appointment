package com.germ.step_definitons;


import com.germ.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @After
    public void tearDownScenario(Scenario scenario){


        if(scenario.isFailed()) {

            //Driver.closeDriver();   // Taking screenshot
            byte[] screenshot = ((TakesScreenshot) Driver.getDriverPool()).getScreenshotAs(OutputType.BYTES);  // casting in this ((TakesScreenshot)Driver.getDriver())
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }

}
