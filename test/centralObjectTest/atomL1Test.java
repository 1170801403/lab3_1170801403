package centralObjectTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import centralObject.atomL1;

public class atomL1Test
{
	String name = "a";
	atomL1 test = new atomL1(name);
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��atomL1ʵ������ʾԭ�Ӻ˶���Ȼ�����getName()������
	//�жϷ��ص��ַ�����Ԥ���ַ����Ƿ����
	@Test
	public void testGetName()
	{
		assertTrue(test.getName().equals(name));
	}
}
