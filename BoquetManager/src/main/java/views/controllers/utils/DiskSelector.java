package views.controllers.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.paint.Color;

final class DiskSelector implements ChangeListener<TreeItem<String>>
{

	private final CustomFileChooserController diskSelector;

	DiskSelector(CustomFileChooserController customFileChooserController)
	{
		diskSelector = customFileChooserController;
	}

	@Override
	public void changed(ObservableValue<? extends TreeItem<String>> observable,
			TreeItem<String> oldValue, TreeItem<String> itemWhichWasSelected)
	{
		TreeItem<String> selectedItem = itemWhichWasSelected;
		selectDisk(selectedItem);
	}

	private void selectDisk(TreeItem<String> selectedItem)
	{
		if (!(selectedItem.getValue().equals("Following disks are available")))
		{
			String selectedText = selectedItem.getValue();
			System.out.println("Selected Text : " + selectedText);
			diskSelector.lblSelectedFile.setTextFill(Color.BLACK);
			diskSelector.lblSelectedFile.setUnderline(true);
			diskSelector.lblSelectedFile.setText("Selected!");
			diskSelector.txtFieldDefaultPath.setText(selectedText);
		}
	}

	public void selectDiskByTextFieldInput(String userInputDisk, TreeTableView<String> fileChooseTreeTablesView)
	{
		//acces the TreeTableView List and select the item trough given input
		ObservableList<TreeItem<String>> listOfDisks=fileChooseTreeTablesView.getRoot().getChildren();
		for (TreeItem<String> treeItem : listOfDisks)
		{
			if(treeItem.getValue().equals(userInputDisk)) {
				fileChooseTreeTablesView.getSelectionModel().select(treeItem);
			}
		}
	}
	
}