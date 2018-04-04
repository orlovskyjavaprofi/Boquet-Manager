package buildertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import builders.SatTranspondersBuilder;
import helperutils.ReadXmlAndCreateJdomWithOneSat;
import models.SatTransponder;


class SatTransponderBuilderTest
{
	SatTranspondersBuilder satTranspondersBuilderObj;
	
    @BeforeEach
    void setUp()
	{
       	satTranspondersBuilderObj = new SatTranspondersBuilder();
	}

	@Test
	void testingIfSatTransponderBuilderObjectExist()
	{
		assertNotNull(satTranspondersBuilderObj);
	}

	@Test
	void checkIfSatTransponderInversionValidationWorks() {
		 Byte expectedTransponderInversion = 2;
		 Byte mockupValue = 1;
		 
		 satTranspondersBuilderObj.validateInversion(mockupValue);
		 Byte actualInversion = satTranspondersBuilderObj.getTransponderInversion();
		
		assertEquals(expectedTransponderInversion,actualInversion, "checking if polarisation of a transponder has a right value");
	}
	
	@Test
	void checkIfSatTransponderFecInnerValidationWorks() {
		boolean expectedResult = true;
		boolean actualResult = false;
		
		for (Byte b = 2; b <=5 ; b++)
		{
			actualResult = satTranspondersBuilderObj.validateFecInner(b);	
		}	
		
		assertEquals(expectedResult, actualResult,"checking if fecinner of a  transponder has a right value");
	}
	
	@Test 
	void checkIfSatTransponderFecInnerValidationFails(){
		Byte failingMockupValue= 0;
		
		boolean actualResult = satTranspondersBuilderObj.validateFecInner(failingMockupValue);
		
		assertFalse(actualResult);
	}
	
	@Test
	void checkIfSatTransponderPolarisationWorks() {
		boolean expectedResult = true;
		boolean actualResult = false;
		Byte polarisationVertical = 0;
		Byte polarisationHorizontal =1;
		
		actualResult = satTranspondersBuilderObj.validatePolarisation(polarisationVertical);
		actualResult = satTranspondersBuilderObj.validatePolarisation(polarisationHorizontal);
		
		assertEquals(expectedResult, actualResult,"checking if polarisation of a  transponder has a right value");
	}
	
	@Test
	void checkIfSatTransponderPolarizationFails() {
		Byte failingMockupValue= 6;
		boolean actualResult =satTranspondersBuilderObj.validatePolarisation(failingMockupValue);
		
		assertFalse(actualResult);
	}
	
	@Test
	void checkIfJdomSatteliteElementNotNull() {
	    boolean expectedResult= true;
	    ReadXmlAndCreateJdomWithOneSat helperMockSatElement = new ReadXmlAndCreateJdomWithOneSat();
	    
	    try
		{
			helperMockSatElement.readAndSetUpJDomDocument();
			Element sateliteJDomElement = 
					helperMockSatElement.readJdomDocumentAndCreate1rdLevelElementList();
    	        boolean actualResult = 
    	        		satTranspondersBuilderObj.verifySatJdomElement(sateliteJDomElement);
	   
	       	assertEquals(expectedResult, actualResult,"checking if JdomSat Element is not null");
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test
	void checkIfTranspondersListCanBeBuild() {
		boolean expectedResult = true;
		ReadXmlAndCreateJdomWithOneSat helperMockSatElement = 
				new ReadXmlAndCreateJdomWithOneSat();
		boolean actualResult = false;
		List<SatTransponder> listOfTransponders = new LinkedList<SatTransponder>();
		
		try {
			helperMockSatElement.readAndSetUpJDomDocument();
			Element sateliteJDomElement = 
					helperMockSatElement.readJdomDocumentAndCreate1rdLevelElementList();
			listOfTransponders = 
					satTranspondersBuilderObj.buildTransponderSatList(sateliteJDomElement);
			
			if (listOfTransponders.isEmpty() == false) {
				actualResult = true;
//				System.out.println(listOfTransponders.toString());
			}
			
			assertEquals(expectedResult, actualResult,"checking if TranspondersList can be created");
		}catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
