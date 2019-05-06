package circularOrbit;

import java.util.Iterator;

import physicalObject.E1;

public class physicalShelf implements aggregate
{
	private E1[] physical;
	private int last = 0;

	// 构造函数
	// 查找书籍
	public E1 getBookAt(int index)
	{
		return physical[index];
	}

	// 添加书籍
	public void appendBook(E1 book)
	{
		this.physical[last] = book;
		last++;
	}

	// 获得书架存书的数量
	public int getLength()
	{
		return physical.length;
	}

	// 获得书架迭代器对象
	@Override
	public physicalIterator iterator()
	{
		return new physicalShelfIterator(this);

	}
}
