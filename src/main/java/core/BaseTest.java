package core;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected KeywordWeb keyword;
    public BaseTest() {
        keyword = new KeywordWeb();

    }

    @BeforeSuite
    public void beforeSuite() {
        PropertiesFile.setPropertiesFile();
    }

    @BeforeTest
    public void beforeTest() {
        keyword.openBrowser(PropertiesFile.getPropValue("BROWSER_NAME"));
    }

//    @AfterTest
//    public void afterTest() {
//        keyword.closeBrowser();
//    }
}
