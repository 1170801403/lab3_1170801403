package centralObjectTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import centralObject.atomL1;

public class atomL1Test
{
	String name = "a";
	int a1 = 3;
	int a2 = 5;
	atomL1 test = new atomL1(name,a1,a2);
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��atomL1ʵ������ʾԭ�Ӻ˶���Ȼ�����getName()������
	//�жϷ��ص��ַ�����Ԥ���ַ����Ƿ����
	@Test
	public void testGetName()
	{
		assertTrue(test.getName().equals(name));
	}
}
