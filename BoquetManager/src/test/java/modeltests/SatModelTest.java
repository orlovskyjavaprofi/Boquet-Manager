package modeltests;

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
	void testingifSatModelDomDocumentSetUP()
	{
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

		assertEquals(expectedResult, actualResult, "checking if SatModel can Save Dom Document form Xml Reader");
	}

	@Test
	void checkAmountOfSatellites()
	{
		Integer expectedResult = 3;
		Integer actualResult = 0;

		try
		{
			satModelObject.readAndSetUpDomDocument(PathToFile);
			actualResult = satModelObject.calculateAmountOfSatellites();
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

		assertEquals(expectedResult, actualResult, "checking amount of sattelite");
	}

	@Test
	void checkIfTheAmountOfSatellitesIsOk()
	{
		Integer expectedResult = 3;
		Integer actualResult = 0;

		try
		{
			settingUpSatModelAndCalculatingAmountOfSatellites();
			actualResult = satModelObject.getAmountOfSatellites();
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

		assertEquals(expectedResult, actualResult, "checking  sattelite model did saved amount of sattelites");
	}

	private void settingUpSatModelAndCalculatingAmountOfSatellites()
			throws ParserConfigurationException, SAXException, IOException
	{
		satModelObject.readAndSetUpDomDocument(PathToFile);
		satModelObject.calculateAmountOfSatellites();
	}

	@Test
	void checkIfSatInformationObjectsAreCreated()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();
			actualResult = satModelObject.createSatInformationObjects();

			assertEquals(expectedResult, actualResult, "checking if satinformation objects are created");

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
	}

	@Test
	void checkIfCreatedSatInformationObjectsAreNotNull()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();
			satModelObject.createSatInformationObjects();
			actualResult = satModelObject.checkIfSatInfoObjectNotNull();
			// System.out.println("Result "+
			// satModelObject.getSortedSatellitesInformationSet().toString());

			assertEquals(expectedResult, actualResult, "checking that one of the satInfo Objects are not null");

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

	}
}
