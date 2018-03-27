package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helperutils.Util;
import models.SatServicesList;

class SatServicesTest
{
	private SatServicesList satServicesObj;
	private String satname;
	private Util helperUtils;
	
	@BeforeEach
	void setUp()
	{
		satname = "Sirius/Astra 1A - 5 east";
		satServicesObj = new SatServicesList(satname);
		helperUtils = new Util();
	}

	@Test
	void testingIfSatServicesObjectExist()
	{
		assertNotNull(satServicesObj);
	}
	
	@Test
	void testingIfSatNameIsEqual()
	{
		SatServicesList anotherSatServicesObject = new SatServicesList("Sirius/Astra 1A - 5 east");
		Integer expectedResult = 0;
		Integer actualResult = satServicesObj.compareTo(anotherSatServicesObject);

		assertEquals(expectedResult, actualResult, "check if one and another satServicesObj have " + "equal names!");
	}
	
	@Test
	void testingIfOneOfTheSatServicesObjectWithoutName()
	{
		SatServicesList anotherSatServicesObject = new SatServicesList();
		Integer expectedResult = 0;
		Integer actualResult = satServicesObj.compareTo(anotherSatServicesObject);

		boolean expectedResultBol = true;
		boolean actualResultBol = false;
		actualResultBol = helperUtils.validateResult(expectedResult, actualResult, actualResultBol);
		
		assertEquals(expectedResultBol, actualResultBol, "check if  one satServices Objects have no name at all!");
	}

}
