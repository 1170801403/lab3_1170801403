package physicalObject;

public class socialE1 extends E1
{
	private final int age;
	private final char sex;

	public socialE1(String name, int realAge, char string)
	{
		super(name);
		this.age = realAge;
		this.sex = string;
	}

	public int getAge()
	{
		return age;
	}

	public char getSex()
	{
		return sex;
	}

	public boolean equals(socialE1 e)
	{
		if (e.getName().equals(this.getName()) && e.getAge() == this.age && e.getSex() == this.sex)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
