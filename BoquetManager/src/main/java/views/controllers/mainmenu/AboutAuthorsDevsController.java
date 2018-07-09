package views.controllers.mainmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AboutAuthorsDevsController  extends Application
{
	@FXML
	private Button OkButOfAboutAuthors;

	@FXML
	private Hyperlink  hyperlinkToAlexanderLinkedin;
	
	@FXML
	private void initialize()
	{
		OkButOfAboutAuthors.setOnAction((event) -> {
		    closeAboutAuthorsWindow();
		});
		
		hyperlinkToAlexanderLinkedin.setOnAction((event) -> {
			openAlexanderLinkedinProfile(event);
		});
	}
	
	private void openAlexanderLinkedinProfile(ActionEvent event)
	{
		String linkedinAlexPage = "www.linkedin.com/in/alexanderorlovsky";
		String ContactAdress= "http://orlovskyjavaprofi.github.io/Boquet-Manager/Contact.html";
		openSuppPageForAlexLinkedin(event, linkedinAlexPage);
		String titleAlertMsg = "Open Alexander Linkedin profile";
		String titleOfAlertMsgHeader = "Alexander Linkedin profile";
		String linkedinAlexanderProfile = "Alexander linkedin profile";
		Alert messageAboutAlexanderLinkedin = initAlertMessage(titleAlertMsg, titleOfAlertMsgHeader);
		FlowPane fp = setUpPaneAndTextMsgForAlerMsg(ContactAdress, linkedinAlexanderProfile);

		showToUserAllertMessage(messageAboutAlexanderLinkedin, fp);
	}
	
	private void showToUserAllertMessage(Alert infoAlexLinkedinMessage, FlowPane fp)
	{
		infoAlexLinkedinMessage.getDialogPane().contentProperty().set(fp);
		infoAlexLinkedinMessage.showAndWait();
	}
	
	private FlowPane setUpPaneAndTextMsgForAlerMsg(String ContactAdress, String profile)
	{
		String textMessage = "Your browser opened a website with "+profile 
				+"  page!\n"
				+ "If no Alexander linkedin profile page was opened,\nthen if you like send a short email to the developer " + " Alexander !";
		FlowPane fp = createHyperLinkAndAddTextToAlertMsg(ContactAdress, textMessage);
		fp.setStyle("-fx-font-weight: bold");
		return fp;
	}

	private FlowPane createHyperLinkAndAddTextToAlertMsg(String ContactAdress, String textMessage)
	{
		Hyperlink link = new Hyperlink();
		link.setText("Contact developer in case when new browser window wasn't opened!");
		FlowPane fp = new FlowPane();
		Label lbl = new Label(textMessage);
		fp.getChildren().addAll(lbl, link);
		
		link.setOnAction((evn) -> {
			openSuppPageForAlexLinkedin(evn, ContactAdress);
		});
		return fp;
	}

	private void openSuppPageForAlexLinkedin(ActionEvent event, String pathToSuppPage)
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
	
	private void openSupportWebPage(ActionEvent actionEvent, String inputPathWebAdr) throws Exception
	{
		getHostServices().showDocument(inputPathWebAdr);
	}
	
	private Alert initAlertMessage(String titleOfAlertMessage, String titleOfAlerMessageHeader)
	{
		Alert InfoMessage = new Alert(AlertType.INFORMATION);
		InfoMessage.setTitle(titleOfAlertMessage);
		InfoMessage.setHeaderText(titleOfAlerMessageHeader);
		return InfoMessage;
	}
	
	private void closeAboutAuthorsWindow()
	{
		Stage currentStage = (Stage) OkButOfAboutAuthors.getScene().getWindow();
		currentStage.close();
	}
	
	public Button getOkButOfAboutAuthors()
	{
		return OkButOfAboutAuthors;
	}

	public void setOkButOfAboutAuthors(Button okButOfAboutAuthors)
	{
		OkButOfAboutAuthors = okButOfAboutAuthors;
	}

	public Hyperlink getHyperlinkToAlexanderLinkedin()
	{
		return hyperlinkToAlexanderLinkedin;
	}

	public void setHyperlinkToAlexanderLinkedin(Hyperlink hyperlinkToAlexanderLinkedin)
	{
		this.hyperlinkToAlexanderLinkedin = hyperlinkToAlexanderLinkedin;
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
	}
	
	
	
	
}
