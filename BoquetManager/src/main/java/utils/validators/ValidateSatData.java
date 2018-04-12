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

	public boolean validateServiceType(Byte inputValue, String xmlType)
	{
		boolean result = false;
		
		switch(xmlType) {
			case "services":
				result = caseServicesForValidation(inputValue, result);
			break;
			case "boquets":
				result = caseBoquetsForValidation(inputValue, result);
			break;
		}

		return result;
	}

	public Byte changesIncorrectValueOfServiceType(Byte inputValue, String input)
	{
		Byte result = 00;
		result = settingDefaultValueForIfServiceTypeIsIncorrect(input, result);	
		boolean evaluatedResult = this.validateServiceType(inputValue, input);
		
		if (evaluatedResult == false) {
		   	return result;
		}else {
			return inputValue;
		}
	
	}

	private Byte settingDefaultValueForIfServiceTypeIsIncorrect(String input, Byte result)
	{
		switch(input) {
			case "services":
				result = 00;
			break;
			case "boquets":
				result = 0;
			break;
		}
		return result;
	}
	
	private boolean caseBoquetsForValidation(Byte inputValue, boolean result)
	{
		if (inputValue <= 1) {
			result = true;
		}
		return result;
	}

	private boolean caseServicesForValidation(Byte inputValue, boolean result)
	{
		if (inputValue <= 01) {
			result = true;
		}
		return result;
	}

	public boolean validateBoquetHiddenOrLockedAttribute(Byte inputHiddenAttribute)
	{
		boolean result = false;
		
		if ( (inputHiddenAttribute <= 1) && ( ! (inputHiddenAttribute < 0))) {
			result = true;
		}
		
		return result;
	}

	public Byte changeIncorrectBoquetHiddenOrLockedAttribute(Byte mockupValue)
	{
		Byte defaultValue = 0;
		boolean evalutationResult = this.validateBoquetHiddenOrLockedAttribute(mockupValue);
		
		if (evalutationResult == true){
			return mockupValue;
		}else {
			return defaultValue;
		}
		
	}


	
	

}
