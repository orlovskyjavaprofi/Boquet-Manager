package fileutilstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import fileutils.FileWriterForXml;
import models.SatModel;
import models.SatServicesModel;
import models.SatTvBoquetModel;
import xmloutputers.XmlOutputerForSatellites;
import xmloutputers.XmlOutputerForServices;
import xmloutputers.XmlOutputerForTvBoquets;

class WriteFileToFilesystemTest
{
	FileWriterForXml fileWriterObj;
	XmlOutputerForSatellites xmlOutputObjForSatellites;
	XmlOutputerForServices xmlOutputObjForServices;
	XmlOutputerForTvBoquets xmlOutputObjForBoquets;
	String inputPathForSat;
	String inputPathForSatServices;
	String inputPathForSatBoquets;
	String boquetPath;
	String satellitesPath;
	String servicesPath;
	SatModel allSattelitesInfoObj;
	SatServicesModel satServicesObj;
	SatTvBoquetModel satTvBoquetModelObj;
	boolean dirExists;
	String workingDir;
	String pathToDir;
	String OutputPath;
	String dirName;
	
	@BeforeEach
	void setUp() throws ParserConfigurationException, SAXException, IOException
	{
		fileWriterObj = new FileWriterForXml();
		workingDir = System.getProperty("user.dir");
		pathToDir = "\\src\\test\\resources\\XmlOuter";
		OutputPath = workingDir+pathToDir;
		dirName = "XmlOuter";
				
		inputPathForSat = "src//main//resources//XML-Files-Update2018//satellites.xml";
		inputPathForSatServices = "src//main//resources//XML-Files-Update2018//services.xml";
		inputPathForSatBoquets = "src//main//resources//XML-Files-Update2018//bouquets.xml";

		boquetPath = "src//test//resources//"+dirName+"//bouquets.xml";
		satellitesPath = "src//test//resources//"+dirName+"//satellites.xml";
		servicesPath = "src//test//resources//"+dirName+"//services.xml";

		initXmlOutputers();
		initSatModels();
		createAllXmlDocumentsObjects();
		createDirectory(OutputPath);
	}

	@AfterEach
	public void checkAndCleanupIfRequired() throws IOException
	{
		executeCleanupDeletion();
	}

	@Test
	void testingIfFileWriterObjectExist()
	{
		assertNotNull(fileWriterObj);
	}

	@Test
	void checkIfFileBoquetDoesExist() throws IOException 
	{
		boolean expectedResult = true;

		fileWriterObj.writeFileToFs(
				xmlOutputObjForBoquets.getSatBoquetsXmlOutputDoc(), boquetPath);
		
		boolean actualResult = fileWriterObj.verifyIfFileExist(boquetPath);
		assertEquals(expectedResult, actualResult,
				" the file " + boquetPath + " wasn't created, because its already exist!");
	}
	
	@Test
	void testingIfFileCanBeCreatedForSatellites() throws IOException
	{
		boolean expectedResult = true;
		boolean actualResult = false;
		String PathToFileLocation = "src//test//resources//"+dirName+"//satellites.xml";
		Document inputSatXmlDoc = xmlOutputObjForSatellites.getSatXmlOutputDoc();

		actualResult = fileWriterObj.writeFileToFs(inputSatXmlDoc, PathToFileLocation);

		assertEquals(expectedResult, actualResult,
				" the file " + PathToFileLocation + " wasn't created, because its already exist!");
	}
	
	@Test
	void testingIfFileCanBeCreatedForServices() throws IOException
	{
		boolean expectedResult = true;
		String PathToFileLocation = "src//test//resources//"+dirName+"//services.xml";
		Document inputServicesXmlDoc = xmlOutputObjForServices.getSatServicesXmlOutputDoc();

		boolean actualResult = fileWriterObj.writeFileToFs(inputServicesXmlDoc, PathToFileLocation);

		assertEquals(expectedResult, actualResult,
				" the file " + PathToFileLocation + " wasn't created, because its already exist!");
	}

	@Test
	void testingIfFileCanBeCreatedForBoquets() throws IOException
	{
		boolean expectedResult = true;

		String PathToFileLocation = "src//test//resources//"+dirName+"//bouquets.xml";
		Document inputBoquetsXmlDoc = xmlOutputObjForBoquets.getSatBoquetsXmlOutputDoc();
		boolean actualResult = fileWriterObj.writeFileToFs(inputBoquetsXmlDoc, PathToFileLocation);

		assertEquals(expectedResult, actualResult,
				" the file " + PathToFileLocation + " wasn't created, because its already exist!");
	}

	@Test
	void testingIfUserDecidedToOverwriteExistingFiles() throws IOException
	{
		boolean expectedResult = true;
		boolean actualResult = false;
		boolean userDecisionToOverwrite = true;
		Integer counter = 0;
		Document inputSatXmlDoc = xmlOutputObjForSatellites.getSatXmlOutputDoc();
		Document inputSatServicesXmlDoc = xmlOutputObjForServices.getSatServicesXmlOutputDoc();
		Document inputSatBoquetsXmlDoc = xmlOutputObjForBoquets.getSatBoquetsXmlOutputDoc();
		List<String> xmlPaths = initPathsToXml(boquetPath, satellitesPath, servicesPath);

		counter = iterateOverXmlPathsAndCheckIfOverwriteOpsSuccess(userDecisionToOverwrite, inputSatXmlDoc,
				inputSatServicesXmlDoc, inputSatBoquetsXmlDoc, counter, xmlPaths);

		if (counter > 0)
		{
			actualResult = true;
		}

		assertEquals(expectedResult, actualResult, " can't overwrite a file!");
	}
	
	@Disabled
	@Test
	void checkMyDirectoryCreator() throws IOException {
		boolean expectedResult = true;
		boolean actualResult = false;

	    System.out.println(OutputPath);
			    
		actualResult  = createDirectory(OutputPath);
		
		assertEquals(expectedResult,actualResult);
	}
	
	@Disabled
	@Test
	void delDir() {
	     boolean result = true;
	     File dir = new File(OutputPath);
	     boolean actualResult = deleteDirectory(dir);
		assertEquals(result,actualResult);
	}
	
	private void executeCleanupDeletion()
	{
		File dir = new File(OutputPath);
		deleteDirectory(dir);
	}
	
	private void createAllXmlDocumentsObjects()
	{
		xmlOutputObjForSatellites.creatingXmlElements(allSattelitesInfoObj);
		xmlOutputObjForServices.creatingXmlElements(satServicesObj);
		xmlOutputObjForBoquets.creatingXmlElements(satTvBoquetModelObj);
	}

	private void initSatModels() throws ParserConfigurationException, SAXException, IOException
	{
		this.initializeSatModel(inputPathForSat);
		this.initializeServicesModel(inputPathForSatServices);
		this.initializeBoquetsModel(inputPathForSatBoquets);
	}

	private void initializeBoquetsModel(String inputpath) throws ParserConfigurationException, SAXException, IOException
	{
		satTvBoquetModelObj = new SatTvBoquetModel();
		satTvBoquetModelObj.readAndSetUpJDomDocument(inputpath);
		satTvBoquetModelObj.buildSetOfBoquets(satTvBoquetModelObj.readJdomDocumentAndCreateBouquetsElementList());
	}

	private void initializeServicesModel(String inputPath)
			throws ParserConfigurationException, SAXException, IOException
	{
		satServicesObj = new SatServicesModel();
		satServicesObj.readAndSetUpJDomDocument(inputPath);
		satServicesObj.buildAsetOfSatServices(satServicesObj.readJdomDocumentAndCreate1rdLevelElementList());
	}

	private void initXmlOutputers()
	{
		xmlOutputObjForSatellites = new XmlOutputerForSatellites();
		xmlOutputObjForServices = new XmlOutputerForServices();
		xmlOutputObjForBoquets = new XmlOutputerForTvBoquets();
	}

	
	
	private Integer iterateOverXmlPathsAndCheckIfOverwriteOpsSuccess(boolean userDecisionToOverwrite,
			Document inputSatXmlDoc, Document inputSatServicesXmlDoc, Document inputSatBoquetsXmlDoc, Integer counter,
			List<String> xmlPaths) throws IOException
	{
		final String boquetPath = "src//test//resources//XmlOuter//bouquets.xml";
		final String satellitesPath = "src//test//resources//XmlOuter//satellites.xml";
		final String servicesPath = "src//test//resources//XmlOuter//services.xml";
		boolean fileWriterOverwriteStatus = false;

		for (String xmlPath : xmlPaths)
		{
			// System.out.println(xmlPath);
			switch (xmlPath)
			{
				case boquetPath:
					fileWriterOverwriteStatus = fileWriterObj.overwriteExistingFile(userDecisionToOverwrite, boquetPath,
							inputSatBoquetsXmlDoc);
					counter = checkIfFileOverwriteOperationSuccess(fileWriterOverwriteStatus, counter);
					break;
				case satellitesPath:
					fileWriterOverwriteStatus = fileWriterObj.overwriteExistingFile(userDecisionToOverwrite,
							satellitesPath, inputSatXmlDoc);
					counter = checkIfFileOverwriteOperationSuccess(fileWriterOverwriteStatus, counter);
					break;
				case servicesPath:
					fileWriterOverwriteStatus = fileWriterObj.overwriteExistingFile(userDecisionToOverwrite,
							servicesPath, inputSatServicesXmlDoc);
					counter = checkIfFileOverwriteOperationSuccess(fileWriterOverwriteStatus, counter);
					break;
			}
			// System.out.println(counter);
		}
		return counter;
	}

	private List<String> initPathsToXml(String boquetPath, String satellitesPath, String servicesPath)
	{
		List<String> xmlPaths = new ArrayList<String>();
		xmlPaths.add(boquetPath);
		xmlPaths.add(satellitesPath);
		xmlPaths.add(servicesPath);
		return xmlPaths;
	}

	private Integer checkIfFileOverwriteOperationSuccess(boolean filewWriterOverwriteStatus, Integer counter)
	{
		// System.out.println("status: " + filewWriterOverwriteStatus );
		if (filewWriterOverwriteStatus == true)
		{
			counter++;
		}
		return counter;
	}

	public void initializeSatModel(String inputPathForSat)
			throws ParserConfigurationException, SAXException, IOException
	{
		allSattelitesInfoObj = new SatModel();
		allSattelitesInfoObj.readAndSetUpDomDocument(inputPathForSat);
		allSattelitesInfoObj.calculateAmountOfSatellites();
		allSattelitesInfoObj.createSatInformationObjects();
	}

	private boolean deleteDirectory(File dir)
	{
		String pathToDir = dir.getPath();
		boolean result = false;

		if ((dir.exists() == true) && (dir.isDirectory() == true))
		{
			String[] elems = dir.list();
			deleteFilesInGivenDir(pathToDir, elems);
			result = deleteEmptyDir(dir, result);
		} else
		{
			result = false;
		}

		return result;
	}

	private void deleteFilesInGivenDir(String pathToDir, String[] elems)
	{
		File fileToBeDeleted;
		for (String files : elems)
		{
			fileToBeDeleted = new File(pathToDir + "\\" + files);
//			System.out.println(fileToBeDeleted.getPath() + " del " + fileToBeDeleted.delete());
		}
	}

	private boolean deleteEmptyDir(File dir, boolean result)
	{
		if (dir.isDirectory() == true)
		{
			result = dir.delete();
		}
		return result;
	}

	  public boolean  createDirectory(String Input) throws IOException {
            boolean result = false;
	        File dir = new File(Input);
	        if (dir.exists() == false && dir.isDirectory() == false ) {
	            	    result = dir.mkdir();
	            } else {
	                result = false;
	            }
            
        return result;
	    }

}
