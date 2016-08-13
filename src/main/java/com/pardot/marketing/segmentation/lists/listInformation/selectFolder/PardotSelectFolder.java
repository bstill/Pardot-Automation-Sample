package com.pardot.marketing.segmentation.lists.listInformation.selectFolder;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotSelectFolder {
    private Reporting reporting;

    private String searchFieldClass = "ember-text-field filter-by";
    private String addFolderButtonXpath = ".//*[@id='asset-chooser-app-modal']/div[3]/div/div[2]/div/div[2]/div/div[2]/div";
    private String selectFolderModalTitleXpath = ".//*[@id='asset-chooser-app-modal']/div[1]/h3";
    private String modalTitleText = "Select A Folder";
    private String chooseSelectedButtonId = "select-asset";

    private String folderListContainerClass = "ember-list-container";

    private String addNewFolderNameFieldClass = "ember-text-field js-new-folder";
    private String addNewFolderSaveButtonClass = "btn-success";

    private String waitIndicatorId = "indicator";

    public PardotSelectFolder(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isSelectFolderModalLoaded(Selenium selenium) {
        reporting.writeInfo("  -> Verify Select A Folder Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("    -> Verify Select Folder Modal Title is: " + modalTitleText);
        if (!selenium.getText(By.xpath(selectFolderModalTitleXpath)).equals(modalTitleText)) {
            selenium.throwRuntimeException("Modal Title is Not: " + modalTitleText + "(" + selenium.getText(By.xpath(selectFolderModalTitleXpath)) + ")");
        }
    }

    public void isFolderExist(Selenium selenium, String folderName) {
        reporting.writeInfo("  -> Verify Folder Exists");

        reporting.writeInfo("    -> Search for Folder");
        selenium.clear(By.cssSelector("Input[class*='" + searchFieldClass + "'"));
        selenium.sendKeys(By.cssSelector("Input[class*='" + searchFieldClass + "'"), folderName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.className(folderListContainerClass), By.tagName("span"));

        for(WebElement e : elements){
            if (e.getText().equals(folderName)) {
                reporting.writeInfo("    -> Folder Found");
                return;
            }
        }

        selenium.throwRuntimeException("Folder Not Found: " + folderName);
    }

    public void selectFolder(Selenium selenium, String folderName) {
        reporting.writeInfo("  -> Select Folder");

        reporting.writeInfo("    -> Search for Folder");
        selenium.clear(By.cssSelector("Input[class*='" + searchFieldClass + "'"));
        selenium.sendKeys(By.cssSelector("Input[class*='" + searchFieldClass + "'"), folderName);

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        List<WebElement> elements = selenium.findElements(By.className(folderListContainerClass), By.tagName("span"));

        for(WebElement e : elements){
            if (e.getText().equals(folderName)) {
                reporting.writeInfo("    -> Click Folder");
                e.click();
                return;
            }
        }

        selenium.throwRuntimeException("Folder Not Found: " + folderName);
    }

    public void selectCreateFolderButton(Selenium selenium) {
        reporting.writeInfo("    -> Click Add Folder Button");
        selenium.click(By.xpath(addFolderButtonXpath));
    }

    public void addNewFolder (Selenium selenium, String folderName) {
        reporting.writeInfo("  -> Add Folder");

        reporting.writeInfo("    -> Enter Folder Name");
        selenium.clear(By.cssSelector("Input[class*='" + addNewFolderNameFieldClass + "'"));
        selenium.sendKeys(By.cssSelector("Input[class*='" + addNewFolderNameFieldClass + "'"), folderName);

        reporting.writeInfo("    -> Save Folder");
        selenium.click(By.cssSelector("Div[class*='" + addNewFolderSaveButtonClass + "'"));
    }

    public void selectChooseSelectedButton(Selenium selenium) {
        reporting.writeInfo("    -> Click Choose Selected Button");
        selenium.click(By.id(chooseSelectedButtonId));
    }




}
