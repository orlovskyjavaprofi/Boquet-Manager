package xmloutputertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import models.SatModel;
import testutil.xmlConsolePrinter;
import xmloutputers.XmlOutputerForSatellites;


class XmlOutputerForSatellitesTest
{
	XmlOutputerForSatellites xmlOutputObjForSatellites;
	SatModel allSattelitesInfoObj;
	String pathToSatellitesXml;
	xmlConsolePrinter xmlOutputerObj;
	
	@BeforeEach
	void setUp() throws ParserConfigurationException, SAXException, IOException
	{
		pathToSatellitesXml = "src//main//resources//XML-Files-Update2018//satellites.xml";
		xmlOutputObjForSatellites = new XmlOutputerForSatellites();
		allSattelitesInfoObj = new SatModel();
		allSattelitesInfoObj.readAndSetUpDomDocument(pathToSatellitesXml);
		allSattelitesInfoObj.calculateAmountOfSatellites();
      	allSattelitesInfoObj.createSatInformationObjects();
      	xmlOutputerObj = new xmlConsolePrinter();
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
		
		//xmlOutputerObj.printXmlResult(xmlOutputObjForSatellites.getSatXmlOutputDoc());
		
		assertEquals(expectedResult, actualResult, "checking if satellite xml elements are not null");

	}
	
}
