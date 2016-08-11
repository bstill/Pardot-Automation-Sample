package com.pardot.prospects.prospect.overview;

import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotProspect {
    private String listMenuButtonLinkText = "Lists";

    private String waitIndicatorId = "indicator";

    public void isProspectPageLoaded(Selenium selenium, String pageTitleText) {
        System.out.println("  -> Verify Prospect Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Prospect Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void selectListsMenu (Selenium selenium) {
        System.out.println("  -> Click Lists Menu Button");
        selenium.click(By.linkText(listMenuButtonLinkText));
    }


}
