package modeltests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import models.UiModels.UiModelServicesList;

class UiModelServicesListTest
{
	UiModelServicesList modelObjServices;
	
	@BeforeEach
	void setUp()
	{
		modelObjServices = new UiModelServicesList("test",0,"test");
	}
	
	@Test
	void testingIfmodelObjServicesObjectExist()
	{
		assertNotNull(modelObjServices);
	}
	
	@Test
	void testIfSatNameSetUp() {
		String expectedValue = "Astra - 19 east";
		modelObjServices.setSatName(expectedValue);
		String actualValue = modelObjServices.getSatName();
		assertEquals(expectedValue,actualValue, "The name of sattellite was not set up!" );
	}
	
	@Test
	void testIfSatPositionSetUp() {
		Integer expectedValue = 192;
		modelObjServices.setSatPosition(expectedValue);
		Integer actualValue = modelObjServices.getSatPosition();
		assertEquals(expectedValue,actualValue, "The position of sattellite was not set up!" );
	}

	@Test
	void testIfDiseqSetUp() {
		String expectedValue = "0";
		modelObjServices.setSatDiseqc(expectedValue);
		String actualValue = modelObjServices.getSatDiseqc();
		assertEquals(expectedValue,actualValue, "The diseqc of sattellite was not set up!" );
	}

}
