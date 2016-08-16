package com.pardot.marketing.segmentation.lists;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotSegmentationLists {
    private Reporting reporting;

    private String pageTitleText = "Lists";

    private String addListButtonId = "listxistx_link_create";

    private String listsFilterFieldName = "table_filter";
    private String listsTableId = "listx_table";

    private String waitIndicatorId = "indicator";

    public PardotSegmentationLists(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isSegmentationListsPageLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify Segmentation Lists Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Sign In Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }
    }

    public void clickAddListButton(Selenium selenium) {
        reporting.writeStep("---> Open List Information");
        reporting.writeInfo("-----> Click Add List Button");
        selenium.click(By.id(addListButtonId));
    }

    public void isListExist(Selenium selenium, String listName) {
        reporting.writeInfo("---> Verify List Exists");

        reporting.writeInfo("-----> Search for List: " + listName);
        selenium.clear(By.name(listsFilterFieldName));
        selenium.sendKeys(By.name(listsFilterFieldName), listName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.id(listsTableId), By.tagName("tr"));

        for(WebElement e : elements){
            if (selenium.getText(e).contains(listName)) {
                reporting.writePass("List Found");
                return;
            }
        }

        selenium.throwRuntimeException("List Not Found: " + listName, true);
    }

    public void isListNotExist(Selenium selenium, String listName) {
        reporting.writeInfo("---> Verify List Does Not Exists");

        reporting.writeInfo("-----> Search for List: " + listName);
        selenium.clear(By.name(listsFilterFieldName));
        selenium.sendKeys(By.name(listsFilterFieldName), listName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.id(listsTableId), By.tagName("tr"));

        for(WebElement e : elements){
            if (selenium.getText(e).contains(listName)) {
                selenium.throwRuntimeException("List Found: " + listName, true);
            }
        }

        reporting.writePass("List Not Found");
    }

    public void clickList(Selenium selenium, String listName) {
        reporting.writeStep("---> Select List");

        reporting.writeInfo("-----> Search for List: " + listName);
        selenium.clear(By.name(listsFilterFieldName));
        selenium.sendKeys(By.name(listsFilterFieldName), listName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Click List");
        selenium.click(selenium.findChildElement(By.id(listsTableId), By.linkText(listName)));
    }


}
