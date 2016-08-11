package com.pardot.marketing.emails.emailEditor.sending;

import com.api.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotEmailSending {
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

    public void isEmailSendingPageLoaded(Selenium selenium, String emailName) {
        System.out.println("  -> Verify Email Sending Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Email Sending Page Title Contains: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }

        System.out.println("    -> Verify Email Name is: " + emailName);
        if (!selenium.getText(By.id(emailNameHeaderId)).contains(emailName)) {
            selenium.throwRuntimeException("Page Title is Not: " + emailName);
        }
    }

    public void clickSendNowButton(Selenium selenium) {
        System.out.println("  -> Click Send Now Button");

        System.out.println("    -> *Email Functionality Disabled");
        selenium.click(By.linkText(sendNowButtonName));
    }

    public void enterEmailToFrom(Selenium selenium, String listName, String sender, String subject) {
        System.out.println("  -> Enter Email To/From/Subject Data");

        System.out.println("    -> Enter To: " + listName);
        System.out.println("      -> Open List Dropdown");
        selenium.click(selenium.findChildElement(selenium.findElement(By.id(listsDropdownId)), By.tagName("b")));

        System.out.println("      -> Search for List: " + listName);
        selenium.sendKeys(selenium.findChildElement(selenium.findElement(By.className(listsSearchDivClass)), By.tagName("input")), listName);

        System.out.println("      -> Select List");
        selenium.doubleClick(By.className(listsSearchResultsDropdownClass));
        isListExist(selenium, listName);

        System.out.println("    -> Enter From: " + sender);
        selenium.selectByVisibleText(By.name(senderSelectName), sender);

        System.out.println("    -> Enter Subject: " + subject);
        selenium.clear(By.name(subjectFieldName));
        selenium.sendKeys(By.name(subjectFieldName), subject);
    }

    public void isListExist(Selenium selenium, String listName) {
        System.out.println("      -> Verify List Added to Email To Field: " + listName);
        List<WebElement> elements = selenium.findElements(By.className(listsSelectedContainerClass), By.tagName("li"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "data-name").equals(listName)) {
                System.out.println("        -> Found List");
                return;
            }
        }

        selenium.throwRuntimeException("List Not Added to Email To Field: " + listName);
    }


}
