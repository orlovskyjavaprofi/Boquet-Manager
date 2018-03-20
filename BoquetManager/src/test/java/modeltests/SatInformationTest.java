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

import models.SatInformation;

class SatInformationTest
{
	private SatInformation satInfoObject;
	private String satname;
    private Integer flags;
	private Integer position;
	private List<Integer> satTransponderFrequencyInputList;
	String PathToFile;
	
	@BeforeEach
	void setUp()
	{
		satname = "Sirius/Astra 1A - 5 east";
		flags = 1;
		position  =50;
		satTransponderFrequencyInputList = new LinkedList<Integer>();
		PathToFile = "src//test//resources//transponderFrequencyList//SeriusSat.txt";
		this.readFileToFrequencyList(PathToFile);
		satInfoObject = new SatInformation(satname, flags, position,satTransponderFrequencyInputList);
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

		result = validateResult(expectedResult,  actualResult,result);
		
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
		actualResultBol = validateResult(expectedResult, actualResult, actualResultBol);

		assertEquals(expectedResultBol, actualResultBol, "check if  one satInfo Objects have no name at all!");
	}

	private boolean validateResult(Integer expectedResult, Integer actualResult, boolean result)
	{
		if (expectedResult < actualResult)
		{
			result = false;
		}else
		{
			result = true;
		}
		return result;
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
		while(in.hasNext()) {
		    result.add( Integer.parseInt(in.next() ));
		}
		in.close();
		this.setSatTransponderFrequencyInputList(result);
	}
	
	public void setSatTransponderFrequencyInputList(List<Integer> satTransponderFrequencyInputList)
	{
		this.satTransponderFrequencyInputList = satTransponderFrequencyInputList;
	}
	
	
}
