package physicalObject;



public class trackE1 extends E1 
{
	private final int number;//号码
	private final String nationaility;//国籍
	private final int age;//年龄
	private final double best;//本年度最好成绩
	//private int group;//所在组别，这个可以改
//	public int getGroup()
//	{
//		return group;
//	}
//	public void setGroup(int group)
//	{
//		this.group = group;
//	}
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
//	@Override
//	public int compareTo(Object o)//实现comparable接口，使用treeSet排序
//	{
//		if(this.best - ((trackE1)o).getBest()>0)
//		{
//			return 1;
//		}
//		else 
//		{
//			return 0;
//		}
//	}
	public boolean equals(trackE1 temp)//当运动员的所有参数都相同时，说明是两个相同的运动员
	{
		//double的成绩在其他地方比较
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
