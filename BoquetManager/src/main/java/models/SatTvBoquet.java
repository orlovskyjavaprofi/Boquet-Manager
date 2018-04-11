package models;

import java.util.List;

public class SatTvBoquet
{
	private Byte satTvBoquetServiceType;
    private String satTvBoquetId;
	private String satTvBoquetChannelName;
    private Byte satTvBoquetHidden;
    private Byte satTvBoquetLocked; 
    private List<SatBoquetTvChannel> listOfSatTvChannels;
    
	public SatTvBoquet(Byte inputBoquetServiceType, String inputBoquetId,
			String inputBoquetName, Byte inputHiddenBoquet, Byte inputLockedBoquet,
			List<SatBoquetTvChannel> inputSatTvChannelsList
			)
	{
		this.satTvBoquetServiceType = inputBoquetServiceType;
		this.satTvBoquetId = inputBoquetId;
		this.satTvBoquetChannelName = inputBoquetName; 
		this.satTvBoquetHidden = inputHiddenBoquet;
		this.satTvBoquetLocked = inputLockedBoquet;
	    this.listOfSatTvChannels = inputSatTvChannelsList;
	}
	
	public List<SatBoquetTvChannel> getListOfSatTvChannels()
	{
		return listOfSatTvChannels;
	}

	public void setListOfSatTvChannels(List<SatBoquetTvChannel> listOfSatTvChannels)
	{
		this.listOfSatTvChannels = listOfSatTvChannels;
	}

	public Byte getSatTvBoquetLocked()
	{
		return satTvBoquetLocked;
	}

	public void setSatTvBoquetLocked(Byte satTvBoquetLocked)
	{
		this.satTvBoquetLocked = satTvBoquetLocked;
	}

	public Byte getSatTvBoquetHidden()
	{
		return satTvBoquetHidden;
	}

	public void setSatTvBoquetHidden(Byte satTvBoquetHidden)
	{
		this.satTvBoquetHidden = satTvBoquetHidden;
	}

	public String getSatTvBoquetChannelName()
	{
		return satTvBoquetChannelName;
	}

	public void setSatTvBoquetChannelName(String satTvBoquetChannelName)
	{
		this.satTvBoquetChannelName = satTvBoquetChannelName;
	}

	public String getSatTvBoquetId()
	{
		return satTvBoquetId;
	}

	public void setSatTvBoquetId(String satTvBoquetId)
	{
		this.satTvBoquetId = satTvBoquetId;
	}

	public Byte getSatTvBoquetServiceType()
	{
		return satTvBoquetServiceType;
	}

	public void setSatTvBoquetServiceType(Byte satTvBoquetServiceType)
	{
		this.satTvBoquetServiceType = satTvBoquetServiceType;
	}

	@Override
	public String toString()
	{
		return "SatTvBoquetv  \n satTvBoquetServiceType: " + satTvBoquetServiceType 
				+ " satTvBoquetId: " + satTvBoquetId
				+ " satTvBoquetChannelName: " + satTvBoquetChannelName +"\n"
				+ " satTvBoquetHidden: " + satTvBoquetHidden
				+ " satTvBoquetLocked: " + satTvBoquetLocked +"\n"
				+ " listOfSatTvChannels\n " + listOfSatTvChannels ;
	}
	
	
	
}
