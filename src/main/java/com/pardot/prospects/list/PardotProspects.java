package com.pardot.prospects.list;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotProspects {
    private String pageTitleText = "Prospects";

    private String addProspectButtonId = "pr_link_create";

    private String prospectsFilterDateRangeDropdownId = "dateRange_pr";

    private String prospectsFilterFieldName = "table_filter";
    private String prospectsTableId = "prospect_table";

    private String waitIndicatorId = "indicator";

    public void isProspectsPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Prospects Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Prospects Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void selectAddProspectButton(Selenium selenium) {
        System.out.println("  -> Open Create Prospect Page");

        System.out.println("    -> Click Add Prospect Button");
        selenium.click(By.id(addProspectButtonId));
    }

    public void isProspectExist(Selenium selenium, String prospectName) {
        System.out.println("  -> Verify Prospect Exists");

        System.out.println("    -> Search for Prospect");
        selenium.selectByVisibleText(By.id(prospectsFilterDateRangeDropdownId), "Today");
        selenium.clear(By.name(prospectsFilterFieldName));
        selenium.sendKeys(By.name(prospectsFilterFieldName), prospectName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.id(prospectsTableId), By.tagName("tr"));

        for(WebElement e : elements){
            if (selenium.getText(e).contains(prospectName)) {
                System.out.println("    -> Prospect Found");
                return;
            }
        }

        selenium.throwRuntimeException("Prospect Not Found: " + prospectName);
    }


}
