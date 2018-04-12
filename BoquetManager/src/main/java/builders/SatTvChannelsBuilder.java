package builders;

import java.util.LinkedList;
import java.util.List;

import org.jdom2.Element;

import models.SatTvChannel;
import utils.validators.ValidateSatData;

public class SatTvChannelsBuilder
{

	public List<SatTvChannel> buildListOfSatTvChannels(List<Element> satTvChannelList)
	{
		List<SatTvChannel> resultListOfSatTvChannels = new LinkedList<SatTvChannel>();  
		String inputServiceId;
		String inputChannelName;
		Byte inputServiceType = 0;
		ValidateSatData validatorObj = new ValidateSatData();
		String typeOfServices= "services";
		
		for (Element jdomSatTvChannelList : satTvChannelList)
		{
			inputServiceId = jdomSatTvChannelList.getAttributeValue("service_id");
			inputChannelName = jdomSatTvChannelList.getAttributeValue("name");
			inputServiceType = Byte.parseByte(jdomSatTvChannelList.getAttributeValue("service_type"));
			inputServiceType = validatorObj.changesIncorrectValueOfServiceType(inputServiceType,typeOfServices);
			resultListOfSatTvChannels.add(new SatTvChannel(inputServiceId, inputChannelName, inputServiceType) );
		}

		 return resultListOfSatTvChannels;
	}

}
