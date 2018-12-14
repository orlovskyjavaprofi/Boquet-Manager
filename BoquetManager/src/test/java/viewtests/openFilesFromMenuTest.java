package viewtests;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

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

	@Test
	//This Test only works on windows machine for Linux , MacOs, Unix use another pattern
	void testIf3DifferentFilesCanBeOpenedAndStored() {
		List<String> expectedPathsToFiles= new ArrayList<String>();
		List<String> actualPathsToFiles= new ArrayList<String>();
		testPathToFiles(expectedPathsToFiles);
		
		for (String pathToFiles : expectedPathsToFiles)
		{
			clickOn("#menuFile");
			clickOn("#menuItemOpenXml");
			clickOn("#txtFieldDefaultPath");
			write(pathToFiles);
			clickOn("#okBtn");
		}
		actualPathsToFiles = mainViewObj.getMainMenuController().getPathsOfValidXmlFiles();
		assertEquals(expectedPathsToFiles, actualPathsToFiles, "mismatch of paths to files!");
	}

	private void testPathToFiles(List<String> pathsToFiles)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val2 = "c:\\TEST\\XML-Files-Update2018\\satellites.xml";
		String val3 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
		pathsToFiles.add(val1);
		pathsToFiles.add(val2);
		pathsToFiles.add(val3);
	}


}
