package viewtests;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import org.testfx.matcher.control.TextMatchers;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import views.app.MainView;

class servicesViewTest extends ApplicationTest
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
	void testIfServicesFormIsShown() {
		verifyThat("#servicesListBorderPaneMain" , isVisible()  );
	}
	
	@Test
	void testIfServicesControllerCanSaveTheListFromProvider() {
		validUserInput();
        boolean expectedResult = false;
        boolean actualResult =  mainViewObj.getMainMenuController().getServicesListController().getProviderInstance()
        		.getPathsOfValidXmlFiles().isEmpty();
	
        assertEquals(expectedResult,actualResult,"service controller didn't loaded data from maincontroller!" );
	}

	@Test
	void testIfServicesListControllerGettingtheRightFilenamefromProviderModel() {
		boolean expectedResult = true;
		validUserInput();
		boolean actualResult = mainViewObj.getMainMenuController().getServicesListController()
				               .checkProviderModelForPathToServices();
		assertEquals(expectedResult,actualResult,"a given provider does not consist the services.xml" );
	}
	
    @Test
    void testIfServicesListUiIsPopulatedWithData() {
    	validUserInput();
    	
    	verifyThat("#colSatTvChnName", isNotNull());
    	verifyThat("#colSatName", isNotNull());
    	verifyThat("#colSatTransponderId", isNotNull());
    }
	
	private void validUserInput()
	{
		List<String> pathsToFiles = new ArrayList<String>();
		initPathToXmlList(pathsToFiles);
		writePathToCustomFileChooser(pathsToFiles);
	}

	private void writePathToCustomFileChooser(List<String> xmlPathsToFiles)
	{
		for (String pathToFiles : xmlPathsToFiles)
		{
			clickOn("#menuFile");
			clickOn("#menuItemOpenXml");
			clickOn("#txtFieldDefaultPath");
			write(pathToFiles);
			clickOn("#okBtn");			
		}

	}
	
	private void initPathToXmlList(List<String> inputPathsToFiles)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val2 = "c:\\TEST\\XML-Files-Update2018\\satellites.xml";
		String val3 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
		inputPathsToFiles.add(val1);
		inputPathsToFiles.add(val2);
		inputPathsToFiles.add(val3);	
	}
	
}
