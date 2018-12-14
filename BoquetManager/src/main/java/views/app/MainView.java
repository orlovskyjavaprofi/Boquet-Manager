package views.app;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import views.controllers.mainmenu.MainMenuController;

public class MainView extends Application
{
	private GridPane mainViewGridPane;
	private String title;
	private Scene mainViewScene;
    private ResourceBundle langResources;
    
	public MainView() throws IOException
	{
		String pathToFxmlForm =  "/views/fxmls/MainForm.fxml";
		this.setLangResources( ResourceBundle.getBundle("internationalization/menuDef") );
        this.setMainViewGridPane((GridPane) FXMLLoader.load(	getClass().getResource(pathToFxmlForm),  this.getLangResources()));		
		this.setTitle("Boquet-manager for sattelite tv channels");
		mainViewScene = new Scene(getMainViewGridPane());
		
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

}
