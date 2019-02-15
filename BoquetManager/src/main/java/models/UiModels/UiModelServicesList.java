package models.UiModels;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UiModelServicesList
{
	private SimpleStringProperty satName;
    private SimpleIntegerProperty satPosition;
    private SimpleStringProperty satDiseqc;
    private SimpleStringProperty transponderId;
    private SimpleStringProperty channelName;
    
	public UiModelServicesList(String inputSatName, Integer inputSatPos, String inputForDiseqc, 
			String transponderId, String channelName )
	{
		this.satName = new SimpleStringProperty(inputSatName);
		this.satPosition = new SimpleIntegerProperty(inputSatPos);
		this.satDiseqc = new SimpleStringProperty(inputForDiseqc);
		this.transponderId = new SimpleStringProperty(transponderId);
		this.channelName = new SimpleStringProperty(channelName);
	}

	public UiModelServicesList(String inputSatName)
	{
		this.satName = new SimpleStringProperty(inputSatName);
	}

	public String getSatName()
	{
		return satName.get();
	}

	public void setSatName(String inputText)
	{	
		this.satName.set(inputText);
	}

	public Integer getSatPosition()
	{
		return satPosition.get();
	}

	public void setSatPosition(Integer satPosition)
	{
		this.satPosition.set(satPosition);
	}

	public String getSatDiseqc()
	{
		return satDiseqc.get();
	}

	public void setSatDiseqc(String satDiseqc)
	{
		this.satDiseqc.set(satDiseqc);
	}

	public String getTransponderId()
	{
		if (transponderId != null)
		{
			return transponderId.get();
		} else
		{
			return "";
		}
	}

	public void setTransponderId(String transponderId)
	{
		this.transponderId.set(transponderId);
	}

	public String getChannelName()
	{
		if (channelName != null) {
		  return channelName.get();
		}else {
			return "";
		}
	}

	public void setChannelName(String channelName)
	{
		this.channelName.set(channelName);
	}

	
}
