package physicalObject;

import static org.junit.Assert.assertTrue;

import java.util.*;

public class E1//���еķ������Ѿ�ʵ�֣���Ȼ������abstract
{
	private final String name;//��������
	//Abstraction function:
	//��Ϊ���й������ĸ��࣬���й�ͬ���ԣ������ơ�
	//Representation invariant:
	//һ����������������String���ͣ����ɱ� 
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

	public E1(String name)
	{
		this.name = name;
		checkRep();
	}

	public boolean equals(E1 e)
	{
		if(e.getName().equals(this.name))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
