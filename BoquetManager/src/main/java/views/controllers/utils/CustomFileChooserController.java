package views.controllers.utils;


import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.validators.ValidateXmlFile;
import views.controllers.mainmenu.MainMenuController;
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
	
	private MainMenuController mainMenuController;
	
	List<DiskModelForCustomFileChooser> listOfFileSystemItems;
    TreeItem<DiskModelForCustomFileChooser> rootTreeItem;
    String diskSelectedByUser;
    String diskRoot;
    
	@FXML
	private void initialize()
	{		
		createRootTreeItem();
		createAndSetupRoots();
		setupViewForColumn();
		
		cancelBtn.setOnAction((event) -> {
			closeCustomFileChooser();
		});
		
		okBtn.setOnAction((event) -> {
			String inputPathToFile = getTxtFieldDefaultPath().getText();
			ValidateXmlFile validateXml= new ValidateXmlFile();
			String validFilePattern ="application/xml";
			String expectedPatternOfInputFile =validateXml.validateInputXmlFile(inputPathToFile);
			
			validateFileGivenByTheUser(inputPathToFile, validFilePattern, expectedPatternOfInputFile);
		});
		
		txtFieldDefaultPath.setOnMouseClicked((event) -> {
			String currentValueInTxtFieldDefPath = txtFieldDefaultPath.getText();			
			String defaultText="... type here the path to xml file";
			checkIfTxtFieldDefPathIsNotEmpty(currentValueInTxtFieldDefPath, defaultText);
		});
		
		txtFieldDefaultPath.textProperty().addListener((observable, oldValue, newValue) -> {
			//System.out.println("TextField Text Changed (newValue: " + newValue + ")");		
			
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

	private void validateFileGivenByTheUser(String inputPathToFile, String validFilePattern,
			String expectedPatternOfInputFile)
	{
		if (validFilePattern.equals(expectedPatternOfInputFile)) {
			Path pathToFile = insertValidXmlToMainController(inputPathToFile);
			checkIfMainControllerExistAndUpdateMainController(pathToFile);
			closeCustomFileChooser();
		}else{		
		   displayAlertMessageIfUserOpenedWrongFile();
		}
	}

	private void updateMainViewLoadedFileMsg(Path pathToFile)
	{
		getMainMenuController().getFilesLoadStateLbl().setText( "File " + pathToFile.getFileName().toString() + " is loaded!" );		
		getMainMenuController().getFilesLoadStateLbl().setTextFill(Color.BLUE);
	}

	private Path insertValidXmlToMainController(String inputPathToFile)
	{
		Path pathToFile = Paths.get(inputPathToFile);
		//System.out.println("writing file to main controller:  "+ inputPathToFile);
		checkIfMainControllerExistAndAddValidXml(inputPathToFile);
		return pathToFile;
	}

	private void checkIfMainControllerExistAndAddValidXml(String inputPathToFile)
	{
		if (getMainMenuController() != null)
		{
			getMainMenuController().addValidXmlFileToList(inputPathToFile);
		}
	}
	
	private void checkIfMainControllerExistAndUpdateMainController(Path pathToFile)
	{
		if (getMainMenuController() != null)
		{
		  updateMainViewLoadedFileMsg(pathToFile);
		}
	}

	private void displayAlertMessageIfUserOpenedWrongFile()
	{
		Alert alertMessage = initAlertMessage("Wrong file!!!", "Please open xml only file!!");
		   FlowPane fp = setUpPaneForMessage();
		   fp.setStyle("-fx-font-weight: bold");
		   showToUserAllertMessage(alertMessage,fp);
	}
	private void showToUserAllertMessage(Alert InfoMessage, FlowPane fp)
	{
		InfoMessage.getDialogPane().contentProperty().set(fp);
		InfoMessage.showAndWait();
	}
	
	private Alert initAlertMessage(String titleOfAlertMessage, String titleOfAlerMessageHeader)
	{
		Alert InfoMessage = new Alert(AlertType.WARNING);
		InfoMessage.setTitle(titleOfAlertMessage);
		InfoMessage.setHeaderText(titleOfAlerMessageHeader);
		return InfoMessage;
	}
	
	private FlowPane setUpPaneForMessage()
	{
		FlowPane fp = new FlowPane();

		return fp;
	}
	
	private void handleForDoubleClickMouseEvent(MouseEvent mouseEvent)
	{
		if (mouseEvent.getClickCount() == 2)
		{
			System.out.println("Double clicked on ");
			String patternForRoot = "Following disks are available";
			TreeItem<DiskModelForCustomFileChooser> pointerToRoot = 
					fileChooseTreeTablesView.getSelectionModel()
					.selectedItemProperty().get();
						
			findingRootElement(patternForRoot, pointerToRoot);
			String dirPath = buildingPathFromSelectionToRoot(patternForRoot);
			System.out.println("path after build path "+dirPath);
			Integer positionOfLastSlash = dirPath.length();
			dirPath = cutSlashAtGivenPath(dirPath, positionOfLastSlash);
			System.out.println("new path "+dirPath );
			setTextFieldAndInitTableView(dirPath);
			
		}
	}	
	
	private void findingRootElement(String patternForRoot, TreeItem<DiskModelForCustomFileChooser> pointerToRoot)
	{
		while (pointerToRoot.getValue().getFileSystemItem().equals(patternForRoot) == false)
		{
		    setDiskRoot(pointerToRoot.getValue().getFileSystemItem());
		    pointerToRoot = pointerToRoot.getParent();				
		    if (pointerToRoot == null)
			{
				break;
			}
		}

		System.out.println("root Disk " + getDiskRoot());
	}
	
	private String buildingPathFromSelectionToRoot(String patternForRoot)
	{
		TreeItem<DiskModelForCustomFileChooser> pointerFromUserSelection  = 
				fileChooseTreeTablesView.getSelectionModel()	.selectedItemProperty().get();
			String dirPath= new String();


		while( pointerFromUserSelection != null) {
			dirPath = analizeifRootAlreadyInPath(patternForRoot, pointerFromUserSelection, dirPath);
			pointerFromUserSelection = pointerFromUserSelection.getParent();
		}
		
		return dirPath;
	}
	
	private String analizeifRootAlreadyInPath(String patternForRoot, TreeItem<DiskModelForCustomFileChooser> pointerFromUserSelection,
			String dirPath)
	{

		String windowsSlash="\\";
		if (pointerFromUserSelection.getValue().getFileSystemItem().contains(patternForRoot) == false)
		{	
			dirPath = pointerFromUserSelection.getValue().getFileSystemItem() + windowsSlash+dirPath;
			dirPath = dirPath.replace(":\\\\",":\\");
			
			System.out.println("res " + dirPath);
		}

		return dirPath;
	}
	
	private String cutSlashAtGivenPath(String dirPath, Integer positionOfLastSlash)
	{
		if (positionOfLastSlash > 3)
		{
			dirPath = dirPath.substring(0, positionOfLastSlash - 1);
		}
		return dirPath;
	}
	
	private void setTextFieldAndInitTableView(String dirPath)
	{
		getTxtFieldDefaultPath().setText(dirPath);
		setUpForValuesForViewAndInitOfIt(dirPath);
		fileChooseTreeTablesView.getSelectionModel().selectedItemProperty().get().setExpanded(true);
	}
	
	private void setUpForValuesForViewAndInitOfIt(String dirPath)
	{
		boolean expandedDirStatus =
				fileChooseTreeTablesView.getSelectionModel().selectedItemProperty().get().isExpanded();
		boolean userSelectionHasLeaf = 
				fileChooseTreeTablesView.getSelectionModel().selectedItemProperty().get().isLeaf();
		getFsItemsFromDisk(dirPath, expandedDirStatus, userSelectionHasLeaf);
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
		addFsItemsToDiskListModel(fsDirsList, fsDirsLastModifiedDates, listOfFs);
		addFsItemsToDiskListModel(fsFilesList, fsFilesLastModifiedDates, listOfFs);	

       this.setListOfFileSystemItems(listOfFs);		
       addFsItemsFromDiskModelToTheGivenRoot();

	}

	private void addFsItemsToDiskListModel(ArrayList<String> fsDirsList, ArrayList<String> fsDirsLastModifiedDates,
			List<DiskModelForCustomFileChooser> listOfFs)
	{
		for (int i = 0; i < fsDirsList.size(); i++)
		{
			listOfFs.add( new DiskModelForCustomFileChooser (fsDirsList.get(i),fsDirsLastModifiedDates.get(i) ));
		}
	}

	private void addFsItemsFromDiskModelToTheGivenRoot()
	{
		this.setRootTreeItem(fileChooseTreeTablesView.getSelectionModel()
				.selectedItemProperty().get()); 
		List <DiskModelForCustomFileChooser> diskFsItems = this.getListOfFileSystemItems();
		   for (DiskModelForCustomFileChooser diskModelForCustomFileChooser : diskFsItems)
			{
				this.getRootTreeItem().getChildren().add(
						new TreeItem<DiskModelForCustomFileChooser>(diskModelForCustomFileChooser)
				);
			}
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

	public String getDiskRoot()
	{
		return diskRoot;
	}

	public void setDiskRoot(String diskRoot)
	{
		this.diskRoot = diskRoot;
	}

	public void injectMainController(MainMenuController inputMainMenuController)
	{
	    setMainMenuController(inputMainMenuController); 	
	}

	public MainMenuController getMainMenuController()
	{
		return mainMenuController;
	}

	public void setMainMenuController(MainMenuController mainMenuController)
	{
		this.mainMenuController = mainMenuController;
	}

	public void injectMainMenuController(MainMenuController inputMainController)
	{
		setMainMenuController(inputMainController);
	}

	
}