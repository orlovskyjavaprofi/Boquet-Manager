package views.controllers.mainmenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.Element;
import org.xml.sax.SAXException;
import builders.SatBouquetsBuilder;
import fileutils.ReadXmlAndCreateJdomWithAllBouquets;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import models.ProviderBetweenUiControllers;
import models.SatBoquetTvChannel;
import models.SatTvBoquet;
import models.UiModels.UiModelChannel;

public class FavoritesListController
{
	@FXML
	Button butCopySatTvChnfromOneSatTvToAnother;
	@FXML
	Button btnCreateNewFavGrp;	
	@FXML
	Button butChangeOrderOfSatTvGrp;
	@FXML
	Button btnRenameChn;	
	@FXML
	Button btnDelSatTvFavChn;	
	@FXML
	Button btnMarkSatTvFavChnForCopy;	
	@FXML
	TextField txtSearchSatTvChnInFavGroups;	
	@FXML
	Button btnSearchSatTvChnInGrp;
	@FXML
	Tab tabRootElemSatTvFavorites;
	@FXML
    TabPane tabPaneSatTvFavGrp;
	@FXML
	TableView<UiModelChannel> tblFavoritesSatTv;
	@FXML
	private TableColumn<UiModelChannel, String> colChnName;
	@FXML
	private TableColumn<UiModelChannel, String> colServiceID;
	@FXML
	private TableColumn<UiModelChannel, String> colTransponderId;
	@FXML
	private TableColumn<UiModelChannel, String> colTransponderOnid;
	@FXML
	private TableColumn<UiModelChannel, Integer> colSatPos;
	
	private boolean stateOfProvider;
	private ProviderBetweenUiControllers providerInstance;
	private String pathToBoquetsXMLFile;
	private SortedSet<SatTvBoquet> setOfSortedSatTvBoquets;
	
	@FXML
	private void initialize() {
				
	}

	public boolean checkProviderModelForPathToFavorites()
	{
		String validFilename = "bouquets.xml";
		boolean result = false;
		List<String> pathToXmlFilesList = getProviderInstance().getPathsOfValidXmlFiles();
		
		for (String inputPathToXml : pathToXmlFilesList)
		{		
			result = checkIfGivenFileNameForServicesIsAValidOne(validFilename, result, inputPathToXml);		
		}
		
		return result;
	}
	
	private boolean checkIfGivenFileNameForServicesIsAValidOne(String validFilename, boolean result,
			String inputPathToXml)
	{
		if(inputPathToXml.contains(validFilename)) {
//			System.out.print(inputPathToXml);
			setPathToBoquetsXMLFile(inputPathToXml);
			result = true;
		}
		return result;
	}
		
	public void buildingSatTvBoquetsAndPopulateUiOfFavorites()
	{		
		setStateOfProvider(statusOfLoadedDataFromMainController());
		Integer counter = 0;
		if (getStateOfProvider() == true)
		{			
			setStateOfProvider( checkProviderModelForPathToBoquets());
			if (getStateOfProvider() == true)
			{
				ReadXmlAndCreateJdomWithAllBouquets helperObj =
						new ReadXmlAndCreateJdomWithAllBouquets(getPathToBoquetsXMLFile());

				List<UiModelChannel> tempListofSatTvChn= new ArrayList<UiModelChannel>();
				tryToBuildAndDisplayRightView(counter, helperObj, tempListofSatTvChn);
			}
		}
	}

	private void tryToBuildAndDisplayRightView(Integer counter, ReadXmlAndCreateJdomWithAllBouquets helperObj,
			List<UiModelChannel> tempListofSatTvChn)
	{
		try
		{
			buildAsetOfSatTvBoquets(helperObj);						

			for (SatTvBoquet satTvBoquet : getSetOfSortedSatTvBoquets())
			{
				counter = generateViewForRightPartOfMainWindow(counter, tempListofSatTvChn, satTvBoquet);
			}
            getSetOfSortedSatTvBoquets().clear(); //will be used for future features!
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Integer generateViewForRightPartOfMainWindow(Integer counter, List<UiModelChannel> tempListofSatTvChn,
			SatTvBoquet satTvBoquet)
	{

		if (counter == 0)
		{
			generateViewForFirstTab(tempListofSatTvChn, satTvBoquet);
			counter++;
		} else
		{
			generateViewForNextTab(tempListofSatTvChn, satTvBoquet);
			counter++;
		}
		
		return counter;
	}

	private void generateViewForNextTab(List<UiModelChannel> tempListofSatTvChn, SatTvBoquet satTvBoquet)
	{
		ObservableList<UiModelChannel> observableTempListOfUiModel;
		
		TableView<UiModelChannel> tempTblFavoritesSatTv;
		Tab tempTab;
		TableColumn<UiModelChannel, String> tempColChnName;
		TableColumn<UiModelChannel, String> tempColServiceID;
		TableColumn<UiModelChannel, String> tempColTransponderId;
		TableColumn<UiModelChannel, String> tempColTransponderOnid;
		TableColumn<UiModelChannel, Integer> tempColSatPos;
		tempTab = new Tab(satTvBoquet.getSatTvBoquetName());
		tempTblFavoritesSatTv = new TableView<UiModelChannel>();
		tempColChnName = setUpTempColChnName();
		tempColServiceID = setUpTempColServicesId();							
		tempColTransponderId = setUpTempColTransponderId();
		tempColTransponderOnid = setUpColTransponderOnid();
		tempColSatPos = setUpColSatPos();
		
		tempTblFavoritesSatTv.getColumns().setAll(
				tempColChnName,tempColServiceID,tempColTransponderId,
				tempColTransponderOnid,tempColSatPos);
								
		createListOfSatTvChn(tempListofSatTvChn, satTvBoquet);
		observableTempListOfUiModel = createObservableTempList(tempListofSatTvChn, tempColChnName,
				tempColServiceID, tempColTransponderId, tempColTransponderOnid, tempColSatPos);
		
		tempTblFavoritesSatTv.setItems(observableTempListOfUiModel);
		tempTab.setContent(tempTblFavoritesSatTv);
		getTabPaneSatTvFavGrp().getTabs().add(tempTab);
		cleanUpLists(tempListofSatTvChn);
	}

	private void generateViewForFirstTab(List<UiModelChannel> tempListofSatTvChn, SatTvBoquet satTvBoquet)
	{
		ObservableList<UiModelChannel> observableListOfUiModel;
		getTabRootElemSatTvFavorites().setText(satTvBoquet.getSatTvBoquetName());
		observableListOfUiModel = setUpViewOfSatTvChannels(tempListofSatTvChn, satTvBoquet);							
		getTblFavoritesSatTv().setItems(observableListOfUiModel);
		cleanUpLists(tempListofSatTvChn);
	}

	private ObservableList<UiModelChannel> createObservableTempList(List<UiModelChannel> tempListofSatTvChn,
			TableColumn<UiModelChannel, String> tempColChnName, TableColumn<UiModelChannel, String> tempColServiceID,
			TableColumn<UiModelChannel, String> tempColTransponderId,
			TableColumn<UiModelChannel, String> tempColTransponderOnid,
			TableColumn<UiModelChannel, Integer> tempColSatPos)
	{
		ObservableList<UiModelChannel> observableTempListOfUiModel;
		observableTempListOfUiModel = FXCollections.observableArrayList(tempListofSatTvChn);
		createViewForChnName(tempColChnName);
		createViewForServiceId(tempColServiceID);
		createViewForTransponderId(tempColTransponderId);
		createViewForTransponderOnid(tempColTransponderOnid);
		createViewForSatPosition(tempColSatPos);
		return observableTempListOfUiModel;
	}

	private TableColumn<UiModelChannel, Integer> setUpColSatPos()
	{
		TableColumn<UiModelChannel, Integer> tempColSatPos;
		tempColSatPos = new TableColumn<UiModelChannel, Integer>();
		tempColSatPos.setText("Sat position");
		tempColSatPos.setMinWidth(getColSatPos().getMinWidth());
		tempColSatPos.setPrefWidth(getColSatPos().getPrefWidth());
		tempColSatPos.setMaxWidth(getColSatPos().getMaxWidth());
		return tempColSatPos;
	}

	private TableColumn<UiModelChannel, String> setUpColTransponderOnid()
	{
		TableColumn<UiModelChannel, String> tempColTransponderOnid;
		tempColTransponderOnid = new TableColumn<UiModelChannel, String>();
		tempColTransponderOnid.setText("Transponder Onid");
		tempColTransponderOnid.setMinWidth(getColTransponderOnid().getMinWidth());
		tempColTransponderOnid.setPrefWidth(getColTransponderOnid().getPrefWidth());
		tempColTransponderOnid.setMaxWidth(getColTransponderOnid().getMaxWidth());
		return tempColTransponderOnid;
	}

	private TableColumn<UiModelChannel, String> setUpTempColTransponderId()
	{
		TableColumn<UiModelChannel, String> tempColTransponderId;
		tempColTransponderId = new TableColumn<UiModelChannel, String>();
		tempColTransponderId.setText("Transponder Id");
		tempColTransponderId.setMinWidth( getColTransponderId().getMinWidth());
		tempColTransponderId.setPrefWidth(getColTransponderId().getPrefWidth());
		tempColTransponderId.setMaxWidth(getColTransponderId().getMaxWidth());
		return tempColTransponderId;
	}

	private TableColumn<UiModelChannel, String> setUpTempColServicesId()
	{
		TableColumn<UiModelChannel, String> tempColServiceID;
		tempColServiceID= new TableColumn<UiModelChannel, String>();
		tempColServiceID.setText("Service Id");
		tempColServiceID.setMinWidth(getColSatPos().getMinWidth());
		tempColServiceID.setPrefWidth(getColSatPos().getPrefWidth());
		tempColServiceID.setMaxWidth(getColSatPos().getMaxWidth());
		return tempColServiceID;
	}

	private TableColumn<UiModelChannel, String> setUpTempColChnName()
	{
		TableColumn<UiModelChannel, String> tempColChnName;
		tempColChnName = new TableColumn<UiModelChannel, String>();
		tempColChnName.setText("Channel name");
		tempColChnName.setMinWidth(getColChnName().getMinWidth());
		tempColChnName.setPrefWidth(getColChnName().getPrefWidth());
		tempColChnName.setMaxWidth(getColChnName().getMaxWidth());
		return tempColChnName;
	}

	private ObservableList<UiModelChannel> setUpViewOfSatTvChannels(List<UiModelChannel> tempListofSatTvChn,
			SatTvBoquet satTvBoquet)
	{
		ObservableList<UiModelChannel> observableListOfUiModel;
		createListOfSatTvChn(tempListofSatTvChn, satTvBoquet);
		observableListOfUiModel = FXCollections.observableArrayList(tempListofSatTvChn);
		createViewForChnName(getColChnName());
		createViewForServiceId(getColServiceID());
		createViewForTransponderId(getColTransponderId());
		createViewForTransponderOnid(getColTransponderOnid());
		createViewForSatPosition(getColSatPos());
		return observableListOfUiModel;
	}

	private void cleanUpLists(List<UiModelChannel> tempListofSatTvChn)
	{
		tempListofSatTvChn.clear();
	}

	private void createViewForChnName(TableColumn<UiModelChannel, String> tableColumn)
	{
		tableColumn.setCellValueFactory((TableColumn.CellDataFeatures<UiModelChannel,String> param)
		-> 	new ReadOnlyStringWrapper(param.getValue().getTvChannelName()	));
	}

	private void createViewForTransponderOnid(TableColumn<UiModelChannel, String> tableColumn)
	{
		tableColumn.setCellValueFactory((TableColumn.CellDataFeatures<UiModelChannel,String> param)
				-> 	new ReadOnlyStringWrapper(param.getValue().getTransponderOnid()));
	}

	private void createViewForTransponderId(TableColumn<UiModelChannel, String> tableColumn)
	{
		tableColumn.setCellValueFactory((TableColumn.CellDataFeatures<UiModelChannel,String> param)
				-> 	new ReadOnlyStringWrapper(param.getValue().getTransponderId()));
	}

	private void createViewForServiceId(TableColumn<UiModelChannel, String> tableColumn)
	{
		tableColumn.setCellValueFactory((TableColumn.CellDataFeatures<UiModelChannel,String> param)
				-> 	new ReadOnlyStringWrapper(param.getValue().getChannelServiceID()));
	}

	private void createViewForSatPosition(TableColumn<UiModelChannel, Integer> tableColumn)
	{
		tableColumn.setCellValueFactory(
				new Callback<CellDataFeatures<UiModelChannel,Integer>, 
				ObservableValue<Integer>>() {
					@Override
					public ObservableValue<Integer> call(
							CellDataFeatures<UiModelChannel, Integer> param)
					{
						ObservableValue<Integer> UiModelForSatTvPos = new SimpleIntegerProperty(param.getValue().
								getChannelSatPosition()).asObject();
						
						return UiModelForSatTvPos;
					}
		});
	}

	private void createListOfSatTvChn(List<UiModelChannel> tempListofSatTvChn, SatTvBoquet satTvBoquet)
	{
		for (SatBoquetTvChannel tvChn  : satTvBoquet.getListOfSatTvChannels())
		{
			tempListofSatTvChn.add(new UiModelChannel(
					tvChn.getSatBoquetTvChannelServiceID(),
					tvChn.getSatBoquetTvChannelName(),
					tvChn.getSatBoquetTvChannelTransponderId(),
					tvChn.getSatBoquetTvChanneltransponderOnid(),
					tvChn.getSatBoquetTvChannelsatPosition()
			));
		}
	}

	private void buildAsetOfSatTvBoquets(ReadXmlAndCreateJdomWithAllBouquets helperObj)
			throws ParserConfigurationException, SAXException, IOException
	{
		List<Element> listOfBoquetsJdomElems;
		SatBouquetsBuilder satBoquetsBuilderObj;
		helperObj.readAndSetUpJDomDocument();
		listOfBoquetsJdomElems= helperObj.readJdomDocumentAndCreateBouquetsElementList();
		satBoquetsBuilderObj = new SatBouquetsBuilder();
		setSetOfSortedSatTvBoquets( satBoquetsBuilderObj.buildSatTvBoquetsSet(listOfBoquetsJdomElems));
        //System.out.println(getSetOfSortedSatTvBoquets().toString());
	}
	
	public boolean checkProviderModelForPathToBoquets()
	{ 
		String validFilename = "bouquets.xml";
		boolean result = false;
		List<String> pathToXmlFilesList = getProviderInstance().getPathsOfValidXmlFiles();
		
		for (String inputPathToXml : pathToXmlFilesList)
		{		
			result = checkIfGivenFileNameForServicesIsAValidOne(validFilename, result, inputPathToXml);
			
		}
		
		return result;
	}
	
	public boolean statusOfLoadedDataFromMainController()
	{
		boolean result = false;

			result = getProviderInstance().getPathsOfValidXmlFiles().isEmpty();
			if (result == false)
			{
				result = true;
			}
		return  result;
	}

	public Button getBtnSearchSatTvChnInGrp()
	{
		return btnSearchSatTvChnInGrp;
	}

	public void setBtnSearchSatTvChnInGrp(Button btnSearchSatTvChnInGrp)
	{
		this.btnSearchSatTvChnInGrp = btnSearchSatTvChnInGrp;
	}

	public TextField getTxtSearchSatTvChnInFavGroups()
	{
		return txtSearchSatTvChnInFavGroups;
	}

	public void setTxtSearchSatTvChnInFavGroups(TextField txtSearchSatTvChnInFavGroups)
	{
		this.txtSearchSatTvChnInFavGroups = txtSearchSatTvChnInFavGroups;
	}

	public Button getButCopySatTvChnfromOneSatTvToAnother()
	{
		return butCopySatTvChnfromOneSatTvToAnother;
	}

	public void setButCopySatTvChnfromOneSatTvToAnother(Button butCopySatTvChnfromOneSatTvToAnother)
	{
		this.butCopySatTvChnfromOneSatTvToAnother = butCopySatTvChnfromOneSatTvToAnother;
	}

	public Button getBtnCreateNewFavGrp()
	{
		return btnCreateNewFavGrp;
	}

	public void setBtnCreateNewFavGrp(Button btnCreateNewFavGrp)
	{
		this.btnCreateNewFavGrp = btnCreateNewFavGrp;
	}

	public Button getButChangeOrderOfSatTvGrp()
	{
		return butChangeOrderOfSatTvGrp;
	}

	public void setButChangeOrderOfSatTvGrp(Button butChangeOrderOfSatTvGrp)
	{
		this.butChangeOrderOfSatTvGrp = butChangeOrderOfSatTvGrp;
	}

	public Button getBtnRenameChn()
	{
		return btnRenameChn;
	}

	public void setBtnRenameChn(Button btnRenameChn)
	{
		this.btnRenameChn = btnRenameChn;
	}

	public Button getBtnDelSatTvFavChn()
	{
		return btnDelSatTvFavChn;
	}

	public void setBtnDelSatTvFavChn(Button btnDelSatTvFavChn)
	{
		this.btnDelSatTvFavChn = btnDelSatTvFavChn;
	}

	public Button getBtnMarkSatTvFavChnForCopy()
	{
		return btnMarkSatTvFavChnForCopy;
	}

	public void setBtnMarkSatTvFavChnForCopy(Button btnMarkSatTvFavChnForCopy)
	{
		this.btnMarkSatTvFavChnForCopy = btnMarkSatTvFavChnForCopy;
	}

	public String getPathToBoquetsXMLFile()
	{
		return pathToBoquetsXMLFile;
	}

	public void setPathToBoquetsXMLFile(String pathToBoquetsXMLFile)
	{
		this.pathToBoquetsXMLFile = pathToBoquetsXMLFile;
	}

	public Tab getTabRootElemSatTvFavorites()
	{
		return tabRootElemSatTvFavorites;
	}

	public void setTabRootElemSatTvFavorites(Tab tabRootElemSatTvFavorites)
	{
		this.tabRootElemSatTvFavorites = tabRootElemSatTvFavorites;
	}

	public TableView<UiModelChannel> getTblFavoritesSatTv()
	{
		return tblFavoritesSatTv;
	}

	public void setTblFavoritesSatTv(TableView<UiModelChannel> tblFavoritesSatTv)
	{
		this.tblFavoritesSatTv = tblFavoritesSatTv;
	}

	public TabPane getTabPaneSatTvFavGrp()
	{
		return tabPaneSatTvFavGrp;
	}

	public void setTabPaneSatTvFavGrp(TabPane tabPaneSatTvFavGrp)
	{
		this.tabPaneSatTvFavGrp = tabPaneSatTvFavGrp;
	}

	public boolean getStateOfProvider()
	{
		return stateOfProvider;
	}

	public void setStateOfProvider(boolean stateOfProvider)
	{
		this.stateOfProvider = stateOfProvider;
	}

	public ProviderBetweenUiControllers getProviderInstance()
	{
		return providerInstance;
	}

	public void setProviderInstance(ProviderBetweenUiControllers providerInstance)
	{
		this.providerInstance = providerInstance;
	}

	public SortedSet<SatTvBoquet> getSetOfSortedSatTvBoquets()
	{
		return setOfSortedSatTvBoquets;
	}

	public void setSetOfSortedSatTvBoquets(SortedSet<SatTvBoquet> setOfSortedSatTvBoquets)
	{
		this.setOfSortedSatTvBoquets = setOfSortedSatTvBoquets;
	}

	public TableColumn<UiModelChannel, String> getColChnName()
	{
		return colChnName;
	}

	public void setColChnName(TableColumn<UiModelChannel, String> colChnName)
	{
		this.colChnName = colChnName;
	}

	public TableColumn<UiModelChannel, String> getColServiceID()
	{
		return colServiceID;
	}

	public void setColServiceID(TableColumn<UiModelChannel, String> colServiceID)
	{
		this.colServiceID = colServiceID;
	}

	public TableColumn<UiModelChannel, String> getColTransponderId()
	{
		return colTransponderId;
	}

	public void setColTransponderId(TableColumn<UiModelChannel, String> colTransponderId)
	{
		this.colTransponderId = colTransponderId;
	}

	public TableColumn<UiModelChannel, String> getColTransponderOnid()
	{
		return colTransponderOnid;
	}

	public void setColTransponderOnid(TableColumn<UiModelChannel, String> colTransponderOnid)
	{
		this.colTransponderOnid = colTransponderOnid;
	}

	public TableColumn<UiModelChannel, Integer> getColSatPos()
	{
		return colSatPos;
	}

	public void setColSatPos(TableColumn<UiModelChannel, Integer> colSatPos)
	{
		this.colSatPos = colSatPos;
	}

}
