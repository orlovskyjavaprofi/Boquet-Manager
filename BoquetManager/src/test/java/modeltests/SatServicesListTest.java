package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helperutils.Util;
import models.SatServicesList;

class SatServicesListTest
{
	private SatServicesList satServicesObj;
	private String satname;
	private Util helperUtils;
	private Integer position;
    private Byte satDiseqc; 
	
	@BeforeEach
	void setUp()
	{
		satname = "Sirius/Astra 1A - 5 east";
		position  = 0050;
		satDiseqc = 2;
		satServicesObj = new SatServicesList(satname, position,satDiseqc);
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

	@Test
	void checkIfPositionNotNull()
	{
		Integer positionValue = satServicesObj.getSatPosition();

		assertNotNull(positionValue);
	}

	@Test
	void checkIfDiseqcNotNull() {
		Byte diseqcValue = satServicesObj.getSatDiseqc();
		
		assertNotNull(diseqcValue);
	}
	
	
}
