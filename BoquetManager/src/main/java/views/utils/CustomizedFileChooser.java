package views.utils;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomizedFileChooser extends Application
{
	private BorderPane fileChooserViewBorderPane;
	private String title;
	private Scene mainViewScene;
	
	public CustomizedFileChooser() throws IOException{
		
		String pathToFxmlForm = "/views/fxmls/utils/CustomizedFileChooser.fxml";

		this.setFileChooserViewBorderPane( (BorderPane) FXMLLoader.load(
					getClass().getResource(pathToFxmlForm)));
			this.setTitle("Alexander customized FileChooser");
			mainViewScene = new Scene(this.getFileChooserViewBorderPane());
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage customizedFileChooserMainStage) throws Exception
	{
		customizedFileChooserMainStage.setTitle(this.getTitle());
		customizedFileChooserMainStage.setScene(this.getMainViewScene());
		customizedFileChooserMainStage.show();
	}

	public BorderPane getFileChooserViewBorderPane()
	{
		return fileChooserViewBorderPane;
	}

	public void setFileChooserViewBorderPane(BorderPane fileChooserViewBorderPane)
	{
		this.fileChooserViewBorderPane = fileChooserViewBorderPane;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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
