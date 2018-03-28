package modeltests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import models.SatTransponder;
import models.SatTvChannel;

class SatTransponderTest
{
	SatTransponder satTransponderObject;
	String inputSatTransponderId;
	String inputSatOnid;
	Integer inputTransponderFrequency; 
	Integer inputSymbolRate; 
	Byte inputInversionValue;
	Byte inputFecInner;
	Byte inputPolarisation;
	Byte inputPolrarisationForTvChannel;
	List<SatTvChannel> listOfSatTvChannels; 
	SatTvChannel dummySatTvChannel;
	
	@BeforeEach
	void setUp()
	{
		initTransponderValues();	
		initSatTvChannelList();

		satTransponderObject = new SatTransponder(inputSatTransponderId, inputSatOnid,
				                                 inputTransponderFrequency, inputInversionValue,
				                                 inputSymbolRate, inputFecInner, inputPolarisation,
				                                 listOfSatTvChannels 
				                                 );
		
	}

	private void initSatTvChannelList()
	{
		listOfSatTvChannels = new LinkedList<SatTvChannel>();
		dummySatTvChannel = new SatTvChannel( "0001", "MTV Live HD", 
				inputPolrarisationForTvChannel);
		listOfSatTvChannels.add(dummySatTvChannel);
		dummySatTvChannel = new SatTvChannel( "0002", "MTV Polska", 
				inputPolrarisationForTvChannel);
		listOfSatTvChannels.add(dummySatTvChannel);
	}

	private void initTransponderValues()
	{
		inputSatTransponderId = "001e";
		inputSatOnid = "11fd";
		inputTransponderFrequency = 10723000;
		inputInversionValue = 2;
		inputSymbolRate = 29900000;
		inputFecInner = 3;
		inputPolarisation = 0;
		inputPolrarisationForTvChannel = 01;
	}

	@Test
	void testingIfSatTransponderObjectExist()
	{
		assertNotNull(satTransponderObject);
	}

	@Test
	void checkIfTransponderIdNotNull()
	{
		String inputTransponderIdValue = satTransponderObject.getTransponderId();

		assertNotNull(inputTransponderIdValue);
	}
	
	@Test
	void checkIfOnidNotNull() {
		String inputOnidValue = satTransponderObject.getTransponderOnid();

		assertNotNull(inputOnidValue);
	}

	@Test
	void checkIfTransponderFrequencyNotNull() {
		Integer inputFrequencyValue = satTransponderObject.getTransponderFrequency();

		assertNotNull(inputFrequencyValue);
	}
	
	@Test
	void checkIfInversionNotNull() {
		Byte inputInversionValue = satTransponderObject.getTransponderInversion();

		assertNotNull(inputInversionValue);
	}
	
	@Test
	void checkIfSymbolRateNotNull() {
		Integer inputSymbolRateValue = satTransponderObject.getTransponderSymbolRate();

		assertNotNull(inputSymbolRateValue);
	}
	
	@Test
	void checkIfFecInnerNotNull() {
		Byte inputFecInnerValue = satTransponderObject.getTransponderFecInner();

		assertNotNull(inputFecInnerValue);
	}
	
	@Test
	void checkIfPolarisationNotNull() {
		Byte inputPolarisation = satTransponderObject.getTransponderPolarisation();

		assertNotNull(inputPolarisation);
	}
	
	@Test
	void checkIfTvChannelListNotNull() {
		List<SatTvChannel> listOfSatTvChannels = satTransponderObject.getListOfSatTvChannels();
		
		assertNotNull(listOfSatTvChannels);
	}
	
	@Test
    @Disabled
	void outputSatTvChannelList() {
		System.out.println(satTransponderObject.toString());
	}
	
}
