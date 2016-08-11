package com.pardot.marketing.segmentation.lists;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotSegmentationLists {
    private String pageTitleText = "Lists";

    private String addListButtonId = "listxistx_link_create";

    private String listsFilterFieldName = "table_filter";
    private String listsTableId = "listx_table";

    private String waitIndicatorId = "indicator";

    public void isSegmentationListsPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Segmentation Lists Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Sign In Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }
    }

    public void selectAddListButton(Selenium selenium) {
        System.out.println("  -> Open List Information");
        System.out.println("    -> Click Add List Button");
        selenium.click(By.id(addListButtonId));
    }

    public void isListExist(Selenium selenium, String listName) {
        System.out.println("  -> Verify List Exists");

        System.out.println("    -> Search for List");
        selenium.clear(By.name(listsFilterFieldName));
        selenium.sendKeys(By.name(listsFilterFieldName), listName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.id(listsTableId), By.tagName("tr"));

        for(WebElement e : elements){
            if (selenium.getText(e).contains(listName)) {
                System.out.println("    -> List Found");
                return;
            }
        }

        selenium.throwRuntimeException("List Not Found: " + listName);
    }

    public void isListNotExist(Selenium selenium, String listName) {
        System.out.println("  -> Verify List Does Not Exists");

        System.out.println("    -> Search for List");
        selenium.clear(By.name(listsFilterFieldName));
        selenium.sendKeys(By.name(listsFilterFieldName), listName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.id(listsTableId), By.tagName("tr"));

        for(WebElement e : elements){
            if (selenium.getText(e).contains(listName)) {
                selenium.throwRuntimeException("List Found: " + listName);
            }
        }

        System.out.println("    -> List Not Found");
    }

    public void selectList(Selenium selenium, String listName) {
        System.out.println("  -> Select List");

        System.out.println("    -> Search for List");
        selenium.clear(By.name(listsFilterFieldName));
        selenium.sendKeys(By.name(listsFilterFieldName), listName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Click List");
        selenium.click(selenium.findChildElement(By.id(listsTableId), By.linkText(listName)));
    }


}
