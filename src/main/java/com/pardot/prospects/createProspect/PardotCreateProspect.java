package com.pardot.prospects.createProspect;

import com.api.Selenium;
import com.generic.RandomData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PardotCreateProspect {
    RandomData random = new RandomData();

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

    public void isCreateProspectPageLoaded(Selenium selenium) {
        System.out.println("  -> Verify Create Prospect Page Loaded");

        selenium.waitElementInvisible(By.id(waitIndicatorId));

        System.out.println("    -> Verify Create Prospect Page Title is: " + pageTitleText);
        if (!selenium.getTitle().contains(pageTitleText)) {
            selenium.throwRuntimeException("Page Title is Not: " + pageTitleText);
        }

        System.out.println("    -> Verify Lists Page Sub Title is: " + pageSubTitleText);
        if (!selenium.getText(By.xpath(createProspectPageSubTitleXpath)).equals(pageSubTitleText)) {
            selenium.throwRuntimeException("Page Sub Title is Not: " + pageSubTitleText);
        }
    }

    public void createProspect(Selenium selenium, String firstName, String lastName, String email, String score, String listName) {
        System.out.println("  -> Create a New Prospect");

        System.out.println("    -> Enter First Name: " + firstName);
        selenium.clear(By.name(firstNameFieldName));
        selenium.sendKeys(By.name(firstNameFieldName), firstName);

        System.out.println("    -> Enter Last Name: " + lastName);
        selenium.clear(By.name(lastNameFieldName));
        selenium.sendKeys(By.name(lastNameFieldName), lastName);

        System.out.println("    -> Enter Email: " + email);
        selenium.clear(By.name(emailFieldName));
        selenium.sendKeys(By.name(emailFieldName), email);

        System.out.println("    -> Select Random Campaign from Current Group");
        List<WebElement> elements = selenium.findElements(By.name(campaignDropdownName), By.tagName("optgroup"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "label").equals("Current")) {
                System.out.println("      -> Found Current Group");
                List<WebElement> selectOptions = selenium.findElements(e, By.tagName("option"));

                System.out.println("      -> Select Campaign");
                selenium.click(By.name(campaignDropdownName));

                selenium.switchToWindow();
                selenium.doubleClick(selectOptions.get(random.getRandomNumber(selectOptions.size())));

                break;
            }
        }

        System.out.println("    -> Select Random Profile");
        elements = selenium.findElements(By.name(profileDropdownName), By.tagName("option"));

        System.out.println("      -> Select Profile");
        selenium.click(By.name(profileDropdownName));

        Integer rand;
        do {
            rand = random.getRandomNumber(elements.size());

            selenium.switchToWindow();

            selenium.doubleClick(elements.get(rand));
        } while (rand == 0);

        System.out.println("    -> Enter Score: " + score);
        selenium.clear(By.name(scoreFieldName));
        selenium.sendKeys(By.name(scoreFieldName), score);

        System.out.println("    -> Toggle List Selection Section");
        selenium.click(By.id(listsToggleButtonId));

        System.out.println("    -> Open List Dropdown");
        selenium.click(By.xpath(listsDropdownXpath));

        System.out.println("    -> Search for List: " + listName);
        WebElement search = selenium.findChildElement(By.className(listsSearchDivClass), By.tagName("input"));
        selenium.sendKeys(search, listName);

        System.out.println("    -> Select List");
        selenium.doubleClick(By.className(listsSearchResultsDropdownClass));

        isProspectListExist(selenium, listName);
    }

    public void isProspectListExist(Selenium selenium, String listName) {
        System.out.println("  -> Verify List Added to Prospect: " + listName);
        List<WebElement> elements = selenium.findElements(By.className(listsSelectedContainerClass), By.tagName("li"));

        for(WebElement e : elements){
            if (selenium.getAttribute(e, "data-name").equals(listName)) {
                System.out.println("    -> Found List");

                return;
            }
        }

        selenium.throwRuntimeException("List Not Added to Prospect: " + listName);
    }

    public void clickCreateProspectButton (Selenium selenium) {
        System.out.println("  -> Click Create Prospect Button");
        selenium.click(By.xpath(createProspectCommitButtonXpath));
    }


}
