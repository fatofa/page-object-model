package test;

import core.BaseTest;
import core.LogHelper;
import core.PropertiesFile;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.HomePage;

import java.awt.*;
import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class Home001 extends BaseTest {

    private HomePage objHome;
    public static final Logger logger = LogHelper.getLogger();

    public Home001() {
        super();
    }

    @Test
    public void testCase_0001() throws InterruptedException {
        System.out.println("testCase_0001");
        objHome = new HomePage(this.keyword);
//        objHome.goToElementMenu();
        objHome.goToBrowser();

        keyword.waitForElementPresent(PropertiesFile.getPropValue("BTN_NEW_TAB"),60);
//
//        keyword.executeJavaScript("document.getElementById('tabButton').click()");
//        keyword.waitForElementPresent(PropertiesFile.getPropValue("TEXT_NEW_TAB"),60);
//        keyword.swithToTab(0);
//        keyword.waitForElementPresent(PropertiesFile.getPropValue("BTN_NEW_TAB"),60);
//        keyword.swithToTab(1);
//        keyword.waitForElementPresent(PropertiesFile.getPropValue("TEXT_NEW_TAB"),60);
//        Thread.sleep(2000);
//        keyword.closeTab(1);
//        Thread.sleep(2000);
        keyword.clickElement(PropertiesFile.getPropValue("BTN_NEW_WINDOW"));
//        keyword.listWindowID();
        keyword.switchToWindowByIndex(1);
//        keyword.switchToWindow(PropertiesFile.getPropValue("WINDOW_1"));4
        Thread.sleep(2000);
        keyword.switchToWindowByIndex(0);
        Thread.sleep(2000);

        keyword.closeWindowByIndex(1);
        Thread.sleep(2000);
    }

    @Test
    public void test0002() throws InterruptedException, IOException, AWTException {
        System.out.println("testCase_0001");
        objHome = new HomePage(this.keyword);
//        objHome.goToElementMenu();
        objHome.goToBrowser();

//        keyword.waitForElementPresent(PropertiesFile.getPropValue("BTN_CLICK_ME_1"),60);
//        keyword.clickElement(PropertiesFile.getPropValue("BTN_CLICK_ME_1"));
//        Thread.sleep(2000);
//
//        assertEquals(PropertiesFile.getPropValue("TEXT_VERIFY_CLICK_ME_1"), keyword.getAlertText());
//        keyword.takeScreenshot2("jpg" , "report/alert.png");
//
//        keyword.acceptAlert();
//        Thread.sleep(2000);


        objHome.testAlert4();
    }

    @Test
    public void test0003() throws InterruptedException {
        System.out.println("testCase_0001");
        objHome = new HomePage(this.keyword);
//        objHome.goToElementMenu();
        objHome.goToBrowser();

        Thread.sleep(2000);


//        System.out.println("Get number of element is "
//                + keyword.getNumberOfElementsByTagName(PropertiesFile.getPropValue("TEXT_IFRAME")));
//
        keyword.switchToFrame(PropertiesFile.getPropValue("TEXT_IFRAME1"));
        assertTrue(keyword.verifyElementPresent(PropertiesFile.getPropValue("IFRAME_TEXT1")));
        keyword.scrollToPosition();
    }

    @Test
    public void test0004() throws InterruptedException {
        System.out.println("testCase_0001");
        objHome = new HomePage(this.keyword);
//        objHome.goToElementMenu();
        objHome.goToBrowser();

        Thread.sleep(2000);


//        System.out.println("Get number of element is "
//                + keyword.getNumberOfElementsByTagName(PropertiesFile.getPropValue("TEXT_IFRAME")));
//
        keyword.switchToFrame(PropertiesFile.getPropValue("PARENT_FRAME"));
        assertTrue(keyword.verifyElementPresent(PropertiesFile.getPropValue("PARENT_FRAME_TEXT")));
        //assertTrue(keyword.verifyElementPresent(PropertiesFile.getPropValue("IFRAME_TEXT1")));
//        keyword.scrollToPosition();
        //keyword.switchToFrameByIndex(0);
        keyword.switchToIFrameByXpath(PropertiesFile.getPropValue("TEXT_CHILDREN_FRAME"));
        assertTrue(keyword.verifyElementPresent(PropertiesFile.getPropValue("CHILDREN_FRAME")));
        //assertTrue(keyword.verifyElementPresent(PropertiesFile.getPropValue("PARENT_FRAME_TEXT")));
        keyword.switchToParentFrame();
        assertTrue(keyword.verifyElementPresent(PropertiesFile.getPropValue("PARENT_FRAME_TEXT")));

    }



}
