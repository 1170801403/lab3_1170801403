package physicalObjectTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import physicalObject.atomE1;

public class atomE1Test
{
	String name = "a";
	atomE1 test = new atomE1(name);
	atomE1 test2 = new atomE1(name);
	
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��atomE1ʵ������ʾ������Ӷ���Ȼ�����getName()������
	//�жϷ��ص��ַ�����Ԥ���ַ����Ƿ����
	@Test
	public void testGetName()
	{
		assertTrue(test.getName().equals(name));
	}
	//Testing strategy for getName()
	//�����ض��Ĳ����½�����atomE1ʵ������ʾ������Ӷ���Ȼ�����equals()������
	//�ж�����ʵ���Ƿ����
	@Test
	public void testEquals()
	{
		assertTrue(test.equals(test2));
	}
}
