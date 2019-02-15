package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import fileutils.ReadXmlAndCreateJdomWithAllSat;
import models.SatServicesModel;
import models.SatServicesList;

public class SatServicesModelTest
{
	
	String PathToFile;
	SatServicesModel satServicesObject;
	
	@BeforeEach
	void setUp()
	{
		satServicesObject = new SatServicesModel();
		PathToFile = "src//main//resources//XML-Files-Update2018//services.xml";
	}

	@Test
	void testingIfSatServicesObjectExist()
	{
		assertNotNull(satServicesObject);
	}
	
	@Test
	void testingIfSatServicesCanReadAJDomDocument()
	{
		boolean result = false;
		
		try
		{
			result = satServicesObject.readAndSetUpJDomDocument(PathToFile);
			
			assertEquals(true, result, "cannot read a Jdom document.");
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	@Test
	void testingifSatServicesModelJDomDocumentSetUP()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		try
		{		                                  
			satServicesObject.readAndSetUpJDomDocument(PathToFile);
			actualResult = satServicesObject.checkIfJDomDocumetIsSetUp();
			
			assertEquals(expectedResult, actualResult, "checking if SatServicesModel can save JDom Document from  Xml Reader");
			
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	@Test
	void checkAmountOfSatellites()
	{
		Integer expectedResult = 3;
		Integer actualResult = 0;

		try
		{
			satServicesObject.readAndSetUpJDomDocument(PathToFile);
			satServicesObject.calculateAmountOfSatellitesForServices();
			actualResult = satServicesObject.getAmountOfSatellites();
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, actualResult, "checking amount of services  with channels"	);
	}
	
	@Test
	void checkIfAListOfJdomElementsIsCreated()
	{
		boolean expectedResult = true;
		boolean actualResult = false;
		
		try
		{
			satServicesObject.readAndSetUpJDomDocument(PathToFile);
			List<Element> empListElem = satServicesObject.
					readJdomDocumentAndCreate1rdLevelElementList();

			if (empListElem.isEmpty() == false)
			{
				actualResult = true;
			}

			assertEquals(expectedResult, actualResult, "checking if a list of Jdom elements is created");

		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void checkIfSatServicesSetIsNotNull() {
		boolean expectedResult = true;
		boolean actualResult = false;
		ReadXmlAndCreateJdomWithAllSat allSat;

		try
		{
			satServicesObject.readAndSetUpJDomDocument(PathToFile);
			actualResult = satServicesObject	.buildAsetOfSatServices(
					satServicesObject.readJdomDocumentAndCreate1rdLevelElementList()
			);
			
//			printListOfServicesToConsole();
			
			assertEquals(expectedResult, actualResult, "checking that sat services set is not null"	);
			
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void printListOfServicesToConsole()
	{
		SortedSet<SatServicesList> listofServices;
		listofServices = satServicesObject.getSetOfSatelitesServices();
		for (SatServicesList satServicesList : listofServices)
		{
		   System.out.println(satServicesList.toString());
		}
	}

}