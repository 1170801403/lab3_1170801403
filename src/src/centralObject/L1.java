package centralObject;

import static org.junit.Assert.assertTrue;

import java.util.*;

import physicalObject.E1;

public class L1
{
	private final String name;
	private final int a1;
	private final int a2;
	//Abstraction function:
	//��Ϊ������������ĸ��࣬���й�ͬ���ԣ������ơ�,����ɲ��ֵ���Ŀa1,a2
	//Representation invariant:
	//һ�����������������String���ͣ����ɱ�,a1��a2�಻�ɱ�
	//Safety from rep exposure:
	//String�����ǲ��ɱ�ģ�ͬʱ���������������final���ͣ�ָ�򲻻�ı䣬��a1��a2��final���ͣ�ָ���޷��ı�
	public void checkRep()
	{
		assertTrue(name!=null);
		assertTrue(a1>=0);
		assertTrue(a2>=0);
	}
	public String getName()
	{
		checkRep();
		return name;
	}
	public L1(String name,int a1,int a2)//���캯��
	{
		
		this.name = name;
		this.a1 = a1;
		this.a2 = a2;
		checkRep();
	}
	public int getA1()
	{
		checkRep();
		return a1;
	}
	public int getA2()
	{
		checkRep();
		return a2;
	}
}
