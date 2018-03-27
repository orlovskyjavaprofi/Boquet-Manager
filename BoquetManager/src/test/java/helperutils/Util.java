package helperutils;

public class Util
{

	public boolean validateResult(Integer expectedResult, Integer actualResult, boolean result)
	{
		if (expectedResult < actualResult)
		{
			result = false;
		}else
		{
			result = true;
		}
		
		return result;
	}
	
}
