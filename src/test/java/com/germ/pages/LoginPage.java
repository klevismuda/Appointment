package com.germ.pages;

import com.germ.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriverPool(), this);
    }

    @FindBy(xpath = "//div[@style='font-size: 14pt; font-weight: bold; margin-bottom: 1em;']")
    public WebElement text;
}
