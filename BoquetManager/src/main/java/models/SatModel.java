package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class SatModel
{
	private org.jdom2.Document satJdomDocument;
	private Integer AmountOfSatellites;
	private SortedSet<SatInformation> sortedSatellitesInformationSet = new TreeSet<SatInformation>();
	private Set<String> namesOfSatellitesSet = new LinkedHashSet<String>();
	private List<Integer> flagsOfSatellitesList = new ArrayList<Integer>();
	private List<Integer> positionOfSatellitesList = new ArrayList<Integer>();
	private List<Integer> transponderOFSatellitesFrequencyList = new LinkedList<Integer>();

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
		
		for (Element oneLevelDeepIntoSat : empListElements)
		{
			satName = oneLevelDeepIntoSat.getAttributeValue("name");

			amountOfSats = checkIfSatHasANameAndSetSatInfoObject(amountOfSats, satName, oneLevelDeepIntoSat);
		}

		return amountOfSats;
	}

	private int checkIfSatHasANameAndSetSatInfoObject(int amountOfSats, String satName, Element oneLevelDeepIntoSat)
	{
		Integer satFlags = 0;
		Integer satPosition = 0;	
		List<Integer> transponderOFSatellitesFrequencyList = new LinkedList<Integer>();
		
		if ((satName.isEmpty() == false))
		{
			satFlags = Integer.parseInt(oneLevelDeepIntoSat.getAttributeValue("flags"));
			satPosition = Integer.parseInt(oneLevelDeepIntoSat.getAttributeValue("position"));
			transponderOFSatellitesFrequencyList = parsingSecondLevelXml(
					oneLevelDeepIntoSat.getChildren("transponder"));	
			settingUpDataForSatInfoObject(satName, satFlags, satPosition, 
					transponderOFSatellitesFrequencyList);			
			amountOfSats++;
		}
		return amountOfSats;
	}

	private List<Integer> parsingSecondLevelXml(List<Element> oneLevelDeepIntoSat)
	{
		List<Element> twoLevelsDeep = oneLevelDeepIntoSat;
		transponderOFSatellitesFrequencyList = new LinkedList<Integer>();

		for (Element twoLevelDeepIntoSat : twoLevelsDeep)
		{
			Attribute attributeFrequency = twoLevelDeepIntoSat.getAttribute("frequency");
			transponderOFSatellitesFrequencyList.add(
					Integer.parseInt(attributeFrequency.getValue())
			);
		}

		return transponderOFSatellitesFrequencyList;
	}

	private void settingUpDataForSatInfoObject(String satName, Integer satFlags, Integer satPosition,
			List<Integer> transponderOFSatellitesFrequencyList)
	{
		this.getFlagsOfSatellitesList().add(satFlags);
		this.getPositionOfSatellitesList().add(satPosition);
		this.getNamesOfSatellitesSet().add(satName);
		this.setTransponderOFSatellitesFrequencyList(transponderOFSatellitesFrequencyList);
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
		Iterator<String> iteratorOverSatInfoNames = this.getNamesOfSatellitesSet().iterator();
		Iterator<Integer> iteratorOverSatInfoFlags = this.getFlagsOfSatellitesList().iterator();
		Iterator<Integer> iteratorOverSatInfoPosition = this.getPositionOfSatellitesList().iterator();
		List<Integer> satInfoTransponderFrequencyList = this.getTransponderOFSatellitesFrequencyList();

		constructingAndAddingSatInfoObjectToSet(amountOfSat, sortedSatellitesSet, iteratorOverSatInfoNames,
				iteratorOverSatInfoFlags, iteratorOverSatInfoPosition, satInfoTransponderFrequencyList);

		this.setSortedSatellitesInformationSet(sortedSatellitesSet);
	}

	private void constructingAndAddingSatInfoObjectToSet(Integer amountOfSat,
			SortedSet<SatInformation> sortedSatellitesSet, Iterator<String> iteratorOverSatInfoNames,
			Iterator<Integer> iteratorOverSatInfoFlags, Iterator<Integer> iteratorOverSatInfoPosition,
			List<Integer> satInforTransponderFrequencyList)
	{
		SatInformation satInoObject;
		for (int i = 0; i < amountOfSat; i++)
		{
			satInoObject = creatingNewSatInfoObject(iteratorOverSatInfoNames, iteratorOverSatInfoFlags,
					iteratorOverSatInfoPosition, satInforTransponderFrequencyList);

			sortedSatellitesSet.add(satInoObject);
		}
	}

	private SatInformation creatingNewSatInfoObject(Iterator<String> iteratorOverSatInfoNames,
			Iterator<Integer> iteratorOverSatInfoFlags, Iterator<Integer> iteratorOverSatInfoPosition,
			List<Integer> iteratorOVerSatInforTransponderFrequencyList)
	{
		SatInformation satInoObject;
		satInoObject = new SatInformation(iteratorOverSatInfoNames.next(), iteratorOverSatInfoFlags.next(),
				iteratorOverSatInfoPosition.next(), iteratorOVerSatInforTransponderFrequencyList);
		return satInoObject;
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
			if (result == false)
			{
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

	public Set<String> getNamesOfSatellitesSet()
	{
		return namesOfSatellitesSet;
	}

	public void setNamesOfSatellitesSet(Set<String> namesOfSatellitesSet)
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

	public boolean checkIfFlagsFromJdomDocumentSet()
	{
		if (this.getFlagsOfSatellitesList() != null)
		{
			return true;
		} else
		{
			return false;
		}

	}

	public List<Integer> getFlagsOfSatellitesList()
	{
		return flagsOfSatellitesList;
	}

	public void setFlagsOfSatellitesList(List<Integer> flagsOfSatellitesList)
	{
		this.flagsOfSatellitesList = flagsOfSatellitesList;
	}

	public boolean checkIfSatInfoObjectFlagsList()
	{
		List<Integer> currentFlag = this.getFlagsOfSatellitesList();

		return checkIfANumbersInProvidedListArecorrect(currentFlag);
	}

	private boolean checkIfANumbersInProvidedListArecorrect(List<Integer> inputList)
	{
		if (inputList.toString().isEmpty() != true)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkIfPositionFromJdomDocumentSet()
	{
		List<Integer> currentPosition = this.getPositionOfSatellitesList();

		return checkIfANumbersInProvidedListArecorrect(currentPosition);
	}

	public List<Integer> getPositionOfSatellitesList()
	{
		return positionOfSatellitesList;
	}

	public void setPositionOfSatellitesList(List<Integer> positionOfSatellitesList)
	{
		this.positionOfSatellitesList = positionOfSatellitesList;
	}

	public List<Integer> convertAJdomElementListToIntegerList(List<Element> inputJdomElemList)
	{
		List<Integer> res = new LinkedList<>();

		for (int count = 0; count < inputJdomElemList.size(); count++)
		{
			res.add(Integer.parseInt(inputJdomElemList.get(count).getText()));
		}
		return res;
	}

	public List<Integer> getTransponderOFSatellitesFrequencyList()
	{
		return transponderOFSatellitesFrequencyList;
	}

	public void setTransponderOFSatellitesFrequencyList(List<Integer> transponderOFSatellitesFrequencyList)
	{
		this.transponderOFSatellitesFrequencyList = transponderOFSatellitesFrequencyList;
	}

}
