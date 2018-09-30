package viewtests;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import views.app.MainView;

class openFilesFromMenuTest extends ApplicationTest
{
	MainView mainViewObj;
    String extractedTitle;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		mainViewObj = new MainView();
		stage.setScene(mainViewObj.getMainViewScene());
		stage.setTitle(mainViewObj.getTitle());
		extractedTitle = stage.getTitle();

		stage.show();
		stage.toFront();
	}

	@Test
	void testingIfMainViewObjectExist() throws IOException
	{
		assertNotNull(mainViewObj);
	}
	
	@Test
	void testIfCustomeFileChooserCanBeInvoked()
	{
		clickOn("#menuFile");
		clickOn("#menuItemOpenXml");
		verifyThat("#CustomFileChooserBorderPane", isVisible());	
		clickOn("#cancelBtn");
	}
	
	@Test
	//This Test only works on windows machine for Linux , MacOs, Unix use another pattern
	void testIfCustomerFileChooserCanShowRootDisks() {
		clickOn("#menuFile");
		clickOn("#menuItemOpenXml");
		String diskPattern= "Disk amount: 0";

		verifyThat("#lblDiskAmount",  not(hasText(diskPattern)));
		clickOn("#cancelBtn");
	}


}
