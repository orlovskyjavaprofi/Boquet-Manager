package xmloutputertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import helperutils.ReadXmlAndCreateJdomWithAllSat;
import models.SatServicesModel;
import testutil.xmlConsolePrinter;
import xmloutputers.XmlOutputerForServices;

class xmlOutputerForServicesTest
{
	XmlOutputerForServices xmlOutputObjForServices;
	String pathToServicesXml;
	SatServicesModel satServicesObj;
	ReadXmlAndCreateJdomWithAllSat mockupJdomAllSatServices; 
	xmlConsolePrinter xmlOutputerObj;
	
	@BeforeEach
	void setUp() throws ParserConfigurationException, SAXException, IOException
	{
		xmlOutputObjForServices = new XmlOutputerForServices();
		pathToServicesXml ="src//main//resources//XML-Files-Update2018//services.xml";
		satServicesObj = new SatServicesModel();
		satServicesObj.readAndSetUpJDomDocument(pathToServicesXml);
		satServicesObj.buildAsetOfSatServices( 
				satServicesObj.readJdomDocumentAndCreate1rdLevelElementList() );
		xmlOutputerObj = new xmlConsolePrinter();
	}
	
	@Test
	void testingIfXmlOutputerForServicesObjectExist()
	{
		assertNotNull(xmlOutputObjForServices);
	}
	
	@Test
	void testingIfXmlOutputerWorkingWithPopulatedServicesModel() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputObjForServices.checkIfServicesModelSetUp(
				                                                                                              satServicesObj);
		
		assertEquals(expectedResult, actualResult, "ServicesModel wasn't setup");	
	}
	
	@Test
	void testingIfXmlOutputRootElementCreated() {
		String expectedResult = "zapit";
		String actualResult = xmlOutputObjForServices.getSatServicesXmlOutputDoc().
				getRootElement().getName();
		assertEquals(expectedResult, actualResult, "creating services xml root");
	}
	
	@Test
	void testingIfnewXmlDocumentIsNotNull() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputObjForServices.verifyXmlOutputerJdomDocument();
				
		assertEquals(expectedResult, actualResult, "checking that Jdom services "
				+ "document in Xml outputer is"	+ "set up");
	}
	
	@Test
	void testingIfXmlOutputerCreatesServicesXmlElements() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputObjForServices.creatingXmlElements(satServicesObj);
		
	    //xmlOutputerObj.printXmlResult(xmlOutputObjForServices.getSatServicesXmlOutputDoc());
		
		assertEquals(expectedResult, actualResult, "checking if services xml elements are not null");
	}
	
}
