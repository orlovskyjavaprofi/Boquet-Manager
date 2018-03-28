package models;

import java.util.List;

public class SatTransponder
{
	private String transponderId;
    private String transponderOnid;
    private Integer transponderFrequency;
    private Integer transponderSymbolRate;
    private Byte transponderInversion;
    private Byte transponderFecInner;
    private Byte transponderPolarisation;
    private List<SatTvChannel> listOfSatTvChannels; 
    
	public SatTransponder(String inputSatTransponderId, String inputOnid,
		        	Integer inputTransponderFrequency, Byte inputTransponderInversion,
		     	Integer inputTransponderSymbolRate, Byte inputTransponderFecInner,
		     	Byte inputTransponderPolarisation,  List<SatTvChannel> inpuListOfSatTvChannels
			)
	{
		this.transponderOnid = inputOnid;
		this.transponderId = inputSatTransponderId;
		this.transponderFrequency = inputTransponderFrequency;
		this.transponderInversion = inputTransponderInversion;
		this.transponderSymbolRate = inputTransponderSymbolRate;
		this.transponderFecInner = inputTransponderFecInner;
		this.transponderPolarisation = inputTransponderPolarisation;
		this.listOfSatTvChannels = inpuListOfSatTvChannels;
	}

	@Override
	public String toString()
	{
		return "SatTransponder "
	             + "\n============================================================================\n"
				+ "transponderId: " + transponderId 
				+ "  transponderOnid: " + transponderOnid
				+ " transponderFrequency: " + transponderFrequency +"\n" 
				+ "transponderSymbolRate: " + transponderSymbolRate
				+ " transponderInversion: " + transponderInversion +"\n"
				+ "transponderFecInner: " + transponderFecInner
				+ " transponderPolarisation: " + transponderPolarisation +"\n"
				  + "\n============================================================================\n"
				+ "This transponder consist following channel list"+"\n"
				+ listOfSatTvChannels.toString();
	}

	public String getTransponderId()
	{
		return transponderId;
	}

	public void setTransponderId(String transponderId)
	{
		this.transponderId = transponderId;
	}

	public String getTransponderOnid()
	{
		return transponderOnid;
	}

	public void setTransponderOnid(String transponderOnid)
	{
		this.transponderOnid = transponderOnid;
	}

	public Integer getTransponderFrequency()
	{
		return transponderFrequency;
	}

	public void setTransponderFrequency(Integer transponderFrequency)
	{
		this.transponderFrequency = transponderFrequency;
	}

	public Byte getTransponderInversion()
	{
		return transponderInversion;
	}

	public void setTransponderInversion(Byte transponderInversion)
	{
		this.transponderInversion = transponderInversion;
	}

	public Integer getTransponderSymbolRate()
	{
		return transponderSymbolRate;
	}

	public void setTransponderSymbolRate(Integer transponderSymbolRate)
	{
		this.transponderSymbolRate = transponderSymbolRate;
	}

	public Byte getTransponderFecInner()
	{
		return transponderFecInner;
	}

	public void setTransponderFecInner(Byte transponderFecInner)
	{
		this.transponderFecInner = transponderFecInner;
	}

	public Byte getTransponderPolarisation()
	{
		return transponderPolarisation;
	}

	public void setTransponderPolarisation(Byte transponderPolarisation)
	{
		this.transponderPolarisation = transponderPolarisation;
	}

	public List<SatTvChannel> getListOfSatTvChannels()
	{
		return listOfSatTvChannels;
	}

	public void setListOfSatTvChannels(List<SatTvChannel> listOfSatTvChannels)
	{
		this.listOfSatTvChannels = listOfSatTvChannels;
	}

	
	
}
