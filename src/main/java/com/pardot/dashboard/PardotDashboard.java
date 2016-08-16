package com.pardot.dashboard;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotDashboard {
    private Reporting reporting;

    private String userAccountMenuDropdownId = "menu-account";
    private String pageTitleText = "Dashboard";

    private String marketingMenuId = "mark-tog";
    private String marketingSegmentationMenuLinkText = "Segmentation";
    private String marketingSegmentationListsMenuLinkText = "Lists";
    private String marketingEmailsLinkText = "Emails";

    private String prospectsMenuId = "pro-tog";
    private String prospectsProspectListLinkText = "Prospect List";

    private String signOutLinkText = "Sign Out";

    private String waitIndicatorId = "indicator";

    public PardotDashboard(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isDashboardPageLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify Dashboard Page Loaded");
        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Dashboard Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }
    }

    public void clickMarketingEmails(Selenium selenium) throws InterruptedException {
        reporting.writeStep("---> Navigate to Marketing\\Emails");

        reporting.writeInfo("-----> Hover Over Marketing Menu Item");
        selenium.mouseHover(By.id(marketingMenuId));

        Thread.sleep(500);
        reporting.writeInfo("-----> Click Emails Menu Item");
        selenium.click(By.linkText(marketingEmailsLinkText));
    }

    public void clickMarketingSegmentationLists(Selenium selenium) throws InterruptedException {
        reporting.writeStep("---> Navigate to Marketing\\Segmentation\\Lists");

        reporting.writeInfo("-----> Hover Over Marketing Menu Item");
        selenium.mouseHover(By.id(marketingMenuId));

        reporting.writeInfo("-----> Hover Over Segmentation Menu Item");
        selenium.mouseHover(By.linkText(marketingSegmentationMenuLinkText));

        Thread.sleep(500);
        reporting.writeInfo("-----> Click Lists Menu Item");
        selenium.click(By.linkText(marketingSegmentationListsMenuLinkText));
    }

    public void clickProspectsProspectList(Selenium selenium) throws InterruptedException {
        reporting.writeStep("---> Navigate to Prospects\\Prospect List");

        reporting.writeInfo("-----> Hover Over Prospects Menu Item");
        selenium.mouseHover(By.id(prospectsMenuId));

        Thread.sleep(500);
        reporting.writeInfo("-----> Click Prospects List Menu Item");
        selenium.click(By.linkText(prospectsProspectListLinkText));
    }

    public void signOut(Selenium selenium) throws InterruptedException {
        reporting.writeStep("---> Sign Out of Pardot");

        reporting.writeInfo("-----> Hover Over User Account Menu Item");
        selenium.mouseHover(By.id(userAccountMenuDropdownId));

        Thread.sleep(500);
        reporting.writeInfo("-----> Click Sign Out Link");
        selenium.click(By.linkText(signOutLinkText));
    }

}
