package views.controllers.mainmenu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BtcDonationFormForAlexController
{
	@FXML
	private BorderPane BtcDonationForAlexBorderPane;

	@FXML
	private Label BtcDonationInformationLbl;

	@FXML
	private Label BtcQrtextLbl;

	@FXML
	private Label BtcWalletAlexLbl;

	@FXML
	private TextField btcWalletIdTxtField;

	@FXML
	private Button btcDonationOkBtn;

	@FXML
	private void initialize()
	{
		btcDonationOkBtn.setOnAction((event) -> {
             showThankYouMsg();
			
			closeSupportProjectWindow();
		});
	}

	private void showThankYouMsg()
	{
		String titleAlertMsg = "Thank you!";
		String titleOfAlertMsgHeader = "Thank you for your BTC donation!";
		Alert donateInfoMessage = initAlertMessage(titleAlertMsg, titleOfAlertMsgHeader);
		showToUserAllertMessage(donateInfoMessage);
	}

	private Alert initAlertMessage(String titleOfAlertMessage, String titleOfAlerMessageHeader)
	{
		Alert donateInfoMessage = new Alert(AlertType.INFORMATION);
		donateInfoMessage.setTitle(titleOfAlertMessage);
		donateInfoMessage.setHeaderText(titleOfAlerMessageHeader);
		return donateInfoMessage;
	}
	
	private void showToUserAllertMessage(Alert donateInfoMessage)
	{
		donateInfoMessage.showAndWait();
	}
	
	private void closeSupportProjectWindow()
	{
		Stage currentStage = (Stage) btcDonationOkBtn.getScene().getWindow();
		currentStage.close();
	}
	
	
	
	public BorderPane getBtcDonationForAlexBorderPane()
	{
		return BtcDonationForAlexBorderPane;
	}

	public void setBtcDonationForAlexBorderPane(BorderPane btcDonationForAlexBorderPane)
	{
		BtcDonationForAlexBorderPane = btcDonationForAlexBorderPane;
	}

	public Label getBtcDonationInformationLbl()
	{
		return BtcDonationInformationLbl;
	}

	public void setBtcDonationInformationLbl(Label btcDonationInformationLbl)
	{
		BtcDonationInformationLbl = btcDonationInformationLbl;
	}

	public Button getBtcDonationOkBtn()
	{
		return btcDonationOkBtn;
	}

	public void setBtcDonationOkBtn(Button btcDonationOkBtn)
	{
		this.btcDonationOkBtn = btcDonationOkBtn;
	}

}
