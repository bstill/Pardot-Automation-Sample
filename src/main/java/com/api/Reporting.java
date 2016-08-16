package com.api;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;
import static com.relevantcodes.extentreports.LogStatus.*;
import static com.relevantcodes.extentreports.NetworkMode.OFFLINE;

public class Reporting {
    private ExtentReports report;
    private ExtentTest testReport;
    private Json json;
    public String reportPath = ".\\reports\\";
    public String reportFilenamePrefix = "Automation_Report_";
    //public String reportFilenameHtml = "Automation_Report.html";
    //public String reportFilenameJson = "Automation_Report.json";
    public String screenshotPath = ".\\reports\\screenshots\\";

    private String randomUUIDString = UUID.randomUUID().toString();

    private Integer stepCount = 0;

    public Reporting() {
        report = new ExtentReports(reportPath + reportFilenamePrefix + randomUUIDString + ".html", false, NEWEST_FIRST, OFFLINE );
        json = new Json(reportPath, reportFilenamePrefix + randomUUIDString + ".json");
    }

    public void writeInfo(String message) {
        writeLogEntry(INFO, message, false);
    }

    public void writeStep(String message) {
        writeLogEntry(INFO, message, true);
    }

    public void writeError(String message) {
        writeLogEntry(ERROR, message, false);
    }

    public void writeError(String message, String screenshot) {
        writeLogEntry(ERROR, message, screenshot, false);
    }

    public void writePass(String message) {
        writeLogEntry(PASS, message, false);
    }

    public void writePass(String message, String screenshot) {
        writeLogEntry(PASS, message, screenshot, false);
    }

    public void writeFail(String message) {
        writeLogEntry(FAIL, message, false);
    }

    public void writeFail(String message, String screenshot) {
        writeLogEntry(FAIL, message, screenshot, false);
    }

    public void writeWarning(String message) {
        writeLogEntry(WARNING, message, false);
    }

    public void writeWarning(String message, String screenshot) {
        writeLogEntry(WARNING, message, screenshot, false);
    }

    public void writeFatal(String message) {
        writeLogEntry(FATAL, message, false);
    }

    public void writeFatal(String message, String screenshot) {
        writeLogEntry(FATAL, message, screenshot, false);
    }

    public void writeSkip(String message) {
        writeLogEntry(SKIP, message, false);
    }

    public void writeSkip(String message, String screenshot) {
        writeLogEntry(SKIP, message, screenshot, false);
    }

    private void writeLogEntry(LogStatus logStatus, String message, Boolean isStep) {
        List<String> list = new ArrayList<String>();
        String key = "";
        String jsonMessage = message;

        if (logStatus == FAIL) {
             message = "FAIL: " + message;
        }

        if (logStatus == FATAL) {
            message = "FATAL: " + message;
        }

        if (logStatus == PASS) {
            message = "PASS: " + message;
        }

        System.out.println(message);
        testReport.log(logStatus, message);

        switch(logStatus) {
            case INFO: json.addValue("Type","Info");
                break;
            case PASS: json.addValue("Type","Check");
                json.addValue("Result","Pass");
                break;
            case FAIL: json.addValue("Type","Check");
                json.addValue("Result","Fail");
                break;
            case FATAL: json.addValue("Type","Fatal");
                break;
            case SKIP: json.addValue("Type","Skip");
                break;
            case ERROR: json.addValue("Type","Error");
                break;
            case WARNING: json.addValue("Type","Warning");
                break;
        }
        if (isStep) {
            stepCount += 1;
        }
        if ((logStatus == PASS) || (logStatus == FAIL) || (logStatus == INFO)) {
            json.addValue("Step", stepCount);
        }
        json.addValue("Output", jsonMessage);
        json.addArray();
    }

    private void writeLogEntry(LogStatus logStatus, String message, String screenshot, Boolean isStep) {
        List<String> list = new ArrayList<String>();
        String key = "";
        String jsonMessage = message;

        if (logStatus == FAIL) {
            message = "FAIL: " + message;
        }

        if (logStatus == FATAL) {
            message = "FATAL: " + message;
        }

        if (logStatus == PASS) {
            message = "PASS: " + message;
        }

        System.out.println(message);
        testReport.log(logStatus, message);
        testReport.log(logStatus, "Screenshot: " + testReport.addScreenCapture(screenshot));

        switch(logStatus) {
            case INFO: json.addValue("Type","Info");
                break;
            case PASS: json.addValue("Type","Check");
                json.addValue("Result","Pass");
                break;
            case FAIL: json.addValue("Type","Check");
                json.addValue("Result","Fail");
                break;
            case FATAL: json.addValue("Type","Fatal");
                break;
            case SKIP: json.addValue("Type","Skip");
                break;
            case ERROR: json.addValue("Type","Error");
                break;
            case WARNING: json.addValue("Type","Warning");
                break;
        }
        if (isStep) {
            stepCount += 1;
        }
        if ((logStatus == PASS) || (logStatus == FAIL) || (logStatus == INFO)) {
            json.addValue("Step", stepCount);
        }
        json.addValue("Output", jsonMessage);
        json.addValue("Screenshot", screenshot);
        json.addArray();
    }

    public void startTest(String testName, String description) {
        testReport = report.startTest(testName, description);
        testReport.log(INFO, "Test Identifier: " + randomUUIDString);
        json.addValue("TestCase", testName);
        json.addValue("Description", description);
        json.addValue("Identifier", randomUUIDString);
        json.addArray();
    }

    public void endTest() throws IOException {
        try {
            report.endTest(testReport);
            json.write();
        } catch (IOException e) {
            throw e;
        }
    }

    public void flush() throws IOException {
        try {
            report.flush();
            json.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            report.close();
            json.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public void exceptionReportingFatal(String message) {
        writeFatal(message);
    }

    public void exceptionReportingFail(String message, String screenshot) {
        writeFail(message, screenshot.replace(reportPath, ".\\"));
    }

    public void exceptionReportingFail(String message) {
        writeFail(message);
    }
}
