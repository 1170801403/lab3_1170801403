package physicalObject;



public class trackE1 extends E1 
{
	private final int number;//����
	private final String nationaility;//����
	private final int age;//����
	private final double best;//�������óɼ�
	//private int group;//�������������Ը�
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
		super(name);//���๹�캯��
		this.number = number;
		this.nationaility = nationaility;
		this.age = age;
		this.best = best;
	}
	//�������඼�ǲ��ɱ��
	public int getNumber()
	{
		return number;
	}
	public String getNationaility()
	{
		return nationaility;
	}
	public int getAge()//���ص�ֻ��һ������
	{
		return age;
	}
	public double getBest()
	{
		return best;
	}
//	@Override
//	public int compareTo(Object o)//ʵ��comparable�ӿڣ�ʹ��treeSet����
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
	public boolean equals(trackE1 temp)//���˶�Ա�����в�������ͬʱ��˵����������ͬ���˶�Ա
	{
		//double�ĳɼ��������ط��Ƚ�
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
