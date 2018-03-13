package testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import models.SatModel;
import utils.XmlReaderAndJdomDocumentCreator;

class SatModelTest
{
	SatModel satModelObject;
	String PathToFile;
	XmlReaderAndJdomDocumentCreator xmlReaderObject;

	@BeforeEach
	void setUp()
	{
		satModelObject = new SatModel();
		PathToFile = "src//main//resources//XML-Files-Update2018//satellites.xml";
	}

	@Test
	void testingIfSatModelObjectExist()
	{
		assertNotNull(satModelObject);
	}

	@Test
	void testingIfSatModelCanReadADomDocument()
	{

		boolean result = false;
		try
		{
			result = satModelObject.readAndSetUpDomDocument(PathToFile);
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

		assertEquals(true, result, "cannot read a dom document.");
	}

	@Test
	void testingifSatModelDomDocumentSetUP() {
		boolean expectedResult = true;
		boolean actualResult = false;
		
		try
		{
			satModelObject.readAndSetUpDomDocument(PathToFile);
			 actualResult = satModelObject.checkIfDomDocumetIsSetUp();
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

		assertEquals(expectedResult,actualResult,"checking if SatModel can Save Dom Document form Xml Reader");
	}
}
