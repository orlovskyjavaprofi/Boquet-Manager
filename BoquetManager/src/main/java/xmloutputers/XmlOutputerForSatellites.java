package xmloutputers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import models.SatInformation;
import models.SatModel;

public class XmlOutputerForSatellites
{
    private Document satXmlOutputDoc;
		
	public boolean verifyThaSatModelIsSetUp(SatModel allSattelitesInfoObj)
	{
		boolean result = false;
		
		if (allSattelitesInfoObj.getSortedSatellitesInformationSet().isEmpty() == false  ) {
			result = true;
		}
		
		return result;
		
	}

	public void  createXmlRoot()
	{
		Element  satelliteRoot = new Element("satellites");
		Document doc = new Document(satelliteRoot);
		this.setSatXmlOutputDoc(doc);
	}

	public boolean creatingXmlElements(SatModel allSattelitesInfoObj)
	{
		boolean result = false;
		Integer amountOfSatellites = 0;
		boolean statusOfMySatModelSetup = this.verifyThaSatModelIsSetUp(allSattelitesInfoObj);
		List<Attribute> listOfAttributesForSat = new ArrayList<Attribute>();
		List<Attribute> listOfAttributesForTransponder = new ArrayList<Attribute>();
		
		if (statusOfMySatModelSetup == true) {
			amountOfSatellites	= allSattelitesInfoObj.getAmountOfSatellites();
		
	        result = chekingAmountOfSatAndGeneratingXml(allSattelitesInfoObj, result, 
	        		amountOfSatellites,	listOfAttributesForSat, listOfAttributesForTransponder);
		}

		return result;
	}

	private boolean chekingAmountOfSatAndGeneratingXml(SatModel allSattelitesInfoObj, 
			boolean result, 	Integer amountOfSatellites, List<Attribute> listOfAttributesForSat,
			List<Attribute> listOfAttributesForTransponder)
	{
		if (amountOfSatellites > 0) {
			    result = true;
		    Element satElement = new Element("sat");
		       	        
			    satElement = generatingNewSatXmlDocument(
			    		allSattelitesInfoObj, listOfAttributesForSat,	listOfAttributesForTransponder, 
			    		satElement);     
		}
		return result;
	}

	private Element generatingNewSatXmlDocument(SatModel allSattelitesInfoObj,
			List<Attribute> listOfAttributesForSat,
			List<Attribute> listOfAttributesForTransponder, Element satElement)
	{
		for ( SatInformation satObj  : allSattelitesInfoObj.getSortedSatellitesInformationSet())
		{
		      	listOfAttributesForSat.add( new Attribute("name",satObj.getSatName()) );
		      	listOfAttributesForSat.add( new Attribute("flags", 
		      			                                                      Integer.toString(satObj.getSatFlags())));
		      	listOfAttributesForSat.add( new Attribute("position", 
		      			                                                  Integer.toString(satObj.getSatPosition())));
		  	satElement.setAttributes(listOfAttributesForSat);				
		    listOfAttributesForSat.removeAll(listOfAttributesForSat);
		    
		    iteratingOverListsOfSat(listOfAttributesForTransponder, satElement, satObj);
		    
		    this.getSatXmlOutputDoc().getRootElement().addContent(satElement);
		    satElement = new Element("sat");
		    
		}
		return satElement;
	}

	private void iteratingOverListsOfSat(List<Attribute> listOfAttributesForTransponder, 
			Element satElement, SatInformation satObj)
	{
		Element transponderElem;
		for (int index = 0; index < satObj.getSatTransponderFrequencyList().size(); index++)
		{
			transponderElem = generatingNewTransponderElem(
					                        listOfAttributesForTransponder, satObj, index);

			transponderElem.setAttributes(listOfAttributesForTransponder);
			listOfAttributesForTransponder.removeAll(listOfAttributesForTransponder);
			satElement.addContent(transponderElem);
		}
	}

	private Element generatingNewTransponderElem(
			List<Attribute> listOfAttributesForTransponder, SatInformation satObj,	int index)
	{
		Element transponderElem;
		transponderElem = new Element("transponder");
		listOfAttributesForTransponder
			   .add(new Attribute("frequency", Integer.toString(
					                                           satObj.getSatTransponderFrequencyList().get(index))));
		listOfAttributesForTransponder
				.add(new Attribute("symbol_rate", Integer.toString(
						                                       satObj.getSatSymbolRateList().get(index))));
		listOfAttributesForTransponder
				.add(new Attribute("polarization", Byte.toString(
						                                       satObj.getSatPolarisationList().get(index))));
		listOfAttributesForTransponder
				.add(new Attribute("fec_inner", Byte.toString(satObj.getSatFecInnerList().get(index))));
		
		return transponderElem;
	}

	public boolean verifyXmlOutputerJdomDocument()
	{
		boolean result = false;
		
		if (this.getSatXmlOutputDoc().hasRootElement() == true) {
			result = true;
		}
		
		return result;
	}

	public Document getSatXmlOutputDoc()
	{
		return satXmlOutputDoc;
	}

	public void setSatXmlOutputDoc(Document satXmlOutputDoc)
	{
		this.satXmlOutputDoc = satXmlOutputDoc;
	}

	
}
