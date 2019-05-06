package circularOrbit;

public class tie
{
	private final String name1;
	private final String name2;
	private final float ini;

	public tie(String name1, String name2, float ini)
	{
		this.name1 = name1;
		this.name2 = name2;
		this.ini = ini;
	}

	public String getName1()
	{
		return name1;
	}

	public String getName2()
	{
		return name2;
	}

	public float getIni()
	{
		return ini;
	}
}
