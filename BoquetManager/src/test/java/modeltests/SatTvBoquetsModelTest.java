package modeltests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import helperutils.ReadXmlAndCreateJdomWithAllBouquets;
import models.SatTvBoquet;
import models.SatTvBoquetModel;

public class SatTvBoquetsModelTest
{
	SatTvBoquetModel BoquetModelObj;
	String PathToFile;
	
	@BeforeEach
	void setUp() throws ParserConfigurationException, SAXException, IOException
	{
		BoquetModelObj = new SatTvBoquetModel();
		PathToFile = "src//main//resources//XML-Files-Update2018//bouquets.xml";
		BoquetModelObj.readAndSetUpJDomDocument(PathToFile);
	}
	
	@Test
	void testingIfSatTvBoquetModelObjectExist()
	{
		assertNotNull(BoquetModelObj);
	}
	
	@Test
	void testingIfBoqueModelCanReadAJDomDocument()
	{
		boolean result = false;

		try
		{
			result = BoquetModelObj.readAndSetUpJDomDocument(PathToFile);
			assertEquals(true, result, "cannot read a Jdom document.");
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testingifBoquetsModelJDomDocumentSetUP()
	{
		boolean expectedResult = true;
		boolean actualResult = false;

		actualResult = BoquetModelObj.checkIfJDomDocumetIsSetUp();

		assertEquals(expectedResult, actualResult,
				"checking if SatTvBoquetModel  can save JDom Document from  Xml Reader");

	}
	
	@Test
	void checkIfBoquetSetIsNotNull()
	{
		boolean expectedResult = true;
		boolean actualResult = false;
		Set<SatTvBoquet> resultSetOfSatTvBoquets;

		actualResult = BoquetModelObj.buildSetOfBoquets(BoquetModelObj.
				readJdomDocumentAndCreateBouquetsElementList());
		resultSetOfSatTvBoquets = BoquetModelObj.getResultSetOfSatTvBoquets();
		// System.out.println(resultSetOfSatTvBoquets.toString());
		assertEquals(expectedResult, actualResult, "checking if set of boquets not null");

	}
	
	@Test
	void checkIfXmlElemsForBoquetCanBeRead() {
		boolean expectedResult = true;
		boolean actualResult = false;
		List<Element> resultBoquet = new ArrayList<Element>();
		
		resultBoquet = BoquetModelObj.readJdomDocumentAndCreateBouquetsElementList();
		
		if (resultBoquet.isEmpty() == false) {
			actualResult = true;
		}

		assertEquals(expectedResult, actualResult,"checking if xml elements for boquets can be read");
	}
}
