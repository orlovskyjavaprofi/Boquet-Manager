package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import helperutils.ReadXmlAndCreateJdomWithAllSat;
import models.SatServicesModel;

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
			result = satServicesObject.readAndSetUpAjDomDocument(PathToFile);
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

		assertEquals(true, result, "cannot read a Jdom document.");
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

		assertEquals(expectedResult, actualResult, "checking if SatServicesModel can save JDom Document from  Xml Reader");
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
	void checkIfSatServicesSetIsNotNull() {
		boolean expectedResult = true;
		boolean actualResult = false;
		ReadXmlAndCreateJdomWithAllSat allSat;
		
		try
		{
			allSat = new ReadXmlAndCreateJdomWithAllSat();
			allSat.readAndSetUpJDomDocument();
			satServicesObject.readAndSetUpAjDomDocument(PathToFile);
			actualResult = satServicesObject.buildAsetOfSatServices(allSat.readJdomDocumentAndCreate1rdLevelElementList());
//			System.out.println(satServicesObject.getSetOfSatelitesServices().toString());
			
			assertEquals(expectedResult, actualResult, "checking that sat services set is not null"	);
			
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}