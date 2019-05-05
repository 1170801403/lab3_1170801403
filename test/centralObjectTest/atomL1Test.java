package centralObjectTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import centralObject.atomL1;

public class atomL1Test
{
	String name = "a";
	atomL1 test = new atomL1(name);
	//Testing strategy for getName()
	//采用特定的参数新建一个atomL1实例，表示原子核对象，然后调用getName()函数，
	//判断返回的字符串与预期字符串是否相等
	@Test
	public void testGetName()
	{
		assertTrue(test.getName().equals(name));
	}
}
