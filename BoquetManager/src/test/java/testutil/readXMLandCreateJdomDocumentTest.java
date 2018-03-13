package testutil;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

class readXMLandCreateJdomDocumentTest
{
	XmlReaderAndJdomDocumentCreator xmlReaderObject;
	String PathToFile;

	@BeforeEach
	void setUp()
	{
		PathToFile = "src//main//resources//XML-Files-Update2018//services.xml";
		xmlReaderObject = new XmlReaderAndJdomDocumentCreator(PathToFile);
	}

	@Test
	void testingIfXmlReaderObjectExist()
	{
		assertNotNull(xmlReaderObject);
	}

	private boolean readXMLOperation(boolean resultOfGettingJDom)
	{
		try
		{
			resultOfGettingJDom = xmlReaderObject.getDocumentFromDomParser(PathToFile);
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
		return resultOfGettingJDom;
	}
	
	@Test
	void testingIfSatDomDocumentReadSuccess()
	{
		boolean resultOfDomDocumenRead = false;
		try
		{
			resultOfDomDocumenRead = xmlReaderObject.readDomDocument(PathToFile);
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

		assertTrue(resultOfDomDocumenRead);
	}

	@Test
	void testIfJdomDocumentWasRead()
	{
		boolean resultOfGettingJDom = false;
		resultOfGettingJDom = readXMLOperation(resultOfGettingJDom);

		assertTrue(resultOfGettingJDom);
	}

	@Test
	void testXmlReaderPathToXmlFile() {
        String actualPath= xmlReaderObject.getPathToFile();

        assertEquals(PathToFile,actualPath, "checking if the path to a given xml is correct");		
	}


}
