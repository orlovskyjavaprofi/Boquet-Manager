package viewtests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import views.utils.CustomizedFileChooser;

class customizedFileChooserTest extends ApplicationTest
{
	private CustomizedFileChooser customFileChooser;
	private String extractedTitle;

	@Override
	public void start(Stage stage) throws Exception
	{
		customFileChooser = new CustomizedFileChooser();
		stage.setScene(customFileChooser.getMainViewScene());
		stage.setTitle(customFileChooser.getTitle());
		extractedTitle = stage.getTitle();

		stage.show();
		stage.toFront();
	}

	@Test
	void testingIfMainViewObjectExist() throws IOException
	{
		assertNotNull(customFileChooser);
	}

	@Test
	void displaylayoutForm()
	{
		verifyThat("#CustomFileChooserBorderPane", isVisible());
		clickOn("#cancelBtn");
	}
	
	@Test
	void checkIfTheWindowClosedAfterCancselWasClicked() {	
		clickOn("#cancelBtn");
		verifyThat("#CustomFileChooserBorderPane", isNull() );
	}
	
	//This test is os dependent, if you use other os adjust for you os!
	@Test 
	void checkIfDiskWasSelected() {
	    clickOn("#txtFieldDefaultPath").write("C:\\");
		verifyThat("#lblSelectedFile", hasText("Selected!"));
	}
	
	
}
