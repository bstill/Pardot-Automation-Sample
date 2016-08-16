package com.api;

import com.generic.RandomData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium {
    private WebDriver wd;
    private Actions action;
    private WebDriverWait wait;

    private Integer pageLoadTimeout = 60;
    private Integer implicitWait = 0;

    private Reporting reporting;
    private RandomData random = new RandomData();

    public Selenium(Reporting reporting) {
        this.reporting = reporting;
    }

    private void cleanup() {
        stop();
    }

    public void start() {
        try {
            wd = new FirefoxDriver();
            action = new Actions(wd);
            wait = new WebDriverWait(wd, 10, 100);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to start browser");
            cleanup();
            throw e;
        }
    }

    public void stop() {
        try {
            wd.close();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to terminate browser");
            throw e;
        }
    }

    public void goUrl(String url) {
        try {
            wd.get(url);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to get: " + url);
            cleanup();
            throw e;
        }
    }

    public void setPageLoadTimeout() {
        setPageLoadTimeout(pageLoadTimeout);
    }

    public void setPageLoadTimeout(Integer timeout) {
        try {
            pageLoadTimeout = timeout;
            wd.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to set pageLoadTimeout");
            cleanup();
            throw e;
        }
    }

    public void setImplicitWait() {
        setImplicitWait(implicitWait);
    }

    public void setImplicitWait(Integer timeout) {
        try {
            implicitWait = timeout;
            wd.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to set implicitlyWait");
            cleanup();
            throw e;
        }
    }

    public void switchToWindow() {
        try {
            wd.switchTo().window(getWindowHandle());
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to switch to window");
            cleanup();
            throw e;
        }
    }

    public String getWindowHandle() {
        try {
            return wd.getWindowHandle();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to get window handle");
            cleanup();
            throw e;
        }
    }

    public String getTitle() {
        try {
            return wd.getTitle();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to get window title");
            cleanup();
            throw e;
        }
    }

    public String takeScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
            String screenshot = reporting.screenshotPath + random.getRandomStringAlphaNumeric(20) + ".png";
            FileUtils.copyFile(scrFile, new File(screenshot));
            return screenshot;
        } catch (Exception e) {
            reporting.exceptionReportingFatal("Failed to take screenshot");
            cleanup();
            throw new RuntimeException(e);
        }
    }

    public WebElement findElement(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to find element");
            cleanup();
            throw e;
        }
    }

    public WebElement findChildElement(By parentLocator, By childLocator) {
        try {
            return findChildElement(findElement(parentLocator), childLocator);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to find child element");
            cleanup();
            throw e;
        }
    }

    public WebElement findChildElement(WebElement element, By childLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, childLocator));
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to find child element");
            cleanup();
            throw e;
        }
    }

    public List<WebElement> findElements(By parentLocator, By childLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parentLocator, childLocator));
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to find elements");
            cleanup();
            throw e;
        }
    }

    public List<WebElement> findElements(WebElement element, By childLocator) {
        try {
            return element.findElements(childLocator);
            //return wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, childLocator));
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to find elements");
            cleanup();
            throw e;
        }
    }

    public String getText(By locator) {
        return getText(findElement(locator));
    }

    public String getText(WebElement element) {
        try {
            return element.getText();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to get text");
            cleanup();
            throw e;
        }
    }

    public String getAttribute(By locator, String attribute) {
        return getAttribute(findElement(locator), attribute);
    }

    public String getAttribute(WebElement element, String attribute) {
        try {
            return element.getAttribute(attribute);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to get attribute");
            cleanup();
            throw e;
        }
    }

    public WebElement isElementClickable(By locator) {
        return isElementClickable(findElement(locator));
    }

    public WebElement isElementClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to check is clickable");
            cleanup();
            throw e;
        }
    }

    public void click(By locator) {
        click(findElement(locator));
    }

    public void click(WebElement element) {
        try {
            element.click();
            //findElementClickable(element).click();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to click");
            cleanup();
            throw e;
        }
    }

    public void doubleClick(By locator) {
        doubleClick(findElement(locator));
    }

    public void doubleClick(WebElement element) {
        try {
            action.moveToElement(element).doubleClick().build().perform();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to double click");
            cleanup();
            throw e;
        }
    }

    public void mouseHover(By locator) {
        mouseHover(findElement(locator));
    }

    public void mouseHover(WebElement element) {
        try {
            action.moveToElement(element).build().perform();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to mouse hover");
            cleanup();
            throw e;
        }
    }

    public void clear(By locator) {
        clear(findElement(locator));
    }

    public void clear(WebElement element) {
        try {
            element.clear();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to clear");
            cleanup();
            throw e;
        }
    }

    public void sendKeys(By locator, String text) {
        sendKeys(findElement(locator), text);
    }

    public void sendKeys(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to send keys");
            cleanup();
            throw e;
        }
    }

    public void selectByVisibleText(By locator, String itemText) {
        selectByVisibleText(findElement(locator), itemText);
    }

    public void selectByVisibleText(WebElement element, String itemText) {
        try {
            new Select(element).selectByVisibleText(itemText);
        } catch (WebDriverException e) {
            reporting.exceptionReportingFatal("Failed to select by visible text");
            cleanup();
            throw e;
        }
    }

    public Boolean isSelected(By locator) {
        return isSelected(findElement(locator));
    }

    public Boolean isSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (WebDriverException e) {
            reporting.exceptionReportingFail("Failed to check if selected");
            cleanup();
            throw e;
        }
    }

    public void waitElementInvisible(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            reporting.exceptionReportingFail("Failed to wait for element invisible");
            cleanup();
            throw e;
        }
    }

    public void throwRuntimeException(String message, Boolean useScreenshot) {
        try {
            if (useScreenshot) {
                reporting.exceptionReportingFail(message, takeScreenshot());
            } else {
                reporting.exceptionReportingFail(message);
            }
            throw new RuntimeException (message);
        } catch (RuntimeException e) {
            cleanup();
            throw e;
        }
    }





}
