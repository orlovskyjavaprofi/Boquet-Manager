package utils.validators;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class ValidateXmlFile
{

	public String validateInputXmlFile(String pathToXmlFile)
	{

		final File file = new File(pathToXmlFile);
		String mimeFileType = "Undetermined";
		mimeFileType = validatingActualXmlFile(pathToXmlFile, file, mimeFileType);

		return mimeFileType;
	}

	private String validatingActualXmlFile(String pathToXmlFile, final File file, String mimeFileType)
	{
		try
		{
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			mimeFileType = URLConnection.guessContentTypeFromStream(is);
			is.close();
			mimeFileType = checkIfGivenFileNotXml(mimeFileType);	
		} catch (IOException ioException)
		{
			System.out.println(
					"ERROR: Unable to determine file type for " + pathToXmlFile + " due to exception " + ioException);
		}
		return mimeFileType;
	}

	private String checkIfGivenFileNotXml(String mimeFileType)
	{
		if (mimeFileType == null ) {
			mimeFileType = "Undetermined";
		}
		return mimeFileType;
	}

}
