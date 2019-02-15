package buildertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import builders.SatServicesListBuilder;
import fileutils.ReadXmlAndCreateJdomWithAllSat;
import models.SatServicesList;

class SatServicesListBuilderTest
{
	SatServicesListBuilder satServiceListBuilderObj;

	@BeforeEach
	void setUp()
	{
		satServiceListBuilderObj = new SatServicesListBuilder();
	}

	@Test
	void testingIfSatServiceListBuilderObjectExist()
	{
		assertNotNull(satServiceListBuilderObj);
	}
	
	@Test
	void checkingIfDiseqcValid() {
		boolean expectedResult = true;
		boolean actualResult = false;
		
		for (Byte b = 0; b <=3 ; b++)
		{
			actualResult = satServiceListBuilderObj.validateDiseqc(b);	
		}
		
		assertEquals(expectedResult, actualResult,"checking if diseq of a  service  has a right value");
	}

	@Test 
	void checkIfSatTransponderFecInnerValidationFails(){
		Byte failingMockupValue= 6;
		
		boolean actualResult =  satServiceListBuilderObj.validateDiseqc(failingMockupValue);	
		
		assertFalse(actualResult);
	}
	
	@Test
	void checkIfServicesSetIsNotNullAndServiceListCanBeBuild() {
		boolean expectedResult = true;
		boolean actualResult = false;
		String pathToXml = "src//main//resources//XML-Files-Update2018//services.xml";
		ReadXmlAndCreateJdomWithAllSat helperObjAllSats =
				new ReadXmlAndCreateJdomWithAllSat(pathToXml);
		SortedSet<SatServicesList> setOfSortedSatellitesServices = new TreeSet<SatServicesList>();
		List<Element> listOfSatellites = new LinkedList<Element>();
		
		try
		{
			helperObjAllSats.readAndSetUpJDomDocument();
			listOfSatellites = helperObjAllSats.readJdomDocumentAndCreate1rdLevelElementList();
			setOfSortedSatellitesServices =   
					satServiceListBuilderObj.buildSatServicesSet(listOfSatellites);
					
			actualResult = checkIfServicesSetIsNotEmpty(actualResult, setOfSortedSatellitesServices);
			
			assertEquals(expectedResult, actualResult,"checking if SatServices set can be created");
			
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private boolean checkIfServicesSetIsNotEmpty(boolean actualResult,
			SortedSet<SatServicesList> setOfSortedSatellitesServices)
	{
		if(setOfSortedSatellitesServices.isEmpty() == false) {
//				System.out.println(setOfSortedSatellitesServices.toString() );
			actualResult = true;
		}
		return actualResult;
	}
	
	
}
