package com.pardot.marketing.emails;

import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotEmails {
    private String pageTitleText = "Emails";
    private String sendListEmailButtonXpath = ".//*[@id='em_module']/div[1]/span/a";
    private String waitIndicatorId = "indicator";

    public void isEmailsPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Emails Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Emails Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void selectSendListEmailButton(Selenium selenium) {
        System.out.println("  -> Open Basic Email Information Modal");

        System.out.println("    -> Click Send List Email Button");
        selenium.click(By.xpath(sendListEmailButtonXpath));
    }



}
