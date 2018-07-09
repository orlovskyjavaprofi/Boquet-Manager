package views.controllers.mainmenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutBoquetManagerProjectController
{

	@FXML
	private Button OkButOfAboutPrj;

	@FXML
	private void initialize()
	{
		OkButOfAboutPrj.setOnAction((event) -> {
		    closeAboutProjectWindow();
		});
	}

	private void closeAboutProjectWindow()
	{
		Stage currentStage = (Stage) OkButOfAboutPrj.getScene().getWindow();
		currentStage.close();
	}
	
	public Button getOkButOfAboutPrj()
	{
		return OkButOfAboutPrj;
	}

	public void setOkButOfAboutPrj(Button okButOfAboutPrj)
	{
		OkButOfAboutPrj = okButOfAboutPrj;
	}

}
