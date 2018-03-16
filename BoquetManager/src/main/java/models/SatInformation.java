package models;

public class SatInformation implements Comparable<SatInformation>
{
	private String satName;
    private Integer satFlags;
    private Integer satPosition;
    
	public SatInformation() {
		satName="unknowsatname";
	}
	
	public SatInformation(String satName)
	{
		this.satName = satName;
	}

	public SatInformation(String inputSatName, Integer inputFlags, Integer inputPosition)
	{
		this.satName = inputSatName;
		this.satFlags = inputFlags;
		this.satPosition = inputPosition;
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
		return "\n satName = " +  this.getSatName() + "\n"+
	                  " satFlags = "+ this.getSatFlags()+"\n"+
				    " satPosition = "+ this.getSatPosition()+"\n";
	}

	public Integer getSatFlags()
	{
		return satFlags;
	}

	public void setSatFlags(Integer satFlags)
	{
		this.satFlags = satFlags;
	}

	public Integer getSatPosition()
	{
		return satPosition;
	}

	public void setSatPosition(Integer satPosition)
	{
		this.satPosition = satPosition;
	}
    
}
