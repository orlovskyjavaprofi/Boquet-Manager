package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Element;
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

	@Test
	void checkIfCreatedSatInformationObjectsConsistFlags()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();
			actualResult = satModelObject.checkIfSatInfoObjectFlagsList();
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

	@Test
	void checkFlagsFromJdomDocument()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();
			actualResult = satModelObject.checkIfFlagsFromJdomDocumentSet();

			assertEquals(expectedResult, actualResult, "checking if SatModel did loaded flags valuesl");
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
	void checkPositionFromJdomDocument()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();

			actualResult = satModelObject.checkIfPositionFromJdomDocumentSet();
//			 System.out.println("Result "+
//			 satModelObject.getSortedSatellitesInformationSet().toString());

			assertEquals(expectedResult, actualResult, "checking if SatModel did loaded flags valuesl");

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
	public void checkContentsOfNewCreatedSaInfoObjectIsnotNull() {
		boolean expectedResult = true;
		boolean actualResult = false;
		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();
			
			System.out.println( satModelObject.getSortedSatellitesInformationSet().toString());

			 actualResult = satModelObject.checkContentsOfSatInfoObjectNotNull();
			
			 
			assertEquals(expectedResult, actualResult, "checking if SatInfo Object have a content");
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
	public void checkIfJdomElementListCanBeConvertedToAStringList()
	{
		List<Element> inputElementList = creatingAJDomElementMockupInteger();
		List<Integer> actualResult = new LinkedList<Integer>();
		List<Integer> expectedResult = new LinkedList<Integer>();

		for (int i = 1; i <= 4; i++)
		{
			expectedResult.add(i);
		}

		try
		{
			this.settingUpSatModelAndCalculatingAmountOfSatellites();
			actualResult = satModelObject.convertAJdomElementListToIntegerList(inputElementList);

			assertEquals(expectedResult, actualResult, "checking if Jdom Element List was converted to a String Listl");
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

	private List<Element> creatingAJDomElementMockupInteger()
	{
		
		List<Element> testingList = new LinkedList<Element>();
		Element elem [] = new Element[4];
        int counter =0;
        
		for (int i = 0; i < elem.length; i++)
		{
			elem[i] = new Element("test"+ counter++);
			elem[i].setAttribute(new Attribute("test","test"+counter));
			elem[i].setText( Integer.toString(counter) );
			testingList.add(elem[i]);
		}

		return testingList;
	}

}
