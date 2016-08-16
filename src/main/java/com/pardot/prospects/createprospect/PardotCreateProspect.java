package com.pardot.prospects.createprospect;

import com.api.Reporting;
import com.api.Selenium;
import com.generic.RandomData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotCreateProspect {
    private Reporting reporting;

    private RandomData random = new RandomData();

    private String pageTitleText = "Prospects";
    private String createProspectPageSubTitleXpath = ".//*[@id='pr_form_update']/form/h3";
    private String pageSubTitleText = "Create Prospect";

    private String firstNameFieldName = "default_field_3361";
    private String lastNameFieldName = "default_field_3371";
    private String emailFieldName = "email";
    private String campaignDropdownName = "campaign_id";
    private String profileDropdownName = "profile_id";
    private String scoreFieldName = "score";
    private String listsToggleButtonId = "toggle-inputs-lists-";
    private String listsDropdownXpath = ".//*[@id='pr_fields_lists_wrapper_']/div/div/div/div/a/div/b";
    private String listsSearchDivClass = "chzn-search";
    private String listsSearchResultsDropdownClass = "chzn-results";
    private String createProspectCommitButtonXpath = ".//*[@id='pr_form_update']/form/div[21]/input[3]";

    private String listsSelectedContainerClass = "selected-lists";

    private String waitIndicatorId = "indicator";

    public PardotCreateProspect(Reporting reporting) {
        this.reporting = reporting;
    }

    public void isCreateProspectPageLoaded(Selenium selenium) {
        reporting.writeInfo("---> Verify Create Prospect Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        reporting.writeInfo("-----> Verify Create Prospect Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText, true);
        } else {
            reporting.writePass("Page Title Found");
        }

        reporting.writeInfo("-----> Verify Lists Page Sub Title is: " + pageSubTitleText);
        if (!selenium.getText(By.xpath(createProspectPageSubTitleXpath)).equals(pageSubTitleText)) {
            selenium.throwRuntimeException("Page Sub Title is Not: " + pageSubTitleText, true);
        } else {
            reporting.writePass("Page Sub Title Found");
        }
    }

    public void createProspect(Selenium selenium, String firstName, String lastName, String email, String score, String listName) {
        reporting.writeStep("---> Create a New Prospect");

        reporting.writeInfo("-----> Enter First Name: " + firstName);
        selenium.clear(By.name(firstNameFieldName));
        selenium.sendKeys(By.name(firstNameFieldName), firstName);

        reporting.writeInfo("-----> Enter Last Name: " + lastName);
        selenium.clear(By.name(lastNameFieldName));
        selenium.sendKeys(By.name(lastNameFieldName), lastName);

        reporting.writeInfo("-----> Enter Email: " + email);
        selenium.clear(By.name(emailFieldName));
        selenium.sendKeys(By.name(emailFieldName), email);

        reporting.writeInfo("-----> Select Random Campaign from Current Group");
        List<WebElement> elements = selenium.findElements(By.name(campaignDropdownName), By.tagName("optgroup"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "label").equals("Current")) {
                reporting.writeInfo("-------> Found Current Group");
                List<WebElement> selectOptions = selenium.findElements(e, By.tagName("option"));

                reporting.writeInfo("-------> Select Campaign");
                selenium.click(By.name(campaignDropdownName));

                selenium.switchToWindow();
                selenium.doubleClick(selectOptions.get(random.getRandomNumber(selectOptions.size())));

                break;
            }
        }

        reporting.writeInfo("-----> Select Random Profile");
        elements = selenium.findElements(By.name(profileDropdownName), By.tagName("option"));

        reporting.writeInfo("-------> Select Profile");
        selenium.click(By.name(profileDropdownName));

        Integer rand;
        do {
            rand = random.getRandomNumber(elements.size());

            selenium.switchToWindow();

            selenium.doubleClick(elements.get(rand));
        } while (rand == 0);

        reporting.writeInfo("-----> Enter Score: " + score);
        selenium.clear(By.name(scoreFieldName));
        selenium.sendKeys(By.name(scoreFieldName), score);

        reporting.writeInfo("-----> Toggle List Selection Section");
        selenium.click(By.id(listsToggleButtonId));

        reporting.writeInfo("-----> Open List Dropdown");
        selenium.click(By.xpath(listsDropdownXpath));

        reporting.writeInfo("-----> Search for List: " + listName);
        WebElement search = selenium.findChildElement(By.className(listsSearchDivClass), By.tagName("input"));
        selenium.sendKeys(search, listName);

        reporting.writeInfo("-----> Select List");
        selenium.doubleClick(By.className(listsSearchResultsDropdownClass));

        isProspectListExist(selenium, listName);
    }

    public void isProspectListExist(Selenium selenium, String listName) {
        reporting.writeInfo("---> Verify List Added to Prospect: " + listName);
        List<WebElement> elements = selenium.findElements(By.className(listsSelectedContainerClass), By.tagName("li"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "data-name").equals(listName)) {
                reporting.writePass("Found List");

                return;
            }
        }

        selenium.throwRuntimeException("List Not Added to Prospect: " + listName, true);
    }

    public void clickCreateProspectButton (Selenium selenium) {
        reporting.writeStep("---> Click Create Prospect Button");
        selenium.click(By.xpath(createProspectCommitButtonXpath));
    }


}
