package viewtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

class mainViewTest extends ApplicationTest
{
	MainView mainViewObj;
    String extractedTitle;
    List<String> EnglishMenuList = new ArrayList<String>();
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
//					System.out.println(menuItem.getId() +" "+langBox.isSelected());
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

}
