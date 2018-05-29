package xmloutputers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import models.SatBoquetTvChannel;
import models.SatTvBoquet;
import models.SatTvBoquetModel;

public class XmlOutputerForTvBoquets
{
	private Document satBoquetsXmlOutputDoc;

	public XmlOutputerForTvBoquets()
	{
		 this.createXmlRoot();
	}

	private void createXmlRoot()
	{
		Element boquetRoot = new Element("zapit");
		Document doc = new Document(boquetRoot);
		this.setSatBoquetsXmlOutputDoc(doc);
	}
	
	public boolean checkIfBoquetsModelSetUp(Set<SatTvBoquet> setOfBoquets)
	{
		boolean result = false;
		
		if (setOfBoquets.isEmpty() == false   ) {
			result = true;
		}
				
	   return result;
	}

	public Document getSatBoquetsXmlOutputDoc()
	{
		return satBoquetsXmlOutputDoc;
	}

	public void setSatBoquetsXmlOutputDoc(Document satBoquetsXmlOutputDoc)
	{
		this.satBoquetsXmlOutputDoc = satBoquetsXmlOutputDoc;
	}

	public boolean verifyXmlOutputerJdomDocument()
	{
		boolean result = false;
		
		if ( this.getSatBoquetsXmlOutputDoc().hasRootElement() == true ) {
			result = true;
		}
		
		return result;	
	}

	public boolean creatingXmlElements(SatTvBoquetModel satTvBoquetModelObj)
	{
		boolean result = false;
		
		List<Attribute> listOfAttributesForBoquet = new LinkedList<Attribute>();
		List<Attribute> listofAttributesForBoquetChannel = new LinkedList<Attribute>();
		boolean statusOfMyBoquetModelSetup = this.verifyXmlOutputerJdomDocument();	
		
	   if (statusOfMyBoquetModelSetup == true) {
			iteratingOverSatTvBoquetAddingBoquetToRoot(satTvBoquetModelObj, listOfAttributesForBoquet,
					listofAttributesForBoquetChannel);
			
			if (this.getSatBoquetsXmlOutputDoc().getDocument().getContentSize()  > 0) {
				result = true;
			}
	   }
		
		return result;
	}

	private void iteratingOverSatTvBoquetAddingBoquetToRoot(SatTvBoquetModel satTvBoquetModelObj,
			List<Attribute> listOfAttributesForBoquet, List<Attribute> listofAttributesForBoquetChannel)
	{
		for (SatTvBoquet satTvBoquet : satTvBoquetModelObj.getResultSetOfSatTvBoquets() )
		{
			Element satBoquetElement = new Element("Bouquet");
			addingAttributesForBouquetElem(listOfAttributesForBoquet, satTvBoquet,
					                                               satBoquetElement);
			iteratingOverBouquetChannelsAndAddingToBouquet(listofAttributesForBoquetChannel, satTvBoquet,
					satBoquetElement);
			this.getSatBoquetsXmlOutputDoc().getRootElement().addContent(satBoquetElement);
		}
	}

	private void iteratingOverBouquetChannelsAndAddingToBouquet(List<Attribute> listofAttributesForBoquetChannel,
			SatTvBoquet satTvBoquet, Element satBoquetElement)
	{
		for (SatBoquetTvChannel satTvBoquetChn : satTvBoquet.getListOfSatTvChannels())
		{
			Element satTvChannelElement = new Element("channel");
			addingAttributesForBoquetChannelElem(listofAttributesForBoquetChannel, satTvBoquetChn,
					satTvChannelElement);
			satBoquetElement.addContent(satTvChannelElement);
		}
	}

	private void addingAttributesForBoquetChannelElem(List<Attribute> listofAttributesForBoquetChannel,
			SatBoquetTvChannel satTvBoquetChn, Element satTvChannelElement)
	{
		listofAttributesForBoquetChannel.add(new Attribute("serviceID", 
				satTvBoquetChn.getSatBoquetTvChannelServiceID() ));
		listofAttributesForBoquetChannel.add(new Attribute("name",
				satTvBoquetChn.getSatBoquetTvChannelName() ));
		listofAttributesForBoquetChannel.add(new Attribute("tsid",
				satTvBoquetChn.getSatBoquetTvChannelTransponderId() ));
		listofAttributesForBoquetChannel.add(new Attribute("onid",
				satTvBoquetChn.getSatBoquetTvChanneltransponderOnid() ));
		listofAttributesForBoquetChannel.add(new Attribute("sat",
				Integer.toString(satTvBoquetChn.getSatBoquetTvChannelsatPosition()) ));
		satTvChannelElement.setAttributes(listofAttributesForBoquetChannel);
		listofAttributesForBoquetChannel.removeAll(listofAttributesForBoquetChannel);
	}

	private void addingAttributesForBouquetElem(List<Attribute> listOfAttributesForBoquet, SatTvBoquet satTvBoquet,
			Element satBoquetElement)
	{
		listOfAttributesForBoquet.add(new Attribute("type", 
				Byte.toString(satTvBoquet.getSatTvBoquetServiceType()) ));
		listOfAttributesForBoquet.add(new Attribute("bouquet_id",
				satTvBoquet.getSatTvBoquetId() ));
		listOfAttributesForBoquet.add(new Attribute("name",
				satTvBoquet.getSatTvBoquetName() ));
		listOfAttributesForBoquet.add(new Attribute("hidden",
				Byte.toString(satTvBoquet.getSatTvBoquetHidden())	));
		listOfAttributesForBoquet.add(new Attribute("locked",
				Byte.toString(satTvBoquet.getSatTvBoquetLocked()) ));
		satBoquetElement.setAttributes(listOfAttributesForBoquet);
		listOfAttributesForBoquet.removeAll(listOfAttributesForBoquet);
	}

}
