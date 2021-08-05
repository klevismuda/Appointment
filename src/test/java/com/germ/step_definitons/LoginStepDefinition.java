package com.germ.step_definitons;

import com.germ.pages.LoginPage;
import com.germ.utilities.BrowserUtils;
import com.germ.utilities.ConfigurationReader;
import com.germ.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinition {

    LoginPage loginPage = new LoginPage();

    @When("user is on main page")
    public void user_is_on_main_page() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriverPool().get(url);
    }
    @Then("user should not see {string} in text")
    public void user_should_not_see_in_text(String expected) {
        BrowserUtils.waitForVisibility(loginPage.text, 20);
        String actual = loginPage.text.getText();

        Assert.assertTrue(actual.contains(expected));
    }


}
