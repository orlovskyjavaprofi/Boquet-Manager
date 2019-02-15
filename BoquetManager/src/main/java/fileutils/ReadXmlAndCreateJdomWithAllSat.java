package fileutils;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class ReadXmlAndCreateJdomWithAllSat
{
	String pathToXml;
	private org.jdom2.Document satJdomDocument;
	boolean readXmlResult;

	public ReadXmlAndCreateJdomWithAllSat(String inputPathToXmlFile){
		pathToXml = inputPathToXmlFile;
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
	
public List<Element> readJdomDocumentAndCreate1rdLevelElementList(){
		
		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("sat");
		
		return empListElements;	
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
