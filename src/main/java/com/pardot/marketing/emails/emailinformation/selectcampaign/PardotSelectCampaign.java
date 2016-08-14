package com.pardot.marketing.emails.emailinformation.selectcampaign;

import com.api.Reporting;
import com.api.Selenium;
import com.generic.RandomData;
import com.pardot.marketing.segmentation.lists.listInformation.selectFolder.PardotSelectFolder;
import org.openqa.selenium.*;

import java.util.List;

public class PardotSelectCampaign {
    private Reporting reporting;

    private RandomData random = new RandomData();

    private String selectFolderModalTitleXpath = ".//*[@id='asset-chooser-app-modal']/div[1]/h3";
    private String modalTitleText = "Select A Campaign";

    private String campaignListContainerId = "folder-contents";

    private String chooseSelectedButtonId = "select-asset";

    private String waitIndicatorId = "indicator";

    public PardotSelectCampaign(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isSelectCampaignModalLoaded(Selenium selenium) {
        System.out.println("---> Verify Select A Campaign Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("-----> Verify Select A Campaign Modal Title is: " + modalTitleText);
        if (!selenium.getText(By.xpath(selectFolderModalTitleXpath)).contains(modalTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + modalTitleText, true);
        } else {
            reporting.writePass("PASS: Page Title Found");
        }
    }

    public String selectRandomContainer(Selenium selenium) {
        System.out.println("---> Select Random Container");

        List<WebElement> elements = selenium.findElements(By.id(campaignListContainerId), By.tagName("h4"));

        Integer index = random.getRandomNumber(elements.size());
        selenium.click(elements.get(index));

        return selenium.getText(elements.get(index));
    }

    public void clickChooseSelectedButton(Selenium selenium) {
        System.out.println("---> Click Choose Selected Button");
        selenium.click(By.id(chooseSelectedButtonId));
    }


}
