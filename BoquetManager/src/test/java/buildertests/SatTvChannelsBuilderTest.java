package buildertests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import builders.SatTvChannelsBuilder;
import helperutils.ReadXmlAndCrerateJdomWithChannelInfo;
import models.SatTvChannel;

class SatTvChannelsBuilderTest
{
     SatTvChannelsBuilder satTvChannelsBuilderObj;
     ReadXmlAndCrerateJdomWithChannelInfo helperObj;
     List<Element> satTvChannelList;
     
     @BeforeEach
     void setUp()
 	{
    	   satTvChannelsBuilderObj = new SatTvChannelsBuilder();
    	   helperObj = new ReadXmlAndCrerateJdomWithChannelInfo();
 	}
	
	@Test
	void testingIfSatTvChannelsBuilderObjectExist()
	{
		assertNotNull(satTvChannelsBuilderObj);
	}

	@Test
	void checkIfSatTvChannelListCanBeCreated() {
		List<SatTvChannel> listOfSatTvChannels;
		
		try
		{
			helperObj.readAndSetUpJDomDocument();
			satTvChannelList =	helperObj.readJdomDocumentAndCreate3rdLevelElementList();
			listOfSatTvChannels =  satTvChannelsBuilderObj.buildListOfSatTvChannels(
					satTvChannelList);
            //	 System.out.println(listOfSatTvChannels.toString());
			
			assertNotNull(listOfSatTvChannels);
			
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Disabled
	@Test
	void testingHelperObj() {
		List<Element> satTvChannelList = null;
		
		try
		{
			helperObj.readAndSetUpJDomDocument();
			//System.out.println("Reading XML: "+ helperObj.isReadXmlResult() );
			satTvChannelList =	helperObj.readJdomDocumentAndCreate3rdLevelElementList();
			checkAndOuputTvChannelList(satTvChannelList);
			
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkAndOuputTvChannelList(List<Element> satTvChannelList)
	{
		if (satTvChannelList.isEmpty() == false) {
			for (Element threeLevelDeepIntoSat : satTvChannelList)
			{
			     System.out.print(threeLevelDeepIntoSat.getAttributeValue("service_id") + " " );	
			     System.out.print(threeLevelDeepIntoSat.getAttributeValue("name") + " " );
			     System.out.print(threeLevelDeepIntoSat.getAttributeValue("service_type") +"\n" );
			}
			
		}
	}
	
}
