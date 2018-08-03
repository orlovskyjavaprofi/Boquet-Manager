package views.controllers.utils;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomFileChooserController  
{
	@FXML
	BorderPane CustomFileChooserBorderPane;

	@FXML
	Button cancelBtn;
	
	@FXML
	Button okBtn;
	
	@FXML
	TextField txtFieldDefaultPath;
	
	@FXML
	Label lblSelectedFile;
	
	@FXML
	Label lblTitle;
	
	@FXML
	TreeTableColumn<String,String> treeTableColFilesystem;
	
	@FXML
	TreeTableView<String> FileChooseTreeTablesView;
	
	@FXML
	Label lblDiskAmount;
	
	Integer diskAmountResult;
	
	TreeItem<String> rootNodeOfFs;
	
	public CustomFileChooserController() {
		
		FileSystem fileSystemObj = FileSystems.getDefault();
        this.setRootNodeOfFs( new TreeItem<>("Following disks are available"));
        Integer countDiskAmount = 0;
		for (Path root : fileSystemObj.getRootDirectories())
		{ 
			this.getRootNodeOfFs().getChildren().add(new TreeItem<>(root.toString()));
			countDiskAmount++;
		}
		this.setDiskAmountResult(countDiskAmount);
		
	}
	
	@FXML
	private void initialize()
	{
		cancelBtn.setOnAction((event) -> {
		    closeCustomerChooserWindow();
		});
	
		this.getRootNodeOfFs().setExpanded(true);
		this.getTreeTableColFilesystem().setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<String, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue()));
		
		this.getFileChooseTreeTablesView().setRoot(this.getRootNodeOfFs());
		this.getCountDiskAmount().setText("Disk amount: "+this.getDiskAmountResult() );
	}
	
	private void closeCustomerChooserWindow()
	{
		Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
		currentStage.close();
	}
	
	public BorderPane getCustomFileChooserBorderPane()
	{
		return CustomFileChooserBorderPane;
	}

	public void setCustomFileChooserBorderPane(BorderPane customFileChooserBorderPane)
	{
		CustomFileChooserBorderPane = customFileChooserBorderPane;
	}

	public Button getCancelBtn()
	{
		return cancelBtn;
	}

	public void setCancelBtn(Button cancelBtn)
	{
		this.cancelBtn = cancelBtn;
	}

	public Button getOkBtn()
	{
		return okBtn;
	}

	public void setOkBtn(Button okBtn)
	{
		this.okBtn = okBtn;
	}

	public TextField getTxtFieldDefaultPath()
	{
		return txtFieldDefaultPath;
	}

	public void setTxtFieldDefaultPath(TextField txtFieldDefaultPath)
	{
		this.txtFieldDefaultPath = txtFieldDefaultPath;
	}

	public Label getLblSelectedFile()
	{
		return lblSelectedFile;
	}

	public void setLblSelectedFile(Label lblSelectedFile)
	{
		this.lblSelectedFile = lblSelectedFile;
	}

	public TreeTableColumn<String, String> getTreeTableColFilesystem()
	{
		return treeTableColFilesystem;
	}

	public void setTreeTableColFilesystem(TreeTableColumn<String, String> treeTableColFilesystem)
	{
		this.treeTableColFilesystem = treeTableColFilesystem;
	}

	public TreeTableView<String> getFileChooseTreeTablesView()
	{
		return FileChooseTreeTablesView;
	}

	public void setFileChooseTreeTablesView(TreeTableView<String> fileChooseTreeTablesView)
	{
		FileChooseTreeTablesView = fileChooseTreeTablesView;
	}

	public Label getLblTitle()
	{
		return lblTitle;
	}

	public void setLblTitle(Label lblTitle)
	{
		this.lblTitle = lblTitle;
	}

	public TreeItem<String> getRootNodeOfFs()
	{
		return rootNodeOfFs;
	}

	public void setRootNodeOfFs(TreeItem<String> rootNodeOfFs)
	{
		this.rootNodeOfFs = rootNodeOfFs;
	}

	public Label getCountDiskAmount()
	{
		return lblDiskAmount;
	}

	public void setCountDiskAmount(Label countDiskAmount)
	{
		this.lblDiskAmount = countDiskAmount;
	}

	public Integer getDiskAmountResult()
	{
		return diskAmountResult;
	}

	public void setDiskAmountResult(Integer diskAmountResult)
	{
		this.diskAmountResult = diskAmountResult;
	}



}
