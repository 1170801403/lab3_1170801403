package physicalObject;



public class trackE1 extends E1 
{
	private final int number;//����
	private final String nationaility;//����
	private final int age;//����
	private final double best;//�������óɼ�
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
	public boolean equals(trackE1 temp)//�Ƚ������˶�Ա�Ƿ���ͬһ���˶�Ա
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
