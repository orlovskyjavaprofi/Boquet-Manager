package helperutils;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class ReadXmlAndCreateJdomWithAllBouquets
{
	String pathToXml;
	private org.jdom2.Document boquetsJdomDocument;
	boolean readXmlResult;
	
	public ReadXmlAndCreateJdomWithAllBouquets(){
		pathToXml = "src//main//resources//XML-Files-Update2018//bouquets.xml";
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
	
	private boolean checkingUpTheXmlReaderStatus(boolean result,
			XmlReaderAndJdomDocumentCreator xmlReader,
			boolean xmlReaderStatus)
	{
		if (xmlReaderStatus == true)
		{
			this.setBoquetsJdomDocument(xmlReader.getJDomDocumentResult());
			result =xmlReaderStatus;
		}
		return result;
	}
	
public List<Element> readJdomDocumentAndCreateBouquetsElementList(){
		
		Element root = this.getBoquetsJdomDocument().getRootElement();
		List<Element> bouquetsList = root.getChildren("Bouquet");

		return bouquetsList;	
	}
	
	public String getPathToXml()
	{
		return pathToXml;
	}
	public void setPathToXml(String pathToXml)
	{
		this.pathToXml = pathToXml;
	}

	public org.jdom2.Document getBoquetsJdomDocument()
	{
		return boquetsJdomDocument;
	}

	public void setBoquetsJdomDocument(org.jdom2.Document boquetsJdomDocument)
	{
		this.boquetsJdomDocument = boquetsJdomDocument;
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
