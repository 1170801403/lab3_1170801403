package circularOrbitTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import circularOrbit.tie;



public class tieTest
{
	
	String name1 = "a";//��Ҫ���г�ʼ��
	String name2 = "b";
	float ini = 1.02f; 
	tie testTie = new tie(name1, name2, ini);
	
	//Testing strategy for getName1()
	//�����ض��Ĳ����½�һ��tieʵ������ʾ"��ϵ"����Ȼ�����getName1()������
	//�жϷ��ص��ַ�����Ԥ���ַ����Ƿ����
	@Test
	public void testGetName1()
	{
		assertTrue(testTie.getName1().equals(name1));
	}
	
	//Testing strategy for getName2()
	//�����ض��Ĳ����½�һ��tieʵ������ʾ"��ϵ"����Ȼ�����getName2()������
	//�жϷ��ص��ַ�����Ԥ���ַ����Ƿ����
	@Test
	public void testGetName2()
	{
		assertTrue(testTie.getName2().equals(name2));
	}
	
	//Testing strategy for getIni()
	//�����ض��Ĳ����½�һ��tieʵ������ʾ"��ϵ"����Ȼ�����getIni()������
	//�жϷ��ص�������Ԥ�����ֵĲ��Ƿ��ںϷ���Χ��
	@Test
	public void testGetIni()
	{
		assertTrue((testTie.getIni()-ini)<1e-6);
	}
}
