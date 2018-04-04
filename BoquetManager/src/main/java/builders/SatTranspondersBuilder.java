package builders;

import java.util.LinkedList;
import java.util.List;
import org.jdom2.Element;
import models.SatTransponder;
import models.SatTvChannel;

public class SatTranspondersBuilder
{
	private Byte transponderInversion;
    private Byte transponderFecinner; 
    private Byte transponderPolarisation;
    
	public List<SatTransponder> buildTransponderSatList(Element oneSateliteJdomElement)
	{
		List<SatTransponder> listOfTranspondersForOneSatelite = new LinkedList<SatTransponder>();
		List<Element> transponderElemsForOneSat = oneSateliteJdomElement.getChildren("transponder");

		String transponderId;
	    String transponderOnid;
	    Integer transponderFrequency = 0;
	    Integer transponderSymbolRate = 0;
	    List<SatTvChannel> listOfSatTvChannelperTranspoder;
	    SatTvChannelsBuilder builderOfSatTvChanel= new SatTvChannelsBuilder();

		for(Element satTransponderElem : transponderElemsForOneSat){
			
		  setUpAndValidateInversionFecInnerPolarisation(satTransponderElem);
		  transponderId =   satTransponderElem.getAttributeValue("id");
		  transponderOnid = satTransponderElem.getAttributeValue("onid");
		  transponderFrequency = convertingValueOfFrequency(satTransponderElem);
		  transponderSymbolRate = convertingValueOfSymbolRate(satTransponderElem);	
		  listOfSatTvChannelperTranspoder = creatingSatTvChannelList(
				                                                       builderOfSatTvChanel, 
				                                                       satTransponderElem
				                                                     );
		
	 	  addingTranspoderToTransponderList(listOfTranspondersForOneSatelite, 
		  transponderId, transponderOnid,	transponderFrequency, 
		  transponderSymbolRate, listOfSatTvChannelperTranspoder);
		 }
		
		return listOfTranspondersForOneSatelite;
	}

	private void addingTranspoderToTransponderList(List<SatTransponder> listOfTranspondersForOneSatelite,
			String transponderId, String transponderOnid, Integer transponderFrequency, Integer transponderSymbolRate,
			List<SatTvChannel> listOfSatTvChannelperTranspoder)
	{
		listOfTranspondersForOneSatelite.add(
		buildSatTransponder(transponderId, transponderOnid, transponderFrequency, 
				transponderSymbolRate,	listOfSatTvChannelperTranspoder)
		);
	}

	private SatTransponder buildSatTransponder(String transponderId, String transponderOnid,
			Integer transponderFrequency, Integer transponderSymbolRate,
			List<SatTvChannel> listOfSatTvChannelperTranspoder)
	{
		return new SatTransponder(
			transponderId, 
			transponderOnid,
			transponderFrequency, 
		    	this.getTransponderInversion(),
		    	transponderSymbolRate, 
		 	this.getTransponderFecinner(),
		 	this.getTransponderPolarisation(), 
		 	listOfSatTvChannelperTranspoder
		  );
	}

	private List<SatTvChannel> creatingSatTvChannelList(SatTvChannelsBuilder builderOfSatTvChanel,
			Element satTransponderElem)
	{
		return builderOfSatTvChanel.buildListOfSatTvChannels(
				satTransponderElem.getChildren("channel")
			);
	}

	private int convertingValueOfSymbolRate(Element satTransponderElem)
	{
		return Integer.parseInt(satTransponderElem.getAttributeValue("symbol_rate"));
	}

	private int convertingValueOfFrequency(Element satTransponderElem)
	{
		return Integer.parseInt(satTransponderElem.getAttributeValue("frequency"));
	}

	private void setUpAndValidateInversionFecInnerPolarisation(Element satTransponderElem)
	{
		this.validateInversion( Byte.parseByte(
				       satTransponderElem.getAttributeValue("inversion") )
				 );
		 this.validateFecInner(
				 Byte.parseByte(
				       satTransponderElem.getAttributeValue("fec_inner") )
				 );
		this.validatePolarisation(
				 Byte.parseByte(
					       satTransponderElem.getAttributeValue("polarization") )	
				);
	}
        
	public boolean validateInversion(Byte input)
	{
		final Byte standardValueOfInversion = 2;
		
		if( input == 2 ) {
			this.setTransponderInversion(standardValueOfInversion);
			return true;
		}else {
			this.setTransponderInversion( standardValueOfInversion );
			return false;
		}
	}

	public boolean validateFecInner(Byte inputFecInner)
	{
		boolean result = false;
		final Byte defaultValue = 2;
		
		result = setUpValidValuesForFecInner(inputFecInner, defaultValue);
		return result;
	}

	private boolean setUpValidValuesForFecInner(Byte inputFecInner, final Byte defaultValue)
	{
		boolean result = false;
		switch (inputFecInner)
		{
			case 2:
				result = setValidFecInnerValue(inputFecInner);
				break;
			case 3:
				result = setValidFecInnerValue(inputFecInner);
				break;
			case 4:
				result = setValidFecInnerValue(inputFecInner);
				break;
			case 5:
				result = setValidFecInnerValue(inputFecInner);
				break;
			default:
				result = setValidFecInnerValue(defaultValue);
				result = false;
				break;
		}
		return result;
	}

	public boolean validatePolarisation(Byte inputPolarisation)
	{
		boolean result = false;
	    final Byte defaultValue = 0;
	    
		result = evaluateValidPolarizationValues(inputPolarisation, defaultValue);
		
		return result;
	}

	private boolean evaluateValidPolarizationValues(Byte inputPolarisation, final Byte defaultValue)
	{
		boolean result;
		switch (inputPolarisation)
		{
			case 1:
				result = setUpValidPolarizationValue(inputPolarisation);
				break;
			case 0:
				result = setUpValidPolarizationValue(inputPolarisation);
				break;
			default:
				result = setUpValidPolarizationValue(defaultValue);
				result = false;
				break;
		}
		return result;
	}

	public Byte getTransponderInversion()
	{
		return transponderInversion;
	}

	public void setTransponderInversion(Byte transponderInversion)
	{
		this.transponderInversion = transponderInversion;
	}
	
	private boolean setUpValidPolarizationValue(Byte inputPolarisation)
	{
		boolean result = true;
		this.setTransponderPolarisation(inputPolarisation);
		
		return result;
	}
	
	public Byte getTransponderPolarisation()
	{
		return transponderPolarisation;
	}

	public void setTransponderPolarisation(Byte transponderPolarisation)
	{
		this.transponderPolarisation = transponderPolarisation;
	}

	private boolean setValidFecInnerValue(Byte inputFecInner)
	{
		this.setTransponderFecinner(inputFecInner);
		return true;
	}

	public Byte getTransponderFecinner()
	{
		return transponderFecinner;
	}

	public void setTransponderFecinner(Byte transponderFecinner)
	{
		this.transponderFecinner = transponderFecinner;
	}

	public boolean verifySatJdomElement(Element sateliteJDomElement )
	{
		if (sateliteJDomElement.hasAttributes() == true) {
			return true;
		}else {
			return false;
		}
	}



}
