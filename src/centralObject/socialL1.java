package centralObject;

public class socialL1 extends L1
{
	private final int age;
	private final char sex;
	//中心物体可由多个物体构成
	public socialL1(String name, int age, char sex,int a1,int a2)
	{
		super(name,a1,a2);
		this.age = age;
		this.sex = sex;
	}
	public int getAge()
	{
		return age;
	}
	public char getSex()
	{
		return sex;
	}
}
