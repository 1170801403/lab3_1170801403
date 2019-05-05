package abstractFactory;

import centralObject.L1;

import physicalObject.trackE1;

public class trackFactory 
{
	
	public L1 manufactureL(String name)
	{
		return new L1(name);
	}
	
	public trackE1 manufactureE(String name,int number,String nationaility,int age,double best)
	{
		return new trackE1(name, number, nationaility, age, best);
	}
}
