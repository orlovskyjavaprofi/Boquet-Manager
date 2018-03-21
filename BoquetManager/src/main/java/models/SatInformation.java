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
	private List<Integer> satSymbolRateList = new LinkedList<Integer>();
	private List<Byte> satPolarisationList = new LinkedList<Byte>();
	private List<Byte> satFecInnerList = new LinkedList<Byte>();
	
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
			   List<Integer> satInputTransponderFrequencyList,
			   List<Integer> satInputTransponderSymbolRateList,
			   List<Byte> satInputPolarisationList,
			   List<Byte> satInputFecInnerList
			)
	{
		this.satName = inputSatName;
		this.satFlags = inputFlags;
		this.satPosition = inputPosition;
		this.satTransponderFrequencyList = satInputTransponderFrequencyList;
		this.satSymbolRateList = satInputTransponderSymbolRateList;
		this.satPolarisationList = satInputPolarisationList;
		this.satFecInnerList = satInputFecInnerList;
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
		return "\n================="+ " Sattelite name = " + this.getSatName()+"================================================\n"				
				+ " satFlags = " + this.getSatFlags() 
				+ " satPosition = " + this.getSatPosition()
				+"\n Numbers of frequency: "+this.getSatTransponderFrequencyList().size()+","
				+ " Numbers of symbolrate: "+this.getSatSymbolRateList().size()+","
				+ "\n Numbers of satPolarization: "+this.getSatPolarisationList().size()+","
				+ " Numbers of satFecInner: "+ this.getSatFecInnerList().size()+"\n";
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

	public boolean checkIfSymbolrateNotNull()
	{
		boolean statusOftheSymbolRateObject = this.getSatSymbolRateList().isEmpty();
		if (statusOftheSymbolRateObject == false)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public List<Integer> getSatSymbolRateList()
	{
		return satSymbolRateList;
	}

	public void setSatSymbolRateList(List<Integer> satSymbolRateList)
	{
		this.satSymbolRateList = satSymbolRateList;
	}

	public boolean checkIfPolarisationNotNull()
	{
		boolean result = true;
		
		if ( this.getSatPolarisationList().isEmpty() == false ) {
			result = false;
		}else {
			result = true;
		}
		
		return result;
	}

	public List<Byte> getSatPolarisationList()
	{
		return satPolarisationList;
	}

	public void setSatPolarisationList(List<Byte> satPolarisationList)
	{
		this.satPolarisationList = satPolarisationList;
	}

	public boolean checkfec_innerNotNull()
	{		
		boolean result = true;
		
		if ( this.getSatFecInnerList().isEmpty() == false ) {
			result = false;
		}else {
			result = true;
		}
		
		return result;
	}

	public List<Byte> getSatFecInnerList()
	{
		return satFecInnerList;
	}

	public void setSatFecInnerList(List<Byte> satFecInnerList)
	{
		this.satFecInnerList = satFecInnerList;
	}



}
