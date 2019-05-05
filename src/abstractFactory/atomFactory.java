package abstractFactory;

import centralObject.atomL1;
import physicalObject.atomE1;

public class atomFactory
{
	public atomL1 manufactureL(String name,int a1,int a2)
	{
		return new atomL1(name,a1,a2);
	}
	
	public atomE1 manufactureE(String name)
	{
		return new atomE1(name);
	}
}
