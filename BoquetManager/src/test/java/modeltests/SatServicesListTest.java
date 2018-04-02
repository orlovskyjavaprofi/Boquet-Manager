package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helperutils.Util;
import models.SatServicesList;
import models.SatTransponder;
import models.SatTvChannel;

class SatServicesListTest
{
	private SatServicesList satServicesObj;
	private String satname;
	private Util helperUtils;
	private Integer position;
    private Byte satDiseqc; 
	private List<SatTransponder> listOfSatTransponders;
	private List<SatTvChannel> listofSatTvChannel;
	
	@BeforeEach
	void setUp()
	{
		initDummyTransponderFields();	
		initDummyListsTranspondersAndTvChannels();		
		initDummyTransponder();
		 
		 satServicesObj.setListOfTransponders(listOfSatTransponders);
		 
	}

	private void initDummyListsTranspondersAndTvChannels()
	{
		listOfSatTransponders = new LinkedList<SatTransponder>();
		listofSatTvChannel = new LinkedList<SatTvChannel>();		
		helperUtils = new Util();
	}

	private void initDummyTransponderFields()
	{
		satname = "Sirius/Astra 1A - 5 east";
		position  = 0050;
		satDiseqc = 2;
		satServicesObj = new SatServicesList(satname, position,satDiseqc);
	}

	private void initDummyTransponder()
	{
		initDummyListOfSatTvChannels();
		
		 listOfSatTransponders.add( 
				
		new SatTransponder(  "0009", "0056",
	        	new Integer(11823000), Byte.parseByte("2"),
	     	new Integer(27500000), Byte.parseByte("3"),
	     	Byte.parseByte("1"),  listofSatTvChannel )
	    );
	}

	private void initDummyListOfSatTvChannels()
	{
		listofSatTvChannel.add(new SatTvChannel("1b62", "DR1", Byte.parseByte("01")));
		listofSatTvChannel.add(new SatTvChannel("1b6c", "DR2", Byte.parseByte("01")));
		listofSatTvChannel.add(new SatTvChannel("1b76", "TV8", Byte.parseByte("01")));
		listofSatTvChannel.add(new SatTvChannel("1b80", "CNN", Byte.parseByte("01")));
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
	
	@Test
	void checkIfTransponderListNotNull() {
		List<SatTransponder> listOfTransponders = satServicesObj.getListOfTransponders();
		
		assertNotNull(listOfTransponders);
	}
	
}