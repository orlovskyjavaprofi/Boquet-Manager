package views.app;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.ProviderBetweenUiControllers;
import views.controllers.mainmenu.MainMenuController;
import views.controllers.mainmenu.ServicesListController;

public class MainView extends Application
{
	private GridPane mainViewGridPane;
	private String title;
	private Scene mainViewScene;
    private ResourceBundle langResources;
    private MainMenuController mainMenuController;
    private Parent rootNode;
    private final ProviderBetweenUiControllers providerBetweenControllers;
    
	public MainView() throws IOException
	{
		FXMLLoader fxmlLoader = initPathToFxmlMainFormAndInitFxmlLoader();
		providerBetweenControllers = new ProviderBetweenUiControllers();
		rootNode = fxmlLoader.load();
		setMainViewGridPane((GridPane)rootNode);
        mainMenuController = (MainMenuController)fxmlLoader.getController(); 
        mainMenuController.setShareableProviderObj(providerBetweenControllers);
        
		this.setTitle("Boquet-manager for sattelite tv channels");
		mainViewScene = new Scene(getMainViewGridPane());

	}


	private FXMLLoader initPathToFxmlMainFormAndInitFxmlLoader()
	{
		String pathToFxmlForm = pathToMainFxmlSetUp();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathToFxmlForm));
		fxmlLoader.setResources(this.getLangResources());
		return fxmlLoader;
	}


	private String pathToMainFxmlSetUp()
	{	
		String pathToFxmlForm = "/fxmls/MainForm.fxml";
		this.setLangResources( ResourceBundle.getBundle("internationalization/menuDef") );
		return pathToFxmlForm;
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle(this.getTitle());
		primaryStage.setScene(this.getMainViewScene());
		primaryStage.show();
	}

	public ResourceBundle getLangResources()
	{
		return langResources;
	}

	public void setLangResources(ResourceBundle inputLangResources)
	{
		langResources = inputLangResources;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public GridPane getMainViewGridPane()
	{
		return mainViewGridPane;
	}

	public void setMainViewGridPane(GridPane mainViewGridPane)
	{
		this.mainViewGridPane = mainViewGridPane;
	}

	public Scene getMainViewScene()
	{
		return mainViewScene;
	}

	public void setMainViewScene(Scene mainViewScene)
	{
		this.mainViewScene = mainViewScene;
	}

	public MainMenuController getMainMenuController()
	{
		return mainMenuController;
	}

	public void setMainMenuController(MainMenuController mainMenuController)
	{
		this.mainMenuController = mainMenuController;
	}

}
