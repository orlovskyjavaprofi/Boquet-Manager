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
	private List<Integer> transponderOFSatellitesSymbolRateList = new LinkedList<Integer>();
	private List<Byte> transponderOfSatellitesPolarizationList = new LinkedList<Byte>();
	private List<Byte> transponderOfSatellitesFecInnerList = new LinkedList<Byte>();
	
	public boolean readAndSetUpDomDocument(String inputPath)
			throws ParserConfigurationException, SAXException, IOException
	{
		XmlReaderAndJdomDocumentCreator xmlReader = new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(xmlReader.getPathToFile());

		if (xmlReaderStatus == true)
		{
			this.setSatJdomDocument(xmlReader.getJDomDocumentResult());
		}

		return checkIfJDomDocumentWasCreated(xmlReaderStatus);
	}

	private boolean checkIfJDomDocumentWasCreated(boolean xmlReaderStatus)
	{
		if (xmlReaderStatus == true)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkIfJDomDocumetIsSetUp()
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
		amountOfSats = checkNameOfSatAndCreateInformationObject(amountOfSats, empListElements);
		this.setAmountOfSatellites(amountOfSats);
       
		return amountOfSats;
	}

	private int checkNameOfSatAndCreateInformationObject(int amountOfSats, 
			List<Element> empListElements)
	{
		String satName = "";	
		
		for (Element oneLevelDeepIntoSat : empListElements)
		{
			satName = oneLevelDeepIntoSat.getAttributeValue("name");

			amountOfSats = checkIfSatHasANameAndSetSatInfoObjectCalculateAmountSats(amountOfSats, satName, 
					oneLevelDeepIntoSat);
			this.setAmountOfSatellites(amountOfSats);
			this.createSatInformationObjects(); 
		}

		return amountOfSats;
	}

	private int checkIfSatHasANameAndSetSatInfoObjectCalculateAmountSats(int amountOfSats, 
			String satName, Element oneLevelDeepIntoSat)
	{
		Integer satFlags = 0;
		Integer satPosition = 0;	
		
		if ((satName.isEmpty() == false))
		{
			satFlags = Integer.parseInt(oneLevelDeepIntoSat.getAttributeValue("flags"));
			satPosition = Integer.parseInt(oneLevelDeepIntoSat.getAttributeValue("position"));
			List<Integer> satellitesFrequencyList = new LinkedList<Integer>();
			List<Integer> satellitesSymbolRateList = new LinkedList<Integer>();
			List<Byte> satellitesPolarizationList = new LinkedList<Byte>();
			List<Byte> satellitesFecInnerList = new LinkedList<Byte>();
			
			satellitesFrequencyList = parsingSecondlevelXmlsatFrequencyList(oneLevelDeepIntoSat);	
			satellitesSymbolRateList = parsingSecondlevelXmlsatSymbolrateList(oneLevelDeepIntoSat);
			satellitesPolarizationList = parsingSecondlevelXmlPolarizationList(oneLevelDeepIntoSat);
			satellitesFecInnerList = parsingSecondlevelXmlFecInnerList(oneLevelDeepIntoSat);
			
			settingUpDataForSatInfoObject(	satName, 	satFlags, satPosition,
					                         satellitesFrequencyList, satellitesSymbolRateList, 
					                         satellitesPolarizationList,satellitesFecInnerList	);	
			
			amountOfSats++;		
		}
		return amountOfSats;
	}

	@SuppressWarnings("unchecked")
	private List<Byte> parsingSecondlevelXmlFecInnerList(Element oneLevelDeepIntoSat)
	{
		return (List<Byte>)(Object)parsingSecondLevelXml(	
				oneLevelDeepIntoSat.getChildren("transponder"), "fec_inner" );
	}

	@SuppressWarnings({ "unchecked" })
	private List<Byte> parsingSecondlevelXmlPolarizationList(Element oneLevelDeepIntoSat)
	{
		return (List<Byte>)(Object)parsingSecondLevelXml(	
				oneLevelDeepIntoSat.getChildren("transponder"), "polarization"	);
	}

	@SuppressWarnings("unchecked")
	private List<Integer> parsingSecondlevelXmlsatSymbolrateList(Element oneLevelDeepIntoSat)
	{
		return (List<Integer>)(Object)parsingSecondLevelXml(	
				oneLevelDeepIntoSat.getChildren("transponder"), "symbol_rate"	);
	}

	@SuppressWarnings("unchecked")
	private List<Integer> parsingSecondlevelXmlsatFrequencyList(Element oneLevelDeepIntoSat)
	{
		List<Integer> satellitesFrequencyList;
		satellitesFrequencyList = (List<Integer>)(Object)parsingSecondLevelXml(	
				oneLevelDeepIntoSat.getChildren("transponder"), "frequency"	);
		return satellitesFrequencyList;
	}

	private void settingUpDataForSatInfoObject(String satName, 
			Integer satFlags,
			Integer satPosition,
			List<Integer> satellitesFrequencyList, 
			List<Integer>satellitesSymbolRateList,
			List<Byte> satellitesPolarizationList,
			List<Byte> satellitesFecInnerList
			)
	{
		initAllSatInfoAttributes(satName, satFlags, satPosition, satellitesFrequencyList, satellitesSymbolRateList,
				satellitesPolarizationList, satellitesFecInnerList);
	}

	private void initAllSatInfoAttributes(String satName, Integer satFlags, Integer satPosition,
			List<Integer> satellitesFrequencyList, List<Integer> satellitesSymbolRateList,
			List<Byte> satellitesPolarizationList, List<Byte> satellitesFecInnerList)
	{
		initSatInfoFirstLevelAtributes(satName, satFlags, satPosition);
		initSatInfoSecondLevelAtributes(satellitesFrequencyList, satellitesSymbolRateList, satellitesPolarizationList,
				satellitesFecInnerList);
	}

	private void initSatInfoSecondLevelAtributes(List<Integer> satellitesFrequencyList,
			List<Integer> satellitesSymbolRateList, List<Byte> satellitesPolarizationList,
			List<Byte> satellitesFecInnerList)
	{
		this.setTransponderOFSatellitesFrequencyList(satellitesFrequencyList);
		this.setTransponderOFSatellitesSymbolRateList(satellitesSymbolRateList);
		this.setTransponderOfSatellitesPolarizationList(satellitesPolarizationList);
		this.setTransponderOfSatellitesFecInnerList(satellitesFecInnerList);
	}

	private void initSatInfoFirstLevelAtributes(String satName, Integer satFlags, Integer satPosition)
	{
		this.getFlagsOfSatellitesList().add(satFlags);
		this.getPositionOfSatellitesList().add(satPosition);
		this.getNamesOfSatellitesSet().add(satName);
	}
	
	private List<Object> parsingSecondLevelXml(List<Element> inputoneLevelDeepIntoSat,
			String attributName)
	{
		List<Element> twoLevelsDeep = inputoneLevelDeepIntoSat;
		List<Integer> resultIntegerList = new LinkedList<Integer>();
		List <Byte> resultByteList = new LinkedList<Byte>();
		List<Object> result = new LinkedList<Object>();
		
		for (Element twoLevelDeepIntoSat : twoLevelsDeep)
		{
			Attribute attribute = twoLevelDeepIntoSat.getAttribute(attributName);	
			
			switch(attributName) {
				case "frequency":
					resultIntegerList = addFrequencyElemToTransponder(attribute,resultIntegerList);
					break;
				case "symbol_rate":
					resultIntegerList = addSymbolRateElemToTransponder(attribute,resultIntegerList);
					break;
				case "polarization":
					resultByteList  =  addPolarizationElemToTransponder(attribute,resultByteList);
					break;
				case "fec_inner":
					resultByteList  =  addFecInnerElemToTransponder(attribute,resultByteList);
					break;
			}	
		}
		
		result = analyzingAReturnTypeListForSatInfoObject(
				attributName, resultIntegerList, resultByteList, result);	
		
			return result;		
	}

	@SuppressWarnings("unchecked")
	private List<Object> analyzingAReturnTypeListForSatInfoObject(String attributName,
			List<Integer> resultIntegerList,	List<Byte> resultByteList, List<Object> result)
	{
		switch(attributName) {
			case "frequency":
				result = (List<Object>)(Object)resultIntegerList;
				break;
			case "symbol_rate":
				result = (List<Object>)(Object)resultIntegerList;
				break;
			case "polarization":
				result = (List<Object>)(Object)resultByteList;
				break;
			case "fec_inner":
				result = (List<Object>)(Object)resultByteList;
				break;
		}
		return result;
	}

	private List<Integer> addFrequencyElemToTransponder(Attribute attributeFrequency,
			List<Integer> inputResultIntegerList)
	{
		inputResultIntegerList.add(
				Integer.parseInt(attributeFrequency.getValue())
		);
		return inputResultIntegerList;
	}
	
	private  List<Integer> addSymbolRateElemToTransponder(Attribute attributeSymbolRate, 
			List<Integer> inputResultIntegerList)
	{
		inputResultIntegerList.add(
				Integer.parseInt(attributeSymbolRate.getValue())
		);
		return inputResultIntegerList;
	}

	private List<Byte> addPolarizationElemToTransponder(Attribute attributePolarization, 
			List<Byte> inputResultByteList)
	{
		boolean Result = false;
		String defaultNum = "0";
		
		Result = this.validateGivenPolarizationNumber(
				Byte.parseByte(attributePolarization.getValue()));
		
		checkingIfPolarizationNumberIsValid(attributePolarization, inputResultByteList, Result, defaultNum);

		return inputResultByteList;
	}

	private void checkingIfPolarizationNumberIsValid(Attribute attributePolarization, List<Byte> inputResultByteList,
			boolean Result, String defaultNum)
	{
		if (Result == true)
		{
			inputResultByteList.add(Byte.parseByte(attributePolarization.getValue()));
		}else {
			inputResultByteList.add(Byte.parseByte(defaultNum));
		}
	}
	
	private List<Byte> addFecInnerElemToTransponder(Attribute attributeFecInner, 
			List<Byte> inputResultByteList)
	{
		boolean Result = false;
		String defaultNum = "1";
		
		Result = this.validateGivenFecInnerNumber(
				Byte.parseByte(attributeFecInner.getValue()));
		
		checkingIfPolarizationNumberIsValid(attributeFecInner, inputResultByteList, Result, defaultNum);

		return inputResultByteList;
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

	private void buildingSatInformationObjects(Integer amountOfSat,
			SortedSet<SatInformation> sortedSatellitesSet)
	{
		Iterator<String> iteratorOverSatInfoNames = this.getNamesOfSatellitesSet().iterator();
		Iterator<Integer> iteratorOverSatInfoFlags = this.getFlagsOfSatellitesList().iterator();
		Iterator<Integer> iteratorOverSatInfoPosition = this.getPositionOfSatellitesList().iterator();
        
		constructingAndAddingSatInfoObjectToSet(amountOfSat, sortedSatellitesSet,
				iteratorOverSatInfoNames,
				iteratorOverSatInfoFlags, 
				iteratorOverSatInfoPosition);

		this.setSortedSatellitesInformationSet(sortedSatellitesSet);
	}

	private void constructingAndAddingSatInfoObjectToSet(Integer amountOfSat,
			SortedSet<SatInformation> sortedSatellitesSet,
			Iterator<String> iteratorOverSatInfoNames,
			Iterator<Integer> iteratorOverSatInfoFlags,
			Iterator<Integer> iteratorOverSatInfoPosition	)
	{
		
		iteratingOverSattelitesAndCreatingSatInfoObject(amountOfSat, sortedSatellitesSet, iteratorOverSatInfoNames,
				iteratorOverSatInfoFlags, iteratorOverSatInfoPosition);
	}

	private void iteratingOverSattelitesAndCreatingSatInfoObject(Integer amountOfSat,
			SortedSet<SatInformation> sortedSatellitesSet, Iterator<String> iteratorOverSatInfoNames,
			Iterator<Integer> iteratorOverSatInfoFlags, Iterator<Integer> iteratorOverSatInfoPosition)
	{
		SatInformation satInoObject;
		for (int i = 0; i < amountOfSat; i++)
		{
			satInoObject = creatingNewSatInfoObject(iteratorOverSatInfoNames,
					iteratorOverSatInfoFlags,
					iteratorOverSatInfoPosition);	
			sortedSatellitesSet.add(satInoObject);
		}
	}

	private SatInformation creatingNewSatInfoObject(
			Iterator<String> iteratorOverSatInfoNames,
			Iterator<Integer> iteratorOverSatInfoFlags, 
			Iterator<Integer> iteratorOverSatInfoPosition	)
	{
		SatInformation satInoObject;
		satInoObject = new SatInformation(
				iteratorOverSatInfoNames.next(),
				iteratorOverSatInfoFlags.next(),
				iteratorOverSatInfoPosition.next(), 
				this.getTransponderOFSatellitesFrequencyList(),
				this.getTransponderOFSatellitesSymbolRateList(),
				this.getTransponderOfSatellitesPolarizationList(),
				this.getTransponderOfSatellitesFecInnerList()
				);
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

	public List<Integer> getTransponderOFSatellitesSymbolRateList()
	{
		return transponderOFSatellitesSymbolRateList;
	}

	public void setTransponderOFSatellitesSymbolRateList(List<Integer> transponderOFSatellitesSymbolRateList)
	{
		this.transponderOFSatellitesSymbolRateList = transponderOFSatellitesSymbolRateList;
	}

	public boolean checkContentsOfSatInfoObjectNotNull()
	{
		boolean result = false;

		for (Iterator<SatInformation> iteratorOverSatInfoObjects = this.getSortedSatellitesInformationSet()
				.iterator(); iteratorOverSatInfoObjects.hasNext();)
		{
			result = checkSetOfSatInfoObject(iteratorOverSatInfoObjects);
		}
		return result;
	}

	private boolean checkSetOfSatInfoObject( Iterator<SatInformation> iteratorOverSatInfoObjects)
	{
		boolean result = true;
		if (iteratorOverSatInfoObjects.hasNext())
		{
			result = checkContensOfIteratorSatInfoElement(iteratorOverSatInfoObjects);
		}else {
			result = false;
		}
		
		return result;
	}

	private boolean checkContensOfIteratorSatInfoElement(Iterator<SatInformation> iteratorOverSatInfoObjects)
	{
		boolean result;
		if (iteratorOverSatInfoObjects.next().toString().isEmpty() == false)
		{
			result = true;

		} else
		{
			result = false;
		}
		return result;
	}

	public List<Byte> getTransponderOfSatellitesPolarizationList()
	{
		return transponderOfSatellitesPolarizationList;
	}

	public void setTransponderOfSatellitesPolarizationList(List<Byte> transponderOfSatellitesPolarizationList)
	{
		this.transponderOfSatellitesPolarizationList = transponderOfSatellitesPolarizationList;
	}

	public boolean validateGivenPolarizationNumber(Byte inputPolarizationNumber)
	{
		boolean result = false;
		
		for (Byte validNum = 0; validNum <= 3 ; validNum++)
		{
			result = validateGivenNumber(inputPolarizationNumber, validNum);
			if (result == true) {
				break;
			}
		}
		
		return result;
	}
	
	public boolean validateGivenFecInnerNumber(Byte inputFecInnerNumber)
	{
		boolean result = false;
		
		for (Byte validNum = 1; validNum <= 9 ; validNum++)
		{
			result = validateGivenNumber(inputFecInnerNumber, validNum);
			if (result == true) {
				break;
			}
		}
		return result; 
	}
	
	private boolean validateGivenNumber(Byte inputPolarizationNumber, Byte validNum)
	{
		boolean result;
		if (  validNum == inputPolarizationNumber) {
			result = true;
		}else
		{
			result = false;
		}
		return result;
	}

	public List<Byte> getTransponderOfSatellitesFecInnerList()
	{
		return transponderOfSatellitesFecInnerList;
	}

	public void setTransponderOfSatellitesFecInnerList(List<Byte> transponderOfSatellitesFecInnerList)
	{
		this.transponderOfSatellitesFecInnerList = transponderOfSatellitesFecInnerList;
	}
	
}
