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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.app.MainView;

class mainViewTest extends ApplicationTest
{
	MainView mainViewObj;
    String extractedTitle;
    List<String> EnglishMenuList = new ArrayList<String>();
   
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
	
	private void iterateOverMenus(Stage stage)
	{
		ObservableList<Node> listOfChilds;

		listOfChilds = stage.getScene().getRoot().getChildrenUnmodifiable();
		AnchorPane anchorPaneObj = (AnchorPane) listOfChilds.get(0);
		listOfChilds = anchorPaneObj.getChildren();
		MenuBar node1 = (MenuBar) listOfChilds.get(0);
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
//			System.out.println(EnglishMenuList.get(index));
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

}
