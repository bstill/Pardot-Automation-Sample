package com.api;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;
import static com.relevantcodes.extentreports.NetworkMode.OFFLINE;

public class Reporting {
    private ExtentReports report;
    private ExtentTest testReport;
    public String reportPath = ".\\reports\\";
    public String reportFilename = "Automation_Report.html";
    public String screenshotPath = ".\\reports\\screenshots\\";

    public Reporting() {
        report = new ExtentReports(reportPath + reportFilename, false, NEWEST_FIRST, OFFLINE );
    }

    public void writeInfo(String message) {
        writeLogEntry(LogStatus.INFO, message);
    }

    public void writeError(String message) {
        writeLogEntry(LogStatus.ERROR, message);
    }

    public void writeError(String message, String screenshot) {
        writeLogEntry(LogStatus.ERROR, message, screenshot);
    }

    public void writePass(String message) {
        writeLogEntry(LogStatus.PASS, message);
    }

    public void writePass(String message, String screenshot) {
        writeLogEntry(LogStatus.PASS, message, screenshot);
    }

    public void writeFail(String message) {
        writeLogEntry(LogStatus.FAIL, message);
    }

    public void writeFail(String message, String screenshot) {
        writeLogEntry(LogStatus.FAIL, message, screenshot);
    }

    public void writeWarning(String message) {
        writeLogEntry(LogStatus.WARNING, message);
    }

    public void writeWarning(String message, String screenshot) {
        writeLogEntry(LogStatus.WARNING, message, screenshot);
    }

    public void writeFatal(String message) {
        writeLogEntry(LogStatus.FATAL, message);
    }

    public void writeFatal(String message, String screenshot) {
        writeLogEntry(LogStatus.FATAL, message, screenshot);
    }

    public void writeSkip(String message) {
        writeLogEntry(LogStatus.SKIP, message);
    }

    public void writeSkip(String message, String screenshot) {
        writeLogEntry(LogStatus.SKIP, message, screenshot);
    }

    private void writeLogEntry(LogStatus logStatus, String message) {
        System.out.println(message);
        testReport.log(logStatus, message);
    }

    private void writeLogEntry(LogStatus logStatus, String message, String screenshot) {
        System.out.println(message);
        testReport.log(logStatus, message);
        testReport.log(logStatus, screenshot);
        testReport.log(LogStatus.INFO, "Screenshot: " + testReport.addScreenCapture(screenshot));
    }



    public void startTest(String testName, String description) {
        testReport = report.startTest(testName, description);
    }

    public void endTest() {
        report.endTest(testReport);
    }

    public void flush() {
        report.flush();
    }

    public void close() {
        report.close();
    }

    public void exceptionReportingFatal(String message) {
        writeFatal("Exception: " + message.substring(0, message.indexOf("Build info:")));
    }

    public void exceptionReportingFail(String message, String screenshot) {
        writeFail("Exception: " +message.substring(0, message.indexOf("Build info:")), screenshot.replace(reportPath, ".\\"));
    }

    public void exceptionReportingFail(String message) {
        writeFail("Exception: " +message.substring(0, message.indexOf("Build info:")));
    }
}
