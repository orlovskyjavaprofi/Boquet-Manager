package builders;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jdom2.Element;

import models.SatBoquetTvChannel;
import models.SatTvBoquet;
import utils.validators.ValidateSatData;

public class SatBouquetsBuilder
{

	public SortedSet<SatTvBoquet> buildSatTvBoquetsSet(
			List<Element> listOfBoquetsJdomElems)
	{	
		  SortedSet<SatTvBoquet> setOftheSatTvBoquets= new TreeSet<SatTvBoquet>();
		  buildingSetOfSatTvBoquets(listOfBoquetsJdomElems, setOftheSatTvBoquets );
		
		  return setOftheSatTvBoquets;
		
	}
	
	private void buildingSetOfSatTvBoquets(List<Element> inputListOfBoquetsElems, 
			 SortedSet<SatTvBoquet> outputSetOfSatTvBoquets) {
		
        Byte satTvBoquetServiceType;
        String satTvBoquetId;
        String satTvBoquetName;
        Byte satTvBoquetHidden;
        Byte satTvBoquetLocked; 
        List<SatBoquetTvChannel> listOfSatTvBoquetChannels;
        
        ValidateSatData validateSatDatObj = new ValidateSatData();
        String typeOfServices="boquets";
        Integer amountOfAttrobutesForGiveChannel = 0;
  		for (Element boquetElem : inputListOfBoquetsElems)
  		{
  			amountOfAttrobutesForGiveChannel = boquetElem.getChild("channel").getAttributes().size();
  					                                                        
  			if ( (boquetElem.getAttributes().size() == 5) &&  (amountOfAttrobutesForGiveChannel == 5) ){
  				
	  			satTvBoquetServiceType = Byte.parseByte(boquetElem.getAttributeValue("type"));
	  			satTvBoquetServiceType = validateSatDatObj.changesIncorrectValueOfServiceType(
	  					satTvBoquetServiceType, typeOfServices);
	  			satTvBoquetId = boquetElem.getAttributeValue("bouquet_id");
	  			satTvBoquetName = boquetElem.getAttributeValue("name");
	  			satTvBoquetHidden = Byte.parseByte( boquetElem.getAttributeValue("hidden") );
	  			satTvBoquetHidden = 
	  			  validateSatDatObj.changeIncorrectBoquetHiddenOrLockedAttribute(satTvBoquetHidden);
	  			satTvBoquetLocked =  Byte.parseByte(boquetElem.getAttributeValue("locked") );
	  			satTvBoquetLocked = 
	  			  validateSatDatObj.changeIncorrectBoquetHiddenOrLockedAttribute( satTvBoquetLocked);
	  			listOfSatTvBoquetChannels = 
	  			   createNewBuilderForBuildingBoquetTvChannelslist(boquetElem);
	  			
	  			outputSetOfSatTvBoquets.add(createNewBoquet(
	  					    satTvBoquetServiceType,
	  					    satTvBoquetId, 
	  					    satTvBoquetName,  
	  					    satTvBoquetHidden, 
	  					    satTvBoquetLocked,
	  				      	listOfSatTvBoquetChannels 
	  					   )
	  					);
  			}
  		}

	}
	
	private SatTvBoquet createNewBoquet( 
			              Byte inputServiceType,  
			              String inputSatTvBoquetId,  
			              String inputSatTvBoquetName, 
			              Byte inputSatTvBoquetHidden, 
			              Byte InputsatTvBoquetLocked,   
			             List<SatBoquetTvChannel> inputlistOfSatTvBoquetChannels
			) {
		return new SatTvBoquet( inputServiceType, inputSatTvBoquetId,  
				                                 inputSatTvBoquetName,  inputSatTvBoquetHidden,  
				                                 InputsatTvBoquetLocked,  inputlistOfSatTvBoquetChannels);		
	}
		
	private List<SatBoquetTvChannel> createNewBuilderForBuildingBoquetTvChannelslist(Element boquetJdomElem)
	{
		List<Element> satTvChannelsFromTheBoquet = boquetJdomElem.getChildren("channel");
		return new SatTVBoquetChannelsBuilder().buildListOfBoquetSatTvChannels(
				satTvChannelsFromTheBoquet
			);
	}

}
