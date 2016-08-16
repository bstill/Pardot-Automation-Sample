package com.pardot.prospects.prospect.lists;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotProspectLists {
    private Reporting reporting;

    private String pageTitleText = "Lists";

    private String listsSelectedContainerClass = "selected-lists";

    private String waitIndicatorId = "indicator";

    public PardotProspectLists(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isProspectListsPageLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify Prospect Lists Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Prospect Lists Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }
    }

    public void isProspectListExist(Selenium selenium, String listName) {
        reporting.writeInfo("---> Verify List Added to Prospect: " + listName);
        List<WebElement> elements = selenium.findElements(By.className(listsSelectedContainerClass), By.tagName("li"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "data-name").equals(listName)) {
                reporting.writePass("Found List");
                return;
            }
        }

        selenium.throwRuntimeException("Prospect List Not Found: " + listName, true);
    }


}
