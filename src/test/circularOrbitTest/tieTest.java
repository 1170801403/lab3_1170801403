package circularOrbitTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import circularOrbit.tie;



public class tieTest
{
	
	String name1 = "a";//需要进行初始化
	String name2 = "b";
	float ini = 1.02f; 
	tie testTie = new tie(name1, name2, ini);
	
	//Testing strategy for getName1()
	//采用特定的参数新建一个tie实例，表示"关系"对象，然后调用getName1()函数，
	//判断返回的字符串与预期字符串是否相等
	@Test
	public void testGetName1()
	{
		assertTrue(testTie.getName1().equals(name1));
	}
	
	//Testing strategy for getName2()
	//采用特定的参数新建一个tie实例，表示"关系"对象，然后调用getName2()函数，
	//判断返回的字符串与预期字符串是否相等
	@Test
	public void testGetName2()
	{
		assertTrue(testTie.getName2().equals(name2));
	}
	
	//Testing strategy for getIni()
	//采用特定的参数新建一个tie实例，表示"关系"对象，然后调用getIni()函数，
	//判断返回的数字与预期数字的差是否在合法范围内
	@Test
	public void testGetIni()
	{
		assertTrue((testTie.getIni()-ini)<1e-6);
	}
}
