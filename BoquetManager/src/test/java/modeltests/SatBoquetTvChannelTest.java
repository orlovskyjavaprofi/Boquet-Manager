package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.SatBoquetTvChannel;

class SatBoquetTvChannelTest
{
	SatBoquetTvChannel satBoquetTvChannel;
	String mockupServiceID;
	String mockupTvChannelName;
	String mockupTransponderId;
	String mockupTransponderOnid;
	Integer mockupSatPosition;
	
	@BeforeEach
	void setUp()
	{
		mockupServiceID ="0e2e";
		mockupTvChannelName = "Discovery EE";
		mockupTransponderId = "0016";
		mockupTransponderOnid = "0056";
		mockupSatPosition = 050;
		satBoquetTvChannel = new SatBoquetTvChannel(mockupServiceID,
				mockupTvChannelName, mockupTransponderId, 
				mockupTransponderOnid,mockupSatPosition);
	}
	
	@Test
	void testingIfSatInormatiionObjectExist()
	{
		assertNotNull(satBoquetTvChannel);
	}

	@Test
	void testingIfSatBoquetTvChannelServiceIDNotNull()
	{
		String currentValueOfBoquetTvChannelServiceId = satBoquetTvChannel.getSatBoquetTvChannelServiceID();
		assertNotNull(currentValueOfBoquetTvChannelServiceId);
	}
	
	@Test
	void testingIfSatBoquetTvChannelisNotNull() {
		String currentValueOfBoquetTvChannelName = satBoquetTvChannel.getSatBoquetTvChannelName();
		assertNotNull(currentValueOfBoquetTvChannelName);
	}
	
	@Test
	void testingIfSatBoquetTvChannelTransponderIdNotNull() {
		String currentValueOfBoquetTransponderId = satBoquetTvChannel.getSatBoquetTvChannelTransponderId();
	    assertNotNull(currentValueOfBoquetTransponderId);
	}
	
	@Test
	void testingIfSatBoquetTvChannelTransponderOnidNotNull() {
		String  currentValueOfTransponderOnid = satBoquetTvChannel.getSatBoquetTvChanneltransponderOnid();
		assertNotNull(currentValueOfTransponderOnid);
	}
	
	@Test
	void testingIfSatBoquetTvChannelSatPositionNotNull() {
		Integer currentValueOfSatPosition = satBoquetTvChannel.getSatBoquetTvChannelsatPosition();
		assertNotNull(currentValueOfSatPosition);
	}
	
}
