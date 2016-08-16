package com.pardot.prospects.list;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotProspects {
    private Reporting reporting;

    private String pageTitleText = "Prospects";

    private String addProspectButtonId = "pr_link_create";

    private String prospectsFilterDateRangeDropdownId = "dateRange_pr";

    private String prospectsFilterFieldName = "table_filter";
    private String prospectsTableId = "prospect_table";

    private String waitIndicatorId = "indicator";

    public PardotProspects(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isProspectsPageLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify Prospects Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Prospects Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }
    }

    public void clickAddProspectButton(Selenium selenium) {
        reporting.writeStep("---> Open Create Prospect Page");

        reporting.writeInfo("-----> Click Add Prospect Button");
        selenium.click(By.id(addProspectButtonId));
    }

    public void isProspectExist(Selenium selenium, String prospectName) {
        reporting.writeInfo("---> Verify Prospect Exists");

        reporting.writeInfo("-----> Search for Prospect: " + prospectName);
        selenium.selectByVisibleText(By.id(prospectsFilterDateRangeDropdownId), "Today");
        selenium.clear(By.name(prospectsFilterFieldName));
        selenium.sendKeys(By.name(prospectsFilterFieldName), prospectName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.id(prospectsTableId), By.tagName("tr"));

        for(WebElement e : elements){
            if (selenium.getText(e).contains(prospectName)) {
                reporting.writePass("Prospect Found");
                return;
            }
        }

        selenium.throwRuntimeException("Prospect Not Found: " + prospectName, true);
    }


}
