package xmloutputertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import models.SatModel;
import xmloutputers.XmlOutputerForSatellites;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


class xmlOutputerForSatellitesTest
{
	XmlOutputerForSatellites xmlOutputObjForSatellites;
	SatModel allSattelitesInfoObj;
	String pathToSatellitesXml;
	
	@BeforeEach
	void setUp() throws ParserConfigurationException, SAXException, IOException
	{
		pathToSatellitesXml = "src//main//resources//XML-Files-Update2018//satellites.xml";
		xmlOutputObjForSatellites = new XmlOutputerForSatellites();
		allSattelitesInfoObj = new SatModel();
		allSattelitesInfoObj.readAndSetUpDomDocument(pathToSatellitesXml);
		allSattelitesInfoObj.calculateAmountOfSatellites();
      	allSattelitesInfoObj.createSatInformationObjects();
      	xmlOutputObjForSatellites.createXmlRoot();
	}
		
	@Test
	void testingIfXmlOutputerForSatellitesObjectExist()
	{
		assertNotNull(xmlOutputObjForSatellites);
	}
	
	@Test
	void testinIfXmlOutputerWorkingWithPopulatedSatModel() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputObjForSatellites.
				            verifyThaSatModelIsSetUp(allSattelitesInfoObj);
		assertEquals(expectedResult, actualResult, "SatModel wasn't setup");	
	}
	
	@Test
	void testingIfXmlOutputRootElement() {
		String expectedResult = "satellites";
		String actualResult = xmlOutputObjForSatellites.getSatXmlOutputDoc().
				getRootElement().getName();
		
		assertEquals(expectedResult, actualResult, "creating sattelites xml root");
	}

	@Test
	void testingThatXmlOutputerJdomDocumentIsSetup() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputObjForSatellites.verifyXmlOutputerJdomDocument();

		assertEquals(expectedResult, actualResult, "checking that Jdom document in Xml outputer is"
				+ "set up");
	}
	
	@Test
	void testingIfXmlOutputerCreatesXmlElements() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputObjForSatellites.
				creatingXmlElements(allSattelitesInfoObj);
		// this.printXmlResult(xmlOutputObjForSatellites);
		
		assertEquals(expectedResult, actualResult, "checking if xml elements are not null");

	}
	
	public void printXmlResult(XmlOutputerForSatellites inputXml) {  
	    
	    XMLOutputter xmlOutput = new XMLOutputter();
	     Format f = Format.getPrettyFormat();  
	     f.setEncoding("iso-8859-1");
	     xmlOutput.setFormat(f);

		 try
		{
			xmlOutput.output(inputXml.getSatXmlOutputDoc(), System.out);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
