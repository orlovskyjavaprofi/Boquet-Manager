package models;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class SatServicesModel
{
	private org.jdom2.Document satServicesJdomDocument;
	private org.jdom2.Document satJdomDocument;
	private Integer AmountOfSatellites;
	
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
	
	public boolean readAndSetUpJDomDocument(String inputPath)
			throws ParserConfigurationException, SAXException, IOException
	{
		XmlReaderAndJdomDocumentCreator xmlReader = new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(xmlReader.getPathToFile());

		if (xmlReaderStatus == true)
		{
			this.setSatJdomDocument(xmlReader.getJDomDocumentResult());
		}

		return checkIfJDocumentWasCreated(xmlReaderStatus);
	}
	
	public boolean checkIfJDomDocumetIsSetUp()
	{
		if (this.getSatJdomDocument().hasRootElement() == true)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public Integer calculateAmountOfSatellitesForServices()
	{
		int amountOfSats = 0;

		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("sat");
		amountOfSats = checkNameOfSatAndCreateInformationObject(amountOfSats, empListElements);
		this.setAmountOfSatellites(amountOfSats);
       
		return amountOfSats;
	}
	
	private int checkNameOfSatAndCreateInformationObject(int amountOfSats, 
			List<Element> empListElements)
	{
		String satName = "";	
		
		for (Element oneLevelDeepIntoSat : empListElements)
		{
			satName = oneLevelDeepIntoSat.getAttributeValue("name");

			amountOfSats = checkIfSatHasANameAndServiceInfoObjectCalculateAmountSats(
					amountOfSats, satName, oneLevelDeepIntoSat);
		}
		
		return amountOfSats;
	}

	private int checkIfSatHasANameAndServiceInfoObjectCalculateAmountSats(
			int amountOfSats, String satName,	Element oneLevelDeepIntoSat)
	{
		if ((satName.isEmpty() == false))
		{
			amountOfSats++;		
		}
		
		return amountOfSats;
	}

	
	
	public Integer getAmountOfSatellites()
	{
		return AmountOfSatellites;
	}

	public void setAmountOfSatellites(Integer amountOfSatellites)
	{
		AmountOfSatellites = amountOfSatellites;
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
