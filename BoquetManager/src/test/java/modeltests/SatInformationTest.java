package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helperutils.Util;
import models.SatInformation;

class SatInformationTest
{
	private SatInformation satInfoObject;
	private String satname;
    private Integer flags;
	private Integer position;
	private List<Integer> satTransponderFrequencyInputList;
	private  List<Integer> satTransponderSymbolRateInputList;
	private List<Byte> satPolarisationList ;
	private List<Byte> satFecInnerList ;
	String pathToFrequencyFile;
	String pathToSymbolRateFile;
	private Util helperUtils;
	
	@BeforeEach
	void setUp()
	{
		satname = "Sirius/Astra 1A - 5 east";
		flags = 1;
		position  =50;
		satTransponderFrequencyInputList = new LinkedList<Integer>();
	    satPolarisationList = new LinkedList<Byte>();
		satFecInnerList = new LinkedList<Byte>();
		helperUtils = new Util();
	    setUpMockPathsForReadingFiles();
		
		this.readFileToFrequencyList(pathToFrequencyFile);
		satPolarisationList = this.initValuesForPolarisationNotNullTest();	
		satFecInnerList = this.initValuesForFecInnerNotNullTest();
		this.readingFileSetingUpTransponderSymbolRateList(pathToSymbolRateFile);

		satInfoObject = new SatInformation(
				satname, 
				flags, 
				position,
				satTransponderFrequencyInputList, 
				satTransponderSymbolRateInputList, 
				satPolarisationList, satFecInnerList);
	}

	private void setUpMockPathsForReadingFiles()
	{
		pathToFrequencyFile = "src//test//resources//transponderFrequencyList//SeriusSat.txt";
		pathToSymbolRateFile = "src//test//resources//transponderSymbolRateList//SeriusSatSymbolRate.txt";
	}

	@Test
	void testingIfSatInormatiionObjectExist()
	{
		assertNotNull(satInfoObject);
	}

	// Java 8 api
	// value 0 if both srings are equal
	// value less then 0 , if one of the values are less then another
	// vlaue great then 0, if one of the value are greater then another

	@Test
	void testingIfSatNameIsEqual()
	{
		SatInformation anotherSatInfoObject = new SatInformation("Sirius/Astra 1A - 5 east");
		Integer expectedResult = 0;
		Integer actualResult = satInfoObject.compareTo(anotherSatInfoObject);

		assertEquals(expectedResult, actualResult, "check if one and another satInfo Obects have " + "equal names!");
	}

	@Test
	void testingIfDifferentSatInfoObjectsEqual()
	{
		Integer expectedResult = 0;
		SatInformation anotherSatInfoObject = new SatInformation("Hotbird - 13 east");
		boolean result = true;
		Integer actualResult = satInfoObject.compareTo(anotherSatInfoObject);

		result = helperUtils.validateResult(expectedResult,  actualResult,result);
		
		assertFalse(result, "check if  one and another satInfo Objects have different names!");
	}

	@Test
	void testingIfOneOfTheSatInfoObjectWithoutName()
	{
		SatInformation anotherSatInfoObject = new SatInformation();
		Integer expectedResult = 0;
		Integer actualResult = satInfoObject.compareTo(anotherSatInfoObject);

		boolean expectedResultBol = true;
		boolean actualResultBol = false;
		actualResultBol = helperUtils.validateResult(expectedResult, actualResult, actualResultBol);

		assertEquals(expectedResultBol, actualResultBol, "check if  one satInfo Objects have no name at all!");
	}


	
	@Test
	void checkIfFlagsNotNull() {
		
		Integer flagValue = satInfoObject.getSatFlags();
		
		assertNotNull(flagValue);
	}
	
	@Test
	void checkIfPositionNotNull() {
		
		Integer Position = satInfoObject.getSatPosition();
		
		assertNotNull(Position);
	}
	
	@Test 
	void checkLengthOftransponderfrequency() {
		
	    boolean expectedResult = true;
	    boolean actualResult =  false;
	    
	    actualResult =  satInfoObject.checkTheLengthOfTransponderFrequency();
	    
		assertEquals(expectedResult, actualResult, "check if  the length of transponder frequency is ok");
	}

	@Test
	void checkIFSybolRateIsNotNull(){
		boolean expectedResult = true;
		boolean actualResult = false;
		
		actualResult = satInfoObject.checkIfSymbolrateNotNull();
		
		assertEquals(expectedResult, actualResult, "Check if Symobolrate is not null");	
	}
	
	@Test
	void checkIfPolarisationNotNull() {
		boolean expectedResult = false;
		boolean actualResult = false;

		actualResult = satInfoObject.checkIfPolarisationNotNull();
				
		assertEquals(expectedResult, actualResult, "Check if polarisation is not null");
	}

	@Test
	void checkIfFecInnerNoNull() {
		boolean expectedResult = false;
		boolean actualResult = false;

		actualResult = satInfoObject.checkfec_innerNotNull();
				
		assertEquals(expectedResult, actualResult, "Check if fec_inner is not null");
	}
	
	private List<Byte> initValuesForPolarisationNotNullTest()
	{
		 List<Byte> inputSatPolarisationList = new LinkedList<Byte>();
		for (byte b = 0; b <= 3; b++)
		{
			inputSatPolarisationList.add( b);
		}
		
		return inputSatPolarisationList;
	}
	
	private List<Byte> initValuesForFecInnerNotNullTest()
	{
		 List<Byte> inputSatPolarisationList = new LinkedList<Byte>();
		for (byte b = 1; b <= 9; b++)
		{
			inputSatPolarisationList.add( b);
		}
		
		return inputSatPolarisationList;
	}
	
	
	private void  readFileToFrequencyList(String pathToFile) {
		List<Integer> result = new LinkedList<Integer>();
		try
		{
			readingFileSetingUpTransponderFrequencyList(pathToFile, result);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readingFileSetingUpTransponderFrequencyList(String pathToFile, List<Integer> result)
			throws FileNotFoundException
	{
		Scanner in = new Scanner(new FileReader(pathToFile));
		iteratingOverFileRowsAndAddingIntegersToList(result, in);
		in.close();
		this.setSatTransponderFrequencyInputList(result);
	}

	private void iteratingOverFileRowsAndAddingIntegersToList(List<Integer> result, Scanner in)
	{
		while(in.hasNext()) {
		    result.add( Integer.parseInt(in.next() ));
		}
	}
	
	private void readingFileSetingUpTransponderSymbolRateList(String pathToFile)

	{
		List<Integer> result = new LinkedList<Integer>();
		
		Scanner in;
		try
		{
			in = new Scanner(new FileReader(pathToFile));
			iteratingOverFileRowsAndAddingIntegersToList(result, in);
			in.close();
			this.setSatTransponderSymbolRateInputList(result);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setSatTransponderFrequencyInputList(List<Integer> satTransponderFrequencyInputList)
	{
		this.satTransponderFrequencyInputList = satTransponderFrequencyInputList;
	}

	public List<Integer> getSatTransponderSymbolRateInputList()
	{
		return satTransponderSymbolRateInputList;
	}

	public void setSatTransponderSymbolRateInputList(List<Integer> satTransponderSymbolRateInputList)
	{
		this.satTransponderSymbolRateInputList = satTransponderSymbolRateInputList;
	}

}
