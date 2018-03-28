package models;

public class SatTvChannel
{
	private String serviceID;
    private String channelName;
	private Byte serviceType;
	
	public SatTvChannel(String inputServiceID,String inputChannelName, Byte inputServiceType)
	{
		this.serviceID = inputServiceID;
		this.channelName = inputChannelName;
		this.serviceType = inputServiceType;
	}

	public SatTvChannel()
	{
		this.channelName = "unknowChanelname";
	}

	@Override
	public String toString()
	{
		return "SatTvChannel: channelName: " + channelName +
				                           " serviceId: "         + serviceID        + 
				                           " serviceType: "    + String.format("%02d", serviceType)+"\n";
		
		
	}

	public String getServiceID()
	{
		return serviceID;
	}

	public void setServiceID(String serviceID)
	{
		this.serviceID = serviceID;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

	public Byte getServiceType()
	{
		return serviceType;
	}

	public void setServiceType(Byte serviceType)
	{
		this.serviceType = serviceType;
	}
	
}
