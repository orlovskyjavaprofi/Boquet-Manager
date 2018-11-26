package testutil.testwalkingdirectory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import views.utils.filesystemtools.FileSystemWalker;

//it works only on windows machine , put the archive dirfiles.zip , 
//src/test/resources/filesystemtest to your C:\ drive
class directoryWalkerTest
{
	FileSystemWalker fsWalkerObject;
	String mockObjDiskletter = "C:\\";
	String directoryAtTest = "test";

	@BeforeEach
	void setUp()
	{
		fsWalkerObject = new FileSystemWalker();
	}

	@Test
	void testingIfFileSystemWalkerExist()
	{
		assertNotNull(fsWalkerObject);
	}

	@Test
	void testingIfDirectoriesCanBeOutputedAsList()
	{
		ArrayList<String> expectedDirList = new ArrayList<String>();
		creatingMockupDirList(expectedDirList);
		// System.out.println(expectedDirList.toString());
		ArrayList<String> actualDirList = fsWalkerObject.getDirectoriesList(mockObjDiskletter + directoryAtTest);
		assertEquals(expectedDirList, actualDirList, "geting directories list failed");
	}

	@Test
	void testingIfFilesCanBeOutputedAsList()
	{
		ArrayList<String> expectedFilesList = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
		{
			createMockFileList(expectedFilesList, i);
		}
        //	System.out.println(expectedFilesList.toString());
		 ArrayList<String> actualFileList = fsWalkerObject.getFilesList(mockObjDiskletter + directoryAtTest);
		assertEquals(expectedFilesList, actualFileList, "geting files list failed");
	}

	@Test
	void testingLastModifiedAttributeForDirectorys() {
		ArrayList<String> resultOfDirctorysLastModifiedList = new ArrayList<String>();
		ArrayList<String> actualDirList = fsWalkerObject.getDirectoriesList(mockObjDiskletter + directoryAtTest);
		resultOfDirctorysLastModifiedList = fsWalkerObject.getLastModifiedAttributes(mockObjDiskletter + directoryAtTest, actualDirList);
		//System.out.println(resultOfDirctorysLastModifiedList.toString());
		assertNotNull(resultOfDirctorysLastModifiedList);
	}
	
	@Test 
	void testingLastModifiedAttributeForFiles() {
		ArrayList<String> resultOfFilesLastModifiedList = new ArrayList<String>();
		ArrayList<String> actualFileList = fsWalkerObject.getFilesList(mockObjDiskletter + directoryAtTest);
		resultOfFilesLastModifiedList =fsWalkerObject.getLastModifiedAttributes(mockObjDiskletter + directoryAtTest, actualFileList);
		assertNotNull(resultOfFilesLastModifiedList);
	}
	
	private void createMockFileList(ArrayList<String> expectedFilesList, int i)
	{
		if (i == 1)
		{
			expectedFilesList.add("test1.txt");
			expectedFilesList.add("test10.txt");
		} else
		{
			if (i >0)
			{
				expectedFilesList.add("test" + i + ".txt");
			}
		}
	}

	private void creatingMockupDirList(ArrayList<String> expectedDirList)
	{
		for (int i = 0; i < 10; i++)
		{
			naturalOrderOfDirs(expectedDirList, i);
		}
	}

	private void naturalOrderOfDirs(ArrayList<String> expectedDirList, int i)
	{
		if (i == 2)
		{
			expectedDirList.add("dir10");
			expectedDirList.add("dir2");
		} else
		{
			expectedDirList.add("dir" + i);
		}
	}

}
