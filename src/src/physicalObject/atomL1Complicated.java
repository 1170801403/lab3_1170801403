package physicalObject;

public class atomL1Complicated extends E1
{
	private final int proton;//质子个数
	private final int neutron;//中子个数
	public atomL1Complicated(String name, int proton, int neutron)
	{
		super(name);
		this.proton = proton;
		this.neutron = neutron;
	}
	public int getProton()
	{
		return proton;
	}
	public int getNeutron()
	{
		return neutron;
	}
	
}
