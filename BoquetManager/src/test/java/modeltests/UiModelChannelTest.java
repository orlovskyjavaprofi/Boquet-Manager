package modeltests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.UiModels.UiModelChannel;

class UiModelChannelTest
{
	UiModelChannel uiModelChannelObj;
	
	@BeforeEach
	void setUp()
	{
		uiModelChannelObj = new UiModelChannel();
	}
	
	@Test
	void testingIfuiModelBoquetsListObjectExist()
	{
		assertNotNull(uiModelChannelObj);
	}
	
    @Test
    void checkIfChannelServiceIDCanBeSetUp() {
    	String expectedValue = "6dca";
    	uiModelChannelObj.setChannelServiceID(expectedValue);
    	String actualValue = uiModelChannelObj.getChannelServiceID();
    			
    	assertEquals(expectedValue,actualValue, "The service id of channel was not set up!" );    			
    } 
    
    @Test
    void checkIfTvChannelNameCanBeSetUp() {
    	String expectedValue = "Bayerisches FS Nord";
    	uiModelChannelObj.setTvChannelName(expectedValue);
    	String actualValue = uiModelChannelObj.getTvChannelName();
    			
    	assertEquals(expectedValue,actualValue, "The channel name was not set up!" );    			
    } 
    
    @Test
    void checkIfTransponderIdCanBeSetUp() {
    	String expectedValue = "044d";
    	uiModelChannelObj.setTransponderId(expectedValue);
    	String actualValue = uiModelChannelObj.getTransponderId();
    			
    	assertEquals(expectedValue,actualValue, "The transponder id was not set up!" );    			
    } 
    
    @Test
    void checkIfTransponderOnidCanBeSetUp() {
    	String expectedValue = "0001";
    	uiModelChannelObj.setTransponderOnid(expectedValue);
    	String actualValue = uiModelChannelObj.getTransponderOnid();
    			
    	assertEquals(expectedValue,actualValue, "The transponder onid was not set up!" );    			
    } 
    
    @Test
    void checkIfChannelSatPositionCanBeSetUp() {
    	Integer expectedValue = 192;
    	uiModelChannelObj.setChannelSatPosition(expectedValue);
    	Integer actualValue = uiModelChannelObj.getChannelSatPosition();
    			
    	assertEquals(expectedValue,actualValue, "The sat position was not set up!" );    			
    } 
    
    @Test
    void checkIfNewChannelCanBeCreated() {
    	UiModelChannel uiModelChannelObj2;
    	uiModelChannelObj2 = new UiModelChannel("6dca","Bayerisches FS Nord", "044d","0001",192 );
    	assertNotNull(uiModelChannelObj2);  			
    } 
	

}
