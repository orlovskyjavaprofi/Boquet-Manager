package helperutils;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class ReadXmlAndCreateJdomWithOneSat
{
	String pathToXml;
	private org.jdom2.Document satJdomDocument;
	boolean readXmlResult;
	
	public ReadXmlAndCreateJdomWithOneSat(){
		pathToXml = "src//main//resources//XML-Files-Update2018//services.xml";
	}

	public void readAndSetUpJDomDocument()
			throws ParserConfigurationException, SAXException, IOException
	{
		String inputPath = this.getPathToXml();
		boolean result = false;
		
		XmlReaderAndJdomDocumentCreator xmlReader = 
				new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(xmlReader.getPathToFile());

		result = checkingUpTheXmlReaderStatus(result, xmlReader, xmlReaderStatus);
       
		this.setReadXmlResult(result);
	}

	public Element readJdomDocumentAndCreate1rdLevelElementList(){
		
		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("sat");
		
		Element expectedSatElement = null; 
		
		expectedSatElement = iteratingOverFirstLevelXmlAndLookingUpSatName(empListElements, expectedSatElement);
			
		return expectedSatElement;	
	}
	
	private Element iteratingOverFirstLevelXmlAndLookingUpSatName(List<Element> empListElements,
			Element expectedSatElement)
	{
		String satName;
		//looking up for sattelite
		for (Element oneLevelDeepIntoSat : empListElements)
		{
			satName = oneLevelDeepIntoSat.getAttributeValue("name");
			
			if(satName.equals("Sirius/Astra 1A - 5 east")){
				expectedSatElement = oneLevelDeepIntoSat;
				break;
			}
			
		}
		return expectedSatElement;
	}
	
	private boolean checkingUpTheXmlReaderStatus(boolean result, XmlReaderAndJdomDocumentCreator xmlReader,
			boolean xmlReaderStatus)
	{
		if (xmlReaderStatus == true)
		{
			this.setSatJdomDocument(xmlReader.getJDomDocumentResult());
			result =xmlReaderStatus;
		}
		return result;
	}
	
	public String getPathToXml()
	{
		return pathToXml;
	}

	public void setPathToXml(String pathToXml)
	{
		this.pathToXml = pathToXml;
	}

	public org.jdom2.Document getSatJdomDocument()
	{
		return satJdomDocument;
	}

	public void setSatJdomDocument(org.jdom2.Document satJdomDocument)
	{
		this.satJdomDocument = satJdomDocument;
	}

	public boolean isReadXmlResult()
	{
		return readXmlResult;
	}

	public void setReadXmlResult(boolean readXmlResult)
	{
		this.readXmlResult = readXmlResult;
	}
	
	
}
