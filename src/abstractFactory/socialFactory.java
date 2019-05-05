package abstractFactory;


import centralObject.socialL1;
import physicalObject.socialE1;


public class socialFactory
{
	public socialL1 manufactureL(String name, int age, char sex)
	{
		return new socialL1(name,age,sex);
	}
	
	public socialE1 manufactureE(String name, int realAge, char realSex)
	{
		return new socialE1(name,realAge,realSex);
	}
}
