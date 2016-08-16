package com.pardot.marketing.emails.emaileditor.sending;

import com.api.Reporting;
import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotEmailSending {
    private Reporting reporting;

    private String emailNameHeaderId = "control_name";
    private String pageTitleText = "Sending";

    private String sendNowButtonName = "Send Now";

    private String listsDropdownId = "email-wizard-list-select";
    private String listsSearchDivClass = "chzn-search";
    private String listsSearchResultsDropdownClass = "chzn-results";

    private String listsSelectedContainerClass = "selected-lists";

    private String senderSelectName = "a_sender[]";

    private String subjectFieldName = "subject_a";

    private String waitIndicatorId = "indicator";

    public PardotEmailSending(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isEmailSendingPageLoaded(Selenium selenium, String emailName) {
        reporting.writeInfo("---> Verify Email Sending Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Email Sending Page Title Contains: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }

        reporting.writeInfo("-----> Verify Email Name is: " + emailName);
        if (!selenium.getText(By.id(emailNameHeaderId)).contains(emailName)) {
            selenium.throwRuntimeException("Email Name is Not: " + emailName, true);
        } else {
            reporting.writePass("Email Name Found");
        }
    }

    public void clickSendNowButton(Selenium selenium) {
        reporting.writeStep("---> Click Send Now Button");

        reporting.writeInfo("-----> *Email Functionality Disabled");
        selenium.click(By.linkText(sendNowButtonName));
    }

    public void enterEmailToFrom(Selenium selenium, String listName, String sender, String subject) {
        reporting.writeStep("---> Enter Email To/From/Subject Data");

        reporting.writeInfo("-----> Enter To: " + listName);
        reporting.writeInfo("-------> Open List Dropdown");
        selenium.click(selenium.findChildElement(selenium.findElement(By.id(listsDropdownId)), By.tagName("b")));

        reporting.writeInfo("-------> Search for List: " + listName);
        selenium.sendKeys(selenium.findChildElement(selenium.findElement(By.className(listsSearchDivClass)), By.tagName("input")), listName);

        reporting.writeInfo("-------> Select List");
        selenium.doubleClick(By.className(listsSearchResultsDropdownClass));
        isListExist(selenium, listName);

        reporting.writeInfo("-----> Enter From: " + sender);
        selenium.selectByVisibleText(By.name(senderSelectName), sender);

        reporting.writeInfo("-----> Enter Subject: " + subject);
        selenium.clear(By.name(subjectFieldName));
        selenium.sendKeys(By.name(subjectFieldName), subject);
    }

    public void isListExist(Selenium selenium, String listName) {
        reporting.writeInfo("-------> Verify List Added to Email To Field: " + listName);
        List<WebElement> elements = selenium.findElements(By.className(listsSelectedContainerClass), By.tagName("li"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "data-name").equals(listName)) {
                reporting.writePass("Found List");
                return;
            }
        }

        selenium.throwRuntimeException("List Not Added to Email To Field: " + listName, true);
    }


}
