package centralObject;

import static org.junit.Assert.assertTrue;

import java.util.*;

import physicalObject.E1;

public class L1
{
	private final String name;//���������ǲ��ɱ�����
	//Abstraction function:
	//��Ϊ������������ĸ��࣬���й�ͬ���ԣ������ơ�
	//Representation invariant:
	//һ�����������������String���ͣ����ɱ� 
	//Safety from rep exposure:
	//String�����ǲ��ɱ�ģ�ͬʱ���������������final���ͣ�ָ�򲻻�ı�
	public void checkRep()
	{
		assertTrue(name!=null);
	}
	public String getName()
	{
		checkRep();
		return name;
		
	}
	public L1(String name)//���캯��
	{
		this.name = name;
		checkRep();
	}
}
