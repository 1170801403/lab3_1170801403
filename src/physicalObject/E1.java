package physicalObject;

import static org.junit.Assert.assertTrue;

import java.util.*;

public class E1//所有的方法都已经实现，依然可以用abstract
{
	private final String name;//物体名称
	//Abstraction function:
	//作为所有轨道物体的父类，具有共同属性：“名称”
	//Representation invariant:
	//一个轨道物体的名称是String类型，不可变 
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
