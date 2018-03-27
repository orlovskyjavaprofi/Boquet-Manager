package models;

public class SatServicesList  implements Comparable<SatServicesList>
{
	private String satName;
	
	public SatServicesList(String inputSatName)
	{
		this.satName = inputSatName;
	}	
	
	public SatServicesList()
	{
		this.satName = "unknowsatname";
	}





	@Override
	public String toString()
	{
		return "SatServicesList:\n satName=" + satName ;
	}

	@Override
	public int compareTo(SatServicesList anotherSatServicesObject)
	{
		return this.getSatName().compareTo(anotherSatServicesObject.getSatName());
	}

	public String getSatName()
	{
		return satName;
	}

	public void setSatName(String satName)
	{
		this.satName = satName;
	}
	
	
}
