package helperutils;

import java.io.File;

public class XmlTestResourcesRemover
{
	public static void removeAllXmlResources() {
		String inBoquetPath = "src//test//resources//testXmlOutputDir//bouquets.xml";
		String inSatellitesPath = "src//test//resources//testXmlOutputDir//satellites.xml";
		String inServicesPath = "src//test//resources//testXmlOutputDir//services.xml";
		
		removeAllXmlResourcesHelper(inBoquetPath, inSatellitesPath, inServicesPath);
	}

	private static void removeAllXmlResourcesHelper(String inBoquetPath, String inSatellitesPath,
			String inServicesPath)
	{
		removeAllXmlInTestResources(inBoquetPath);
		removeAllXmlInTestResources(inSatellitesPath);
		removeAllXmlInTestResources(inServicesPath);
	}

	private static void removeAllXmlInTestResources(String Input)
	{
		File fileToBeDeleted = new File( Input);
		if(fileToBeDeleted.exists() && !fileToBeDeleted.isDirectory()) { 
		    fileToBeDeleted.delete();
		}
	}
}
