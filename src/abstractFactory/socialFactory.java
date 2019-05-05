package abstractFactory;


import centralObject.socialL1;
import physicalObject.socialE1;


public class socialFactory
{
	public socialL1 manufactureL(String name, int age, char sex,int a1,int a2)
	{
		return new socialL1(name,age,sex,a1,a2);
	}
	
	public socialE1 manufactureE(String name, int realAge, char realSex)
	{
		return new socialE1(name,realAge,realSex);
	}
}
