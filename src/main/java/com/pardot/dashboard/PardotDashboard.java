package com.pardot.dashboard;

import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotDashboard {
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

    public void isDashboardPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Dashboard Page Loaded");
        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Dashboard Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void selectMarketingEmails(Selenium selenium) throws InterruptedException {
        System.out.println("  -> Navigate to Marketing\\Emails");

        System.out.println("    -> Hover Over Marketing Menu Item");
        selenium.mouseHover(By.id(marketingMenuId));

        Thread.sleep(500);
        System.out.println("    -> Click Emails Menu Item");
        selenium.click(By.linkText(marketingEmailsLinkText));
    }

    public void selectMarketingSegmentationLists(Selenium selenium) throws InterruptedException {
        System.out.println("  -> Navigate to Marketing\\Segmentation\\Lists");

        System.out.println("    -> Hover Over Marketing Menu Item");
        selenium.mouseHover(By.id(marketingMenuId));

        System.out.println("    -> Hover Over Segmentation Menu Item");
        selenium.mouseHover(By.linkText(marketingSegmentationMenuLinkText));

        Thread.sleep(500);
        System.out.println("    -> Click Lists Menu Item");
        selenium.click(By.linkText(marketingSegmentationListsMenuLinkText));
    }

    public void selectProspectsProspectList(Selenium selenium) throws InterruptedException {
        System.out.println("  -> Navigate to Prospects\\Prospect List");

        System.out.println("    -> Hover Over Prospects Menu Item");
        selenium.mouseHover(By.id(prospectsMenuId));

        Thread.sleep(500);
        System.out.println("    -> Click Prospects List Menu Item");
        selenium.click(By.linkText(prospectsProspectListLinkText));
    }

    public void signOut(Selenium selenium) throws InterruptedException {
        System.out.println("  -> Sign Out of Pardot");

        System.out.println("    -> Hover Over User Account Menu Item");
        selenium.mouseHover(By.id(userAccountMenuDropdownId));

        Thread.sleep(500);
        System.out.println("    -> Click Sign Out Link");
        selenium.click(By.linkText(signOutLinkText));
    }

}
