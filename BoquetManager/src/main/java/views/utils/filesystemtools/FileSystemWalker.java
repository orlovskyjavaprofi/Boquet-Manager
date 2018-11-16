package views.utils.filesystemtools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileSystemWalker
{

	public ArrayList<String> getDirectoriesList(String diskInputForDirectoryList)
	{
		ArrayList<String> directoryList = new ArrayList<String>();
		File directory = new File(diskInputForDirectoryList);
		File resultDirs[] = directory.listFiles();
		for (File fileObj : resultDirs)
		{
			if (fileObj.isDirectory())
			{
				directoryList.add(fileObj.getName());
			}
		}

		return directoryList;
	}

	public ArrayList<String> getFilesList(String diskInputForFileList)
	{
		ArrayList<String> fileList = new ArrayList<String>();
		File directory = new File(diskInputForFileList);
		File resultFiles[] = directory.listFiles();
		for (File fileObj : resultFiles)
		{
			if (fileObj.isFile())
			{
				fileList.add(fileObj.getName());
			}
		}
		return fileList;
	}

	public ArrayList<String> getLastModifiedAttributes(String pathToDirecotry,
			ArrayList<String> contentsOfFS)
	{
		ArrayList<String> attributesLastModifiedList = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		
		for (String itemName : contentsOfFS)
		{
			Path path = Paths.get(pathToDirecotry+"\\"+itemName);
			BasicFileAttributeView viewAtributes = Files.getFileAttributeView(	path, BasicFileAttributeView.class);
			getingLastModifiedTimeFromFsList(attributesLastModifiedList, df, path, viewAtributes);
		} 
		
		return attributesLastModifiedList;

	}

	private void getingLastModifiedTimeFromFsList(ArrayList<String> attributesLastModifiedList, DateFormat df, Path path,
			BasicFileAttributeView viewAtributes)
	{
		try
		{
			BasicFileAttributes itemAttributes = viewAtributes.readAttributes();
			String lastModified = df.format(itemAttributes.lastModifiedTime().toMillis());
			//	System.out.println(path+" "+lastModified);
			attributesLastModifiedList.add(lastModified);
		   				
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
