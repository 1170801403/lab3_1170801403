package centralObject;

import static org.junit.Assert.assertTrue;

import java.util.*;

import physicalObject.E1;

public class L1
{
	private final String name;//中心物体是不可变类型
	//Abstraction function:
	//作为所有中心物体的父类，具有共同属性：“名称”
	//Representation invariant:
	//一个中心物体的名称是String类型，不可变 
	//Safety from rep exposure:
	//String本身是不可变的，同时中心物体的名称是final类型，指向不会改变
	public void checkRep()
	{
		assertTrue(name!=null);
	}
	public String getName()
	{
		checkRep();
		return name;
		
	}
	public L1(String name)//构造函数
	{
		this.name = name;
		checkRep();
	}
}
