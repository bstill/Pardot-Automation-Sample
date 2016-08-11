package com.browser;

import com.api.Selenium;

public class PardotBrowser {

    public Selenium startBrowser(String url) {
        Selenium selenium = new Selenium();

        System.out.println("  -> Initialize Browser: " + url);
        selenium.start();
        selenium.goUrl(url);
        selenium.setPageLoadTimeout();
        selenium.setImplicitWait();

        return selenium;
    }

    public void goUrl(Selenium selenium, String url) {
        selenium.goUrl(url);
    }

    public void stopBrowser(Selenium selenium) {
        selenium.stop();
    }




}
