package com.pardot.marketing.emails.emailinformation;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotBasicEmailInformation {
    private Reporting reporting;

    private String listInformationModalTitleId = "myModalLabel";
    private String modalTitleText = "Basic Email Information";

    private String nameFieldName = "name";
    private String emailTypeTextId = "email_type_text_only";
    private String emailTypeHtmlId = "email_type_text_html";
    private String useEmailTemplateName = "from_template";
    private String saveEmailButtonId = "save_information";
    private String folderFieldXpath = ".//*[@id='information_form']/div[3]/div/div/span[2]";
    private String chooseFolderButtonXpath = ".//*[@id='information_form']/div[3]/div/div/button";
    private String campaignFieldXpath = ".//*[@id='information_form']/div[4]/div/div/span[2]";
    private String chooseCampaignButtonXpath = ".//*[@id='information_form']/div[4]/div/div/button";

    private String waitIndicatorId = "indicator";

    public PardotBasicEmailInformation(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isBasicEmailInformationModalLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify Email Information Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Email Information Modal Title is: " + modalTitleText);
        if (!selenium.getText(By.id(listInformationModalTitleId)).contains(modalTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + modalTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }
    }

    public void createEmail(Selenium selenium, String emailName, String emailType, Boolean useEmailTemplate) {
        reporting.writeStep("---> Create Email");

        reporting.writeInfo("-----> Enter Email Name: " + emailName);
        selenium.clear(By.name(nameFieldName));
        selenium.sendKeys(By.name(nameFieldName), emailName);

        reporting.writeInfo("-----> Select Email Type: " + emailType);
        if (emailType.equals("Text")) {
            selenium.click(By.id(emailTypeTextId));
        } else {
            selenium.click(By.id(emailTypeHtmlId));
        }

        reporting.writeInfo("-----> Select Use Email Template: " + useEmailTemplate.toString());
        if (useEmailTemplate) {
            if (!selenium.isSelected(By.id(useEmailTemplateName))) {
                selenium.click(By.id(useEmailTemplateName));
            }
        } else {
            if (selenium.isSelected(By.id(useEmailTemplateName))) {
                selenium.click(By.id(useEmailTemplateName));
            }
        }
    }

    public void clickSaveButton(Selenium selenium) {
        reporting.writeStep("---> Save Basic Email Information");
        selenium.click(By.id(saveEmailButtonId));
    }

    public void clickChooseFolderButton(Selenium selenium) {
        reporting.writeStep("---> Save Basic Email Information");
        selenium.click(By.xpath(chooseFolderButtonXpath));
    }

    public void isFolderSelected(Selenium selenium, String folderName) {
        reporting.writeInfo("---> Verify Folder Field Contains Selected Folder: /" + folderName);
        if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/" + folderName)) {
            selenium.throwRuntimeException("Folder Name is Not: /" + folderName, true);
        } else {
            reporting.writePass("Folder Found");
        }
    }

    public void clickChooseCampaignButton(Selenium selenium) {
        reporting.writeStep("---> Save Basic Email Information");
        selenium.click(By.xpath(chooseCampaignButtonXpath));
    }

    public void isCampaignSelected(Selenium selenium, String campaignName) {
        reporting.writeInfo("---> Verify Campaign Field Contains Selected Campaign: " + campaignName);
        if (!selenium.getText(By.xpath(campaignFieldXpath)).equals(campaignName)) {
            selenium.throwRuntimeException("Campaign Name is Not: " + campaignName, true);
        } else {
            reporting.writePass("Campaign Found");
        }
    }


}
