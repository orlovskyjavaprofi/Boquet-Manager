package views.controllers.mainmenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutLicenseController
{
	@FXML
	private Button okBtnForLicenseInfoWindow;

	@FXML
	private void initialize()
	{
		okBtnForLicenseInfoWindow.setOnAction((event) -> {
		    closeLicenseInfoWindow();
		});
	}

	private void closeLicenseInfoWindow()
	{
		Stage currentStage = (Stage) okBtnForLicenseInfoWindow.getScene().getWindow();
		currentStage.close();
	}

	public Button getOkBtnForLicenseInfoWindow()
	{
		return okBtnForLicenseInfoWindow;
	}

	public void setOkBtnForLicenseInfoWindow(Button okBtnForLicenseInfoWindow)
	{
		this.okBtnForLicenseInfoWindow = okBtnForLicenseInfoWindow;
	}

}
