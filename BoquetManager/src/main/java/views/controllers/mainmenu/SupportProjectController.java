package views.controllers.mainmenu;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SupportProjectController extends Application
{
    private BorderPane BtcFormPane;	
	
	@FXML
	private Button ThankYouForSupBtn;

	@FXML
	private Button AlexPaypalSupBtn;

	@FXML
	private Button AlexBtcSupBtn;

	@FXML
	private void initialize()
	{
		ThankYouForSupBtn.setOnAction((event) -> {
			closeSupportProjectWindow();
		});

		AlexPaypalSupBtn.setOnAction((event) -> {
			makePaypalDonation(event);
		});

		AlexBtcSupBtn.setOnAction((event) -> {	
			loadNewWindowSuppProject();
		});

	}

	private void makePaypalDonation(ActionEvent event)
	{
		String paypalLinkForAlex = "http://orlovskyjavaprofi.github.io/Boquet-Manager/DonateViaPaypal.html";
		String ContactAdress= "http://orlovskyjavaprofi.github.io/Boquet-Manager/Contact.html";
		openPaypalSuppPageForAlex(event, paypalLinkForAlex);
		String titleAlertMsg = "Paypal donation";
		String titleOfAlertMsgHeader = "Donate trough paypal!";
		String PaymentType = "paypal";
		Alert donateInfoMessage = initAlertMessage(titleAlertMsg, titleOfAlertMsgHeader);
		FlowPane fp = setUpPaneAndTextMsgForAlerMsg(ContactAdress, PaymentType);

		showToUserAllertMessage(donateInfoMessage, fp);
	}

	private void loadNewWindowSuppProject() {
		try
		{
			setUpSupportProjectPane();				
			String TitleForANewWindow = "Make a bitcoin donation! Thank You!";				
			Pane currentPane = this.getBtcFormPane();  
			setUpANewwindow(TitleForANewWindow, currentPane);
			
		} catch (IOException e)
		{
		     //exception with creation of support project window window
			e.printStackTrace();
		}
	}
	
	void setUpSupportProjectPane() throws IOException
	{
		String pathToXmlForm= "/views/fxmls/BtcDonationFormForAlex.fxml";				
		this.setBtcFormPane(
				(BorderPane) FXMLLoader.load(
						getClass().getResource( pathToXmlForm)));
	}
	
	private void setUpANewwindow(String TitleForANewWindow, Pane currentPane)
	{
		Scene anotherScn = new Scene( currentPane  );
		Stage stageForScn = new Stage();
		stageForScn.setTitle(TitleForANewWindow);
		stageForScn.setScene(anotherScn);
		stageForScn.show();
	}
	
	private FlowPane setUpPaneAndTextMsgForAlerMsg(String ContactAdress, String PaymentSys)
	{
		String textMessage = "Your browser  opened a website with "+PaymentSys 
				+" donation page!\n"
				+ "If no donation page was opened, then send a short email to the developer " + " Alexander !";
		FlowPane fp = createHyperLinkAndAddTextToAlertMsg(ContactAdress, textMessage);
		return fp;
	}

	private void showToUserAllertMessage(Alert donateInfoMessage, FlowPane fp)
	{
		donateInfoMessage.getDialogPane().contentProperty().set(fp);
		donateInfoMessage.showAndWait();
	}

	private FlowPane createHyperLinkAndAddTextToAlertMsg(String ContactAdress, String textMessage)
	{
		Hyperlink link = new Hyperlink();
		link.setText("contact developer in case when new browser window wasn't opened!");
		FlowPane fp = new FlowPane();
		Label lbl = new Label(textMessage);
		fp.getChildren().addAll(lbl, link);
		
		link.setOnAction((evn) -> {
			openPaypalSuppPageForAlex(evn, ContactAdress);
		});
		return fp;
	}

	private Alert initAlertMessage(String titleOfAlertMessage, String titleOfAlerMessageHeader)
	{
		Alert donateInfoMessage = new Alert(AlertType.INFORMATION);
		donateInfoMessage.setTitle(titleOfAlertMessage);
		donateInfoMessage.setHeaderText(titleOfAlerMessageHeader);
		return donateInfoMessage;
	}

	private void openPaypalSuppPageForAlex(ActionEvent event, String pathToSuppPage)
	{
		try
		{
			openSupportWebPage(event, pathToSuppPage);
		} catch (Exception e)
		{
			// Problem with opening of the donation page
			e.printStackTrace();
		}
	}

	private void closeSupportProjectWindow()
	{
		Stage currentStage = (Stage) ThankYouForSupBtn.getScene().getWindow();
		currentStage.close();
	}

	private void openSupportWebPage(ActionEvent actionEvent, String inputPathWebAdr) throws Exception
	{
		getHostServices().showDocument(inputPathWebAdr);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{

	}

	public Button getThankYouForSupBtn()
	{
		return ThankYouForSupBtn;
	}

	public void setThankYouForSupBtn(Button thankYouForSupBtn)
	{
		ThankYouForSupBtn = thankYouForSupBtn;
	}

	public Button getAlexPaypalSupBtn()
	{
		return AlexPaypalSupBtn;
	}

	public void setAlexPaypalSupBtn(Button alexPaypalSupBtn)
	{
		AlexPaypalSupBtn = alexPaypalSupBtn;
	}

	public Button getAlexBtcSupBtn()
	{
		return AlexBtcSupBtn;
	}

	public void setAlexBtcSupBtn(Button alexBtcSupBtn)
	{
		AlexBtcSupBtn = alexBtcSupBtn;
	}

	public BorderPane getBtcFormPane()
	{
		return BtcFormPane;
	}

	public void setBtcFormPane(BorderPane btcFormPane)
	{
		BtcFormPane = btcFormPane;
	}

}
