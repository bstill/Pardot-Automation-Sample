package com.pardot.marketing.emails.emaileditor.building;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotEmailBuilding {
    private Reporting reporting;

    private String emailNameHeaderId = "control_name";
    private String pageTitleText = "Building";

    private String headerSendingButtonId = "flow_sending";

    private String waitIndicatorId = "indicator";

    public PardotEmailBuilding(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isEmailBuildingPageLoaded(Selenium selenium, String emailName) {
        reporting.writeInfo("---> Verify Email Building Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Email Building Page Title Contains: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }

        reporting.writeInfo("-----> Verify Email Name is: " + emailName);
        if (!selenium.getText(By.id(emailNameHeaderId)).contains(emailName)) {
            selenium.throwRuntimeException("Email is Not: " + emailName, true);
        } else {
            reporting.writePass("Email Name Found");
        }
    }

    public void clickSendingButton(Selenium selenium) {
        reporting.writeStep("---> Click Header Sending Button");
        selenium.click(By.id(headerSendingButtonId));
    }

}
