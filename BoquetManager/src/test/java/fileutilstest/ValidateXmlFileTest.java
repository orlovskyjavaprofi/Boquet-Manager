package fileutilstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.validators.ValidateXmlFile;

class ValidateXmlFileTest
{
    private ValidateXmlFile validateXmlFileObj;
	
	@BeforeEach
	void setUp() {
		validateXmlFileObj = new ValidateXmlFile();
	}
	
	@Test
	void testIfValidateXmlFileExist()
	{
		assertNotNull(validateXmlFileObj);
	}
	 
	 @Test
	 void testIfGivenXmlisValid(){
		 String pathToValidXml =(System.getProperty("user.dir"))+ "\\src\\test\\resources\\filesTypes\\bouquets.xml";
		 String expectedResult = "application/xml";
		 String actualResult = validateXmlFileObj.validateInputXmlFile(pathToValidXml);
		 
		assertEquals(expectedResult, actualResult, "got wrong file type!");
	 }
	 
	 @Test  
	 void testIfGivenWrongXmlIsInvalid() {
		 String pathToInvalidXml =  (System.getProperty("user.dir"))+ "\\src\\test\\resources\\filesTypes\\garbageFile.xml";
		 String actualResult = validateXmlFileObj.validateInputXmlFile(pathToInvalidXml);
         String expectedResult = "Undetermined";
         
		 assertEquals( expectedResult, actualResult, "got wrong file type!");
	 }
	 
}
