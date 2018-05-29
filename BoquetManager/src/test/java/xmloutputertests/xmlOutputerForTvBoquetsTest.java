package xmloutputertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import models.SatTvBoquetModel;
import testutil.xmlConsolePrinter;
import xmloutputers.XmlOutputerForTvBoquets;

class xmlOutputerForTvBoquetsTest
{
	XmlOutputerForTvBoquets xmlOutputerTvBoquetsObj;
	String pathToBoquetsXml;
	SatTvBoquetModel satTvBoquetModelObj;
	xmlConsolePrinter xmlOutputerObj;
	
	@BeforeEach
	void setUp() throws ParserConfigurationException, SAXException, IOException  {
		xmlOutputerTvBoquetsObj = new XmlOutputerForTvBoquets();
		satTvBoquetModelObj = new SatTvBoquetModel();
		pathToBoquetsXml = "src//main//resources//XML-Files-Update2018//bouquets.xml";
		satTvBoquetModelObj.readAndSetUpJDomDocument(pathToBoquetsXml);
		satTvBoquetModelObj.buildSetOfBoquets(satTvBoquetModelObj.
				readJdomDocumentAndCreateBouquetsElementList());  
		xmlOutputerObj = new xmlConsolePrinter();
	}
	
	@Test
	void testingIfXmlOutputerForSatTvBoquetsObjectExist()
	{
		assertNotNull(xmlOutputerTvBoquetsObj);
	}

	@Test
	void testingIfXmlOutputerWorkingWithPopulatedBoquetsModel() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputerTvBoquetsObj.
				checkIfBoquetsModelSetUp(satTvBoquetModelObj.getResultSetOfSatTvBoquets());
				
		assertEquals(expectedResult, actualResult, "BoquetsModel wasn't setup");	
	}
	
	@Test
	void testingIfXmlOutputRootElementCreated() {
		String expectedResult = "zapit";
		String actualResult = xmlOutputerTvBoquetsObj.getSatBoquetsXmlOutputDoc()
				                                                                        .getRootElement().getName();
		
		assertEquals(expectedResult, actualResult, "creating services xml root for boquets");		
	}
	
	@Test
	void testingIfnewXmlDocumentIsNotNull() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputerTvBoquetsObj.verifyXmlOutputerJdomDocument();
		
		assertEquals(expectedResult, actualResult, "checking that Jdom boquets "
				+ "document in Xml outputer is set up");
	}
	
	@Test
	void testingIfXmlOutputerCreatesBoquetsXmlElements() {
		boolean expectedResult = true;
		boolean actualResult = xmlOutputerTvBoquetsObj.
				creatingXmlElements(satTvBoquetModelObj);
//	    xmlOutputerObj.printXmlResult(xmlOutputerTvBoquetsObj.getSatBoquetsXmlOutputDoc());
		
		assertEquals(expectedResult, actualResult, "checking if services xml elements are not null");
	}
}
