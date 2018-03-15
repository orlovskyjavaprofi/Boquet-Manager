package models;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class SatModel
{
	private org.jdom2.Document satJdomDocument;
	private Integer AmountOfSatellites;
	private SortedSet<SatInformation> sortedSatellitesInformationSet = new TreeSet<SatInformation>();
	private SortedSet<String> namesOfSatellitesSet = new TreeSet<String>();

	public boolean readAndSetUpDomDocument(String inputPath)
			throws ParserConfigurationException, SAXException, IOException
	{
		XmlReaderAndJdomDocumentCreator xmlReader = new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(xmlReader.getPathToFile());

		if (xmlReaderStatus == true)
		{
			this.setSatJdomDocument(xmlReader.getJDomDocumentResult());
		}

		return checkIfJDocumentWasCreated(xmlReaderStatus);
	}

	private boolean checkIfJDocumentWasCreated(boolean xmlReaderStatus)
	{
		if (xmlReaderStatus == true)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkIfDomDocumetIsSetUp()
	{
		if (this.getSatJdomDocument().hasRootElement() == true)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public Integer calculateAmountOfSatellites()
	{
		int amountOfSats = 0;

		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("sat");
		amountOfSats = checkNamesOfSatsAndCalculateAmount(amountOfSats, empListElements);
		this.setAmountOfSatellites(amountOfSats);
		
		return amountOfSats;
	}

	private int checkNamesOfSatsAndCalculateAmount(int amountOfSats, List<Element> empListElements)
	{
		String satName = "";
		for (Element empElement : empListElements)
		{
			satName = empElement.getAttributeValue("name");
			if (satName.isEmpty() == false)
			{
				amountOfSats++;
				this.getNamesOfSatellitesSet().add(satName);
			}
		}
		return amountOfSats;
	}

	public boolean createSatInformationObjects()
	{
		Integer amountOfSat = this.getAmountOfSatellites();

		if (amountOfSat.intValue() > 0)
		{
			buildingSatInformationObjects(amountOfSat, sortedSatellitesInformationSet);
			return true;
		} else
		{
			return false;
		}
	}

	private void buildingSatInformationObjects(Integer amountOfSat, SortedSet<SatInformation> sortedSatellitesSet)
	{
		SatInformation satInoObject;
		Iterator<String> iteratorOverSatInfoNames = this.getNamesOfSatellitesSet().iterator();

		for (int i = 0; i < amountOfSat; i++)
		{
			satInoObject = new SatInformation(iteratorOverSatInfoNames.next());
			sortedSatellitesSet.add(satInoObject);
		}
		this.setSortedSatellitesInformationSet(sortedSatellitesSet);
	}

	public boolean checkIfSatInfoObjectNotNull()
	{
		SortedSet<SatInformation> sortedSatellitesSet = this.getSortedSatellitesInformationSet();
		Iterator<SatInformation> it = sortedSatellitesSet.iterator();
		SatInformation satInfoObj;
		boolean result = false;

		while (it.hasNext())
		{
			// Get element
			satInfoObj = it.next();
			result = checkIfsatInfoObjectNotNull(satInfoObj);
			if (result == false) {
				break;
			}
		}

		return result;
	}

	private boolean checkIfsatInfoObjectNotNull(SatInformation satInfoObj)
	{
		boolean result;
		if (satInfoObj == null)
		{
			result = false;
		} else
		{
			result = true;
		}
		
		return result;
	}

	public Integer getAmountOfSatellites()
	{
		return AmountOfSatellites;
	}

	public void setAmountOfSatellites(Integer amountOfSatellites)
	{
		AmountOfSatellites = amountOfSatellites;
	}

	public SortedSet<SatInformation> getSortedSatellitesInformationSet()
	{
		return sortedSatellitesInformationSet;
	}

	public void setSortedSatellitesInformationSet(SortedSet<SatInformation> sortedSatellitesSet)
	{
		this.sortedSatellitesInformationSet = sortedSatellitesSet;
	}

	public SortedSet<String> getNamesOfSatellitesSet()
	{
		return namesOfSatellitesSet;
	}

	public void setNamesOfSatellitesSet(SortedSet<String> namesOfSatellitesSet)
	{
		this.namesOfSatellitesSet = namesOfSatellitesSet;
	}

	public org.jdom2.Document getSatJdomDocument()
	{
		return satJdomDocument;
	}

	public void setSatJdomDocument(org.jdom2.Document satJdomDocument)
	{
		this.satJdomDocument = satJdomDocument;
	}

}
