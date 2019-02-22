package models.UiModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UiModelChannel
{
	private SimpleStringProperty ChannelServiceID;
    private SimpleStringProperty TvChannelName;
    private SimpleStringProperty TransponderId;
    private SimpleStringProperty TransponderOnid;
    private SimpleIntegerProperty ChannelSatPosition;
    
	public UiModelChannel(String channelServiceID, String tvChannelName,
			String transponderId, String transponderOnid,
			Integer channelSatPosition)
	{
		initAllFields();
		ChannelServiceID.set(channelServiceID);
		TvChannelName.set(tvChannelName);
		TransponderId.set(transponderId);
		TransponderOnid.set(transponderOnid);
		ChannelSatPosition.set(channelSatPosition);
	}

	public UiModelChannel() {
		initAllFields();
	}

	private void initAllFields()
	{
		ChannelServiceID = new SimpleStringProperty();
		TvChannelName = new SimpleStringProperty();
		TransponderId = new SimpleStringProperty();
		TransponderOnid = new SimpleStringProperty();
		ChannelSatPosition = new SimpleIntegerProperty();
	}

	public String getChannelServiceID()
	{
		return ChannelServiceID.get();
	}

	public void setChannelServiceID(String inputChannelServiceID)
	{
		ChannelServiceID.set(inputChannelServiceID);
	}

	public String getTvChannelName()
	{
		return TvChannelName.get();
	}

	public void setTvChannelName(String inputTvChannelName)
	{
		TvChannelName.set(inputTvChannelName);
		
	}

	public String getTransponderId()
	{
		return TransponderId.get();
	}

	public void setTransponderId(String inputTransponderId)
	{
		TransponderId.set(inputTransponderId);
	}

	public String getTransponderOnid()
	{
		return TransponderOnid.get();
	}

	public void setTransponderOnid(String inputTransponderOnid)
	{
		TransponderOnid.set(inputTransponderOnid);
	}

	public Integer getChannelSatPosition()
	{
		return ChannelSatPosition.get();
	}

	public void setChannelSatPosition(Integer inputChannelSatPosition)
	{
		ChannelSatPosition.set(inputChannelSatPosition);
	}

}
