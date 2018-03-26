package models;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class SatServicesModel
{
	private org.jdom2.Document satServicesJdomDocument;
	
	public boolean readAndSetUpAjDomDocument(String inputPath)
			throws ParserConfigurationException, SAXException, IOException
	{
		XmlReaderAndJdomDocumentCreator xmlReader = new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(xmlReader.getPathToFile());

		if (xmlReaderStatus == true)
		{
			this.setSatServicesJdomDocument(xmlReader.getJDomDocumentResult());
		}

		return checkIfJDocumentWasCreated(xmlReaderStatus);
	}

	private boolean checkIfJDocumentWasCreated(boolean xmlReaderStatus)
	{
		if (xmlReaderStatus == true)
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public org.jdom2.Document getSatServicesJdomDocument()
	{
		return satServicesJdomDocument;
	}

	public void setSatServicesJdomDocument(org.jdom2.Document satJdomDocument)
	{
		this.satServicesJdomDocument = satJdomDocument;
	}
	

}
