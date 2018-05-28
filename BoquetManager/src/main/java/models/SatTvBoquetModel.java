package models;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.xml.sax.SAXException;

import builders.SatBouquetsBuilder;
import utils.XmlReaderAndJdomDocumentCreator;

public class SatTvBoquetModel
{
	private org.jdom2.Document satBoquetsJdomDocument;
	private Set<SatTvBoquet>resultSetOfSatTvBoquets;
	
	public boolean readAndSetUpJDomDocument(String inputPath)
			throws ParserConfigurationException, SAXException, IOException
	{
		XmlReaderAndJdomDocumentCreator xmlReader = 
				new XmlReaderAndJdomDocumentCreator(inputPath);
		boolean xmlReaderStatus = xmlReader.getDocumentFromDomParser(
				xmlReader.getPathToFile());

		if (xmlReaderStatus == true)
		{
			this.setSatBoquetsJdomDocument(xmlReader.getJDomDocumentResult());
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
	
	public boolean checkIfJDomDocumetIsSetUp()
	{
		if (this.getSatBoquetsJdomDocument().hasRootElement() == true)
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public boolean buildSetOfBoquets(	List<Element> inputBoquetsJdomDocument)
	{
		boolean result = false;
		
		SatBouquetsBuilder builderOfSetOfBoquets = new SatBouquetsBuilder();
		
		if (inputBoquetsJdomDocument.isEmpty()  == false) {
			this.setResultSetOfSatTvBoquets( 
					builderOfSetOfBoquets.buildSatTvBoquetsSet(inputBoquetsJdomDocument));
			return true;
		}
		
		return result;
	}
	

	public Set<SatTvBoquet> getResultSetOfSatTvBoquets()
	{
		return resultSetOfSatTvBoquets;
	}

	public void setResultSetOfSatTvBoquets(Set<SatTvBoquet> resultSetOfSatTvBoquets)
	{
		this.resultSetOfSatTvBoquets = resultSetOfSatTvBoquets;
	}

	public org.jdom2.Document getSatBoquetsJdomDocument()
	{
		return satBoquetsJdomDocument;
	}

	public void setSatBoquetsJdomDocument(org.jdom2.Document satBoquetsJdomDocument)
	{
		this.satBoquetsJdomDocument = satBoquetsJdomDocument;
	}


	
	
}
