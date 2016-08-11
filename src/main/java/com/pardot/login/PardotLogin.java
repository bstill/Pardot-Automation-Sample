package com.pardot.login;

import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotLogin {
    private String emailAddressFieldId = "email_address";
    private String passwordFieldId = "password";
    private String logInButtonName = "commit";

    private String pageTitleText = "Sign In";

    public void isLogInPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Sign In Page Loaded");

        System.out.println("    -> Verify Sign In Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void loginPardot(Selenium selenium, String userName, String password) {
        System.out.println("  -> Perform Log In");

        System.out.println("    -> Enter Email/Username: " + userName  );
        selenium.sendKeys(By.id(emailAddressFieldId), userName);

        System.out.println("    -> Enter Password");
        selenium.sendKeys(By.id(passwordFieldId), password);

        System.out.println("    -> Click Log In Button");
        selenium.click(By.name(logInButtonName));
    }




}
