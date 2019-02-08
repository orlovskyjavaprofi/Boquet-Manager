package views.controllers.mainmenu;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;

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
	private TreeTableView<?> treeTblViewServicesList;
	@FXML
	private TreeTableColumn colSatTvChnName;
	@FXML
	private TreeTableColumn colSatName;
	@FXML
	private TreeTableColumn colSatTransponderId;
	@FXML
	private TextField txtFldSearchSatTvChn;
	

	@FXML
	private void initialize() {
		
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

	public TreeTableView<?> getTreeTblViewServicesList()
	{
		return treeTblViewServicesList;
	}

	public void setTreeTblViewServicesList(TreeTableView<?> treeTblViewServicesList)
	{
		this.treeTblViewServicesList = treeTblViewServicesList;
	}

	public TreeTableColumn getColSatTvChnName()
	{
		return colSatTvChnName;
	}

	public void setColSatTvChnName(TreeTableColumn colSatTvChnName)
	{
		this.colSatTvChnName = colSatTvChnName;
	}

	public TreeTableColumn getColSatName()
	{
		return colSatName;
	}

	public void setColSatName(TreeTableColumn colSatName)
	{
		this.colSatName = colSatName;
	}

	public TreeTableColumn getColSatTransponderId()
	{
		return colSatTransponderId;
	}

	public void setColSatTransponderId(TreeTableColumn colSatTransponderId)
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



}
