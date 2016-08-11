package com.pardot.prospects.prospect.lists;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotProspectLists {
    private String pageTitleText = "Lists";

    private String listsSelectedContainerClass = "selected-lists";

    private String waitIndicatorId = "indicator";

    public void isProspectListsPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Prospect Lists Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Prospect Lists Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void isProspectListExist(Selenium selenium, String listName) {
        System.out.println("  -> Verify List Added to Prospect: " + listName);
        List<WebElement> elements = selenium.findElements(By.className(listsSelectedContainerClass), By.tagName("li"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "data-name").equals(listName)) {
                System.out.println("    -> Found List");
                return;
            }
        }

        selenium.throwRuntimeException("Prospect List Not Found: " + listName);
    }


}
