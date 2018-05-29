package testutil;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class xmlConsolePrinter
{

	public void printXmlResult(Document inputXml) {  
	    
	    XMLOutputter xmlOutput = new XMLOutputter();
	     Format f = Format.getPrettyFormat();  
	     f.setEncoding("UTF-8");
	     xmlOutput.setFormat(f);

		 try
		{
			xmlOutput.output(inputXml, System.out);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
