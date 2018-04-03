package builders;

import java.util.LinkedList;
import java.util.List;

import org.jdom2.Element;

import models.SatTvChannel;

public class SatTvChannelsBuilder
{

	public List<SatTvChannel> buildListOfSatTvChannels(List<Element> satTvChannelList)
	{
		List<SatTvChannel> resultListOfSatTvChannels = new LinkedList<SatTvChannel>();  
		String inputServiceId;
		String inputChannelName;
		Byte inputServiceType = 0;
		
		for (Element jdomSatTvChannelList : satTvChannelList)
		{
			inputServiceId = jdomSatTvChannelList.getAttributeValue("service_id");
			inputChannelName = jdomSatTvChannelList.getAttributeValue("name");
			inputServiceType = Byte.parseByte(jdomSatTvChannelList.getAttributeValue("service_type"));
			resultListOfSatTvChannels.add(new SatTvChannel(inputServiceId, inputChannelName, inputServiceType) );
		}

		 return resultListOfSatTvChannels;
	}

}
