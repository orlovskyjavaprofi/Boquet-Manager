package models;

public class SatBoquetTvChannel
{
     private String satBoquetTvChannelServiceID;
	 private String satBoquetTvChannelName;  
	 private String satBoquetTvChannelTransponderId;
     private String satBoquetTvChanneltransponderOnid;
     private Integer satBoquetTvChannelsatPosition;
     
	public SatBoquetTvChannel(String inputChannelServiceID,
			String inputTvChannelName, String inputTransponderId, 
			String inputTransponderOnid, Integer inputSatPosition)
	{
		this.satBoquetTvChannelServiceID = inputChannelServiceID;
		this.satBoquetTvChannelName = inputTvChannelName;
		this.satBoquetTvChannelTransponderId = inputTransponderId;
		this.satBoquetTvChanneltransponderOnid = inputTransponderOnid;
		this.satBoquetTvChannelsatPosition = inputSatPosition;
	}
	
	public Integer getSatBoquetTvChannelsatPosition()
	{
		return satBoquetTvChannelsatPosition;
	}

	public void setSatBoquetTvChannelsatPosition(Integer satBoquetTvChannelsatPosition)
	{
		this.satBoquetTvChannelsatPosition = satBoquetTvChannelsatPosition;
	}

	public String getSatBoquetTvChanneltransponderOnid()
	{
		return satBoquetTvChanneltransponderOnid;
	}

	public void setSatBoquetTvChanneltransponderOnid(String satBoquetTvChanneltransponderOnid)
	{
		this.satBoquetTvChanneltransponderOnid = satBoquetTvChanneltransponderOnid;
	}

	public String getSatBoquetTvChannelServiceID()
	{
		return satBoquetTvChannelServiceID;
	}

	public void setSatBoquetTvChannelServiceID(String satBoquetTvChannelServiceID)
	{
		this.satBoquetTvChannelServiceID = satBoquetTvChannelServiceID;
	}

	public String getSatBoquetTvChannelName()
	{
		return satBoquetTvChannelName;
	}

	public void setSatBoquetTvChannelName(String satBoquetTvChannelName)
	{
		this.satBoquetTvChannelName = satBoquetTvChannelName;
	}

	public String getSatBoquetTvChannelTransponderId()
	{
		return satBoquetTvChannelTransponderId;
	}

	public void setSatBoquetTvChannelTransponderId(String satBoquetTvChannelTransponderId)
	{
		this.satBoquetTvChannelTransponderId = satBoquetTvChannelTransponderId;
	}

	@Override
	public String toString()
	{
		return  " satBoquetTvChannelName: " + satBoquetTvChannelName
	             +	"satBoquetTvChannelServiceID: " + satBoquetTvChannelServiceID +"\n"
				+ " satBoquetTvChannelTransponderId: "	+ satBoquetTvChannelTransponderId +"\n"
				+ " satBoquetTvChanneltransponderOnid: "	+ satBoquetTvChanneltransponderOnid 
				+ " satBoquetTvChannelsatPosition: " + satBoquetTvChannelsatPosition+"\n";
	}

	
}
