package physicalObject;



public class trackE1 extends E1 
{
	private final int number;//号码
	private final String nationaility;//国籍
	private final int age;//年龄
	private final double best;//本年度最好成绩
	public trackE1(String name,int number,String nationaility,int age,double best)
	{
		super(name);//父类构造函数
		this.number = number;
		this.nationaility = nationaility;
		this.age = age;
		this.best = best;
	}
	//以下四类都是不可变的
	public int getNumber()
	{
		return number;
	}
	public String getNationaility()
	{
		return nationaility;
	}
	public int getAge()//返回的只是一个常量
	{
		return age;
	}
	public double getBest()
	{
		return best;
	}
	public boolean equals(trackE1 temp)//比较两个运动员是否是同一个运动员
	{

		if(temp.getName().equals(this.getName())&&temp.getAge() == this.age&&temp.getNumber() == this.number&&temp.getNationaility().equals(this.nationaility))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
