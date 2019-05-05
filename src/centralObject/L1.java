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
	//作为所有中心物体的父类，具有共同属性：“名称”,各组成部分的数目a1,a2
	//Representation invariant:
	//一个中心物体的名称是String类型，不可变,a1和a2亦不可变
	//Safety from rep exposure:
	//String本身是不可变的，同时中心物体的名称是final类型，指向不会改变，而a1和a2是final类型，指向无法改变
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
	public L1(String name,int a1,int a2)//构造函数
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
