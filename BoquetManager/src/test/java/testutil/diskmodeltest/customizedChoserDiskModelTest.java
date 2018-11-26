package testutil.diskmodeltest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleStringProperty;
import views.controllers.utils.model.DiskModelForCustomFileChooser;

class customizedChoserDiskModelTest
{
     DiskModelForCustomFileChooser diskModel;
	
	@BeforeEach
	void setUp() 
	{
		String fileSystemItem =   "dir1";
		String lastmodifiedDate =  "16.11.2018 10:44";
		diskModel = new DiskModelForCustomFileChooser(fileSystemItem,lastmodifiedDate);
	}

	@Test
	void testIfObjectExist()
	{
		assertNotNull(diskModel);
	}
	
	@Test
	void checkIfDiskModelContainsFileSystemItem() {
		assertTrue(diskModel.getFileSystemItemSetUpState());
	}
	
	@Test
	void checkIfDiskModelContainsDate() {
		assertTrue(diskModel.getFileSystemLastModifiedSetUpState());
	}
	
}
