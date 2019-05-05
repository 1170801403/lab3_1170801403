package abstractFactory;

import centralObject.atomL1;
import physicalObject.atomE1;

public class atomFactory
{
	public atomL1 manufactureL(String name)
	{
		return new atomL1(name);
	}
	
	public atomE1 manufactureE(String name)
	{
		return new atomE1(name);
	}
}
