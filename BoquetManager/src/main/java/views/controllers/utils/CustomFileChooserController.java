package views.controllers.utils;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.controllers.utils.model.DiskModelForCustomFileChooser;
import views.utils.filesystemtools.FileSystemWalker;

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
	TreeTableColumn<DiskModelForCustomFileChooser,String> treeTableColFilesystem;
	
	@FXML
	TreeTableColumn<DiskModelForCustomFileChooser,String>  treeTableColDate;
	
	@FXML
	TreeTableView<DiskModelForCustomFileChooser> fileChooseTreeTablesView;
	
	@FXML
	Label lblDiskAmount;
	
	List<DiskModelForCustomFileChooser> listOfFileSystemItems;
    TreeItem<DiskModelForCustomFileChooser> rootTreeItem;
    String diskSelectedByUser;
    
	public CustomFileChooserController()
	{

	}

	@FXML
	private void initialize()
	{		
		createRootTreeItem();
		createAndSetupRoots();
		setupViewForColumn();
		
		cancelBtn.setOnAction((event) -> {
			closeCustomFileChooser();
		});
		
		txtFieldDefaultPath.setOnMouseClicked((event) -> {
			String currentValueInTxtFieldDefPath = txtFieldDefaultPath.getText();			
			String defaultText="... type here the path to xml file";
			checkIfTxtFieldDefPathIsNotEmpty(currentValueInTxtFieldDefPath, defaultText);
		});
		
		txtFieldDefaultPath.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("TextField Text Changed (newValue: " + newValue + ")");		
			
			ObservableList<TreeItem<DiskModelForCustomFileChooser>> listOfDisks = 
				this.fileChooseTreeTablesView.getRoot().getChildren();
			userSelectOneOfDisks(newValue, listOfDisks);

		});
		
		this.fileChooseTreeTablesView.getSelectionModel().selectedItemProperty().addListener(new DiskSelector(this));
		
		this.fileChooseTreeTablesView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent mouseEvent)
			{
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
				{
					handleForDoubleClickMouseEvent(mouseEvent);
				}
			}

		});
			
	}

	private void handleForDoubleClickMouseEvent(MouseEvent mouseEvent)
	{
		if (mouseEvent.getClickCount() == 2)
		{
			System.out.println("Double clicked on ");
			String patternForRoot = "Following disks are available";
			TreeItem<DiskModelForCustomFileChooser> pointerToRoot = fileChooseTreeTablesView.getSelectionModel()
					.selectedItemProperty().get();
		}
	}	
	
	private void userSelectOneOfDisks(String newValue, ObservableList<TreeItem<DiskModelForCustomFileChooser>> listOfDisks)
	{
		for (TreeItem<DiskModelForCustomFileChooser> treeItem : listOfDisks)
		{
			if (treeItem.getValue().getFileSystemItem().equals(newValue))
			{
				// select item in the list
				System.out.println("user select through input TextField " + treeItem.getValue().getFileSystemItem());
				this.setDiskSelectedByUser( treeItem.getValue().getFileSystemItem()) ;
				DiskSelector userSpecifiedSelection = new DiskSelector(this);
				userSpecifiedSelection.selectDiskByTextFieldInput(treeItem.getValue().getFileSystemItem(), 
						this.fileChooseTreeTablesView);
			}
		}
	}
	
	private void  createRootTreeItem() {
		String defaultText= "Following disks are available";
		 TreeItem<DiskModelForCustomFileChooser> currentItem = 
	       new TreeItem<DiskModelForCustomFileChooser>(
	    		   new DiskModelForCustomFileChooser(defaultText,"") );
		this.setRootTreeItem(currentItem);
		this.getRootTreeItem().setExpanded(true);
	}
	
	private void createAndSetupRoots () {
		FileSystem fileSystemObj = FileSystems.getDefault();
		Integer countDiskAmount = 0;
		for (Path root : fileSystemObj.getRootDirectories())
		{ 
			this.getRootTreeItem().getChildren().add(new TreeItem<DiskModelForCustomFileChooser>
			               (new DiskModelForCustomFileChooser(root.toString(),"") )
					);
			countDiskAmount++;
		}

		this.getLblDiskAmount().setText("Disks amount: " +countDiskAmount.toString());;
	}
	
	private void setupViewForColumn() {
		this.getTreeTableColFilesystem().setCellValueFactory((
	            TreeTableColumn.CellDataFeatures<DiskModelForCustomFileChooser, String> param) -> 
		new ReadOnlyStringWrapper( param.getValue().getValue().getFileSystemItem() ) 
		);
	            		
		this.getTreeTableColDate().setCellValueFactory((
	            TreeTableColumn.CellDataFeatures<DiskModelForCustomFileChooser, String> param) -> 
		new ReadOnlyStringWrapper( param.getValue().getValue().getLastModifiedDateForFileSystemItem() ) 
		);        		
		
		this.getFileChooseTreeTablesView().setRoot(this.getRootTreeItem());
		
	}
	
	
	private void getFsItemsFromDisk(String dirPath, boolean expandedDirStatus,
			boolean userSelectionHasLeaf)
	{
		if (expandedDirStatus== false && userSelectionHasLeaf == true)
		{
			FileSystemWalker walkerFs = new FileSystemWalker();
			ArrayList<String> fsDirsList = walkerFs.getDirectoriesList(dirPath);
			ArrayList<String> fsLastModifiedDatesForDirs = walkerFs.getLastModifiedAttributes(dirPath,
					fsDirsList);
			ArrayList<String> fsFilesList = walkerFs.getFilesList(dirPath);
			ArrayList<String> fsLastModifiedDatesForFiles = walkerFs.getLastModifiedAttributes(dirPath,
					fsFilesList);

			createListOfFilesSystemItems(fsDirsList,fsFilesList,fsLastModifiedDatesForDirs, fsLastModifiedDatesForFiles );

			fsDirsList.clear();
			fsLastModifiedDatesForDirs.clear();
			fsFilesList.clear();
			fsLastModifiedDatesForFiles.clear();
		}
	}
	
	private void createListOfFilesSystemItems(ArrayList<String> fsDirsList, ArrayList<String> fsFilesList,
			ArrayList<String> fsDirsLastModifiedDates, ArrayList<String> fsFilesLastModifiedDates
			)
	{
	    List<DiskModelForCustomFileChooser> listOfFs= new ArrayList<DiskModelForCustomFileChooser>();
		for (int i = 0; i < fsDirsList.size(); i++)
		{
			listOfFs.add( new DiskModelForCustomFileChooser (fsDirsList.get(i),fsDirsLastModifiedDates.get(i) ));
		}
		for (int i = 0; i < fsFilesList.size(); i++)
		{
			listOfFs.add( new DiskModelForCustomFileChooser(fsFilesList.get(i) ,fsFilesLastModifiedDates.get(i) ));
		}	

       this.setListOfFileSystemItems(listOfFs);		
       
//		FileChooseTreeTablesView.getSelectionModel().selectedItemProperty().get().getChildren()
//		.addAll(fileNameTreeItemList);
//		FileChooseTreeTablesView.getSelectionModel().selectedItemProperty().get().getChildren()
//		.addAll( fileLastUpdateTreeItemList);
		
	}
	
	private void checkIfTxtFieldDefPathIsNotEmpty(String currentValueInTxtFieldDefPath, String defaultText)
	{
		if (  !(currentValueInTxtFieldDefPath.equals(defaultText) )
				&& (currentValueInTxtFieldDefPath.isEmpty() == false) && 
				!(currentValueInTxtFieldDefPath.equals(txtFieldDefaultPath.getText())) ) {
			txtFieldDefaultPath.setText(currentValueInTxtFieldDefPath);
		}else {			
			if(currentValueInTxtFieldDefPath.equals(defaultText)) {
			   txtFieldDefaultPath.setText("");
			}
		}
	}
	
	private void closeCustomFileChooser()
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

	public Label getLblTitle()
	{
		return lblTitle;
	}

	public void setLblTitle(Label lblTitle)
	{
		this.lblTitle = lblTitle;
	}

	public TreeTableColumn<DiskModelForCustomFileChooser, String> getTreeTableColFilesystem()
	{
		return treeTableColFilesystem;
	}

	public void setTreeTableColFilesystem(TreeTableColumn<DiskModelForCustomFileChooser, String> treeTableColFilesystem)
	{
		this.treeTableColFilesystem = treeTableColFilesystem;
	}

	public TreeTableColumn<DiskModelForCustomFileChooser, String> getTreeTableColDate()
	{
		return treeTableColDate;
	}

	public void setTreeTableColDate(TreeTableColumn<DiskModelForCustomFileChooser, String> treeTableColDate)
	{
		this.treeTableColDate = treeTableColDate;
	}

	public TreeTableView<DiskModelForCustomFileChooser> getFileChooseTreeTablesView()
	{
		return fileChooseTreeTablesView;
	}

	public void setFileChooseTreeTablesView(TreeTableView<DiskModelForCustomFileChooser> fileChooseTreeTablesView)
	{
		this.fileChooseTreeTablesView = fileChooseTreeTablesView;
	}

	public Label getLblDiskAmount()
	{
		return lblDiskAmount;
	}

	public void setLblDiskAmount(Label lblDiskAmount)
	{
		this.lblDiskAmount = lblDiskAmount;
	}

	public List<DiskModelForCustomFileChooser> getListOfFileSystemItems()
	{
		return listOfFileSystemItems;
	}

	public void setListOfFileSystemItems(List<DiskModelForCustomFileChooser> listOfFileSystemItems)
	{
		this.listOfFileSystemItems = listOfFileSystemItems;
	}

	public TreeItem<DiskModelForCustomFileChooser> getRootTreeItem()
	{
		return rootTreeItem;
	}

	public void setRootTreeItem(TreeItem<DiskModelForCustomFileChooser> rootTreeItem)
	{
		this.rootTreeItem = rootTreeItem;
	}

	public String getDiskSelectedByUser()
	{
		return diskSelectedByUser;
	}

	public void setDiskSelectedByUser(String diskSelectedByUser)
	{
		this.diskSelectedByUser = diskSelectedByUser;
	}

	
}