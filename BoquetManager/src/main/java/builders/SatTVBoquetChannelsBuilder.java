package builders;

import java.util.LinkedList;
import java.util.List;

import org.jdom2.Element;

import models.SatBoquetTvChannel;
import utils.validators.ValidateSatData;

public class SatTVBoquetChannelsBuilder
{

	public List<SatBoquetTvChannel> buildListOfBoquetSatTvChannels(
			List<Element> satTvChannelBoquetList)
	{
		List<SatBoquetTvChannel> resultListOfBoquetTvChannels = 
				new LinkedList<SatBoquetTvChannel>();
		String satBoquetTvChannelServiceID;
		String satBoquetTvChannelName;
		ValidateSatData helperUtilObj = new ValidateSatData();
		String satBoquetTvChannelTransponderId;
		String satBoquetTvChanneltransponderOnid;
		Integer satBoquetTvChannelsatPosition;

		for (Element jdomElemBoquetTvChanell : satTvChannelBoquetList)
		{
			if (jdomElemBoquetTvChanell.getAttributes().size() == 5) {
			
			satBoquetTvChannelServiceID = jdomElemBoquetTvChanell.getAttributeValue("serviceID");
			satBoquetTvChannelName = jdomElemBoquetTvChanell.getAttributeValue("name");
			satBoquetTvChannelName = helperUtilObj.verifyConsistencyOfBoquetTvChannel(satBoquetTvChannelName);
			satBoquetTvChannelTransponderId = jdomElemBoquetTvChanell.getAttributeValue("tsid");
			satBoquetTvChanneltransponderOnid = jdomElemBoquetTvChanell.getAttributeValue("onid");
			satBoquetTvChannelsatPosition = Integer.parseInt(jdomElemBoquetTvChanell.getAttributeValue("sat"));
			
			resultListOfBoquetTvChannels.add(new SatBoquetTvChannel(
					satBoquetTvChannelServiceID, satBoquetTvChannelName,
					satBoquetTvChannelTransponderId, satBoquetTvChanneltransponderOnid, 
					satBoquetTvChannelsatPosition)
			  );
			}
		}
		
		return resultListOfBoquetTvChannels;
	}

}
