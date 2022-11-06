package page;

import core.BasePage;
import core.KeywordWeb;
import core.PropertiesFile;
import static org.testng.AssertJUnit.assertEquals;


public class HomePage extends BasePage {

    public HomePage() {
        super();
    }

   public HomePage(KeywordWeb keyword) {
        super(keyword);
   }

   public void goToElementMenu() {
        keyword.clickElement(PropertiesFile.getPropValue("BTN_ELEMENT_NAME"));
   }

    public void goToBrowser() {
        keyword.maximizeWindow();
        keyword.openUrl(PropertiesFile.getPropValue("BASE_URL"));
    }

    public void  testAlert4() throws InterruptedException {
        //keyword.scrollDow(PropertiesFile.getPropValue("BTN_CLICK_ME_4"));
        keyword.scrollToPosition();
        keyword.waitForElementPresent(PropertiesFile.getPropValue("BTN_CLICK_ME_4"),60);
        keyword.clickElement(PropertiesFile.getPropValue("BTN_CLICK_ME_4"));
        Thread.sleep(2000);


        keyword.dismissAlert();

        Thread.sleep(2000);
        keyword.clickElement(PropertiesFile.getPropValue("BTN_CLICK_ME_4"));
        keyword.setAlertText(PropertiesFile.getPropValue("INPUT_TEXT"));


        assertEquals(PropertiesFile.getPropValue("TEXT_ALERT_RESULT"),
                keyword.getText(PropertiesFile.getPropValue("TEXT_ALERT_RESULT_XPATH")));

    }
}
