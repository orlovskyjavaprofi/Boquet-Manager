package viewtests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.app.MainView;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


class mainViewTest extends ApplicationTest
{
	MainView mainViewObj;
    String extractedTitle;
    List<String> EnglishMenuList = new ArrayList<String>();

    Menu HelpMenuText;
    
	ObservableList<Menu> HelpMainMenuList;
    MenuBar mainMenuBarTest;
    
	@Override
	public void start(Stage stage) throws Exception
	{
		mainViewObj = new MainView();
		stage.setScene(mainViewObj.getMainViewScene());
		stage.setTitle(mainViewObj.getTitle());
		extractedTitle = stage.getTitle();
		
		iterateOverMenus(stage);
		
		stage.show();
		stage.toFront();
	}

	@Test
	void testingIfMainViewObjectExist() throws IOException
	{
		assertNotNull(mainViewObj);
	}

	@Test
	void testinIfTitleHasBeenShown()
	{ 
		String expectedResult = "Boquet-manager for sattelite tv channels";
		String actualResult = extractedTitle;
		
		assertEquals(expectedResult, actualResult, "the title of the stage is not set up!");
	}
	
	@Test
	void testingIfEnglishIsDefaultAsLanguageinMenue(){
		List<String> expectedResultEnglishMenuList = new ArrayList<String>();
		expectedResultEnglishMenuList = initEngMunuelist(expectedResultEnglishMenuList);
		List<String> actualResultEnglishMenuList = this.getEnglishMenuList();
		
		assertEquals(expectedResultEnglishMenuList,actualResultEnglishMenuList,
				             "the english menu list names is not correct!");	
	}
	
	@Test
	void testingIfDefaultLangEngCheckBoxSelected() {
		boolean expectedResult = true;
		boolean actualResult = false;
		boolean preferenceChkBoxState = false;

		ObservableList <Menu> menuList =this.getMainMenuBarTest().getMenus();
		
		preferenceChkBoxState = getActualStateOfChkBoxPreference(preferenceChkBoxState, menuList);
		
		actualResult = evaluateStateOfChkBoxPreferences(actualResult, preferenceChkBoxState, menuList);
		assertEquals(expectedResult,actualResult,"the checkbox eng lang wasn't selected");
	}
	
	@Test
	void testing4DifferentLangMenuChkBoxesAndLangMenuItems() {
		boolean expectedResult = true;
		boolean actualResult=  false;
		boolean stateWhereOnlyOneBoxSelected = false;
		
		ObservableList <Menu> menuList =this.getMainMenuBarTest().getMenus();
		List <String> listOfClickOnLangChkBoxes = new ArrayList<String>();
		listOfClickOnLangChkBoxes =intialiseCommandsForOnClickCheckBoxe(
				                                                               listOfClickOnLangChkBoxes);
		List<String> listOfClickOnLangItemMenu = new ArrayList<String>();
		listOfClickOnLangItemMenu = initLangMenuCommands(listOfClickOnLangItemMenu);
		
		stateWhereOnlyOneBoxSelected = iterateOverMenuListFindMenuWithLangChkBoxes(
				stateWhereOnlyOneBoxSelected,	menuList, listOfClickOnLangChkBoxes, 
				listOfClickOnLangItemMenu);
		if(stateWhereOnlyOneBoxSelected == true) {
			actualResult = true;
		}

		assertEquals(expectedResult,actualResult,"too many language boxes are selected, notic only one box is allowed!");
	}
	
	@Test
	void testingIfUserSelectedGerAsMainMenuLang() {
		boolean expectedResult = true;
		boolean actualResult = false;
		String langSelection = "#menuItemGer";
		
		setUplangForMenuClickAndClickLang(langSelection);
		
		ObservableList <Menu> menuList =this.getMainMenuBarTest().getMenus();
		List<String> expectedResultGerMenuList = new ArrayList<String>();
		expectedResultGerMenuList = this.initGerMunuelist(expectedResultGerMenuList);
		List<String> actualResultGerMenutList = new ArrayList<String>();
		
		actualResult = evaluateResultForLangTest(actualResult, menuList, expectedResultGerMenuList,
				actualResultGerMenutList);
		
		assertEquals(expectedResult, actualResult,"German language wasnt selected");
	}
	
	@Test
	void testingIfUserSelectedEngAsMainMenuLang() {
		boolean expectedResult = true;
		boolean actualResult = false;
		String langSelection = "#menuItemGer";
		setUplangForMenuClickAndClickLang(langSelection);
		langSelection = "#menuItemEng";
		setUplangForMenuClickAndClickLang(langSelection);
		
		ObservableList <Menu> menuList =this.getMainMenuBarTest().getMenus();
		List<String> expectedResultEngMenuList = new ArrayList<String>();
		expectedResultEngMenuList = this.initEngMunuelist(expectedResultEngMenuList);
		List<String> actualResultEngMenutList = new ArrayList<String>();
		
		actualResult = evaluateResultForLangTest(actualResult, menuList, expectedResultEngMenuList,
				actualResultEngMenutList);
		
		assertEquals(expectedResult, actualResult,"German language wasnt selected");
	}
	
	@Test
	void testingIfUserSelectedUkrAsMainMenuLang() {
		boolean expectedResult = true;
		boolean actualResult = false;
		String langSelection = "#menuItemUkr";
		
		setUplangForMenuClickAndClickLang(langSelection);
		
		ObservableList <Menu> menuList =this.getMainMenuBarTest().getMenus();
		List<String> expectedResultUkrMenuList = new ArrayList<String>();
		expectedResultUkrMenuList = this.initUkrMunuelist(expectedResultUkrMenuList);
		List<String> actualResultUkrMenutList = new ArrayList<String>();
		
		actualResult = evaluateResultForLangTest(actualResult, menuList, expectedResultUkrMenuList,
				actualResultUkrMenutList);
		
		assertEquals(expectedResult, actualResult,"German language wasnt selected");
	}
	
	@Test
	void testingIfUserSelectedRusAsMainMenuLang() {
		boolean expectedResult = true;
		boolean actualResult = false;
		String langSelection = "#menuItemRu";
		
		setUplangForMenuClickAndClickLang(langSelection);
		
		ObservableList <Menu> menuList =this.getMainMenuBarTest().getMenus();
		List<String> expectedResultRusMenuList = new ArrayList<String>();
		expectedResultRusMenuList = this.initRuMunuelist(expectedResultRusMenuList);
		List<String> actualResultRusMenutList = new ArrayList<String>();
		
		actualResult = evaluateResultForLangTest(actualResult, menuList, expectedResultRusMenuList,
				actualResultRusMenutList);
		
		assertEquals(expectedResult, actualResult,"German language wasnt selected");
	}

	@Test
	void testIfAControllerCanBeAccessed() {
		assertNotNull(mainViewObj.getMainMenuController()  );
	}

	@Test
	void testingIfPathToFilesCanBeWritten() {
		List<String> expectedMockupValuesList = new ArrayList<String>();
		List<String> actualValuesList = new ArrayList<String>();
		initMockupValues(expectedMockupValuesList);
		writeValidPathToCustomFileChooser(expectedMockupValuesList);
		actualValuesList = mainViewObj.getMainMenuController().getPathsOfValidXmlFiles();
		assertEquals(expectedMockupValuesList, actualValuesList, "Got wrong values for xml path to files from main menu controller");	
	}
	
	@Test
	void testingIfUserInputRighPathToFilesCanBeWritten() {
		List<String> expectedMockupValuesList = new ArrayList<String>();
		List<String> xmlPathsToFiles = new ArrayList<String>();
		initMockupValues(expectedMockupValuesList);
		testRigthWayPathToXmlFiles(xmlPathsToFiles);
		writeValidPathToCustomFileChooser(xmlPathsToFiles);
        
		assertEquals(expectedMockupValuesList, mainViewObj.getMainMenuController().getPathsOfValidXmlFiles(), "Got wrong values for xml path to files from main menu controller caused by user input");		
	}
	
	@Test
	void testingUserInputMoreThen3XmlFiles()
	{
		List<String> xmlPathsToFiles = new ArrayList<String>();
		testPathToXmlFiles(xmlPathsToFiles);
		writePathToCustomFileChooser(xmlPathsToFiles);
	}

	@Test
	void testingIfUserInputsOneAndTheSameFilePaths() {
		List<String> xmlPathsToFiles = new ArrayList<String>();
		testPathToXmlEqualsFiles(xmlPathsToFiles);
		writeSamePathToCustomFileChooser(xmlPathsToFiles);	
    }
	
	@Test
	void testingIfMainControllerCanNotice3ValidFiles() {
		List<String> xmlPathsToFiles = new ArrayList<String>();
		testRigthWayPathToXmlFiles(xmlPathsToFiles);
		writeValidPathToCustomFileChooser(xmlPathsToFiles);

		boolean actualResult = mainViewObj.getMainMenuController().getStatusOfLoadXmlFiles();
       //System.out.println(mainViewObj.getMainMenuController().getObservableListOfXmlPaths().toString());
		assertTrue( actualResult,"The status of loading xml files is false!" );
	}

	@Test
	void testingIfMainControllerCanGetHoldOfServicesListViewLeftSide() {
		List<String> xmlPathsToFiles = new ArrayList<String>();
		testRigthWayPathToXmlFiles(xmlPathsToFiles);
		writeValidPathToCustomFileChooser(xmlPathsToFiles);
		verifyThat("#colSatTvChnName", isVisible());
    	verifyThat("#colSatName", isVisible());
    	verifyThat("#colSatTransponderId", isVisible());
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
		verifyThat(".alert", isVisible());
		clickOn(".alert .button");
	}

	private void writeSamePathToCustomFileChooser(List<String> xmlPathsToFiles)
	{
		Integer counter = 0;
		for (String pathToFiles : xmlPathsToFiles)
		{
			clickOn("#menuFile");
			clickOn("#menuItemOpenXml");
			clickOn("#txtFieldDefaultPath");
			write(pathToFiles);
			clickOn("#okBtn");
			counter = counter + 1;
			counter = alertDialogAfterTwoInput(counter);
		}

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

	private Integer alertDialogAfterTwoInput(Integer counter)
	{
		if (counter == 2)
		{
			verifyThat(".alert", isVisible());
			clickOn(".alert .button");
			counter =0;
		}	
		return counter;
	}
	
	private void testPathToXmlEqualsFiles(List<String> pathsToFiles)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val2 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val3 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val4 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		pathsToFiles.add(val1);
		pathsToFiles.add(val2);
		pathsToFiles.add(val3);
		pathsToFiles.add(val4);	
	}
	
	private void testPathToXmlFiles(List<String> pathsToFiles)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val2 = "c:\\TEST\\XML-Files-Update2018\\satellites.xml";
		String val3 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
		String val4 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
		pathsToFiles.add(val1);
		pathsToFiles.add(val2);
		pathsToFiles.add(val3);
		pathsToFiles.add(val4);
	}
	
	private void testRigthWayPathToXmlFiles(List<String> pathsToFiles)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
		String val2 = "c:\\TEST\\XML-Files-Update2018\\satellites.xml";
		String val3 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
		pathsToFiles.add(val1);
		pathsToFiles.add(val2);
		pathsToFiles.add(val3);
	
	}
	
	private void iterateOverListAddToMainMenuController(List<String> expectedMockupValuesList)
	{
		for (String inputFilePath : expectedMockupValuesList)
		{
			mainViewObj.getMainMenuController().addValidXmlFileToList(inputFilePath);			
		}
	}

	private void initMockupValues(List<String> mockupValuesList)
	{
		String val1 = "c:\\TEST\\XML-Files-Update2018\\bouquets.xml";
	    String val2 = "c:\\TEST\\XML-Files-Update2018\\satellites.xml";
	    String val3 = "c:\\TEST\\XML-Files-Update2018\\services.xml";
	    mockupValuesList.add(val1);
	    mockupValuesList.add(val2);
	    mockupValuesList.add(val3);
	}
	
	
	private boolean evaluateResultForLangTest(boolean actualResult, ObservableList<Menu> menuList,
			List<String> expectedResultRusMenuList, List<String> actualResultRusMenutList)
	{
		getActualMainMenuNames(menuList, actualResultRusMenutList);
		if( expectedResultRusMenuList.equals(actualResultRusMenutList) == true) {
			
			actualResult  = true;
		}
		return actualResult;
	}

	private void setUplangForMenuClickAndClickLang(String inputLang)
	{	
		clickOn("#menuLang");
        clickOn(inputLang);
	}

	private void getActualMainMenuNames(ObservableList<Menu> menuList, List<String> actualResultMenutList)
	{
		for (Menu menu : menuList)
		{
			//	System.out.println(menu.getText());
			actualResultMenutList.add(menu.getText());
		}
		actualResultMenutList.add(this.getHelpMenuText().getText());
	}

	private boolean iterateOverMenuListFindMenuWithLangChkBoxes(
			boolean stateWhereOnlyOneBoxSelected,
			ObservableList<Menu> menuList, List<String> listOfClickOnLangChkBoxes,
			List<String> listOfClickOnLangItemMenu)
	{
		ObservableList<MenuItem> menuItemsLangList;
		for (Menu menu : menuList)
		{
			if (menu.getId().equals("menuLang") ) {
				menuItemsLangList = menu.getItems();
				stateWhereOnlyOneBoxSelected = 
						iterateOverLangMenuItemsAndClickThem(stateWhereOnlyOneBoxSelected,
						menuItemsLangList, listOfClickOnLangChkBoxes, listOfClickOnLangItemMenu);
			}
		}
		return stateWhereOnlyOneBoxSelected;
	}

	private boolean iterateOverLangMenuItemsAndClickThem(
		    	    boolean stateWhereOnlyOneBoxSelected,
		    	    ObservableList<MenuItem> menuItemsLangList, 
			    List<String> listOfClickOnLangChkBoxes,  List<String> listOfClickOnLangItemMenu)
	{
		CheckBox langBox;
		for (MenuItem menuItem : menuItemsLangList)
		{
			langBox = (CheckBox)menuItem.getGraphic();
			//System.out.println(menuItem.getId() +" "+langBox.isSelected());
			stateWhereOnlyOneBoxSelected = 
			iterateOverLangChkBoxesAndMenuItemClickThem(
					stateWhereOnlyOneBoxSelected, langBox, listOfClickOnLangChkBoxes,
					listOfClickOnLangItemMenu);
		}
		return stateWhereOnlyOneBoxSelected;
	}

	private boolean iterateOverLangChkBoxesAndMenuItemClickThem(boolean stateWhereOnlyOneBoxSelected, CheckBox langBox,
			List<String> listOfClickOnLangChkBoxes, List<String> listOfClickOnLangItemMenu)
	{
		int chkBoxCommandCounter = 0;
		chkBoxCommandCounter = ignoreEngChkBox(chkBoxCommandCounter, langBox);
		
		if (langBox.isSelected() == true) {
			stateWhereOnlyOneBoxSelected = true;
			clickOn("#menuLang");
			clickOnCheckBoxAndMenuItem(chkBoxCommandCounter,
					                                            listOfClickOnLangChkBoxes,
					                                            listOfClickOnLangItemMenu);
			chkBoxCommandCounter++;
		}
		return stateWhereOnlyOneBoxSelected;
	}

	private int ignoreEngChkBox(int chkBoxCommandCounter, CheckBox langBox)
	{
		if( langBox.getId().equals("chkBoxEng")) {
			chkBoxCommandCounter++;
		}
		return chkBoxCommandCounter;
	}

	private void clickOnCheckBoxAndMenuItem(int chkBoxCommandCounter, 
			List<String> listOfClickOnLangChkBoxes,
			List<String> listOfClickOnLangItemMenu)
	{
		if (chkBoxCommandCounter < listOfClickOnLangChkBoxes.size())
		{
			clickOn(listOfClickOnLangChkBoxes.get(chkBoxCommandCounter));
			clickOn("#menuLang");
			clickOn(listOfClickOnLangItemMenu.get(chkBoxCommandCounter));
		}
	}

	private List<String> initLangMenuCommands(List<String> listOfClickOnLangItemMenu)
	{
		listOfClickOnLangItemMenu.add("#menuItemEng");
		listOfClickOnLangItemMenu.add("#menuItemGer");
		listOfClickOnLangItemMenu.add("#menuItemUkr");
		listOfClickOnLangItemMenu.add("#menuItemRu");
		
		return listOfClickOnLangItemMenu;
	}

	private List<String>  intialiseCommandsForOnClickCheckBoxe(List<String> listOfClickOnLangChkBoxes)
	{
		listOfClickOnLangChkBoxes.add("#chkBoxRu");
		listOfClickOnLangChkBoxes.add("#chkBoxUkr");
		listOfClickOnLangChkBoxes.add("#chkBoxGer");
		listOfClickOnLangChkBoxes.add("#chkBoxEng");
		
		return listOfClickOnLangChkBoxes;
	}
	
	private boolean evaluateStateOfChkBoxPreferences(boolean actualResult, boolean preferenceChkState,
			ObservableList<Menu> menuList)
	{
		if (preferenceChkState == true)
		{
			clickOn("#menuFile");
			clickOn("#chkBoxUserPreferences");
			actualResult = getUpdatedStateOfChkBoxPreferences(actualResult, preferenceChkState, menuList);
            //	System.out.println("got true!");
		} else
		{
            //System.out.println("got false!");
			actualResult = getStateOfChkBoxEng(actualResult, preferenceChkState, menuList);
		}
		return actualResult;
	}

	private boolean getUpdatedStateOfChkBoxPreferences(boolean actualResult, boolean preferenceChkState,
			ObservableList<Menu> menuList)
	{
		preferenceChkState = getActualStateOfChkBoxPreference(preferenceChkState, menuList);
		actualResult = getStateOfChkBoxEng(actualResult, preferenceChkState, menuList);
		return actualResult;
	}

	private boolean getStateOfChkBoxEng(boolean actualResult, boolean preferenceChkState, ObservableList<Menu> menuList)
	{

		if (preferenceChkState == false)
		{
			for (Menu menu : menuList)
			{
				actualResult = findEngLangAndCheckChkBoxStatus(actualResult, menu);
			}
		}
		return actualResult;
	}

	private boolean findEngLangAndCheckChkBoxStatus(boolean actualResult, Menu menu)
	{
		ObservableList<MenuItem> menuItemList;
		if (menu.getId().equals("menuLang"))
		{
			menuItemList = menu.getItems();
			for (MenuItem menuItem : menuItemList)
			{
				actualResult = checkStateOfChkBox(actualResult, menuItem);
				if (actualResult = true) {
					break;
				}
			}
		}
		return actualResult;
	}

	private boolean checkStateOfChkBox(boolean actualResult, MenuItem menuItem)
	{
		CheckBox expectedLang;
		if (menuItem.getId().equals("menuItemEng"))
		{
			expectedLang = (CheckBox) menuItem.getGraphic();
			actualResult = expectedLang.isSelected();
		}
		return actualResult;
	}

	private boolean getActualStateOfChkBoxPreference(boolean preferenceChkState, ObservableList<Menu> menuList)
	{

		for (Menu menu : menuList)
		{
			preferenceChkState = findMenuFileAndCheckChkBoxPreferences(preferenceChkState, menu);
		}
		return preferenceChkState;
	}

	private boolean findMenuFileAndCheckChkBoxPreferences(boolean preferenceChkState, Menu menu)
	{
		CheckBox preferencesChkBox;
		ObservableList<MenuItem> menuItemList;
		if (menu.getId().equals("menuFile"))
		{
			menuItemList = menu.getItems();
			for (MenuItem menuItem : menuItemList)
			{
				if (menuItem.getId().equals("menuItemPreferences"))
				{
					preferencesChkBox = (CheckBox) menuItem.getGraphic();
					preferenceChkState = preferencesChkBox.isSelected();
					break;
				}
			}
		}
		return preferenceChkState;
	}
	
	private void iterateOverMenus(Stage stage)
	{
		ObservableList<Node> listOfChilds;

		listOfChilds = stage.getScene().getRoot().getChildrenUnmodifiable();
		AnchorPane anchorPaneObj = (AnchorPane) listOfChilds.get(0);
		listOfChilds = anchorPaneObj.getChildren();
		MenuBar node1 = (MenuBar) listOfChilds.get(0);
		this.setMainMenuBarTest(node1);
		MenuBar node2 = (MenuBar) listOfChilds.get(1);
		extractingNameOfMenus(node1, node2);
	}

	private void extractingNameOfMenus(MenuBar mainMenuNode, MenuBar helperMenuNode)
	{
		ObservableList<Menu> listOfMenus;
		List<String> ListOfMenuNames;
		listOfMenus = mainMenuNode.getMenus();
		ListOfMenuNames =   extractResultNamesOfMenu(listOfMenus);
		this.setEnglishMenuList(ListOfMenuNames);
		listOfMenus = helperMenuNode.getMenus();
		this.setHelpMenuText(listOfMenus.get(0));
		this.setHelpMainMenuList(listOfMenus);
		ListOfMenuNames = extractResultNamesOfMenu(listOfMenus);
		this.getEnglishMenuList().addAll(ListOfMenuNames);
	}

	private List<String>  extractResultNamesOfMenu(ObservableList<Menu> listOfMenus)
	{
		List<String> EnglishMenuList = new ArrayList<String>(); 
		int index = 0;
		
		for (Menu menu : listOfMenus)
		{
			EnglishMenuList.add(menu.getText());
          //	System.out.println(EnglishMenuList.get(index));
			index++;
		}
		
		return EnglishMenuList;
	}

	private List<String> initEngMunuelist(List<String> expectedResultEnglishMenuList)
	{
		expectedResultEnglishMenuList.add("File");
		expectedResultEnglishMenuList.add("Edit");
		expectedResultEnglishMenuList.add("View");
		expectedResultEnglishMenuList.add("Lang");
		expectedResultEnglishMenuList.add("SatReceiver");
		expectedResultEnglishMenuList.add("Help");
		
		return expectedResultEnglishMenuList;
	}

	private List<String> initGerMunuelist(List<String> expectedResultGerMenuList)
	{
		expectedResultGerMenuList.add("Datei");
		expectedResultGerMenuList.add("Editieren");
		expectedResultGerMenuList.add("Ansicht");
		expectedResultGerMenuList.add("Sprache");
		expectedResultGerMenuList.add("SatReceiver");
		expectedResultGerMenuList.add("Hilfe");

		return expectedResultGerMenuList;
	}
	
	private List<String> initUkrMunuelist(List<String> expectedResultUkrMenuList)
	{
		expectedResultUkrMenuList.add("\u0424\u0430\u0439\u043B\u0438");
		expectedResultUkrMenuList.add("\u041F\u0440\u0430\u0432\u043A\u0430");
		expectedResultUkrMenuList.add("\u0412\u0438\u0433\u043B\u044F\u0434");
		expectedResultUkrMenuList.add("\u041C\u043E\u0432\u0430");
		expectedResultUkrMenuList.add("\u0421\u0443\u043F\u0443\u0442\u043D\u0438\u043A\u043E\u0432\u0438\u0439 \u043F\u0440\u0438\u0439\u043C\u0430\u0447");
		expectedResultUkrMenuList.add("\u0414\u043E\u0432\u0456\u0434\u043A\u0430");

		return expectedResultUkrMenuList;
	}
	
	private List<String> initRuMunuelist(List<String> expectedResultRusMenuList)
	{
		expectedResultRusMenuList.add("\u0424\u0430\u0439\u043B");
		expectedResultRusMenuList.add("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435");
		expectedResultRusMenuList.add("\u0412\u0438\u0434");
		expectedResultRusMenuList.add("\u042F\u0437\u044B\u043A");
		expectedResultRusMenuList.add("\u0421\u043F\u0443\u0442\u043D\u0438\u043A\u043E\u0432\u044B\u0439 \u0440\u0435\u0441\u0438\u0432\u0435\u0440");
		expectedResultRusMenuList.add("\u0421\u043F\u0440\u0430\u0432\u043A\u0430");

		return expectedResultRusMenuList;
	}
	
	public List<String> getEnglishMenuList()
	{
		return EnglishMenuList;
	}

	public void setEnglishMenuList(List<String> englishMenuList)
	{
		EnglishMenuList = englishMenuList;
	}

	public MenuBar getMainMenuBarTest()
	{
		return mainMenuBarTest;
	}

	public void setMainMenuBarTest(MenuBar mainMenuBarTest)
	{
		this.mainMenuBarTest = mainMenuBarTest;
	}

	public ObservableList<Menu> getHelpMainMenuList()
	{
		return HelpMainMenuList;
	}

	public void setHelpMainMenuList(ObservableList<Menu> helpMainMenuList)
	{
		HelpMainMenuList = helpMainMenuList;
	}

	public Menu getHelpMenuText()
	{
		return HelpMenuText;
	}

	public void setHelpMenuText(Menu menu)
	{
		HelpMenuText = menu;
	}

}
