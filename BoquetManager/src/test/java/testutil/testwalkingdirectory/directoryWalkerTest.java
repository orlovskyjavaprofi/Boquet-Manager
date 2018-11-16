package testutil.testwalkingdirectory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import views.utils.filesystemtools.FileSystemWalker;

//it works only on windows machine , put the archive dirfiles.zip , 
//src/test/resources/filesystemtestto your C:\ drive
class directoryWalkerTest
{
	FileSystemWalker fsWalkerObject;
	String mockObjDiskletter = "C:\\";
	String directoryAtTest= "test";
			
	@BeforeEach
	void setUp() {
		fsWalkerObject = new FileSystemWalker();
	}

	@Test
	void testingIfFileSystemWalkerExist(){
		assertNotNull(fsWalkerObject);
		System.out.println(mockObjDiskletter);
	}

	@Test
	void testingIfDirectoriesCanBePrint() {
		ArrayList<String> expectedDirList = new ArrayList<String>();
		creatingMockupDirList(expectedDirList);
		//System.out.println(expectedDirList.toString());
		ArrayList<String> actualDirList = fsWalkerObject.getDirectoriesList(mockObjDiskletter+directoryAtTest);
	   assertEquals(expectedDirList, actualDirList,"geting directories list failed");	  
	}

	private void creatingMockupDirList(ArrayList<String> expectedDirList)
	{
		for (int i = 0; i <= 10; i++)
		{
			expectedDirList.add("dir"+i);
		}
	}

}
