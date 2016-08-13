package com.pardot.sample.tests;

import com.api.Reporting;
import com.generic.RandomData;
import com.pardot.sample.tests.bb.PardotTest_BB;
import org.junit.*;
import com.api.Selenium;

import java.util.Hashtable;

public class PardotTest {
    private static PardotTest_BB bb;
    private static RandomData rand;
    private static Reporting reporting;

    private Selenium selenium;

    @BeforeClass
    public static void runBeforeAll(){
        reporting = new Reporting();
        bb = new PardotTest_BB(reporting);
        rand = new RandomData();
    }

    @Before
    public void runBeforeEach(){

    }

    @After
    public void runAfterEach() throws InterruptedException {
        reporting.endTest();
        reporting.flush();
    }

    @AfterClass
    public static void runAfterAll(){
        reporting.close();
    }

    @Test
    public void pardotSampleAutomationExercise() throws InterruptedException {
        reporting.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "This is a sample automation exercise.");

        Hashtable options = new Hashtable();

        String folderName = rand.getRandomStringAlpha(10);
        String listName = rand.getRandomStringAlpha(20);

        //1.	Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012)
        //2.	Create a list with a random name (Marketing > Segmentation > Lists)
        options.clear();
        options.put("FolderName", folderName);
        options.put("IsFolderExist", false);
        options.put("ListName", listName);
        options.put("IsDuplicate", false);

        reporting.writeInfo("*** Create New Segmentation List");
        selenium = bb.addSegmentationList(options);

        //3.	Attempt to create another list with that same name and ensure the system correctly gives a validation failure
        options.clear();
        options.put("Selenium", selenium);
        options.put("FolderName", folderName);
        options.put("IsFolderExist", true);
        options.put("ListName", listName);
        options.put("IsDuplicate", true);

        reporting.writeInfo("*** Attempt to Create Duplicate Segmentation List");
        selenium = bb.addSegmentationList(options);

        String originalListName = listName;
        listName = rand.getRandomStringAlpha(20);

        //4.	Rename the original list
        options.clear();
        options.put("Selenium", selenium);
        options.put("FolderName", folderName);
        options.put("IsFolderExist", true);
        options.put("OriginalListName", originalListName);
        options.put("ListName", listName);
        options.put("IsDuplicate", false);

        reporting.writeInfo("*** Rename Segmentation List");
        selenium = bb.editSegmentationList(options);

        //5.	Ensure the system allows the creation of another list with the original name now that the original list is renamed
        options.clear();
        options.put("Selenium", selenium);
        options.put("FolderName", folderName);
        options.put("IsFolderExist", true);
        options.put("ListName", originalListName);
        options.put("IsDuplicate", false);

        reporting.writeInfo("*** Create Segmentation List After Original List Rename");
        selenium = bb.addSegmentationList(options);

        String email = rand.getRandomStringAlpha(10) + "@" + rand.getRandomStringAlpha(10) + ".com";
        String firstName = rand.getRandomStringAlpha(10);
        String lastName = rand.getRandomStringAlpha(10);
        String score = Integer.toString(rand.getRandomNumber(100));

        //6.	Create a new prospect (Prospect > Prospect List)
        //7.	Add your new prospect to the newly created list
        //8.	Ensure the new prospect is successfully added to the list upon save
        options.clear();
        options.put("Selenium", selenium);
        options.put("FirstName", firstName);
        options.put("LastName", lastName);
        options.put("Email", email);
        options.put("Score", score);
        options.put("ListName", listName);

        reporting.writeInfo("*** Create New Prospect and Assign to Newly Created Segmentation List");
        selenium = bb.createProspect(options);

        String emailName = rand.getRandomStringAlpha(10);
        String emailSubject = rand.getRandomStringAlpha(30);

        //9.	Send a text only email to the list (Marketing > Emails)  *Please note, email is disabled in this account so you will not actually be able to send the email.  This is okay.
        options.clear();
        options.put("Selenium", selenium);
        options.put("EmailName", emailName);
        options.put("EmailType", "Text");           // Valid Options: "Text" or "HTML"
        options.put("UseEmailTemplate", false);
        options.put("ListName", listName);
        options.put("FolderName", folderName);
        options.put("IsFolderExist", true);
        options.put("EmailSender", "Account Owner");
        options.put("EmailSubject", emailSubject);

        selenium = bb.sendMarketingListEmail(options);

        //10.	Log out
        options.clear();
        options.put("Selenium", selenium);

        reporting.writeInfo("*** Sign Out of Perdot and Close Browser");
        selenium = bb.signOut(options);
        bb.closeBrowser(options);
    }

}
