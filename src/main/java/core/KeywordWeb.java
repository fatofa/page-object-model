package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;

public class KeywordWeb {

    public static final Logger logger = LogHelper.getLogger();
    private static WebDriver driver;
    public KeywordWeb() {
    }

    // keyword action

    public void openBrowser(String browser) {
    logger.info("Opening browser");
        switch (browser.toUpperCase()) {
            case "CHORME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        logger.info("open browser successfully " + browser);
    }

    public void openUrl(String... url) {

        String rawUrl = url.length > 0 ? url[0]: "";
        if (rawUrl != null && !rawUrl.isEmpty()) {
            logger.info("Go to URL: " + rawUrl);
            logger.info("url: " + url);
            driver.get(rawUrl);
        }
    }

    public void closeBrowser() {
        logger.info("Close browser");
        driver.quit();
    }

    public void clickElement(String elemen) {
        logger.info("Click element: " + elemen);
        driver.findElement(By.xpath(elemen)).click();
    }

    // double click
    public void doubleClick(String elemen) {
        logger.info("Double click: " + elemen);
        Actions builder = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(elemen));
        builder.doubleClick(elementRep).perform();
    }

    // Drag and drop
    public void dragAndDrop(String startElement, String endElement) {
        logger.info("Drag from: " + startElement + " to: " + endElement);
        Actions builder = new Actions(driver);
        WebElement source = driver.findElement(By.xpath(startElement));
        WebElement target = driver.findElement(By.xpath(endElement));

        builder.dragAndDrop(source, target).perform();
    }

    public void rightClick(String element, String menuItems) {
        logger.info("Right Click" + element);
        Actions builder = new Actions(driver);
        WebElement clickMe = driver.findElement(By.xpath(element));
        WebElement editMenuItem = driver.findElement(By.xpath(menuItems));

        builder.contextClick(clickMe).moveToElement(editMenuItem).click().perform();
    }

    public void executeJavaScript(String script) {
        logger.info("Executing JavaScript" + script);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);

    }
    public void takeScreenshot(String srcpath, String filename) throws IOException {
        logger.info("Taking screenshot save to:" + srcpath + "/" + filename + ".png");
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile, new File(srcpath + "/" + filename + ".png"));
    }

    public void takeScreenshot2(String imgformat, String srcpath) throws IOException, InterruptedException, AWTException {
        logger.info("Taking screenshot save to:" + srcpath);
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, imgformat, new File(srcpath));
    }

    public void maximizeWindow() {
        logger.info("Maximizing window...");
        driver.manage().window().maximize();
    }

    public void back() {
        logger.info("back ");
        driver.navigate().back();
    }

    public void forward() {
        logger.info("forward ");
        driver.navigate().forward();
    }

    public void navigateToUrl(String url) {
        logger.info("navigateToUrl " + url);
        driver.navigate().to(url);
    }

    public void acceptAlert() {
        logger.info("acceptAlert ");
        Alert alert = driver.switchTo().alert();

        alert.accept();
    }

    public String getAlertText() {
        logger.info("getAlertText");
        Alert alert = driver.switchTo().alert();

        return alert.getText();
    }

    public void dismissAlert() {
        logger.info("dismissAlert");
        Alert alert = driver.switchTo().alert();

        alert.dismiss();
    }

    public void setAlertText(String alertText) {
        logger.info("setAlertText");
        Alert alert = driver.switchTo().alert();

        alert.sendKeys(alertText);
        
        acceptAlert();
    }

    public void switchToFrame(String frame) {
        logger.info("switchToFrame");
        driver.switchTo().frame(frame);
    }

    public void switchToFrameByIndex(int index) {
        logger.info("switchToFrameByIndex");
        driver.switchTo().frame(index);
    }

    public void switchToIFrame(String element) {
        logger.info("switchToIFrame");
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
    }

    public void switchToIFrameByXpath(String element) {
        logger.info("switchToIFrame");
        WebElement iframe = driver.findElement(By.xpath(element));
        driver.switchTo().frame(iframe);
    }

    public void switchToWindow(String window) {
        logger.info("switchToWindow");
        driver.switchTo().window(window);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToWindowByIndex(int index) {
        logger.info("switchToWindowByIndex");
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index));
    }

    public void switchToParentWindow() {
        logger.info("switchToParentWindow");
        String parentWindowID = driver.getWindowHandle();
        driver.switchTo().window(parentWindowID);
    }

    public void closeWindowTitles(String title) {
        logger.info("closeWindowTitles");

        for (String windowID : driver.getWindowHandles()) {
            String windowTitles = driver.switchTo().window(windowID).getTitle();

            if (windowTitles.equals(title)) {
                driver.close();
                break;
            }
        }
    }

    public void closeWindowByIndex(int index) {
        switchToWindowByIndex(index);
        driver.close();
    }

    //verify keyword
    public boolean verifyElementPresent(String element) {
        logger.info("Verify element: " + element);
        try {
            driver.findElement(By.xpath(element));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyElementVisible(String element) {
        logger.info("verifyElementVisible");
        boolean blnVerify = false;
        try {
            blnVerify =  driver.findElement(By.xpath(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return blnVerify;
    }

    // wait keyword

    public void imWait(long timeout) {
        logger.info("implicitWait");
        driver.manage().timeouts().implicitlyWait(timeout , TimeUnit.SECONDS);
    }

    public void waitForElementPresent(String element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    public void fluentWaitForElementPresent(String elemen, Duration polling, Duration timeout) {
        logger.info("fluentWaitForElementPresent");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elemen)));
    }

    public void swithToTab(int tabNo) {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNo));

//        driver.close();
    }

    public void closeTab(int tabNo) {
        swithToTab(tabNo);
        driver.close();
    }

    public void listWindowID() {
        for (String windowID : driver.getWindowHandles()) {
            logger.info("Window ID: " + windowID);
        }
    }

    public String getText(String element) {
        logger.info("getText: " + element);
        WebElement strElement = driver.findElement(By.xpath(element));
        return strElement.getText();
    }

    public void scrollDow(String element) {
        logger.info("scrollDow: " + element);
        WebElement scroll = driver.findElement(By.xpath(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(scroll);
        actions.perform();
    }

    public void swithToDefaultContent() {
        logger.info("swithToDefaultContent: ");
        driver.switchTo().defaultContent();
    }

    public void scrollToPosition() {
        logger.info("scrollToPosition: ");
//        executeJavaScript("window.scrollBy(x,y)");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,500)");
    }


    public int getNumberOfElements(String element) {
        logger.info("getNumberOfElements: " + element);
        int res = driver.findElements(By.xpath(element)).size();
        return res;
    }

    public int getNumberOfElementsByTagName(String element) {
        logger.info("getNumberOfElements: " + element);
        int res = driver.findElements(By.tagName(element)).size();
        return res;
    }

    public void verifyElementNotPresent(String element) {
        logger.info("verifyElementNotPresent: " + element);
        try {
            assertFalse(driver.findElement(By.xpath(element)).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
