package models;

public class SatServicesList  implements Comparable<SatServicesList>
{
	private String satName;
	private Integer satPosition;
	private Byte satDiseqc;
	
	public SatServicesList(String inputSatName, Integer inputSatPosition,
			                             Byte inputSatDiseqc)
	{
		this.satName = inputSatName;
		this.satPosition = inputSatPosition;
		this.satDiseqc = inputSatDiseqc;
	}	
	
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

	public Integer getSatPosition()
	{
		return satPosition;
	}

	public void setSatPosition(Integer satPosition)
	{
		this.satPosition = satPosition;
	}

	public Byte getSatDiseqc()
	{
		return satDiseqc;
	}

	public void setSatDiseqc(Byte satDiseqc)
	{
		this.satDiseqc = satDiseqc;
	}
	
	
}
