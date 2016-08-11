package com.pardot.marketing.emails.emailEditor.building;

import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotEmailBuilding {
    private String emailNameHeaderId = "control_name";
    private String pageTitleText = "Building";

    private String headerSendingButtonId = "flow_sending";

    private String waitIndicatorId = "indicator";

    public void isEmailBuildingPageLoaded(Selenium selenium, String emailName) {
        System.out.println("  -> Verify Email Building Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Email Building Page Title Contains: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }

        System.out.println("    -> Verify Email Name is: " + emailName);
        if (!selenium.getText(By.id(emailNameHeaderId)).contains(emailName)) {
            selenium.throwRuntimeException("Email is Not: " + emailName);
        }
    }

    public void selectSendingButton(Selenium selenium) {
        System.out.println("  -> Click Header Sending Button");
        selenium.click(By.id(headerSendingButtonId));
    }

}
