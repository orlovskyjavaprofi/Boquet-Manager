package helperutils;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class ReadXmlAndCrerateJdomWithChannelInfo
{
	
	String pathToXml;
	private org.jdom2.Document satJdomDocument;
	boolean readXmlResult;
	
	public ReadXmlAndCrerateJdomWithChannelInfo(){
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

	public List<Element> readJdomDocumentAndCreate3rdLevelElementList(){
		
		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("sat");
		
		Element expectedSatElement = null; 
		Element satTvChannelsElement = null;
		
		expectedSatElement = iteratingOverFirstLevelXmlAndLookingUpSatName(empListElements, expectedSatElement);
		satTvChannelsElement = lookingUpAspecialTransponder(expectedSatElement, satTvChannelsElement);
		
		return evuatingTheResult(satTvChannelsElement);
		
	}

	private List<Element> evuatingTheResult(Element satTvChannelsElement)
	{
		//checkup if the Result list have some Values
		if (satTvChannelsElement.hasAttributes() == true ) {
			return satTvChannelsElement.getChildren("channel");
		}else {
			return null;
		}
	}

	private Element lookingUpAspecialTransponder(Element expectedSatElement, Element satTvChannelsElement)
	{
		List<Element> secondLevelXmlElements;
		//Getting hold of a Transponder
		if(expectedSatElement.getAttributeValue("name").equals("Sirius/Astra 1A - 5 east")) {
			secondLevelXmlElements = expectedSatElement.getChildren("transponder");
			
			satTvChannelsElement = iteratingOverSecondLevelXml(secondLevelXmlElements, satTvChannelsElement);
		}
		return satTvChannelsElement;
	}

	private Element iteratingOverSecondLevelXml(List<Element> secondLevelXmlElements, Element satTvChannelsElement)
	{
		String transponderId;
		for (Element twoLevelDeepIntoSat : secondLevelXmlElements)
		{
			transponderId = twoLevelDeepIntoSat.getAttributeValue("id");
			
			satTvChannelsElement = lookingUpForSpecialTransponder(transponderId, satTvChannelsElement,
					twoLevelDeepIntoSat);
		}
		return satTvChannelsElement;
	}

	private Element lookingUpForSpecialTransponder(String transponderId, Element satTvChannelsElement,
			Element twoLevelDeepIntoSat)
	{
		if(transponderId.equals("000a") ) {
			satTvChannelsElement = twoLevelDeepIntoSat;
		}
		return satTvChannelsElement;
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
	
	
	
	public boolean isReadXmlResult()
	{
		return readXmlResult;
	}

	public void setReadXmlResult(boolean readXmlResult)
	{
		this.readXmlResult = readXmlResult;
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

}
