package com.pardot.sample.tests.bb;

import com.api.Reporting;
import com.api.Selenium;
import com.browser.PardotBrowser;
import com.pardot.dashboard.PardotDashboard;
import com.pardot.login.PardotLogin;
import com.pardot.marketing.emails.PardotEmails;
import com.pardot.marketing.emails.emaileditor.building.PardotEmailBuilding;
import com.pardot.marketing.emails.emaileditor.sending.PardotEmailSending;
import com.pardot.marketing.emails.emailinformation.PardotBasicEmailInformation;
import com.pardot.marketing.emails.emailinformation.selectcampaign.PardotSelectCampaign;
import com.pardot.prospects.prospect.overview.PardotProspect;
import com.pardot.prospects.prospect.lists.PardotProspectLists;
import com.pardot.prospects.list.PardotProspects;
import com.pardot.prospects.createprospect.PardotCreateProspect;
import com.pardot.marketing.segmentation.lists.list.PardotSegmentationList;
import com.pardot.marketing.segmentation.lists.listinformation.PardotListInformation;
import com.pardot.marketing.segmentation.lists.listinformation.selectfolder.PardotSelectFolder;
import com.pardot.marketing.segmentation.lists.PardotSegmentationLists;

import java.util.Hashtable;

public class PardotTest_BB {
    private String pardotUsername = "pardot.applicant@pardot.com";
    private String pardotPassword = "Applicant2012";
    private String pardotUrl = "https://pi.pardot.com";
    private Selenium selenium;
    private Reporting reporting;

    private PardotBrowser browser;
    private PardotLogin login;
    private PardotDashboard dashboard;
    private PardotSegmentationLists segmentationLists;
    private PardotListInformation segmentationListInformation;
    private PardotSelectFolder selectFolder;
    private PardotSegmentationList segmentationList;
    private PardotProspects prospects;
    private PardotCreateProspect createProspect;
    private PardotProspect prospect;
    private PardotProspectLists prospectLists;
    private PardotEmails emails;
    private PardotBasicEmailInformation basicEmailInformation;
    private PardotSelectCampaign selectCampaign;
    private PardotEmailBuilding emailBuilding;
    private PardotEmailSending emailSending;

    public PardotTest_BB (Reporting reporting) {
        this.reporting = reporting;

        browser = new PardotBrowser(this.reporting);
        login = new PardotLogin(this.reporting);
        dashboard = new PardotDashboard(this.reporting);
        segmentationLists = new PardotSegmentationLists(this.reporting);
        segmentationListInformation = new PardotListInformation(this.reporting);
        selectFolder = new PardotSelectFolder(this.reporting);
        segmentationList = new PardotSegmentationList(this.reporting);
        prospects = new PardotProspects(this.reporting);
        createProspect = new PardotCreateProspect(this.reporting);
        prospect = new PardotProspect(this.reporting);
        prospectLists = new PardotProspectLists(this.reporting);
        emails = new PardotEmails(this.reporting);
        basicEmailInformation = new PardotBasicEmailInformation(this.reporting);
        selectCampaign = new PardotSelectCampaign(this.reporting);
        emailBuilding = new PardotEmailBuilding(this.reporting);
        emailSending = new PardotEmailSending(this.reporting);
    }

    //Sign Out of the app
    //10.	Log out
    public Selenium signOut(Hashtable options) throws InterruptedException {
        dashboard.signOut((Selenium)options.get("Selenium"));
        login.isLogInPageLoaded((Selenium)options.get("Selenium"));

        return (Selenium)options.get("Selenium");
    }

    //Close the browser under test
    public void closeBrowser(Hashtable options) {
        browser.stopBrowser((Selenium)options.get("Selenium"));
    }

    //Send Marketing List Email Building Block
    //1.	Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012) (If Necessary)
    //9.	Send a text only email to the list (Marketing > Emails)  *Please note, email is disabled in this account so you will not actually be able to send the email.  This is okay.
    public Selenium sendMarketingListEmail(Hashtable options) throws InterruptedException {
        //If a browser is passed in, it will be used for this building block.
        //If no browser is passed in a new browser will be opened and used.
        //This block requires that the browser be at the Dashboard page.
        if (options.get("Selenium") == null) {
            selenium = browser.startBrowser(pardotUrl);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to Marketing Emails page.
        dashboard.clickMarketingEmails(selenium);
        emails.isEmailsPageLoaded(selenium);

        //Open the Basic Email Information Modal.
        emails.clickSendListEmailButton(selenium);
        basicEmailInformation.isBasicEmailInformationModalLoaded(selenium);

        //Create a New Email
        //  1. Enter the New Basic Email Infromation Form Data.
        basicEmailInformation.createEmail(selenium, (String)options.get("EmailName"), (String)options.get("EmailType"), (Boolean)options.get("UseEmailTemplate"));
        //  2. Select a Folder.
        basicEmailInformation.clickChooseFolderButton(selenium);
        selectFolder.isSelectFolderModalLoaded(selenium);
        if (!(Boolean)options.get("IsFolderExist")) {
            selectFolder.clickCreateFolderButton(selenium);
            selectFolder.addNewFolder(selenium, (String)options.get("FolderName"));
        }
        selectFolder.clickFolder(selenium, (String)options.get("FolderName"));
        selectFolder.clickChooseSelectedButton(selenium);
        //  3. Verify Folder Selected.
        basicEmailInformation.isBasicEmailInformationModalLoaded(selenium);
        basicEmailInformation.isFolderSelected(selenium, (String)options.get("FolderName"));
        // 4. Select a Campaign.
        basicEmailInformation.clickChooseCampaignButton(selenium);
        String campaign;
        selectCampaign.isSelectCampaignModalLoaded(selenium);
        campaign = selectCampaign.selectRandomContainer(selenium);
        selectCampaign.clickChooseSelectedButton(selenium);
        //  5. Verify Campaign Selected.
        basicEmailInformation.isBasicEmailInformationModalLoaded(selenium);
        basicEmailInformation.isCampaignSelected(selenium, campaign);
        //  6. Save Email Information.
        basicEmailInformation.clickSaveButton(selenium);

        //Verify Email Information Building Page Open.
        emailBuilding.isEmailBuildingPageLoaded(selenium, (String)options.get("EmailName"));

        //Open Email Sending Page.
        emailBuilding.clickSendingButton(selenium);
        emailSending.isEmailSendingPageLoaded(selenium, (String)options.get("EmailName"));

        // Enter To/From/Subject Data and Send Email.
        emailSending.enterEmailToFrom(selenium, (String)options.get("ListName"), (String)options.get("EmailSender"), (String)options.get("EmailSubject"));
        emailSending.clickSendNowButton(selenium); //Disabled on Site

        //Reset Browser to Known State
        browser.goUrl(selenium, pardotUrl);

        return selenium;
    }

    //Add Segmentation List Building Block
    //1.	Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012) (If Necessary)
    //2.	Create a list with a random name (Marketing > Segmentation > Lists)
    //3.	Attempt to create another list with that same name and ensure the system correctly gives a validation failure
    //5.	Ensure the system allows the creation of another list with the original name now that the original list is renamed
    public Selenium addSegmentationList(Hashtable options) throws InterruptedException {
        //If a browser is passed in, it will be used for this building block.
        //If no browser is passed in a new browser will be opened and used.
        //This block requires that the browser be at the Dashboard page.
        if (options.get("Selenium") == null) {
            selenium = browser.startBrowser(pardotUrl);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to the Segmentation Lists page.
        dashboard.clickMarketingSegmentationLists(selenium);
        segmentationLists.isSegmentationListsPageLoaded(selenium);

        //Open the Add List Modal.
        segmentationLists.clickAddListButton(selenium);
        segmentationListInformation.isListInformationModalLoaded(selenium);
        segmentationListInformation.isListInformationModalNotPopulated(selenium);

        //Create a New Segmentation List.
        //  1. Add the list name to the List Information Modal.
        segmentationListInformation.createList(selenium, (String)options.get("ListName"));
        //  2. Select a Folder.
        segmentationListInformation.clickChooseFolderButton(selenium);
        selectFolder.isSelectFolderModalLoaded(selenium);
        if (!(Boolean)options.get("IsFolderExist")) {
            selectFolder.clickCreateFolderButton(selenium);
            selectFolder.addNewFolder(selenium, (String)options.get("FolderName"));
        }
        selectFolder.clickFolder(selenium, (String)options.get("FolderName"));
        selectFolder.clickChooseSelectedButton(selenium);
        //  3. Verify Folder Selected.
        segmentationListInformation.isListInformationModalLoaded(selenium);
        segmentationListInformation.isFolderSelected(selenium, (String)options.get("FolderName"));
        //  4. Save List.
        segmentationListInformation.saveList(selenium);

        //Verify List Created or if Duplicate, correct error messages displayed.
        if ((Boolean)options.get("IsDuplicate")) {
            segmentationListInformation.isListInformationDuplicateNameErrorDisplayed(selenium);
        } else {
            segmentationList.isListPageLoaded(selenium, (String)options.get("ListName"));
            dashboard.clickMarketingSegmentationLists(selenium);

            segmentationLists.isSegmentationListsPageLoaded(selenium);
            segmentationLists.isListExist(selenium, (String)options.get("ListName"));
        }

        //Reset Browser to Known State
        browser.goUrl(selenium, pardotUrl);

        return selenium;
    }

    //Edit Segmentation List Building Block
    //1.	Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012) (If Necessary)
    //4.	Rename the original list
    public Selenium editSegmentationList(Hashtable options) throws InterruptedException {
        //If a browser is passed in, it will be used for this building block.
        //If no browser is passed in a new browser will be opened and used.
        //This block requires that the browser be at the Dashboard page.
        if (options.get("Selenium") == null) {
            selenium = browser.startBrowser(pardotUrl);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to the Segmentation Lists page.
        dashboard.clickMarketingSegmentationLists(selenium);
        segmentationLists.isSegmentationListsPageLoaded(selenium);

        //Open the Edit List Modal.
        segmentationLists.clickList(selenium, (String)options.get("OriginalListName"));
        segmentationList.isListPageLoaded(selenium, (String)options.get("OriginalListName"));
        segmentationList.clickEditListLink(selenium);
        segmentationListInformation.isListInformationModalLoaded(selenium);
        segmentationListInformation.isListInformationModalPopulated(selenium, (String)options.get("OriginalListName"), (String)options.get("FolderName"));

        //Create a New Segmentation List.
        //  1. Edit the list name to the List Information Modal.
        segmentationListInformation.createList(selenium, (String)options.get("ListName"));
        //  2. Select a Folder.
        segmentationListInformation.clickChooseFolderButton(selenium);
        selectFolder.isSelectFolderModalLoaded(selenium);
        if (!(Boolean)options.get("IsFolderExist")) {
            selectFolder.clickCreateFolderButton(selenium);
            selectFolder.addNewFolder(selenium, (String)options.get("FolderName"));
        }
        selectFolder.clickFolder(selenium, (String)options.get("FolderName"));
        selectFolder.clickChooseSelectedButton(selenium);
        //  3. Verify Folder Selected.
        segmentationListInformation.isListInformationModalLoaded(selenium);
        segmentationListInformation.isFolderSelected(selenium, (String)options.get("FolderName"));
        //  4. Save List.
        segmentationListInformation.saveList(selenium);

        //Verify List Created or if Duplicate, correct error messages displayed.
        if ((Boolean)options.get("IsDuplicate")) {
            segmentationListInformation.isListInformationDuplicateNameErrorDisplayed(selenium);
        } else {
            segmentationList.isListPageLoaded(selenium, (String)options.get("ListName"));
            dashboard.clickMarketingSegmentationLists(selenium);

            segmentationLists.isSegmentationListsPageLoaded(selenium);
            segmentationLists.isListExist(selenium, (String)options.get("ListName"));

            segmentationLists.isListNotExist(selenium, (String)options.get("OriginalListName"));
        }

        //Reset Browser to Known State
        browser.goUrl(selenium, pardotUrl);

        return selenium;
    }

    //Create Project Building Block
    //1.	Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012) (If Necessary)
    //6.	Create a new prospect (Prospect > Prospect List)
    //7.	Add your new prospect to the newly created list
    //8.	Ensure the new prospect is successfully added to the list upon save
    public Selenium createProspect(Hashtable options) throws InterruptedException {
        //If a browser is passed in, it will be used for this building block.
        //If no browser is passed in a new browser will be opened and used.
        //This block requires that the browser be at the Dashboard page.
        if (options.get("Selenium") == null) {
            selenium = browser.startBrowser(pardotUrl);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to the prospects page.
        dashboard.clickProspectsProspectList(selenium);
        prospects.isProspectsPageLoaded(selenium);

        //Select the Add Prospect Button.
        prospects.clickAddProspectButton(selenium);
        createProspect.isCreateProspectPageLoaded(selenium);

        //Create a new Prospect.
        createProspect.createProspect(selenium, (String)options.get("FirstName"), (String)options.get("LastName"), (String)options.get("Email"), (String)options.get("Score"), (String)options.get("ListName"));
        createProspect.clickCreateProspectButton(selenium);
        prospect.isProspectPageLoaded(selenium, options.get("FirstName") + " " + options.get("LastName"));

        //Verify the List exists on the Prospect Lists page.
        prospect.clickListsMenu(selenium);
        prospectLists.isProspectListsPageLoaded(selenium);
        prospectLists.isProspectListExist(selenium, (String)options.get("ListName"));

        //Navigate to the Prospects page.
        dashboard.clickProspectsProspectList(selenium);
        prospects.isProspectsPageLoaded(selenium);
        //Verify the Prospect appears on the Prospects page
        prospects.isProspectExist(selenium, options.get("FirstName") + " " + options.get("LastName"));

        //Navigate to the Segmentation List page.
        dashboard.clickMarketingSegmentationLists(selenium);
        segmentationLists.isSegmentationListsPageLoaded(selenium);
        //Verify the Prospect appears in the Segmentation List
        segmentationLists.clickList(selenium, (String)options.get("ListName"));
        segmentationList.isListPageLoaded(selenium, (String)options.get("ListName"));
        segmentationList.isListProspectExist(selenium, options.get("FirstName") + " " + options.get("LastName"));

        //Reset Browser to Known State
        browser.goUrl(selenium, pardotUrl);

        return selenium;
    }
}
