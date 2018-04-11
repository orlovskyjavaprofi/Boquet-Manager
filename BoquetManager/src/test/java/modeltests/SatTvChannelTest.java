package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import models.SatTvChannel;

class SatTvChannelTest
{
	SatTvChannel satTvChannelObject;
	String inputServiceID; 
	String inputChannelName;
	Byte inputServiceType;
	
	@BeforeEach
	void setUp()
	{
		inputServiceID = "1fae";
		inputChannelName = "Viasat Xtra 8";
		inputServiceType = 01;
		satTvChannelObject = new SatTvChannel(inputServiceID,inputChannelName,inputServiceType);
	}
	
	
	@Test
	void testingIfSatTvChannelObjectExist()
	{
		assertNotNull(satTvChannelObject);
	}

	@Test
	void checkIfServiceIdNotNull()
	{
		String serviceIdValue = satTvChannelObject.getServiceID();

		assertNotNull(serviceIdValue);
	}
	
	@Test
	void checkIfChannelNameNotNull()
	{
		String channelNameValue = satTvChannelObject.getChannelName();

		assertNotNull(channelNameValue);
	}
	
	@Test
	void checkIfChannelNameIsntAvailable() {
		satTvChannelObject = new SatTvChannel();
		String expectedResult = "unknowChanelname";
		String actualResult = satTvChannelObject.getChannelName();
		
		assertEquals(expectedResult, actualResult, "check if a channel has no name");
	}
	
	@Test
	void checkIfChannelServiceTypeNotNull()
	{
		Byte satTvServiceType = satTvChannelObject.getServiceType();

		assertNotNull(satTvServiceType);
	}
	   
	@Test
	void outputValuesOfTvChannel() {
		System.out.println(satTvChannelObject.toString());
	}
	

	
}
