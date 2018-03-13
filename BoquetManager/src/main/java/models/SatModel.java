package models;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class SatModel
{
	private org.jdom2.Document satJdomDocument;

	public boolean readAndSetUpDomDocument(String inputPath) throws ParserConfigurationException, SAXException, IOException
	{
		XmlReaderAndJdomDocumentCreator xmlReader = new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(xmlReader.getPathToFile());

		if (xmlReaderStatus == true)
		{
			this.setSatJdomDocument(xmlReader.getJDomDocumentResult());
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

	public boolean checkIfDomDocumetIsSetUp()
	{
		if (this.getSatJdomDocument().hasRootElement() == true)
		{
			return true;
		} else
		{
			return false;
		}
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
