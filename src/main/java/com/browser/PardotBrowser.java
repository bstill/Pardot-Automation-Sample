package com.browser;

import com.api.Reporting;
import com.api.Selenium;

public class PardotBrowser {
    private Reporting reporting;

    public PardotBrowser(Reporting reporting) {
        this.reporting = reporting;
    }

    public Selenium startBrowser(String url) {
        Selenium selenium = new Selenium(reporting);

        reporting.writeInfo("..-> Initialize Browser: " + url);
        selenium.start();
        selenium.goUrl(url);
        selenium.setPageLoadTimeout();
        selenium.setImplicitWait();

        return selenium;
    }

    public void goUrl(Selenium selenium, String url) {
        reporting.writeInfo("..-> Go To Url: " + url);
        selenium.goUrl(url);
    }

    public void stopBrowser(Selenium selenium) {
        reporting.writeInfo("..-> Stop Browser");
        selenium.stop();
    }




}
