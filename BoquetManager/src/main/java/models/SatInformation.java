package models;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SatInformation implements Comparable<SatInformation>
{
	private String satName;
	private Integer satFlags;
	private Integer satPosition;
	private List<Integer> satTransponderFrequencyList = new LinkedList<Integer>();

	public SatInformation()
	{
		satName = "unknowsatname";
	}

	public SatInformation(String satName)
	{
		this.satName = satName;
	}

	public SatInformation(
			String inputSatName, 
			Integer inputFlags, 
			Integer inputPosition,
			List<Integer> satInputTransponderFrequencyList)
	{
		this.satName = inputSatName;
		this.satFlags = inputFlags;
		this.satPosition = inputPosition;
		this.satTransponderFrequencyList = satInputTransponderFrequencyList;
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
		return "\n=================================================================\n"
				+ " satName = " + this.getSatName() + " satFlags = " + this.getSatFlags()
				+ " satPosition = " + this.getSatPosition()+
				"\n==================="+ " Numbers of frequency: "+this.getSatTransponderFrequencyList().size() +"=============================================="
				+ "\n satTransponderFrequencylist : "
				+ "\n"+this.getSatTransponderFrequencyList().toString();
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

	public List<Integer> getSatTransponderFrequencyList()
	{
		return satTransponderFrequencyList;
	}

	public void setSatTransponderFrequencyList(List<Integer> satTransponderFrequencyList)
	{
		this.satTransponderFrequencyList = satTransponderFrequencyList;
	}

	public boolean checkTheLengthOfTransponderFrequency()
	{
		boolean result = false;
		Integer lengthOfTranspondersFrequency = 8;
		Integer currentLengthOfTranspondersFrequency = 0;
		
		for (ListIterator<Integer> iterator = satTransponderFrequencyList.listIterator(); 
				iterator.hasNext();)
		{

			currentLengthOfTranspondersFrequency = iterator.next().toString().length();
			result = comparingTheLengthOfTransponderFrequency(
					lengthOfTranspondersFrequency,
					currentLengthOfTranspondersFrequency);
			if (result == false) {
				break;
			}
		}

		return result;

	}

	private boolean comparingTheLengthOfTransponderFrequency(Integer lengthOfTranspondersFrequency,
			Integer currentLengthOfTranspondersFrequency)
	{
		boolean result= false;
		
		if (lengthOfTranspondersFrequency.intValue() == currentLengthOfTranspondersFrequency.intValue())
		{
			result = true;
		} else
		{
			result = false;
		}
		return result;
	}

}
