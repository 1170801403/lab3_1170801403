package circularOrbitTest;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import centralObject.L1;
import circularOrbit.ConcreteCircularObject;
import physicalObject.E1;

//jUNITest�в���ʹ�÷���
public class concreteCircularObjectTest
{
	
	//Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//������۲�洢����Ķ����С�Ƿ����Ҫ��
    @Test
    public void testAddTrack()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	testObject.addTrack();
    	assertTrue(testObject.getTrackObject().size() == 1);
    	assertTrue(testObject.getPhysical().size() == 1);
    }
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����deleteTrack()����ɾ��������ֱ��ò�ͬ�Ĳ�������deleteTrack()
    //�Թ۲��Ƿ��ܴ����쳣��������۲�洢����Ķ����С�Ƿ����Ҫ��
    @Test
    public void testDeleteTrack()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.deleteTrack(2);
    	assertTrue(testObject.getTrackObject().size() == 2);
    	assertTrue(testObject.getPhysical().size() == 2);
    	testObject.deleteTrack(1);
    	assertTrue(testObject.getTrackObject().size() == 1);
    	assertTrue(testObject.getPhysical().size() == 2);//physical����û��ɾ����
    }
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject()�����������������壬�ֱ��ò�ͬ�Ĳ�������addTrackObject()
    //�Թ۲��Ƿ��ܴ����쳣��������۲�洢���������Ķ����С�Ƿ����Ҫ��
    @Test
    public void testAddTrackObject()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 2);
    	assertTrue(testObject.getObjectTrack().size() == 0);
    	assertTrue(testObject.getAngle().size() == 0);
    	assertTrue(testObject.getRelationship().size() == 0);
    	testObject.addTrackObject(ob1, 1);
    	assertTrue(testObject.getObjectTrack().size() == 1);
    	assertTrue(testObject.getAngle().size() == 1);
    	assertTrue(testObject.getTrackObject().get(1).size() == 1);
    	assertTrue(testObject.getTrackObject().get(0).size() == 0);
    	assertTrue(testObject.getRelationship().size() == 1);
    	testObject.addTrackObject(ob2,1);
    	assertTrue(testObject.getObjectTrack().size() == 2);
    	assertTrue(testObject.getAngle().size() == 2);
    	assertTrue(testObject.getTrackObject().get(1).size() == 2);
    	assertTrue(testObject.getTrackObject().get(0).size() == 0);
    	assertTrue(testObject.getRelationship().size() == 2);
    }
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject()�����������������壬���ŵ���deleteTrackObject()
    //���������ò�ͬ�Ĳ�������deleteTrackObject()�������Թ۲��Ƿ��ܴ����쳣�����
    //���۲�洢���������Ķ����С�Ƿ����Ҫ��
    @Test
    public void testDeleteTrackObject()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	E1 ob3 = new E1("c");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addTrackObject(ob3, 1);
    	//���Խ��
    	testObject.deleteTrackObject(ob3, 2);
    	assertTrue(testObject.getObjectTrack().size() == 3);
    	assertTrue(testObject.getAngle().size() == 3);
    	//���岻�ڹ����
    	testObject.deleteTrackObject(ob3, 0);
    	assertTrue(testObject.getObjectTrack().size() == 3);
    	assertTrue(testObject.getAngle().size() == 3);
    	testObject.deleteTrackObject(ob3, 1);
    	assertTrue(testObject.getObjectTrack().size() == 2);
    	assertTrue(testObject.getAngle().size() == 2);
    	assertTrue(testObject.getTrackObject().get(1).size() == 1);
    }
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addCentralObject������ϵͳ������
	//���ģ�Ȼ���жϴӹ��ϵͳ���ص����������Ƿ���Ԥ������
    @Test
    public void addCentralObject()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	L1 central = new L1("YES");
    	testObject.addCentralObject(central);
    	assertTrue(testObject.getCentral().equals(central));
    }
    
    
   //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject()�����������������壬���ŵ���transit()
    //���������ò�ͬ�Ĳ�������transit()�������Թ۲��Ƿ��ܴ����쳣�����
    //���۲�洢���������Ķ����С�Ƿ����Ҫ��
    @Test
    public void testTransit()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	E1 ob3 = new E1("c");
    	E1 ob4 = new E1("d");
    	E1 ob5 = new E1("e");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addTrackObject(ob3, 1);
    	testObject.addTrackObject(ob4, 2);
    	testObject.transit(ob1, 3);
    	assertTrue(testObject.getObjectTrack().size()==4);
    	assertTrue(testObject.getTrackObject().get(0).size() == 1);
    	testObject.transit(ob5, 1);
    	assertTrue(testObject.getObjectTrack().size()==4);
    	assertTrue(testObject.getTrackObject().get(1).size() == 2);
    	testObject.transit(ob1,2);
    	assertTrue(testObject.getObjectTrack().size()==4);
    	assertTrue(testObject.getTrackObject().get(0).size() == 0);
    	assertTrue(testObject.getTrackObject().get(2).size() == 2);
    }
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject()�����������������壬���ŵ���addEErelationship()
    //�������ڹ����������ӹ�ϵ�����ò�ͬ�Ĳ�������addEErelationship()�������Թ۲��Ƿ�
    //�ܴ����쳣��������۲�洢���������Ķ����С�Ƿ����Ҫ��
    @Test
    public void testAddEErelationship()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	E1 ob3 = new E1("c");
    	E1 ob4 = new E1("d");
    	E1 ob5 = new E1("e");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addTrackObject(ob3, 1);
    	testObject.addTrackObject(ob4, 2);
    	testObject.addEErelationship(ob1, ob5);
    	assertTrue(testObject.getRelationship().size() == 4);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 0);
    	testObject.addEErelationship(ob1, ob2);
    	assertTrue(testObject.getRelationship().size() == 4);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 1);
    	assertTrue(testObject.getRelationship().get(ob2).size() == 1);
    	testObject.addEErelationship(ob1, ob4);
    	assertTrue(testObject.getRelationship().size() == 4);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 2);
    	assertTrue(testObject.getRelationship().get(ob4).size() == 1);
    
    }
    
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject(),addEErelationship()�����������͹�ϵ
    //����ò�ͬ�Ĳ�������deleteEErelationship()����ɾ����������Ĺ�ϵ��
    //���۲��Ƿ��ܹ������쳣�����ͬʱ�۲�relationship������Ƿ����Ԥ�ڡ�
  
    @Test
    public void testDeleteEErelationship()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	E1 ob3 = new E1("c");
    	E1 ob4 = new E1("d");
    	E1 ob5 = new E1("e");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addTrackObject(ob3, 1);
    	testObject.addTrackObject(ob4, 2);
    	testObject.addEErelationship(ob1, ob2);
    	testObject.addEErelationship(ob1, ob4);
    	testObject.addEErelationship(ob2, ob3);
    	testObject.deleteEErelationship(ob5, ob1);
    	assertTrue(testObject.getRelationship().size() == 4);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 2);
    	assertTrue(testObject.getRelationship().get(ob2).size() == 2);
    	assertTrue(testObject.getRelationship().get(ob3).size() == 1);
    	assertTrue(testObject.getRelationship().get(ob4).size() == 1);
    	
    	
    	testObject.deleteEErelationship(ob1, ob2);
    	assertTrue(testObject.getRelationship().size() == 4);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 1);
    	assertTrue(testObject.getRelationship().get(ob2).size() == 1);
    	assertTrue(testObject.getRelationship().get(ob3).size() == 1);
    	assertTrue(testObject.getRelationship().get(ob4).size() == 1);
    	
    	
    	testObject.deleteEErelationship(ob2, ob3);
    	assertTrue(testObject.getRelationship().size() == 4);
    	assertTrue(testObject.getRelationship().get(ob1).size() == 1);
    	assertTrue(testObject.getRelationship().get(ob2).size() == 0);
    	assertTrue(testObject.getRelationship().get(ob3).size() == 0);
    	assertTrue(testObject.getRelationship().get(ob4).size() == 1);	
    }
    
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject(),addEErelationship()�����������͹�ϵ
    //����ò�ͬ�Ĳ�������addLErelationship()������ӹ�����������������Ĺ�ϵ��
    //���۲��Ƿ��ܹ������쳣�����ͬʱ�۲�LErelationship������Ƿ����Ԥ�ڡ�
  
    @Test
    public void testAddLErelationship()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	E1 ob3 = new E1("c");
    	E1 ob4 = new E1("d");
    	E1 ob5 = new E1("e");
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addTrackObject(ob3, 1);
    	testObject.addTrackObject(ob4, 2);
    	testObject.addLErelationship(ob5);
    	assertTrue(testObject.getLErelationship().size() == 0);
    	testObject.addLErelationship(ob4);
    	assertTrue(testObject.getLErelationship().size() == 1);
    	testObject.addLErelationship(ob3);
    	assertTrue(testObject.getLErelationship().size() == 2);
    }
    
    //Testing strategy for Edge.getSource()
    //�½�һ��ConcreteCircularObject���󣬵���addTrack()������ϵͳ������
	//�����Ȼ�����addTrackObject(),addEErelationship(),addLErelationship()
    //�����������͹�ϵ����ò�ͬ�Ĳ�������deleteLErelationship()����ɾ��������������������Ĺ�ϵ��
    //���۲��Ƿ��ܹ������쳣�����ͬʱ�۲�LErelationship������Ƿ����Ԥ�ڡ�
  
    @Test
    public void testDeleteLErelationship()
    {
    	ConcreteCircularObject<L1,E1> testObject = new ConcreteCircularObject<L1,E1>();
    	E1 ob1 = new E1("a");
    	E1 ob2 = new E1("b");
    	E1 ob3 = new E1("c");
    	E1 ob4 = new E1("d");
    	E1 ob5 = new E1("e");
//    	E ob2 = (E) new Object();
//    	E ob3 = (E) new Object();
//    	E ob4 = (E) new Object();
//    	E ob5 = (E) new Object();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrack();
    	testObject.addTrackObject(ob1, 0);
    	testObject.addTrackObject(ob2, 1);
    	testObject.addTrackObject(ob3, 1);
    	testObject.addTrackObject(ob4, 2);
    	testObject.addLErelationship(ob4);
    	testObject.addLErelationship(ob3);
    	
    	testObject.deleteLErelationship(ob5);
    	assertTrue(testObject.getLErelationship().size() == 2);
    	testObject.deleteLErelationship(ob3);
    	assertTrue(testObject.getLErelationship().size() == 1);
    	testObject.deleteLErelationship(ob4);
    	assertTrue(testObject.getLErelationship().size() == 0);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
