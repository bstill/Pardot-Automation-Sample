package com.pardot.marketing.segmentation.lists.listinformation;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotListInformation {
    private Reporting reporting;
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

    public PardotListInformation(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isListInformationModalLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify List Information Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify List Information Modal Title is: " + modalTitleText);
        if (!selenium.getText(By.id(listInformationModalTitleId)).equals(modalTitleText)) {
            selenium.throwRuntimeException("Modal Title is Not: " + modalTitleText, true);
        } else {
            reporting.writePass("Modal Title Found");
        }
    }

    public void isListInformationModalPopulated(Selenium selenium, String listName, String folderName) {
        reporting.writeInfo("---> Verify List Information Populated");

        reporting.writeInfo("-----> Verify Name Field Value: " + listName);
        if (!selenium.getAttribute(By.name(nameFieldName), "value").equals(listName)) {
            selenium.throwRuntimeException("Name Field Value is Not: " + listName, true);
        } else {
            reporting.writePass("Name Found");
        }

        reporting.writeInfo("-----> Verify Folder Field Value: /" + folderName);
        if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/" + folderName)) {
            selenium.throwRuntimeException("Folder Field Value is Not: /" + folderName, true);
        } else {
            reporting.writePass("Folder Found");
        }
    }

    public void isListInformationModalNotPopulated(Selenium selenium) {
        reporting.writeInfo("---> Verify List Information NOT Populated");

        reporting.writeInfo("-----> Verify Name Field Value is Blank");
        if (!selenium.getAttribute(By.name(nameFieldName), "value").equals("")) {
            selenium.throwRuntimeException("Name Field Value is Not Blank", true);
        } else {
            reporting.writePass("Name Field Blank");
        }

        reporting.writeInfo("-----> Verify Folder Field Value is Default");
        if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/Uncategorized/Lists")) {
            selenium.throwRuntimeException("Folder Field Value is Not Default", true);
        } else {
            reporting.writePass("Folder Field Default");
        }
    }

    public void clickChooseFolderButton(Selenium selenium) {
        reporting.writeStep("---> Click Choose Folder Button");
        selenium.click(By.xpath(chooseFolderButtonXpath));
    }

    public void isFolderSelected(Selenium selenium, String folderName) {
        reporting.writeInfo("---> Verify Folder Field Contains Selected Folder");

        reporting.writeInfo("-----> Verify Folder Field Value: /" + folderName);
        if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/" + folderName)) {
            selenium.throwRuntimeException("Folder Field Value is Not: /" + folderName, true);
        } else {
            reporting.writePass("Folder Found");
        }
    }

    public void createList(Selenium selenium, String listName) {
        reporting.writeStep("---> Create/Edit Segmentation List");

        reporting.writeInfo("-----> Enter List Name: " + listName);
        selenium.clear(By.name(nameFieldName));
        selenium.sendKeys(By.name(nameFieldName), listName);
    }

    public void saveList(Selenium selenium) {
        reporting.writeStep("---> Save Segmentation List");
        selenium.click(By.id(createListButtonId));
    }

    public void isListInformationDuplicateNameErrorDisplayed(Selenium selenium) {
        reporting.writeInfo("---> Verify List Information Duplicate Name Error Displayed");

        reporting.writeInfo("-----> Verify Header Error Message is: " + createListHeaderErrorText);
        if (!selenium.getText(By.cssSelector("Div[class*='" + createListErrorHeaderClass + "'")).equals(createListHeaderErrorText)) {
            selenium.throwRuntimeException("Header Error Message is Not: " + createListHeaderErrorText, true);
        } else {
            reporting.writePass("Header Error Message Found");
        }

        reporting.writeInfo("-----> Verify Name Error Message is: " + createListDuplicateErrorText);
        if (!selenium.getText(By.id(createListErrorNameId)).equals(createListDuplicateErrorText)) {
            selenium.throwRuntimeException("Name Error Message is Not: " + createListDuplicateErrorText, true);
        } else {
            reporting.writePass("Name Error Message Found");
        }
    }


}
