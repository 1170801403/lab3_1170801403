package circularOrbitTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import circularOrbit.socialNetworkCircle;
import physicalObject.socialE1;

public class socialNetWorkTest
{
	//Testing strategy for addTrackObject()
    //�½�һ��socialNetworkCircle���󣬵���addTrack()�������ӹ����
	//����addTrackObject()������������,���۲�洢���������Ķ����С�Ƿ����Ҫ��
	@Test
	public void addTrackObjectTest()
	{
		socialNetworkCircle testSocial = new socialNetworkCircle();
		char a = "M".charAt(0);
		socialE1 ob1 = new socialE1("a", 1, a);
		socialE1 ob2 = new socialE1("b", 1, a);
		socialE1 ob3 = new socialE1("c", 1, a);
		testSocial.addTrack();
		testSocial.addTrack();
		testSocial.addTrackObject(ob1, 2);
		
		testSocial.addTrackObject(ob1, 0);
		testSocial.addTrackObject(ob2, 1);
		assertTrue(testSocial.getFriend().size()==2);
		
		assertTrue(testSocial.getTrackObject().size() == 2);
		//assertTrue(testSocial.getTrackObject().get(0) == ob1);
		assertTrue(testSocial.getTrackObject().get(0).contains(ob1));
		//assertTrue(testSocial.getTrackObject().get(1) == ob2);
		assertTrue(testSocial.getTrackObject().get(1).contains(ob2));
		
		assertTrue(testSocial.getObjectTrack().size() ==2);
		assertTrue(testSocial.getObjectTrack().get(ob1) == 0);
		assertTrue(testSocial.getObjectTrack().get(ob2) == 1);
		
		assertTrue(testSocial.getFriend().size() == 2);
		assertTrue(testSocial.getFriend().get("a") == ob1);
		assertTrue(testSocial.getFriend().get("b") == ob2);
	}
	
	//Testing strategy for addTrackObject()
    //�½�һ��socialNetworkCircle���󣬵���addTrack()�������ӹ����
	//����addTrackObject()������������,���۲�洢���������Ķ����С�Ƿ����Ҫ��
	@Test
	public void clearTest()
	{
		socialNetworkCircle testSocial = new socialNetworkCircle();
		char a = "M".charAt(0);
		socialE1 ob1 = new socialE1("a", 1, a);
		socialE1 ob2 = new socialE1("b", 1, a);
		socialE1 ob3 = new socialE1("c", 1, a);
		testSocial.addTrack();
		testSocial.addTrack();
		
		testSocial.addTrackObject(ob1, 0);
		testSocial.addTrackObject(ob2, 1);
		
		testSocial.clear();
		//�������friend����
		assertTrue(testSocial.getFriend().size() == 2);
		assertTrue(testSocial.getPhysical().size() == 0);
		assertTrue(testSocial.getTrackObject().size() == 0);
		assertTrue(testSocial.getObjectTrack().size() == 0);
		assertTrue(testSocial.getAngle().size() == 0);
		assertTrue(testSocial.getLErelationship().size() == 0);
		
	}
}
