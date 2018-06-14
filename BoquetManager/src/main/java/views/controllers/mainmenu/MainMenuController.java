package views.controllers.mainmenu;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;

public class MainMenuController
{
    @FXML
    private CheckBox chkBoxEng;
    
    @FXML
    private CheckBox chkBoxGer;
    
    @FXML
    private CheckBox chkBoxUkr;
    
    @FXML
    private CheckBox chkBoxRu;

    @FXML
    private MenuItem menuItemEng;
    
    @FXML 
    private MenuItem menuItemGer;
    
    @FXML
    private MenuItem menuItemUkr;
    
    @FXML 
    private MenuItem menuItemRu;
    
	@FXML
	private void initialize()
	{

		chkBoxEng.setOnAction((even) -> {
			selectEngChkBox();
		});

		chkBoxGer.setOnAction((even) -> {
			selectGerChkBox();
		});

		chkBoxUkr.setOnAction((even) -> {
			selectUkrChkBox();
		});

		chkBoxRu.setOnAction((even) -> {
			selectRusChkBox();
		});

		menuItemEng.setOnAction((even) -> {
			this.getChkBoxEng().setSelected(true);
			selectEngChkBox();
		});
		
		menuItemGer.setOnAction((even) -> {
			this.getChkBoxGer().setSelected(true);
			selectGerChkBox();
		});
		
		menuItemUkr.setOnAction((even) -> {
			this.getChkBoxUkr().setSelected(true);
			selectUkrChkBox();
		});
		
		menuItemRu.setOnAction((even) -> {
			this.getChkBoxRu().setSelected(true);
			selectRusChkBox();
		});

	}

	private void selectRusChkBox()
	{
		boolean chkBoxRusLangStatus = this.getChkBoxRu().isSelected();
		String lang = "rus";
		selectOnlyOneCheckBox(chkBoxRusLangStatus, lang);
	}

	private void selectUkrChkBox()
	{
		boolean chkBoxUkrLangStatus = this.getChkBoxUkr().isSelected();
		String lang = "ukr";
		selectOnlyOneCheckBox(chkBoxUkrLangStatus, lang);
	}

	private void selectGerChkBox()
	{
		boolean chkBoxGerLangStatus = this.getChkBoxGer().isSelected();
		String lang = "ger";
		selectOnlyOneCheckBox(chkBoxGerLangStatus, lang);
	}

	private void selectEngChkBox()
	{
		boolean chkBoxEngLangStatus = this.getChkBoxEng().isSelected();
		String lang = "eng";
		selectOnlyOneCheckBox(chkBoxEngLangStatus, lang);
	}

	private void selectOnlyOneCheckBox(boolean chkBoxLangResult, String inputLang)
	{
		switch(inputLang) {
			case "eng":
				if (chkBoxLangResult== true) {
					unselectChkBoxesGerRuUkr();
				}
			break;
			case "ger":
				if (chkBoxLangResult== true) {
					unselectChkBoxesEngRuUkr();
				}
			break;
			case "ukr":
				if (chkBoxLangResult== true) {
					unselectChkBoxesEngRuGer();
				}
			break;
			case "rus":
				if (chkBoxLangResult== true) {
					unselectChkBoxesEngUkrGer();
				}
			break;
		}

	}

	private void unselectChkBoxesEngUkrGer()
	{
		this.getChkBoxEng().setSelected(false);
		this.getChkBoxUkr().setSelected(false);
		this.getChkBoxGer().setSelected(false);
	}

	private void unselectChkBoxesEngRuGer()
	{
		this.getChkBoxEng().setSelected(false);
		this.getChkBoxRu().setSelected(false);
		this.getChkBoxGer().setSelected(false);
	}

	private void unselectChkBoxesEngRuUkr()
	{
		this.getChkBoxEng().setSelected(false);
		this.getChkBoxRu().setSelected(false);
		this.getChkBoxUkr().setSelected(false);
	}

	private void unselectChkBoxesGerRuUkr()
	{
		this.getChkBoxGer().setSelected(false);
		this.getChkBoxRu().setSelected(false);
		this.getChkBoxUkr().setSelected(false);
	}

	public CheckBox getChkBoxEng()
	{
		return chkBoxEng;
	}

	public void setChkBoxEng(CheckBox chkBoxEng)
	{
		this.chkBoxEng = chkBoxEng;
	}

	public CheckBox getChkBoxGer()
	{
		return chkBoxGer;
	}

	public void setChkBoxGer(CheckBox chkBoxGer)
	{
		this.chkBoxGer = chkBoxGer;
	}

	public CheckBox getChkBoxUkr()
	{
		return chkBoxUkr;
	}

	public void setChkBoxUkr(CheckBox chkBoxUkr)
	{
		this.chkBoxUkr = chkBoxUkr;
	}

	public CheckBox getChkBoxRu()
	{
		return chkBoxRu;
	}

	public void setChkBoxRu(CheckBox chkBoxRu)
	{
		this.chkBoxRu = chkBoxRu;
	}

	public MenuItem getMenuItemEng()
	{
		return menuItemEng;
	}

	public void setMenuItemEng(MenuItem menuItemEng)
	{
		this.menuItemEng = menuItemEng;
	}

	public MenuItem getMenuItemGer()
	{
		return menuItemGer;
	}

	public void setMenuItemGer(MenuItem menuItemGer)
	{
		this.menuItemGer = menuItemGer;
	}

	public MenuItem getMenuItemUkr()
	{
		return menuItemUkr;
	}

	public void setMenuItemUkr(MenuItem menuItemUkr)
	{
		this.menuItemUkr = menuItemUkr;
	}

	public MenuItem getMenuItemRu()
	{
		return menuItemRu;
	}

	public void setMenuItemRu(MenuItem menuItemRu)
	{
		this.menuItemRu = menuItemRu;
	}

}
