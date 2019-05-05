package centralObjectTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import centralObject.socialL1;

public class socialL1Test
{
	String name = "a";
	int age = 12;
	char sex = "M".charAt(0);
	socialL1 test = new socialL1(name, age, sex);
	
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��socialL1ʵ������ʾ�罻���������˶���Ȼ�����getName()������
	//�жϷ��ص��ַ�����Ԥ���ַ����Ƿ����
	@Test
	public void testGetName()
	{
		assertTrue(test.getName().equals(name));
	}
	
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��socialL1ʵ������ʾ�罻���������˶���Ȼ�����getAge()������
	//�жϷ��ص�������Ԥ�������Ƿ����
	@Test
	public void testGetAge()
	{
		assertTrue(test.getAge() == age);
	}
	
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��socialL1ʵ������ʾ�罻���������˶���Ȼ�����getSex()������
	//�жϷ��ص��ַ���Ԥ���ַ��Ƿ����
	@Test
	public void testGetSex()
	{
		assertTrue(test.getSex() == sex);
	}
}
