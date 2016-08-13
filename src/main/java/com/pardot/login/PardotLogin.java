package com.pardot.login;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotLogin {
    private Reporting reporting;

    private String emailAddressFieldId = "email_address";
    private String passwordFieldId = "password";
    private String logInButtonName = "commit";

    private String pageTitleText = "Sign In";

    public PardotLogin(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isLogInPageLoaded(Selenium selenium) {
        reporting.writeInfo("  -> Verify Sign In Page Loaded");

        reporting.writeInfo("    -> Verify Sign In Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void loginPardot(Selenium selenium, String userName, String password) {
        reporting.writeInfo("  -> Perform Log In");

        reporting.writeInfo("    -> Enter Email/Username: " + userName  );
        selenium.sendKeys(By.id(emailAddressFieldId), userName);

        reporting.writeInfo("    -> Enter Password");
        selenium.sendKeys(By.id(passwordFieldId), password);

        reporting.writeInfo("    -> Click Log In Button");
        selenium.click(By.name(logInButtonName));
    }




}
