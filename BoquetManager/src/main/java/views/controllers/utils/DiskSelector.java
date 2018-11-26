package views.controllers.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.paint.Color;
import views.controllers.utils.model.DiskModelForCustomFileChooser;

final class DiskSelector implements ChangeListener<TreeItem<DiskModelForCustomFileChooser>>
{

	private final CustomFileChooserController diskSelector;

	DiskSelector(CustomFileChooserController customFileChooserController)
	{
		diskSelector = customFileChooserController;
	}

	@Override
	public void changed(ObservableValue<? extends TreeItem<DiskModelForCustomFileChooser>> observable,
			TreeItem<DiskModelForCustomFileChooser> oldValue, TreeItem<DiskModelForCustomFileChooser> itemWhichWasSelected)
	{
		TreeItem<DiskModelForCustomFileChooser> selectedItem = itemWhichWasSelected;
		selectDisk(selectedItem);
	}

	private void selectDisk(TreeItem<DiskModelForCustomFileChooser> selectedItem)
	{
		if (!(selectedItem.getValue().equals("Following disks are available")))
		{
			String selectedText = selectedItem.getValue().getFileSystemItem();
			System.out.println("Selected Text : " + selectedText);
			diskSelector.lblSelectedFile.setTextFill(Color.BLACK);
			diskSelector.lblSelectedFile.setUnderline(true);
			diskSelector.lblSelectedFile.setText("Selected!");
			diskSelector.txtFieldDefaultPath.setText(selectedText);
		}
	}

	public void selectDiskByTextFieldInput(String userInputDisk, TreeTableView<DiskModelForCustomFileChooser> fileChooseTreeTablesView)
	{
		//acces the TreeTableView List and select the item trough given input
		ObservableList<TreeItem<DiskModelForCustomFileChooser>> listOfDisks=fileChooseTreeTablesView.getRoot().getChildren();
		for (TreeItem<DiskModelForCustomFileChooser> treeItem : listOfDisks)
		{
			if(treeItem.getValue().getFileSystemItem().equals(userInputDisk)) {
				fileChooseTreeTablesView.getSelectionModel().select(treeItem);
			}
		}
	}
	
}