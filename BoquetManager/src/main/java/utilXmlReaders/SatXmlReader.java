package utilXmlReaders;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SatXmlReader
{
	private org.jdom2.Document jdomDoc;
	private org.jdom2.Document satdocumentResult;

	public boolean readDomDocument(String pathToFile) throws ParserConfigurationException, SAXException, IOException
	{
		if (getDocumentFromDomParser(pathToFile) == true)
		{
			return hasJdomDocumentRoot();
		} else
		{
			return false;
		}
	}

	private boolean hasJdomDocumentRoot()
	{
		this.setJdomDoc( this.getSatdocumentResult() );
		if (jdomDoc.hasRootElement() == true) {
			return true;
		}else {
			return false;
		}
	}

	// Get JDOM document from DOM Parser
	//read xml file
	public boolean getDocumentFromDomParser(String pathToFile)
			throws ParserConfigurationException, SAXException, IOException
	{
		// Create Dom Document
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder;
		domBuilder = dbFactory.newDocumentBuilder();
		Document satDocument = domBuilder.parse(new File(pathToFile));
		DOMBuilder builderOfDom = new DOMBuilder();

		return checkIfNewDocumentHasRoot(satDocument, builderOfDom);
	}

	private boolean checkIfNewDocumentHasRoot(Document satdocument, DOMBuilder builderOfDom)
	{
		if (builderOfDom.build(satdocument).hasRootElement())
		{
			this.setSatdocumentResult(builderOfDom.build(satdocument));
			return true;
		} else
		{
			return false;
		}
	}

	public org.jdom2.Document getSatdocumentResult()
	{
		return satdocumentResult;
	}

	public void setSatdocumentResult(org.jdom2.Document satdocumentResult)
	{
		this.satdocumentResult = satdocumentResult;
	}

	public org.jdom2.Document getJdomDoc()
	{
		return jdomDoc;
	}

	public void setJdomDoc(org.jdom2.Document jdomDoc)
	{
		this.jdomDoc = jdomDoc;
	}
	
}
