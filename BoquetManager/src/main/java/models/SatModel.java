package models;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import utils.XmlReaderAndJdomDocumentCreator;

public class SatModel
{
	private org.jdom2.Document satJdomDocument;
    private Integer AmountOfSatellites;
    
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

	public Integer calculateAmountOfSatellites()
	{
		int amountOfSats= 0;
		
		Element root = this.getSatJdomDocument().getRootElement();
		List<Element> empListElements = root.getChildren("sat");
		amountOfSats = checkNamesOfSatsAndCalculateAmount(amountOfSats, empListElements);
		this.setAmountOfSatellites(amountOfSats);
		return amountOfSats;
	}

	private int checkNamesOfSatsAndCalculateAmount(int amountOfSats, List<Element> empListElements)
	{
		String satName="";
		
		for (Element empElement : empListElements)
		{
			satName = empElement.getAttributeValue("name");
			if( satName.isEmpty() == false ) {
				amountOfSats++;
			}
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

	
}
