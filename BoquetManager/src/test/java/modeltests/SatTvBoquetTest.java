package modeltests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import models.SatBoquetTvChannel;
import models.SatTvBoquet;

public class SatTvBoquetTest
{
	 SatTvBoquet satTvBoquetObj;
     Byte mockuptValueForServiceType;
     String mockupValueForBoquetId;
     String mockupValueForBoquetName;
     Byte mockupValueforHiddenBoquet;
     Byte mockupValueforLocked;
     List<SatBoquetTvChannel> mockuplistOfSatTvBoquetChannels;
    
	@BeforeEach
	void setUp()
	{
		mockuptValueForServiceType = 0;
		mockupValueForBoquetId = "622f";
		mockupValueForBoquetName = "Ticket";
		mockupValueforHiddenBoquet = 0;
		mockupValueforLocked =1;
		mockuplistOfSatTvBoquetChannels = new LinkedList<SatBoquetTvChannel>();
		mockuplistOfSatTvBoquetChannels = createMockupSatTvList(mockuplistOfSatTvBoquetChannels);
		
		satTvBoquetObj = new SatTvBoquet(mockuptValueForServiceType, 
				mockupValueForBoquetId, mockupValueForBoquetName, 
				mockupValueforHiddenBoquet, mockupValueforLocked,mockuplistOfSatTvBoquetChannels);
	}

	@Test
	void testingIfSatTvBoquetExist()
	{
		assertNotNull(satTvBoquetObj);
	}
	
	@Test
	void testingIFSatTvBoquetServiceTypeNotNull() {
		Byte serviceType = satTvBoquetObj.getSatTvBoquetServiceType() ;
				
		assertNotNull(serviceType);
	}

	@Test
	void testingIfSatTvBoquetIdNotNull() {
		String boquetId = satTvBoquetObj.getSatTvBoquetId();
	
		assertNotNull(boquetId);
	}
	
	@Test 
	void testingIfSatTvBoquetNameNotNull() {
		String boquetName = satTvBoquetObj.getSatTvBoquetName();
		
		assertNotNull(boquetName);
	}
	
	@Test
	void testingIfSatTvBoquetHiddenNotNull() {
		Byte hiddenBoquet= satTvBoquetObj.getSatTvBoquetHidden();
		
		assertNotNull(hiddenBoquet);
	}

	@Test
	void testingIfSatTvBoquetLockedNotNull() {
		Byte lockedBoquet = satTvBoquetObj.getSatTvBoquetLocked();
		
		assertNotNull(lockedBoquet);
	}
	
	@Test
	void testtingIfSatTvBoquetListOfSatTvChanelsNotNull() {
		List<SatBoquetTvChannel> listOfSatTvChannels= satTvBoquetObj.getListOfSatTvChannels();
				
		assertNotNull(listOfSatTvChannels);
	}
	
	@Test
	@Disabled
	void outputSatBoquetTv() {
		System.out.println(satTvBoquetObj.toString());
	}
	
	private List<SatBoquetTvChannel> createMockupSatTvList(List<SatBoquetTvChannel> list){

		SatBoquetTvChannel channel= new SatBoquetTvChannel("6dce","Bayerisches FS Nord"
				,"044d","0001",192 );
		list.add(channel);
		channel= new SatBoquetTvChannel("6e97","WDR Siegen","04b1","0001",192 );
		list.add(channel);
		channel= new SatBoquetTvChannel("6e47","SWR Fernsehen RP","0431","0001",192 );
		list.add(channel);
		channel= new SatBoquetTvChannel("6e42","NDR FS NDS","0431","0001",192 );
		list.add(channel);
		channel= new SatBoquetTvChannel("6dd1","SWR Fernsehen BW","044d","0001",192 );
		list.add(channel);
		channel= new SatBoquetTvChannel("6dcf","WDR Köln","044d","0001",192 );
		list.add(channel);
		
        return list;
	}
}
