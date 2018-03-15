package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.SatInformation;

class SatInformationTest
{
	private SatInformation satInfoObject;
	private String satname;

	@BeforeEach
	void setUp()
	{
		satname = "Sirius/Astra 1A - 5 east";
		satInfoObject = new SatInformation(satname);
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
	
}
