package xmloutputers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import models.SatServicesList;
import models.SatServicesModel;
import models.SatTransponder;
import models.SatTvChannel;

public class XmlOutputerForServices
{
	private Document satServicesXmlOutputDoc;

	public XmlOutputerForServices()
	{
        this.createXmlRoot();
	}

	private void createXmlRoot()
	{
		Element servicesRoot = new Element("zapit");
		Document doc = new Document(servicesRoot);
		this.setSatServicesXmlOutputDoc(doc);
	}

	
	public boolean checkIfServicesModelSetUp(SatServicesModel satServicesObj)
	{
		boolean result = false;

		if (satServicesObj.getSetOfSatelitesServices().isEmpty() == false)
		{
			result = true;
		}

		return result;
	}

	public Document getSatServicesXmlOutputDoc()
	{
		return satServicesXmlOutputDoc;
	}

	public void setSatServicesXmlOutputDoc(Document satServicesXmlOutputDoc)
	{
		this.satServicesXmlOutputDoc = satServicesXmlOutputDoc;
	}

	public boolean verifyXmlOutputerJdomDocument()
	{
		boolean result = false;
		
		if (this.getSatServicesXmlOutputDoc().hasRootElement() == true) {
			result = true;
		}
		
		return result;
	}

	public boolean creatingXmlElements(SatServicesModel satServicesObj)
	{
		boolean result = false;
		List<Attribute> listOfAttributesForService = new LinkedList<Attribute>();
		List<Attribute> listOfAttributesForTransponder = new LinkedList<Attribute>();
		List<Attribute> listOfAttributesForTransponderChn = new LinkedList<Attribute>();
		boolean statusOfMyServiceModelSetup = this.checkIfServicesModelSetUp(satServicesObj);
		
		if (statusOfMyServiceModelSetup == true) {

			iteratingOverServicesSetUpAttributes(satServicesObj, listOfAttributesForService,
					listOfAttributesForTransponder, listOfAttributesForTransponderChn);
			
			if( this.getSatServicesXmlOutputDoc().getDocument().getContentSize() > 0  ) {
				result = true;
			}
			
		}
		
		return result;
	}

	private void iteratingOverServicesSetUpAttributes(SatServicesModel satServicesObj,
			List<Attribute> listOfAttributesForService, List<Attribute> listOfAttributesForTransponder,
			List<Attribute> listOfAttributesForTransponderChn)
	{
		for (SatServicesList satServiceObj : satServicesObj.getSetOfSatelitesServices())
		{
			Element satServiceElement = new Element("sat");
			addingAttributesForService(listOfAttributesForService, satServiceObj);
			satServiceElement.setAttributes(listOfAttributesForService);
			listOfAttributesForService.removeAll(listOfAttributesForService);
			
		  iteratingOverTransponderListAttributesSetUp(listOfAttributesForTransponder,
				listOfAttributesForTransponderChn, satServiceObj, satServiceElement);
		  this.getSatServicesXmlOutputDoc().getRootElement().addContent(satServiceElement);
		}
	}

	private void iteratingOverTransponderListAttributesSetUp(List<Attribute> listOfAttributesForTransponder,
			List<Attribute> listOfAttributesForTransponderChn, SatServicesList satServiceObj, Element satServiceElement)
	{
		for (SatTransponder satServiceTransponder :  satServiceObj.getListOfTransponders())
			{
				Element satServiceTransponderElem = new Element("transponder");
				addingAttributesForTransponder(listOfAttributesForTransponder, satServiceTransponder);
				satServiceTransponderElem.setAttributes(listOfAttributesForTransponder);
				listOfAttributesForTransponder.removeAll(listOfAttributesForTransponder);

				addingAttributesForTransponderChannels(listOfAttributesForTransponderChn, 
						satServiceTransponder, satServiceTransponderElem);
				satServiceElement.addContent(satServiceTransponderElem);
			}
	}

	private void addingAttributesForService(List<Attribute> listOfAttributesForService, SatServicesList satServiceObj)
	{
		listOfAttributesForService.add(new Attribute("name",
				satServiceObj.getSatName() ));
		listOfAttributesForService.add(new Attribute("position", 
				Integer.toString(satServiceObj.getSatPosition()) ));
		listOfAttributesForService.add(new Attribute("diseqc", 
				Byte.toString( satServiceObj.getSatDiseqc()) ));
	}

	private void addingAttributesForTransponder(List<Attribute> listOfAttributesForTransponder,
			SatTransponder satServiceTransponder)
	{
		listOfAttributesForTransponder.add(new Attribute("id",
				satServiceTransponder.getTransponderId()));
		listOfAttributesForTransponder.add(new Attribute("onid",
				satServiceTransponder.getTransponderOnid()));
		listOfAttributesForTransponder.add(new Attribute("frequency",
				Integer.toString(satServiceTransponder.getTransponderFrequency()) ));
		listOfAttributesForTransponder.add(new Attribute("inversion",
				Byte.toString(satServiceTransponder.getTransponderInversion()) ));
		listOfAttributesForTransponder.add(new Attribute("symbol_rate",
				Integer.toString(satServiceTransponder.getTransponderSymbolRate()) ));
		listOfAttributesForTransponder.add(new Attribute("fec_inner",
				Byte.toString(satServiceTransponder.getTransponderFecInner()) ));
		listOfAttributesForTransponder.add(new Attribute("polarization",
				Byte.toString(satServiceTransponder.getTransponderPolarisation()) ));
	}

	private void addingAttributesForTransponderChannels(List<Attribute> listOfAttributesForTransponderChn,
			SatTransponder satServiceTransponder, Element satServiceTransponderElem)
	{
		for (SatTvChannel satTvChannelForTrasponder : 
			                           satServiceTransponder.getListOfSatTvChannels())
		{
			Element satServiceTransponderChannel = new Element("channel");
			listOfAttributesForTransponderChn.add(new Attribute("service_id",
					satTvChannelForTrasponder.getServiceID() ));
			listOfAttributesForTransponderChn.add(new Attribute("name",
					satTvChannelForTrasponder.getChannelName() ));
			listOfAttributesForTransponderChn.add(new Attribute("service_type",
					Byte.toString(satTvChannelForTrasponder.getServiceType()) ));
			satServiceTransponderChannel.setAttributes(listOfAttributesForTransponderChn);
			listOfAttributesForTransponderChn.removeAll(listOfAttributesForTransponderChn);
			satServiceTransponderElem.addContent(satServiceTransponderChannel);
		}
	}

}
