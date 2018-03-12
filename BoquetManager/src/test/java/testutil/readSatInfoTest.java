package testutil;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import utilXmlReaders.SatXmlReader;

class readSatInfoTest
{
	SatXmlReader satXmlReaderObject;
	String PathToFile;

	@BeforeEach
	void setUp()
	{
		satXmlReaderObject = new SatXmlReader();
		PathToFile = "src//main//resources//XML-Files-Update2018//satellites.xml";
	}

	@Test
	void testingIfSatReaderObjectExist()
	{
		assertNotNull(satXmlReaderObject);
	}

	private boolean readXMLOperation(boolean resultOfGettingJDom)
	{
		try
		{
			resultOfGettingJDom = satXmlReaderObject.getDocumentFromDomParser(PathToFile);
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
			resultOfDomDocumenRead = satXmlReaderObject.readDomDocument(PathToFile);
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



}
