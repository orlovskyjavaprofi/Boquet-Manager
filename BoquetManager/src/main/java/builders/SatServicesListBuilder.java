package builders;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jdom2.Element;

import models.SatServicesList;
import models.SatTransponder;

public class SatServicesListBuilder
{
	private Byte inputSatDiseqc;
		
	
	public boolean validateDiseqc(Byte inputDiseqc)
	{
		boolean result = false;
		final Byte defaultValue = 0;	
		
		result = setUpValidValuesForDiseqc(inputDiseqc, defaultValue);
		
		return result;	
	}	
	
	private boolean setUpValidValuesForDiseqc(Byte inputDiseqc, final Byte defaultValue)
	{
		boolean result = false;
		
		switch (inputDiseqc)
		{
			case 0:
				result = setValidDiseqcrValue(inputDiseqc);
				break;
			case 1:
				result = setValidDiseqcrValue(inputDiseqc);
				break;
			case 2:
				result = setValidDiseqcrValue(inputDiseqc);
				break;
			case 3:
				result = setValidDiseqcrValue(inputDiseqc);
				break;
			default:
				result = setValidDiseqcrValue(defaultValue);
				result = false;
				break;
		}
		return result;
	}
	
	public SortedSet<SatServicesList> buildSatServicesSet(List<Element> listOfSatellites)
	{

 	    SortedSet<SatServicesList> setOftheServicesList = new TreeSet<SatServicesList>();
 	    buildingSetOfSatServices(listOfSatellites, setOftheServicesList);
 	    
        return setOftheServicesList;
	}

	private void buildingSetOfSatServices(List<Element> listOfSatellites,
			SortedSet<SatServicesList> setOftheServicesList)
	{
		String satName;
		Integer satPosition = 0;
		Byte satDiseqc =0;
		
		List<SatTransponder> listOfTranspondersForOneSat;
		for (Element satelliteElement : listOfSatellites)
		{
 	      	satName = satelliteElement.getAttributeValue("name");
 	      	satPosition = Integer.parseInt(satelliteElement.getAttributeValue("position"));     	
 	      	satDiseqc = validateAndSetUpDiseqc(satelliteElement);
 	      	listOfTranspondersForOneSat = createNewBuilderForBuildingSatTransponderList(
 	      			satelliteElement);
			setOftheServicesList.add(createNewSatServiceList(satName, 
					satPosition, satDiseqc, listOfTranspondersForOneSat)
			);
		}
	}

	private List<SatTransponder> createNewBuilderForBuildingSatTransponderList(
			Element satelliteElement)
	{
		return new SatTranspondersBuilder().buildTransponderSatList(
				satelliteElement);
	}

	private Byte validateAndSetUpDiseqc(Element satelliteElement)
	{
		Byte satDiseqc;
		this.validateDiseqc(Byte.parseByte(satelliteElement.getAttributeValue("diseqc")) );
		satDiseqc = this.getInputSatDiseqc();
		return satDiseqc;
	}

	private SatServicesList createNewSatServiceList(String satName, Integer satPosition, Byte satDiseqc,
			List<SatTransponder> listOfTranspondersForOneSat)
	{
		return new SatServicesList(satName, 
				satPosition, satDiseqc, listOfTranspondersForOneSat);
	}
		
	private boolean setValidDiseqcrValue(Byte inputDiseqc)
	{
		this.setInputSatDiseqc(inputDiseqc);
		return true;
	}

	public Byte getInputSatDiseqc()
	{
		return inputSatDiseqc;
	}

	public void setInputSatDiseqc(Byte inputSatDiseqc)
	{
		this.inputSatDiseqc = inputSatDiseqc;
	}

}
