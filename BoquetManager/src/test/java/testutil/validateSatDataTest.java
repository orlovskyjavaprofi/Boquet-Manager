package testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.validators.ValidateSatData;

class validateSatDataTest
{
	ValidateSatData validatorUtilObj;

	@BeforeEach
	void setUp()
	{
		validatorUtilObj = new ValidateSatData();
	}
	
	@Test
	 public void  checkIfValidateSatDataNotNull(){
		assertNotNull(validatorUtilObj);
	}

    @Test
    public void checkIfNameForBoquetTvChannelNotSetup() {
    	    String expectedResult ="unknown tv channel";
    	    String inputString =""; 
    	    String actualResult =  validatorUtilObj.verifyConsistencyOfBoquetTvChannel(inputString);
    	    
    	    assertEquals(expectedResult,actualResult, "checking if name of boquet Channel is not setup");
    	
    }
    
    @Test
    public void checkIfNameForBoquetTvChannelIsSetup() {
    	    String expectedResult ="BBC World";
    	    String inputString ="BBC World"; 
    	    String actualResult =  validatorUtilObj.verifyConsistencyOfBoquetTvChannel(inputString);
    	    
    	    assertEquals(expectedResult,actualResult, "checking if name of boquet Channel is setup");
    	
    }
    
    @Test
    public void checkFromProvidedServicesServiceType() {
    	    Byte inputValue= 00;
    	    String input = "services";
    	    boolean expectedResult = true;
    	    boolean actualResult = validatorUtilObj.validateServiceType(inputValue,input);
    	    
    	    assertEquals(expectedResult, actualResult,"checking for services.xml if service type is valid");
    }
    
    @Test
    public void checkFromProvidedServicesServiceTypeFails() {
    	    Byte inputValue= 02;
        String input = "services";
    	    boolean actualResult = validatorUtilObj.validateServiceType(inputValue, input);
    	    
    	    assertFalse( actualResult,"checking for services.xml if service type is valid");
    }
    
    @Test
    public void checkFromProvidedBoquetsServiceType() {
    	    Byte inputValue= 0;
    	    String input = "boquets";
    	    boolean expectedResult = true;
    	    boolean actualResult = validatorUtilObj.validateServiceType(inputValue,input);
    	    
    	    assertEquals(expectedResult, actualResult,"checking for boquets.xml if service type is valid");
    }
    
    @Test
    public void checkFromProvidedBoquetsServiceTypeFails() {
    	    Byte inputValue= 2;
        String input = "boquets";
    	    boolean actualResult = validatorUtilObj.validateServiceType(inputValue, input);
    	    
    	    assertFalse( actualResult,"checking for boquets.xml if service type is valid");
    }
    
    @Test
    public void checkFromProvidedServicesIfAInvalidaValueOfServiceTypeIsCorrected() {
    	    Byte expectedValue = 00;
    	    Byte inputValue = 02;
    	    String input = "services";
    	    Byte actualValue = validatorUtilObj.changesIncorrectValueOfServiceType(inputValue,input);
    	    
    	    assertEquals(expectedValue, actualValue, "changing for services.xml  incorrect value of service type");
    }
    
    @Test
    public void checkFromProvidedServicesIfAValidValueOfServiceTypeWasntChanged() {
    	    Byte expectedValue = 01;
    	    Byte inputValue = 01;
    	    String input = "services";
    	    Byte actualValue = validatorUtilObj.changesIncorrectValueOfServiceType(inputValue,input);
    	    
    	    assertEquals(expectedValue, actualValue, "correct for services.xml  value of service type");
    }
    
    @Test
    public void checkFromProvidedBoquetsIfAInvalidaValueOfServiceTypeIsCorrected() {
    	    Byte expectedValue = 0;
    	    Byte inputValue = 2;
    	    String input = "boquets";
    	    Byte actualValue = validatorUtilObj.changesIncorrectValueOfServiceType(inputValue,input);
    	    
    	    assertEquals(expectedValue, actualValue, "changing for boquets.xml incorrect value of service type");
    }
    
    @Test
    public void checkFromProvidedBoquetsIfAValidValueOfServiceTypeWasntChanged() {
    	    Byte expectedValue = 1;
    	    Byte inputValue = 1;
    	    String input = "boquets";
    	    Byte actualValue = validatorUtilObj.changesIncorrectValueOfServiceType(inputValue,input);
    	    
    	    assertEquals(expectedValue, actualValue, "correct for boquets.xml value of service type");
    }
    
    @Test
    public void checkBoquetHiddenAttribute() {
    	   boolean expectedResult = true;
    	   Byte mockupValue = 0;
    	   boolean actualResult = validatorUtilObj.validateBoquetHiddenOrLockedAttribute(mockupValue);
    	   
    	   assertEquals(expectedResult, actualResult, "validate boquet hidden attribute");
    }
    
    @Test
    public void checkFailsBoquetHiddenAttribute() {
    	   Byte mockupValue = -1;
    	   boolean actualResult = validatorUtilObj.validateBoquetHiddenOrLockedAttribute(mockupValue);
    	   
    	   assertFalse( actualResult, "validate that boquet hidden attribute fails");
    }
    
    @Test
    public void checkThatIncorrectHiddenAttributeValueFixed() {
    	    Byte mockupValue = -1;
    	    Byte expectedValue = 0;
    	    Byte actualResult = validatorUtilObj.changeIncorrectBoquetHiddenOrLockedAttribute(mockupValue);
    	    
    	    assertEquals(expectedValue, actualResult, "validate boquet hidden attribute was fixed");
    }
	
}
