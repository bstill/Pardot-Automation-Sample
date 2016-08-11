package com.pardot.marketing.segmentation.lists.list;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotSegmentationList {
    private String editListLinkLinkText = "Edit";

    private String listsContainerId= "listxProspect_table";

    private String waitIndicatorId = "indicator";

    public void isListPageLoaded(Selenium selenium, String pageTitleText) {
        System.out.println("  -> Verify List Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify List Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void selectEditListLink(Selenium selenium) {
        System.out.println("  -> Open List Information");

        System.out.println("    -> Click Edit List Button");
        selenium.click(By.linkText(editListLinkLinkText));
    }

    public void isListProspectExist(Selenium selenium, String prospectName) {
        System.out.println("  -> Verify Prospect Added to List: " + prospectName);

        List<WebElement> elements = selenium.findElements(By.id(listsContainerId), By.tagName("tr"));

        for(WebElement e : elements){
            List<WebElement> cells = selenium.findElements(e, By.tagName("td"));

            for(WebElement td : cells){
                if (selenium.getText(td).trim().equals(prospectName)) {
                    System.out.println("    -> Found Prospect");
                    return;
                }
            }
        }

        selenium.throwRuntimeException("Prospect Not Found: " + prospectName);
    }


}
