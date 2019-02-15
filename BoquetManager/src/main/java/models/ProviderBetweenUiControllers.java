package models;

import java.util.List;


public class ProviderBetweenUiControllers
{
	private List<String> PathsOfValidXmlFiles;
  
    
	public ProviderBetweenUiControllers()
	{
		
	}

	public boolean insertPathOfXmlFileToList(List<String> inputPathsOfValidXmlFiles)
	{
		boolean result = false;
		
		setPathsOfValidXmlFiles(inputPathsOfValidXmlFiles);
	
		if(getPathsOfValidXmlFiles().isEmpty() == false) {
	
			result = true;
		}
		
		return result;
	}


	public List<String> getPathsOfValidXmlFiles()
	{
		return PathsOfValidXmlFiles;
	}


	public void setPathsOfValidXmlFiles(List<String> pathsOfValidXmlFiles)
	{
		PathsOfValidXmlFiles = pathsOfValidXmlFiles;
	}

	

}
