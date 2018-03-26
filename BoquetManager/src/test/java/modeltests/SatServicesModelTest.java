package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import models.SatServicesModel;

public class SatServicesModelTest
{
	
	String PathToFile;
	SatServicesModel satServicesObject;
	
	@BeforeEach
	void setUp()
	{
		satServicesObject = new SatServicesModel();
		PathToFile = "src//main//resources//XML-Files-Update2018//services.xml";
	}

	@Test
	void testingIfSatServicesObjectExist()
	{
		assertNotNull(satServicesObject);
	}
	
	@Test
	void testingIfSatServicesCanReadAJDomDocument()
	{
		boolean result = false;
		
		try
		{
			result = satServicesObject.readAndSetUpAjDomDocument(PathToFile);
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(true, result, "cannot read a Jdom document.");
	}
}
