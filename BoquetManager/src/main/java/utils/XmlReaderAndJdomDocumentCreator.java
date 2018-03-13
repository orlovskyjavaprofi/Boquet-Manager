package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlReaderAndJdomDocumentCreator
{
	private org.jdom2.Document jdomDoc;
	private org.jdom2.Document JdomDocumentResult;
    private String pathToFile; 
    
    public XmlReaderAndJdomDocumentCreator() {
    	
    }
    
	public XmlReaderAndJdomDocumentCreator(String pathToFile)
	{
		this.pathToFile = pathToFile;
	}
	
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
		this.setJdomDoc( this.getJDomDocumentResult() );
		if (    this.getJdomDoc().hasRootElement() == true) {
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
		Document domDocument = domBuilder.parse(new File(pathToFile));
		DOMBuilder builderOfDom = new DOMBuilder();

		return checkIfNewDocumentHasRoot(domDocument, builderOfDom);
	}

	private boolean checkIfNewDocumentHasRoot(Document domDocument, DOMBuilder builderOfDom)
	{
		if (builderOfDom.build(domDocument).hasRootElement())
		{
			this.setJdomDocumentResult(builderOfDom.build(domDocument));
			return true;
		} else
		{
			return false;
		}
	}

	public org.jdom2.Document getJDomDocumentResult()
	{
		return JdomDocumentResult;
	}

	public void setJdomDocumentResult(org.jdom2.Document documentResult)
	{
		this.JdomDocumentResult = documentResult;
	}

	public org.jdom2.Document getJdomDoc()
	{
		return jdomDoc;
	}

	public void setJdomDoc(org.jdom2.Document jdomDoc)
	{
		this.jdomDoc = jdomDoc;
	}

	public String getPathToFile()
	{
		return pathToFile;
	}

	public void setPathToFile(String pathToFile)
	{
		this.pathToFile = pathToFile;
	}
	
}
