package models.UiModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UiModelServicesList
{
	private SimpleStringProperty satName;
    private SimpleIntegerProperty satPosition;
    private SimpleStringProperty satDiseqc;
    
	public UiModelServicesList(String inputSatName, Integer inputSatPos, String inputForDiseqc )
	{
		this.satName = new SimpleStringProperty(inputSatName);
		this.satPosition = new SimpleIntegerProperty(inputSatPos);
		this.satDiseqc = new SimpleStringProperty(inputForDiseqc);
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
	
	
	
}
