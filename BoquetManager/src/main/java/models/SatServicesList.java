package models;

import java.util.List;

public class SatServicesList  implements Comparable<SatServicesList>
{
	private String satName;
	private Integer satPosition;
	private Byte satDiseqc;
	private List<SatTransponder> listOfTransponders;
	
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

	public SatServicesList(String inputSatName, Integer inputSatPosition, Byte inputSatDiseqc,
			List<SatTransponder> inputlistOfTransponders)
	{
		this.satName = inputSatName;
		this.satPosition = inputSatPosition;
		this.satDiseqc = inputSatDiseqc;
		this.listOfTransponders = inputlistOfTransponders;
	}

	@Override
	public String toString()
	{
		return "SatServicesList: satName=" + satName + " position: "+satPosition+
				" diseqc: "+satDiseqc+"\n"+"List of transponders\n"+listOfTransponders.toString()+
				"\n==========================================================\n";
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

	public List<SatTransponder> getListOfTransponders()
	{
		return listOfTransponders;
	}

	public void setListOfTransponders(List<SatTransponder> listOfTransponders)
	{
		this.listOfTransponders = listOfTransponders;
	}
	
	
}
