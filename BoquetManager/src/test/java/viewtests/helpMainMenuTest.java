package viewtests;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import views.app.MainView;
class helpMainMenuTest extends ApplicationTest
{

	MainView mainViewObj;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		mainViewObj = new MainView();
		stage.setScene(mainViewObj.getMainViewScene());
		stage.setTitle(mainViewObj.getTitle());
		stage.show();
		stage.toFront();
	}
	
	@Test
	void testIfAboutLicenseWindowShow() {
		
		clickOn("#menuHelp");
		clickOn("#menuItemLicense");

		verifyThat("#aboutLicense" , isVisible()  );
		clickOn("#okBtnForLicenseInfoWindow");
	}
	
	@Test 
	void testIfAboutLicenseWindowCloseAufterButtonOkClick(){
		clickOn("#menuHelp");
		clickOn("#menuItemLicense");
		clickOn("#okBtnForLicenseInfoWindow");
		
		verifyThat("#mainGridPane" , isVisible()  );
	}

	@Test
	void testIfSupportProjectWindowShow() {
		clickOn("#menuHelp");
		clickOn("#menuItemSuppProject");
		
		verifyThat("#supportProjectPane" , isVisible()  );
	    clickOn("#ThankYouForSupBtn");
	}
	
	@Test
	void testIfAboutBoquetProjectWindowShow() {
		clickOn("#menuHelp");
		clickOn("#menuItemAboutApp");
		verifyThat("#borrderPaneAboutProject", isVisible());
		clickOn("#OkButOfAboutPrj");
	}
	
	
	
	@Disabled
	@Test
	void testIfSupportBtnForPaypalDonationForAlexClicked() {
		clickOn("#menuHelp");
		clickOn("#menuItemSuppProject");
		clickOn("#AlexPaypalSupBtn");
		verifyThat(".alert", isVisible()); 
		clickOn(".alert .button");
		clickOn("#ThankYouForSupBtn");
	}
	
	@Disabled
	@Test
	void testIfSuppotBtnForBtcDonationForAlexClicked() {
		clickOn("#menuHelp");
		clickOn("#menuItemSuppProject");
		clickOn("#AlexBtcSupBtn");
		verifyThat("#BtcDonationForAlexBorderPane", isVisible()); 
     	clickOn("#btcDonationOkBtn");
     	clickOn(".alert .button");
        clickOn("#ThankYouForSupBtn");
	}
	
	@Disabled
	@Test 
	void testIfAcknowledgementForBtcDonationShown() {
		clickOn("#menuHelp");
		clickOn("#menuItemSuppProject");
		clickOn("#AlexBtcSupBtn");
     	clickOn("#btcDonationOkBtn");
     	verifyThat(".alert", isVisible()); 
     	clickOn(".alert .button");
        clickOn("#ThankYouForSupBtn");
	}
	
}
