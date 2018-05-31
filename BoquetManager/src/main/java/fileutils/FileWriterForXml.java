package fileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FileWriterForXml
{
	public boolean verifyIfFileExist(String pathToFile)
	{
		boolean fileDoesExistStatus = false;
		File file = new File(pathToFile);

		if (file.exists() == true)
		{
			fileDoesExistStatus = true;
            //	System.out.println(fileDoesExistStatus);
		}

		return fileDoesExistStatus;
	}

	public boolean writeFileToFs(Document inputSatXmlDoc, String pathToFileLocation)
			throws IOException
	{
		boolean result = false;
		Format styleOfXmlOutputFormat = styleOfXmlFormat();

		result = writeFileWithoutCheckingIfFileExist(inputSatXmlDoc, pathToFileLocation, styleOfXmlOutputFormat);

		return result;
	}

	private Format styleOfXmlFormat()
	{
		Format styleOfXmlOutputFormat = Format.getPrettyFormat();
		styleOfXmlOutputFormat.setEncoding("UTF-8");
		return styleOfXmlOutputFormat;
	}

	private boolean writeFileWithoutCheckingIfFileExist(Document inputSatXmlDoc, 
			String pathToFileLocation,
			Format styleOfXmlOutputFormat) throws IOException
	{
		boolean result = false;
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(styleOfXmlOutputFormat);
		xmlOutput.output(inputSatXmlDoc, new FileWriter(pathToFileLocation));
		if (xmlOutput.toString().isEmpty() == false) {
			result = true;
		}
		return result;
	}

	public boolean overwriteExistingFile(boolean userDecisionToOverwrite, 
			String filePath, Document inputDoc)
			throws IOException
	{
		boolean result = false;
		Format styleOfXmlOutputFormat = styleOfXmlFormat();

		if (userDecisionToOverwrite == true)
		{
			writeFileWithoutCheckingIfFileExist(inputDoc, filePath, styleOfXmlOutputFormat);
			result = true;
		}

		return result;
	}

}
