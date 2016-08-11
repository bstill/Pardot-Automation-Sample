package com.api;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium {
    private WebDriver wd;
    private Actions action;
    private WebDriverWait wait;

    private Integer pageLoadTimeout = 60;
    private Integer implicitWait = 0;

    private void cleanup() {
        stop();
    }

    public void start() {
        try {
            wd = new FirefoxDriver();
            action = new Actions(wd);
            wait = new WebDriverWait(wd, 10, 100);
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public void stop() {
        try {
            wd.close();
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            throw e;
        }
    }

    public void goUrl(String url) {
        try {
            wd.get(url);
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public void switchToWindow() {
        try {
            wd.switchTo().window(getWindowHandle());
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public String getWindowHandle() {
        try {
            return wd.getWindowHandle();
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public String getTitle() {
        try {
            return wd.getTitle();
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }







    public WebElement findElement(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public WebElement findChildElement(By parentLocator, By childLocator) {
        try {
            return findChildElement(findElement(parentLocator), childLocator);
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public WebElement findChildElement(WebElement element, By childLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, childLocator));
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public List<WebElement> findElements(By parentLocator, By childLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parentLocator, childLocator));
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public List<WebElement> findElements(WebElement element, By childLocator) {
        try {
            return element.findElements(childLocator);
            //return wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, childLocator));
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
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
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }



    public void waitElementInvisible(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }

    public void throwRuntimeException(String message) {
        try {
            throw new RuntimeException (message);
        } catch (RuntimeException e) {
            System.err.println("RuntimeException: " + e.getMessage());
            cleanup();
            throw e;
        }
    }





}
