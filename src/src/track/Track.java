package track;

import static org.junit.Assert.assertTrue;

import java.util.*;

import physicalObject.*;//������һ�����е�����
//immutable

public class Track
{
	private final int rep;

	// Abstraction function:
	// ��Ϊ���й���ĸ��࣬���й�ͬ���ԣ����뾶��
	// Representation invariant:
	// һ����������������String���ͣ����ɱ�
	// Safety from rep exposure:
	// String�����ǲ��ɱ�ģ�ͬʱ���������������final���ͣ�ָ�򲻻�ı�
	public void checkRep()
	{
		assertTrue(rep >= 0);
	}

	public Track(int rep)// ���캯��
	{
		checkRep();
		this.rep = rep;
	}

	public int getRep()
	{
		checkRep();
		return rep;
	}
}
