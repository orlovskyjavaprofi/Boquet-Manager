package models;

import java.util.List;

public class SatTvBoquet  implements Comparable<SatTvBoquet>
{
	private Byte satTvBoquetServiceType;
    private String satTvBoquetId;
	private String satTvBoquetName;
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
		this.satTvBoquetName = inputBoquetName; 
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

	public String getSatTvBoquetName()
	{
		return satTvBoquetName;
	}

	public void setSatTvBoquetName(String satTvBoquetName)
	{
		this.satTvBoquetName = satTvBoquetName;
	}

	@Override
	public int compareTo(SatTvBoquet anotherSatTvBoquetObject)
	{
		return this.getSatTvBoquetName().compareTo(
				anotherSatTvBoquetObject.getSatTvBoquetName());
	}
	
	@Override
	public String toString()
	{
		return "\n====================BoquetName: " + satTvBoquetName +
				" ======================="
				+ "  \n ServiceType: " + satTvBoquetServiceType 
				+ " satTvBoquetId: " + satTvBoquetId
				+ " satTvBoquetHidden: " + satTvBoquetHidden
				+ " satTvBoquetLocked: " + satTvBoquetLocked +"\n"
				+ " listOfSatTvChannels\n " + listOfSatTvChannels ;
	}

}
