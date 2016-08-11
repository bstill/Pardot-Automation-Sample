package com.pardot.sample.tests.bb;

import com.api.Reporting;
import com.api.Selenium;
import com.browser.PardotBrowser;
import com.pardot.dashboard.PardotDashboard;
import com.pardot.login.PardotLogin;
import com.pardot.marketing.emails.PardotEmails;
import com.pardot.marketing.emails.emailEditor.building.PardotEmailBuilding;
import com.pardot.marketing.emails.emailEditor.sending.PardotEmailSending;
import com.pardot.marketing.emails.emailInformation.PardotBasicEmailInformation;
import com.pardot.marketing.emails.emailInformation.selectCampaign.PardotSelectCampaign;
import com.pardot.prospects.prospect.overview.PardotProspect;
import com.pardot.prospects.prospect.lists.PardotProspectLists;
import com.pardot.prospects.list.PardotProspects;
import com.pardot.prospects.createProspect.PardotCreateProspect;
import com.pardot.marketing.segmentation.lists.list.PardotSegmentationList;
import com.pardot.marketing.segmentation.lists.listInformation.PardotListInformation;
import com.pardot.marketing.segmentation.lists.listInformation.selectFolder.PardotSelectFolder;
import com.pardot.marketing.segmentation.lists.PardotSegmentationLists;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.Hashtable;

import static junit.framework.TestCase.assertTrue;

public class PardotTest_BB {
    private String pardotUsername = "pardot.applicant@pardot.com";
    private String pardotPassword = "Applicant2012";
    private String pardotUrl = "https://pi.pardot.com";
    private Selenium selenium;
    private Reporting reporting;

    private PardotBrowser browser = new PardotBrowser();
    private PardotLogin login = new PardotLogin();
    private PardotDashboard dashboard = new PardotDashboard();
    private PardotSegmentationLists segmentationLists = new PardotSegmentationLists();
    private PardotListInformation segmentationListInformation = new PardotListInformation();
    private PardotSelectFolder selectFolder = new PardotSelectFolder();
    private PardotSegmentationList segmentationList = new PardotSegmentationList();
    private PardotProspects prospects = new PardotProspects();
    private PardotCreateProspect createProspect = new PardotCreateProspect();
    private PardotProspect prospect = new PardotProspect();
    private PardotProspectLists prospectLists = new PardotProspectLists();
    private PardotEmails emails = new PardotEmails();
    private PardotBasicEmailInformation basicEmailInformation = new PardotBasicEmailInformation();
    private PardotSelectCampaign selectCampaign = new PardotSelectCampaign();
    private PardotEmailBuilding emailBuilding = new PardotEmailBuilding();
    private PardotEmailSending emailSending = new PardotEmailSending();

    public PardotTest_BB (Reporting reporting) {
        this.reporting = reporting;
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
            assertTrue("Browser Initialization: FAILED", (selenium = browser.startBrowser(pardotUrl)) != null);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to Marketing Emails page.
        dashboard.selectMarketingEmails(selenium);
        emails.isEmailsPageLoaded(selenium);

        //Open the Basic Email Information Modal.
        emails.selectSendListEmailButton(selenium);
        basicEmailInformation.isBasicEmailInformationModalLoaded(selenium);

        //Create a New Email
        //  1. Enter the New Basic Email Infromation Form Data.
        basicEmailInformation.createEmail(selenium, (String)options.get("EmailName"), (String)options.get("EmailType"), (Boolean)options.get("UseEmailTemplate"));
        //  2. Select a Folder.
        basicEmailInformation.selectChooseFolderButton(selenium);
        selectFolder.isSelectFolderModalLoaded(selenium);
        if (!(Boolean)options.get("IsFolderExist")) {
            selectFolder.selectCreateFolderButton(selenium);
            selectFolder.addNewFolder(selenium, (String)options.get("FolderName"));
        }
        selectFolder.selectFolder(selenium, (String)options.get("FolderName"));
        selectFolder.selectChooseSelectedButton(selenium);
        //  3. Verify Folder Selected.
        basicEmailInformation.isBasicEmailInformationModalLoaded(selenium);
        basicEmailInformation.isFolderSelected(selenium, (String)options.get("FolderName"));
        // 4. Select a Campaign.
        basicEmailInformation.selectChooseCampaignButton(selenium);
        String campaign;
        selectCampaign.isSelectCampaignModalLoaded(selenium);
        campaign = selectCampaign.selectRandomContainer(selenium);
        selectCampaign.selectChooseSelectedButton(selenium);
        //  5. Verify Campaign Selected.
        basicEmailInformation.isBasicEmailInformationModalLoaded(selenium);
        basicEmailInformation.isCampaignSelected(selenium, campaign);
        //  6. Save Email Information.
        basicEmailInformation.selectSaveButton(selenium);

        //Verify Email Information Building Page Open.
        emailBuilding.isEmailBuildingPageLoaded(selenium, (String)options.get("EmailName"));

        //Open Email Sending Page.
        emailBuilding.selectSendingButton(selenium);
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
            assertTrue("Browser Initialization: FAILED", (selenium = browser.startBrowser(pardotUrl)) != null);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to the Segmentation Lists page.
        dashboard.selectMarketingSegmentationLists(selenium);
        segmentationLists.isSegmentationListsPageLoaded(selenium);

        //Open the Add List Modal.
        segmentationLists.selectAddListButton(selenium);
        segmentationListInformation.isListInformationModalLoaded(selenium);
        segmentationListInformation.isListInformationModalNotPopulated(selenium);

        //Create a New Segmentation List.
        //  1. Add the list name to the List Information Modal.
        segmentationListInformation.createList(selenium, (String)options.get("ListName"));
        //  2. Select a Folder.
        segmentationListInformation.selectChooseFolderButton(selenium);
        selectFolder.isSelectFolderModalLoaded(selenium);
        if (!(Boolean)options.get("IsFolderExist")) {
            selectFolder.selectCreateFolderButton(selenium);
            selectFolder.addNewFolder(selenium, (String)options.get("FolderName"));
        }
        selectFolder.selectFolder(selenium, (String)options.get("FolderName"));
        selectFolder.selectChooseSelectedButton(selenium);
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
            dashboard.selectMarketingSegmentationLists(selenium);

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
            assertTrue("Browser Initialization: FAILED", (selenium = browser.startBrowser(pardotUrl)) != null);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to the Segmentation Lists page.
        dashboard.selectMarketingSegmentationLists(selenium);
        segmentationLists.isSegmentationListsPageLoaded(selenium);

        //Open the Edit List Modal.
        segmentationLists.selectList(selenium, (String)options.get("OriginalListName"));
        segmentationList.isListPageLoaded(selenium, (String)options.get("OriginalListName"));
        segmentationList.selectEditListLink(selenium);
        segmentationListInformation.isListInformationModalLoaded(selenium);
        segmentationListInformation.isListInformationModalPopulated(selenium, (String)options.get("OriginalListName"), (String)options.get("FolderName"));

        //Create a New Segmentation List.
        //  1. Edit the list name to the List Information Modal.
        segmentationListInformation.createList(selenium, (String)options.get("ListName"));
        //  2. Select a Folder.
        segmentationListInformation.selectChooseFolderButton(selenium);
        selectFolder.isSelectFolderModalLoaded(selenium);
        if (!(Boolean)options.get("IsFolderExist")) {
            selectFolder.selectCreateFolderButton(selenium);
            selectFolder.addNewFolder(selenium, (String)options.get("FolderName"));
        }
        selectFolder.selectFolder(selenium, (String)options.get("FolderName"));
        selectFolder.selectChooseSelectedButton(selenium);
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
            dashboard.selectMarketingSegmentationLists(selenium);

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
            assertTrue("Browser Initialization: FAILED", (selenium = browser.startBrowser(pardotUrl)) != null);
            login.isLogInPageLoaded(selenium);
            login.loginPardot(selenium, pardotUsername, pardotPassword);
        } else {
            selenium = (Selenium)options.get("Selenium");
        }
        dashboard.isDashboardPageLoaded(selenium);

        //Navigate to the prospects page.
        dashboard.selectProspectsProspectList(selenium);
        prospects.isProspectsPageLoaded(selenium);

        //Select the Add Prospect Button.
        prospects.selectAddProspectButton(selenium);
        createProspect.isCreateProspectPageLoaded(selenium);

        //Create a new Prospect.
        createProspect.createProspect(selenium, (String)options.get("FirstName"), (String)options.get("LastName"), (String)options.get("Email"), (String)options.get("Score"), (String)options.get("ListName"));
        createProspect.clickCreateProspectButton(selenium);
        prospect.isProspectPageLoaded(selenium, options.get("FirstName") + " " + options.get("LastName"));

        //Verify the List exists on the Prospect Lists page.
        prospect.selectListsMenu(selenium);
        prospectLists.isProspectListsPageLoaded(selenium);
        prospectLists.isProspectListExist(selenium, (String)options.get("ListName"));

        //Navigate to the Prospects page.
        dashboard.selectProspectsProspectList(selenium);
        prospects.isProspectsPageLoaded(selenium);
        //Verify the Prospect appears on the Prospects page
        prospects.isProspectExist(selenium, options.get("FirstName") + " " + options.get("LastName"));

        //Navigate to the Segmentation List page.
        dashboard.selectMarketingSegmentationLists(selenium);
        segmentationLists.isSegmentationListsPageLoaded(selenium);
        //Verify the Prospect appears in the Segmentation List
        segmentationLists.selectList(selenium, (String)options.get("ListName"));
        segmentationList.isListPageLoaded(selenium, (String)options.get("ListName"));
        segmentationList.isListProspectExist(selenium, options.get("FirstName") + " " + options.get("LastName"));

        //Reset Browser to Known State
        browser.goUrl(selenium, pardotUrl);

        return selenium;
    }
}
