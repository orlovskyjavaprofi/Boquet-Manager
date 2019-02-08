package modeltests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import models.ProviderBetweenUiControllers;
import views.app.MainView;

class ProviderBetweenUiControllersTest extends ApplicationTest
{
    ProviderBetweenUiControllers providerObj;
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
    
	@BeforeEach
	void setUp()
	{
		providerObj = new ProviderBetweenUiControllers();
	}
	
	@Test
	void testingIfProviderBetweenUiControllersObjectExist()
	{
		assertNotNull(providerObj);
	}

	@Test
	void testingIfProviderCanStoreThePathsToXmlFiles() {
		List<String> pathsOfValidXmlFiles = new ArrayList<String>();
		initPathOfFillesXmlList(pathsOfValidXmlFiles);
		boolean expectedResult = true;
		boolean actualResult = providerObj.insertPathOfXmlFileToList(pathsOfValidXmlFiles);
		assertEquals(expectedResult,actualResult,"can't insert values to pathsOfValidXmlFiles list!" );
	}

	@Test
	void testingThatProvideGetsValueFromMainMenu() {
		List<String> xmlPathsToFiles = new ArrayList<String>();
		initPathOfFillesXmlList(xmlPathsToFiles);
		writeValidPathToCustomFileChooser(xmlPathsToFiles);
        boolean expectedResult = false;
		boolean actualResult = mainViewObj.getMainMenuController().getShareableProviderObj().
				               getPathsOfValidXmlFiles().isEmpty();
				
		assertEquals(expectedResult,actualResult,"can't store ArrayList in provider object" );
	}

	private void initPathOfFillesXmlList(List<String> pathsOfValidXmlFiles)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val2 = "c:\\TEST\\XML-Files-Update2018\\satellites.xml";
		String val3 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
		pathsOfValidXmlFiles.add(val1);
		pathsOfValidXmlFiles.add(val2);
		pathsOfValidXmlFiles.add(val3);
	}
	
	private void writeValidPathToCustomFileChooser(List<String> xmlPathsToFiles)
	{
		Integer counter = 0;
		for (String pathToFiles : xmlPathsToFiles)
		{
			clickOn("#menuFile");
			clickOn("#menuItemOpenXml");
			clickOn("#txtFieldDefaultPath");
			write(pathToFiles);
			clickOn("#okBtn");
		}

	}
	
	
}
