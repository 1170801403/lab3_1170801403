package circularOrbitTest;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import centralObject.L1;
import centralObject.atomL1;
import circularOrbit.ConcreteCircularObject;
import circularOrbit.atomStructure;
import physicalObject.E1;
import physicalObject.atomE1;

public class atomStructureTest
{
	//Testing strategy for clear()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack(),
	//addTrackObject(),addEErelationship(),addLErelationship()
	//�������ӹ���������Լ������Ĺ�ϵ��Ȼ�����clear()����
	//������塢�����Ĺ�ϵ�����۲�洢���������Ķ����С�Ƿ����Ҫ��
	@Test
	public void clearTest()
	{
		atomStructure testObject = new atomStructure();
    	atomE1 ob1 = new atomE1("a");
    	atomE1 ob2 = new atomE1("b");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addEErelationship(ob1, ob2);
    	testObject.addLErelationship(ob2);
    	testObject.addLErelationship(ob2);
    	testObject.clear();
    	assertTrue(testObject.getPhysical().size() == 0);
    	assertTrue(testObject.getAngle().size() == 0);
    	assertTrue(testObject.getObjectTrack().size() == 0);
    	assertTrue(testObject.getTrackObject().size() == 0);
    	assertTrue(testObject.getLErelationship().size() == 0);
    	//addTrackObject��ʱ��ͼ�����relationship
    	assertTrue(testObject.getRelationship().size() == 2);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 0);
    	assertTrue(testObject.getRelationship().get(ob2).size() == 0);
	}
	
	
	//Testing strategy for creatingTrack()
    //�½�һ��ConcreteCircularObject�������ض��Ĳ�������creatingTrack()
	//�������������ϵͳ�����۲�洢���������Ķ����С�Ƿ����Ҫ��
	@Test
	public void creatingTrackTest()
	{
		atomStructure testObject = new atomStructure();
		int trackNumber2 = 2;
		Map<Integer, Integer> trackObjectNumber = new HashMap<Integer, Integer>();
		trackObjectNumber.put(0, 1);
		trackObjectNumber.put(1, 1);
		testObject.creatingTrack(trackNumber2, trackObjectNumber);
		assertTrue(testObject.getPhysical().size() == 2);
		
		assertTrue(testObject.getTrackObject().size() == 2);
		assertTrue(testObject.getTrackObject().get(0).size() == 1);
		assertTrue(testObject.getTrackObject().get(1).size()  == 1);
		
		//addTrackObject��ʱ��ͼ�����relationship
		assertTrue(testObject.getRelationship().size() == 2);
		assertTrue(testObject.getObjectTrack().size() == 2);
		assertTrue(testObject.getAngle().size() == 2);
	}
	
	
	//Testing strategy for electronicTransition()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack(),
	//addTrackObject(),���������ϵͳ��Ȼ�����electronicTransition��
	//��ɵ���ԾǨ�����۲�洢���������Ķ����С�Ƿ����Ҫ��
	@Test
	public void electronicTransitionTest()
	{
		atomStructure testObject = new atomStructure();
		int trackNumber2 = 2;
		Map<Integer, Integer> trackObjectNumber = new HashMap<Integer, Integer>();
		trackObjectNumber.put(0, 1);
		trackObjectNumber.put(1, 1);
		testObject.creatingTrack(trackNumber2, trackObjectNumber);
    	testObject.electronicTransition(0, 2);
  
    	testObject.electronicTransition(0, 1);
    	assertTrue(testObject.getPhysical().size() == 2);
    	assertTrue(testObject.getTrackObject().size() == 2);
    	assertTrue(testObject.getTrackObject().get(0).size() == 0);
    	assertTrue(testObject.getTrackObject().get(1).size() ==2);
    	
    	assertTrue(testObject.getObjectTrack().size() == 2);

    	
    	assertTrue(testObject.getTrackObjectNumber().size() == 2);
    	assertTrue(testObject.getTrackObjectNumber().get(0) == 0);
    	assertTrue(testObject.getTrackObjectNumber().get(1) == 2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
