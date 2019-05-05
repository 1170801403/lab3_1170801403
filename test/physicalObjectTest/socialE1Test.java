package physicalObjectTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import physicalObject.socialE1;

public class socialE1Test
{
	int age = 12;
    char sex = "M".charAt(0);
    String name = "a";
    socialE1 test = new socialE1(name, age, sex);
    socialE1 test1 = new socialE1(name, age, sex);
    
    //Testing strategy for getAge()
  	//�����ض��Ĳ����½�һ��socialE1ʵ������ʾ�ﾶ�˶�Ա����Ȼ�����getAge()������
  	//�жϷ��ص�������Ԥ�������Ƿ����
    @Test
    public void testGetAge()
    {
    	assertTrue(test.getAge() == age);
    }
    //Testing strategy for getSex()
  	//�����ض��Ĳ����½�һ��socialE1ʵ������ʾ�ﾶ�˶�Ա����Ȼ�����getSex()������
  	//�жϷ��ص��ַ���Ԥ���ַ��Ƿ����
    @Test
    public void testGetSex()
    {
    	assertTrue(test.getSex() == sex);
    }
    //Testing strategy for getAge()
  	//�����ض��Ĳ����½�����socialE1ʵ������ʾ�ﾶ�˶�Ա����Ȼ�����equals()������
  	//�ж�����ʵ���Ƿ���ͬ
    @Test
    public void getEquals()
    {
    	assertTrue(test.equals(test1));
    }
}
