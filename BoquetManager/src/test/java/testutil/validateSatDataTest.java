package testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	
}
