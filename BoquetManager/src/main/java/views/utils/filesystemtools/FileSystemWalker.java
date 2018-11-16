package views.utils.filesystemtools;

import java.util.ArrayList;

public class FileSystemWalker
{

	public ArrayList<String> getDirectoriesList(String diskInput)
	{
		ArrayList<String> expectedDirList = new ArrayList<String>();
		for (int i = 0; i <= 10; i++)
		{
			expectedDirList.add("dir"+i);
		}

		return expectedDirList;
	}

}
