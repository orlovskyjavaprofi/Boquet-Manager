package viewtests;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.jupiter.api.AfterEach;
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

}
