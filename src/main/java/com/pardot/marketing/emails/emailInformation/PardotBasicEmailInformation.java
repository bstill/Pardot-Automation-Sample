package com.pardot.marketing.emails.emailInformation;

import com.api.Selenium;
import org.openqa.selenium.By;

public class PardotBasicEmailInformation {
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

    public void isBasicEmailInformationModalLoaded(Selenium selenium) {
        System.out.println("  -> Verify Email Information Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Email Information Modal Title is: " + modalTitleText);
        if (!selenium.getText(By.id(listInformationModalTitleId)).contains(modalTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + modalTitleText);
        }
    }

    public void createEmail(Selenium selenium, String emailName, String emailType, Boolean useEmailTemplate) {
        System.out.println("  -> Create Email");

        System.out.println("    -> Enter Email Name: " + emailName);
        selenium.clear(By.name(nameFieldName));
        selenium.sendKeys(By.name(nameFieldName), emailName);

        System.out.println("    -> Select Email Type: " + emailType);
        if (emailType.equals("Text")) {
            selenium.click(By.id(emailTypeTextId));
        } else {
            selenium.click(By.id(emailTypeHtmlId));
        }

        System.out.println("    -> Select Use Email Template: " + useEmailTemplate.toString());
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

    public void selectSaveButton(Selenium selenium) {
        System.out.println("  -> Save Basic Email Information");
        selenium.click(By.id(saveEmailButtonId));
    }

    public void selectChooseFolderButton(Selenium selenium) {
        System.out.println("  -> Save Basic Email Information");
        selenium.click(By.xpath(chooseFolderButtonXpath));
    }

    public void isFolderSelected(Selenium selenium, String folderName) {
        System.out.println("  -> Verify Folder Field Contains Selected Folder: /" + folderName);
        if (!selenium.getText(By.xpath(folderFieldXpath)).equals("/" + folderName)) {
            selenium.throwRuntimeException("Folder Name is Not: /" + folderName);
        }
    }

    public void selectChooseCampaignButton(Selenium selenium) {
        System.out.println("  -> Save Basic Email Information");
        selenium.click(By.xpath(chooseCampaignButtonXpath));
    }

    public void isCampaignSelected(Selenium selenium, String campaignName) {
        System.out.println("  -> Verify Campaign Field Contains Selected Campaign: " + campaignName);
        if (!selenium.getText(By.xpath(campaignFieldXpath)).equals(campaignName)) {
            selenium.throwRuntimeException("Campaign Name is Not: " + campaignName);
        }
    }


}
