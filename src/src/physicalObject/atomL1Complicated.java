package physicalObject;

public class atomL1Complicated extends E1
{
	private final int proton;//���Ӹ���
	private final int neutron;//���Ӹ���
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
