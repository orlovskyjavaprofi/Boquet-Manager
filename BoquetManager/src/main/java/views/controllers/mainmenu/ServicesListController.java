package views.controllers.mainmenu;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import builders.SatServicesListBuilder;
import fileutils.ReadXmlAndCreateJdomWithAllSat;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import models.ProviderBetweenUiControllers;
import models.SatServicesList;
import models.UiModels.UiModelServicesList;

public class ServicesListController
{
	@FXML
	private BorderPane servicesListBorderPaneMain;
	@FXML
	private BorderPane serviceListBorderPaneTop;
	@FXML
	private BorderPane servicesListBorderPaneForButtonsTop;
	@FXML
	private BorderPane servicesListBorderPaneButtom;
	@FXML
	private Button btnAddChnToFav;
	@FXML
	private Button btnEditChnInService;
	@FXML
	private Button btnSearchChannel;
	@FXML
	private TreeTableView<UiModelServicesList> treeTblViewServicesList;
	@FXML
	private TreeTableColumn<UiModelServicesList,String> colSatTvChnName;
	@FXML
	private TreeTableColumn<UiModelServicesList,String> colSatName;
	@FXML
	private TreeTableColumn<UiModelServicesList, String> colSatTransponderId;
	@FXML
	private TextField txtFldSearchSatTvChn;
	
	private ProviderBetweenUiControllers providerInstace;
	private String pathToServicesXMLFile;
	private SortedSet<SatServicesList> setOfSortedSatellitesServices;
	private SatServicesListBuilder satServiceListBuilderObj;
	private boolean stateOfProvider;
	private TreeItem<UiModelServicesList> rootTreeItem;
	private List<UiModelServicesList> uiModelForServicesList; 
	
	public ServicesListController() {
		setOfSortedSatellitesServices = new TreeSet<SatServicesList>();
		satServiceListBuilderObj = new SatServicesListBuilder();
		stateOfProvider = false;
		uiModelForServicesList = new ArrayList<UiModelServicesList>();
	}

	@FXML
	private void initialize() {
				
	}

	public void buildingSatServicesAndPopulateUiOfServices()
	{
		
    	setStateOfProvider(statusOfLoadedDataFromMainController());

		if (getStateOfProvider() == true)
		{
			setStateOfProvider( checkProviderModelForPathToServices());
			if (getStateOfProvider() == true)
			{
				try
				{
					buildASetOfSatServices();

					createRootTreeItem();
					createAndSetupRoots();
					
		         getColSatName().setCellValueFactory((
		        		 TreeTableColumn.CellDataFeatures<UiModelServicesList, String> param) ->
		           new ReadOnlyStringWrapper(param.getValue().getValue().getSatName() )
		         );
					
                 getTreeTblViewServicesList().setRoot( getRootTreeItem());
			
				} catch (ParserConfigurationException | SAXException | IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void createAndSetupRoots()
	{
		for(UiModelServicesList root : getUiModelForServicesList()) {
			getRootTreeItem().getChildren().add(new TreeItem<UiModelServicesList>(
					new UiModelServicesList(root.getSatName() ) ) 
				);
		}
	}

	private void createRootTreeItem()
	{
		String defaultText= "List of satellite's";
		TreeItem<UiModelServicesList> rootItem = new TreeItem<UiModelServicesList>(
				  new UiModelServicesList(defaultText));
		setRootTreeItem(rootItem);
		getRootTreeItem().setExpanded(true);
	}


	private void buildASetOfSatServices() throws ParserConfigurationException, SAXException, IOException
	{	
		ReadXmlAndCreateJdomWithAllSat jdomDocumentCreator = 
				  new ReadXmlAndCreateJdomWithAllSat( getPathToServicesXMLFile() );
		List<Element> listOfSatellitesWithServices = new LinkedList<Element>();
		jdomDocumentCreator.readAndSetUpJDomDocument();
		listOfSatellitesWithServices = jdomDocumentCreator.readJdomDocumentAndCreate1rdLevelElementList();
		setSetOfSortedSatellitesServices( satServiceListBuilderObj.buildSatServicesSet(listOfSatellitesWithServices) );	
		UiModelServicesList tempUiModelList;
		for (SatServicesList satServicesList : getSetOfSortedSatellitesServices())
		{
			tempUiModelList = new UiModelServicesList(satServicesList.getSatName(),
					satServicesList.getSatPosition(),String.valueOf(satServicesList.getSatDiseqc()) );

			getUiModelForServicesList().add(tempUiModelList);
		}
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
	
	public boolean checkProviderModelForPathToServices()
	{
		String validFilename = "services.xml";
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
			setPathToServicesXMLFile(inputPathToXml);
			result = true;
		}
		return result;
	}
	
	public BorderPane getServicesListBorderPaneMain()
	{
		return servicesListBorderPaneMain;
	}

	public void setServicesListBorderPaneMain(BorderPane servicesListBorderPaneMain)
	{
		this.servicesListBorderPaneMain = servicesListBorderPaneMain;
	}

	public BorderPane getServiceListBorderPaneTop()
	{
		return serviceListBorderPaneTop;
	}

	public void setServiceListBorderPaneTop(BorderPane serviceListBorderPaneTop)
	{
		this.serviceListBorderPaneTop = serviceListBorderPaneTop;
	}

	public BorderPane getServicesListBorderPaneForButtonsTop()
	{
		return servicesListBorderPaneForButtonsTop;
	}

	public void setServicesListBorderPaneForButtonsTop(BorderPane servicesListBorderPaneForButtonsTop)
	{
		this.servicesListBorderPaneForButtonsTop = servicesListBorderPaneForButtonsTop;
	}

	public BorderPane getServicesListBorderPaneButtom()
	{
		return servicesListBorderPaneButtom;
	}

	public void setServicesListBorderPaneButtom(BorderPane servicesListBorderPaneButtom)
	{
		this.servicesListBorderPaneButtom = servicesListBorderPaneButtom;
	}

	public TreeTableView<UiModelServicesList> getTreeTblViewServicesList()
	{
		return treeTblViewServicesList;
	}

	public void setTreeTblViewServicesList(TreeTableView<UiModelServicesList> treeTblViewServicesList)
	{
		this.treeTblViewServicesList = treeTblViewServicesList;
	}

	public TreeTableColumn<UiModelServicesList, String> getColSatTvChnName()
	{
		return colSatTvChnName;
	}

	public void setColSatTvChnName(TreeTableColumn<UiModelServicesList, String> colSatTvChnName)
	{
		this.colSatTvChnName = colSatTvChnName;
	}

	public TreeTableColumn<UiModelServicesList, String> getColSatName()
	{
		return colSatName;
	}

	public void setColSatName(TreeTableColumn<UiModelServicesList, String> colSatName)
	{
		this.colSatName = colSatName;
	}

	public TreeTableColumn<UiModelServicesList, String> getColSatTransponderId()
	{
		return colSatTransponderId;
	}

	public void setColSatTransponderId(TreeTableColumn<UiModelServicesList, String> colSatTransponderId)
	{
		this.colSatTransponderId = colSatTransponderId;
	}

	public TextField getTxtFldSearchSatTvChn()
	{
		return txtFldSearchSatTvChn;
	}

	public void setTxtFldSearchSatTvChn(TextField txtFldSearchSatTvChn)
	{
		this.txtFldSearchSatTvChn = txtFldSearchSatTvChn;
	}

	public ProviderBetweenUiControllers getProviderInstance()
	{
		return providerInstace;
	}

	public void setProviderInstace(ProviderBetweenUiControllers providerInstace)
	{
		this.providerInstace = providerInstace;
	}


	public String getPathToServicesXMLFile()
	{
		return pathToServicesXMLFile;
	}


	public void setPathToServicesXMLFile(String pathToServicesXMLFile)
	{
		this.pathToServicesXMLFile = pathToServicesXMLFile;
	}


	public SortedSet<SatServicesList> getSetOfSortedSatellitesServices()
	{
		return setOfSortedSatellitesServices;
	}


	public void setSetOfSortedSatellitesServices(SortedSet<SatServicesList> setOfSortedSatellitesServices)
	{
		this.setOfSortedSatellitesServices = setOfSortedSatellitesServices;
	}


	public SatServicesListBuilder getSatServiceListBuilderObj()
	{
		return satServiceListBuilderObj;
	}


	public void setSatServiceListBuilderObj(SatServicesListBuilder satServiceListBuilderObj)
	{
		this.satServiceListBuilderObj = satServiceListBuilderObj;
	}


	public ProviderBetweenUiControllers getProviderInstace()
	{
		return providerInstace;
	}


	public boolean getStateOfProvider()
	{
		return stateOfProvider;
	}


	public void setStateOfProvider(boolean stateOfProvider)
	{
		this.stateOfProvider = stateOfProvider;
	}



	public List<UiModelServicesList> getUiModelForServicesList()
	{
		return uiModelForServicesList;
	}

	public void setUiModelForServicesList(List<UiModelServicesList> uiModelForServicesList)
	{
		this.uiModelForServicesList = uiModelForServicesList;
	}

	public TreeItem<UiModelServicesList> getRootTreeItem()
	{
		return rootTreeItem;
	}

	public void setRootTreeItem(TreeItem<UiModelServicesList> rootTreeItem)
	{
		this.rootTreeItem = rootTreeItem;
	}

	
	
}
