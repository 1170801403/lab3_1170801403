package centralObject;

public class socialL1 extends L1
{
	private final int age;
	private final char sex;
	public socialL1(String name, int age, char sex)
	{
		super(name);
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
