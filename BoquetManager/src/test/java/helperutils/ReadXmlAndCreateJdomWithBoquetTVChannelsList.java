package helperutils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class ReadXmlAndCreateJdomWithBoquetTVChannelsList
{
	String pathToXml;
	private org.jdom2.Document satJdomDocument;
	boolean readXmlResult;
	
	public ReadXmlAndCreateJdomWithBoquetTVChannelsList() {
		pathToXml = "src//main//resources//XML-Files-Update2018//bouquets.xml";
	}
	
	public void readAndSetUpJDomDocument()
			throws ParserConfigurationException, SAXException, IOException
	{
		String inputPath = this.getPathToXml();
		boolean result = false;
		
		XmlReaderAndJdomDocumentCreator xmlReader = 
				new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(
				xmlReader.getPathToFile());

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
	
	public List<Element> readJdomDocumentAndCreateSatTvChannelsElementList(){
		
		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("Bouquet");
		
		Element expectedBoquetElement = null; 
		List<Element> satTvChannelsElement = new LinkedList<Element>();
		
		expectedBoquetElement = iteratingOverFirstLevelXmlAndLookingUpTheBoquet(
				                                   empListElements, expectedBoquetElement);
		satTvChannelsElement = lookingUpAspecialBoquetTvChannels(
				                                   expectedBoquetElement, satTvChannelsElement);
		
		return satTvChannelsElement;
		
	}

	private Element iteratingOverFirstLevelXmlAndLookingUpTheBoquet(
			                        List<Element> empListElements,
			                        Element expectedBoquetElement)
	{
		String boquetName;
		
		//looking up for boquet
		for (Element oneLevelDeepIntoSat : empListElements)
		{
			boquetName = oneLevelDeepIntoSat.getAttributeValue("name");
			
			if(boquetName.equals("Deutsche")){
				expectedBoquetElement = oneLevelDeepIntoSat;
				break;
			}
			
		}
		return expectedBoquetElement;
	}
	
	private List<Element> lookingUpAspecialBoquetTvChannels(
			Element expectedSatElement, List<Element> satTvChannelsElement)
	{
		List<Element> secondLevelXmlElements = new LinkedList<Element>();
		secondLevelXmlElements = expectedSatElement.getChildren("channel");
		
		return secondLevelXmlElements;
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
