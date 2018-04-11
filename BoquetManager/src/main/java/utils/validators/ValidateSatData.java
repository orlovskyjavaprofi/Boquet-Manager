package utils.validators;

public class ValidateSatData
{

	public String verifyConsistencyOfBoquetTvChannel(String inputName)
	{
		String resultName= "unknown tv channel";
		
		if(inputName.isEmpty() == false) {
			resultName = inputName;
		}
		
		return resultName;
	}
	
	

}
