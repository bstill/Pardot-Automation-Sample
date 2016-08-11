package com.api;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting {
    private ExtentReports report;
    private ExtentTest testReport;
    //private String reportPath = ".\\reports\\(" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ") Automation_Report.html";
    private String reportPath = ".\\reports\\Automation_Report.html";

    public Reporting() {
        report = new ExtentReports(reportPath, false);
        testReport = report.startTest("Pardot Sample Automation Test Suite", "This is a sample automation project created as part of the interview process.");
    }

    public void writeInfo(String message) {
        System.out.println(message);
        testReport.log(LogStatus.INFO, message);
        //flush();
    }

    public void writeError(String message) {
        System.out.println(message);
        testReport.log(LogStatus.ERROR, message);
    }

    public void writePass(String message) {
        System.out.println(message);
        testReport.log(LogStatus.PASS, message);
    }

    public void writeFail(String message) {
        System.out.println(message);
        testReport.log(LogStatus.FAIL, message);
    }

    public void writeWarning(String message) {
        System.out.println(message);
        testReport.log(LogStatus.WARNING, message);
    }

    public void writeFatal(String message) {
        System.out.println(message);
        testReport.log(LogStatus.FATAL, message);
    }

    public void writeSkip(String message) {
        System.out.println(message);
        testReport.log(LogStatus.SKIP, message);
    }

    public void endTest() {
        report.endTest(testReport);
    }

    public void flush() {
        report.flush();
    }

}
