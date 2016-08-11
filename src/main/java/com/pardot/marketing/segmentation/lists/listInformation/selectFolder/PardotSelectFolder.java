package com.pardot.marketing.segmentation.lists.listInformation.selectFolder;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotSelectFolder {
    private String searchFieldClass = "ember-text-field filter-by";
    private String addFolderButtonXpath = ".//*[@id='asset-chooser-app-modal']/div[3]/div/div[2]/div/div[2]/div/div[2]/div";
    private String selectFolderModalTitleXpath = ".//*[@id='asset-chooser-app-modal']/div[1]/h3";
    private String modalTitleText = "Select A Folder";
    private String chooseSelectedButtonId = "select-asset";

    private String folderListContainerClass = "ember-list-container";

    private String addNewFolderNameFieldClass = "ember-text-field js-new-folder";
    private String addNewFolderSaveButtonClass = "btn-success";

    private String waitIndicatorId = "indicator";

    public void isSelectFolderModalLoaded(Selenium selenium) {
        try {
            System.out.println("  -> Verify Select A Folder Loaded");

            selenium.waitElementInvisible(By.id(waitIndicatorId));

            System.out.println("    -> Verify Select Folder Modal Title is: " + modalTitleText);
            if (!selenium.getText(By.xpath(selectFolderModalTitleXpath)).equals(modalTitleText)) {
                throw new RuntimeException ("Modal Title is Not: " + modalTitleText + "(" + selenium.getText(By.xpath(selectFolderModalTitleXpath)) + ")");
            }
        } catch (TimeoutException e) {
            System.err.println("Select A Folder Modal Load: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Select A Folder Modal Load: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void isFolderExist(Selenium selenium, String folderName) {
        try {
            System.out.println("  -> Verify Folder Exists");

            System.out.println("    -> Search for Folder");
            selenium.clear(By.cssSelector("Input[class*='" + searchFieldClass + "'"));
            selenium.sendKeys(By.cssSelector("Input[class*='" + searchFieldClass + "'"), folderName);

            selenium.waitElementInvisible(By.id(waitIndicatorId));

            List<WebElement> elements = selenium.findElements(By.className(folderListContainerClass), By.tagName("span"));

            for(WebElement e : elements){
                if (e.getText().equals(folderName)) {
                    System.out.println("    -> Folder Found");
                    return;
                }
            }

            throw new RuntimeException ("Folder Not Found: " + folderName);
        } catch (TimeoutException e) {
            System.err.println("Check Folder Exists: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Check Folder Exists: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void selectFolder(Selenium selenium, String folderName) {
        try {
            System.out.println("  -> Select Folder");

            System.out.println("    -> Search for Folder");
            selenium.clear(By.cssSelector("Input[class*='" + searchFieldClass + "'"));
            selenium.sendKeys(By.cssSelector("Input[class*='" + searchFieldClass + "'"), folderName);

            selenium.waitElementInvisible(By.id(waitIndicatorId));

            List<WebElement> elements = selenium.findElements(By.className(folderListContainerClass), By.tagName("span"));

            for(WebElement e : elements){
                if (e.getText().equals(folderName)) {
                    System.out.println("    -> Click Folder");
                    e.click();
                    return;
                }
            }

            throw new RuntimeException ("Folder Not Found: " + folderName);
        } catch (TimeoutException e) {
            System.err.println("Select Folder: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Select Folder: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void selectCreateFolderButton(Selenium selenium) {
        try {
            System.out.println("    -> Click Add Folder Button");
            selenium.click(By.xpath(addFolderButtonXpath));
        } catch (TimeoutException e) {
            System.err.println("Click Add Folder Button: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        }
    }

    public void addNewFolder (Selenium selenium, String folderName) {
        try {
            System.out.println("  -> Add Folder");

            System.out.println("    -> Enter Folder Name");
            selenium.clear(By.cssSelector("Input[class*='" + addNewFolderNameFieldClass + "'"));
            selenium.sendKeys(By.cssSelector("Input[class*='" + addNewFolderNameFieldClass + "'"), folderName);

            System.out.println("    -> Save Folder");
            selenium.click(By.cssSelector("Div[class*='" + addNewFolderSaveButtonClass + "'"));
        } catch (TimeoutException e) {
            System.err.println("Click Add Folder Button: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        }
    }

    public void selectChooseSelectedButton(Selenium selenium) {
        try {
            System.out.println("    -> Click Choose Selected Button");
            selenium.click(By.id(chooseSelectedButtonId));
        } catch (TimeoutException e) {
            System.err.println("Click Choose Selected Button: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        }
    }




}
