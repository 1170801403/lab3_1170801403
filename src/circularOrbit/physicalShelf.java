package circularOrbit;

import java.util.Iterator;

import physicalObject.E1;

public class physicalShelf implements aggregate
{
	private E1[] physical;
	private int last = 0;

	// ���캯��
	// ��������
	public E1 getphysicalAt(int index)
	{
		return physical[index];
	}

	// �������
	public void appendphysical(E1 book)
	{
		this.physical[last] = book;
		last++;
	}

	// ������������������
	public int getLength()
	{
		return physical.length;
	}

	// ����������������
	@Override
	public physicalIterator iterator()
	{
		return new physicalShelfIterator(this);

	}
}
