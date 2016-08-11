package com.pardot.marketing.segmentation.lists.listInformation;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class PardotListInformation {
    private String nameFieldName = "name";
    private String folderFieldXpath = ".//*[@id='li_form_update']/div[2]/div/div[1]/span[2]";
    private String createListButtonId = "save_information";
    private String listInformationModalTitleId = "myModalLabel";
    private String modalTitleText = "List Information";
    private String chooseFolderButtonXpath = ".//*[@id='li_form_update']/div[2]/div/div[1]/button";

    private String createListErrorHeaderClass = "alert-error";
    private String createListErrorNameId = "error_for_name";
    private String createListDuplicateErrorText = "Please input a unique value for this field";
    private String createListHeaderErrorText = "Please correct the errors below and re-submit";

    private String waitIndicatorId = "indicator";

    public void isListInformationModalLoaded(Selenium selenium) {
        try {
            System.out.println("  -> Verify List Information Loaded");

            selenium.waitElementInvisible(By.id(waitIndicatorId));

            System.out.println("    -> Verify List Information Modal Title is: " + modalTitleText);
            if (!selenium.getText(By.id(listInformationModalTitleId)).equals(modalTitleText)) {
                throw new RuntimeException ("Modal Title is Not: " + modalTitleText);
            }
        } catch (TimeoutException e) {
            System.err.println("List Information Modal Load: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("List Information Modal Load: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void isListInformationModalPopulated(Selenium selenium, String listName, String folderName) {
        try {
            System.out.println("  -> Verify List Information Populated");

            System.out.println("    -> Verify Name Field Value: " + listName);
            if (!selenium.getAttribute(By.name(nameFieldName), "value").equals(listName)) {
                throw new RuntimeException ("Name Field Value is Not: " + listName);
            }
            System.out.println("    -> Verify Folder Field Value: /" + folderName);
            if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/" + folderName)) {
                throw new RuntimeException ("Folder Field Value is Not: /" + folderName);
            }
        } catch (TimeoutException e) {
            System.err.println("Check List Information Modal Populated: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Check List Information Modal Populated: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void isListInformationModalNotPopulated(Selenium selenium) {
        try {
            System.out.println("  -> Verify List Information NOT Populated");

            System.out.println("    -> Verify Name Field Value is Blank");
            if (!selenium.getAttribute(By.name(nameFieldName), "value").equals("")) {
                throw new RuntimeException ("Name Field Value is Not Blank");
            }
            System.out.println("    -> Verify Folder Field Value is Default");
            if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/Uncategorized/Lists")) {
                throw new RuntimeException ("Folder Field Value is Not Default");
            }
        } catch (TimeoutException e) {
            System.err.println("Check List Information Modal Not Populated: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Check List Information Modal Not Populated: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void selectChooseFolderButton(Selenium selenium) {
        try {
            System.out.println("  -> Click Choose Folder Button");
            selenium.click(By.xpath(chooseFolderButtonXpath));
        } catch (TimeoutException e) {
            System.err.println("Click Choose Folder Button: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        }
    }

    public void isFolderSelected(Selenium selenium, String folderName) {
        try {
            System.out.println("  -> Verify Folder Field Contains Selected Folder");

            if (selenium.getText(By.xpath(folderFieldXpath)).equals("/" + folderName)) {
                return;
            }

            throw new RuntimeException ("Folder Not Selected: " + folderName);
        } catch (TimeoutException e) {
            System.err.println("Check Folder Selected: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Check Folder Selected: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }

    public void createList(Selenium selenium, String listName) {
        try {
            System.out.println("  -> Create/Edit Segmentation List");

            System.out.println("    -> Enter List Name: " + listName);
            selenium.clear(By.name(nameFieldName));
            selenium.sendKeys(By.name(nameFieldName), listName);
        } catch (TimeoutException e) {
            System.err.println("Create/Edit List: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        }
    }

    public void saveList(Selenium selenium) {
        try {
            System.out.println("  -> Save Segmentation List");
            selenium.click(By.id(createListButtonId));
        } catch (TimeoutException e) {
            System.err.println("Click Save Button: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        }
    }

    public void isListInformationDuplicateNameErrorDisplayed(Selenium selenium) {
        try {
            System.out.println("  -> Verify List Information Duplicate Name Error Displayed");

            System.out.println("    -> Verify Header Error Message is: " + createListHeaderErrorText);
            if (!selenium.getText(By.cssSelector("Div[class*='" + createListErrorHeaderClass + "'")).equals(createListHeaderErrorText)) {
                throw new RuntimeException ("Header Error Message is Not: " + createListHeaderErrorText);
            }

            System.out.println("    -> Verify Name Error Message is: " + createListDuplicateErrorText);
            if (!selenium.getText(By.id(createListErrorNameId)).equals(createListDuplicateErrorText)) {
                throw new RuntimeException ("Name Error Message is Not: " + createListDuplicateErrorText);
            }
        } catch (TimeoutException e) {
            System.err.println("Verify Duplicate Name Error Displayed: FAILED");
            System.err.println("TimeoutException: " + e.getMessage());

            throw e;
        } catch (RuntimeException e) {
            System.err.println("Verify Duplicate Name Error Displayed: FAILED");
            System.err.println("RuntimeException: " + e.getMessage());

            throw e;
        }
    }


}
