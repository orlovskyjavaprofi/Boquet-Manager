package models;

public class SatInformation implements Comparable<SatInformation>
{
	private String satName;

	public SatInformation() {
		satName="unknowsatname";
	}
	
	public SatInformation(String satName)
	{
		this.satName = satName;
	}

	@Override
	public int compareTo(SatInformation satOtherObjectInfo)
	{
		return this.getSatName().compareTo(satOtherObjectInfo.getSatName());
	}

	public String getSatName()
	{
		return satName;
	}

	public void setSatName(String satName)
	{
		this.satName = satName;
	}

	@Override
	public String toString()
	{
		return "\n satName = " + satName + "\n";
	}
	
	
    
}
