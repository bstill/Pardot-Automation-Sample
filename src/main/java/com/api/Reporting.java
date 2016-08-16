package com.api;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;
import static com.relevantcodes.extentreports.LogStatus.*;
import static com.relevantcodes.extentreports.NetworkMode.OFFLINE;

public class Reporting {
    private ExtentReports report;
    private ExtentTest testReport;
    private Json json;
    public String reportPath = ".\\reports\\";
    public String reportFilenameHtml = "Automation_Report.html";
    public String reportFilenameJson = "Automation_Report.json";
    public String screenshotPath = ".\\reports\\screenshots\\";

    public Reporting() {
        report = new ExtentReports(reportPath + reportFilenameHtml, false, NEWEST_FIRST, OFFLINE );
        json = new Json(reportPath, reportFilenameJson);
    }

    public void writeInfo(String message) {
        writeLogEntry(INFO, message);
    }

    public void writeError(String message) {
        writeLogEntry(ERROR, message);
    }

    public void writeError(String message, String screenshot) {
        writeLogEntry(ERROR, message, screenshot);
    }

    public void writePass(String message) {
        writeLogEntry(PASS, message);
    }

    public void writePass(String message, String screenshot) {
        writeLogEntry(PASS, message, screenshot);
    }

    public void writeFail(String message) {
        writeLogEntry(FAIL, message);
    }

    public void writeFail(String message, String screenshot) {
        writeLogEntry(FAIL, message, screenshot);
    }

    public void writeWarning(String message) {
        writeLogEntry(WARNING, message);
    }

    public void writeWarning(String message, String screenshot) {
        writeLogEntry(WARNING, message, screenshot);
    }

    public void writeFatal(String message) {
        writeLogEntry(FATAL, message);
    }

    public void writeFatal(String message, String screenshot) {
        writeLogEntry(FATAL, message, screenshot);
    }

    public void writeSkip(String message) {
        writeLogEntry(SKIP, message);
    }

    public void writeSkip(String message, String screenshot) {
        writeLogEntry(SKIP, message, screenshot);
    }

    private void writeLogEntry(LogStatus logStatus, String message) {
        List<String> list = new ArrayList<String>();
        String key = "";
        String jsonMessage = message;

        if (logStatus == FAIL) {
             message = "FAIL: " + message;
        }

        if (logStatus == FATAL) {
            message = "FATAL: " + message;
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
        json.addValue("Output", jsonMessage);
        json.addArray();
    }

    private void writeLogEntry(LogStatus logStatus, String message, String screenshot) {
        List<String> list = new ArrayList<String>();
        String key = "";
        String jsonMessage = message;

        if (logStatus == FAIL) {
            message = "FAIL: " + message;
        }

        if (logStatus == FATAL) {
            message = "FATAL: " + message;
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
        json.addValue("Output", jsonMessage);
        json.addValue("Screenshot", screenshot);
        json.addArray();
    }

    public void startTest(String testName, String description) {
        testReport = report.startTest(testName, description);
        json.addValue("TestCase", testName);
        json.addValue("Description", description);
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
