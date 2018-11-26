package views.controllers.utils.model;
import javafx.beans.property.SimpleStringProperty;

public class DiskModelForCustomFileChooser
{
    private SimpleStringProperty fileSystemItem;
    private SimpleStringProperty lastModifiedDateForFileSystemItem;
	
	public DiskModelForCustomFileChooser(String inputFileSystemItem, String inputLastmodifiedDate)
	{
       this.fileSystemItem = new SimpleStringProperty(inputFileSystemItem);
       this.lastModifiedDateForFileSystemItem = new  SimpleStringProperty(inputLastmodifiedDate);
	}

	public boolean getFileSystemItemSetUpState()
	{
		boolean result = false;
		
		if (getFileSystemItem().isEmpty() == false) {
			result = true;
		}
		
		return result;
	}

	public boolean getFileSystemLastModifiedSetUpState()
	{
		boolean result = false;
		
		if (getLastModifiedDateForFileSystemItem().isEmpty() == false  ) {
			result = true;
		}
		
		return result;
	}

	public String getFileSystemItem()
	{
		return fileSystemItem.get();
	}

	public void setFileSystemItem(String inputFileSystemItem)
	{
		this.fileSystemItem.set( inputFileSystemItem);
	}

	public String  getLastModifiedDateForFileSystemItem()
	{
		return lastModifiedDateForFileSystemItem.get();
	}

	public void setLastModifiedDateForFileSystemItem(String inputlastModifiedDateForFileSystemItem)
	{
		this.lastModifiedDateForFileSystemItem.set( inputlastModifiedDateForFileSystemItem);
	}





	
	
}
