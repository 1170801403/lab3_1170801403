package circularOrbit;

import track.*;
import centralObject.L1;

import static org.junit.Assert.assertTrue;

import java.util.*;

import track.*;
import physicalObject.*;
import centralObject.*;

//�ӿ��еķ�����object�ģ�ConcreteCircularObject�еķ��������޶��ģ�ConcreteCircularObject�����еķ���Ҳ�����޶���
//����ӿں�ConcreteCircularObject�еķ��Ͷ���<L extends L1,E extends E1>���ͻ����
public class ConcreteCircularObject<L ,E > implements circularOrbit<L, E>// �ӿں������Ӽ����ţ����������Ҳ��ܼ�extends�����ӿڱ���Ķ�������Լ�extends
{
	L central;// Ĭ��״̬��ͬһ�����ڿɼ���û��get���������ڿͻ��˴��������������Ӱ�ȫ
	final List<Track> physical = new ArrayList<Track>();// �б��ű�ʾ�����ţ���������а�������E,δ��Track����ռλ��
	final Map<Integer, Set<E>> trackObject = new HashMap<Integer, Set<E>>();// ���ӳ��������б�
	final Map<E, Integer> objectTrack = new HashMap<E, Integer>();// ����ӳ�������б�
	final Map<E, Set<E>> relationship = new HashMap<E, Set<E>>();// �洢�������֮��Ĺ�ϵ
	final Set<E> LErelationship = new HashSet<E>();// �洢���ĵ�����͹������֮��Ĺ�ϵ�����罻��������ָ��һ����
	final Map<E, Double> angle = new HashMap<E, Double>();// �洢ÿ������ĽǶ�
	// final Map<E, Integer> objectGroup = new HashMap<E, Integer>();// ���嵽����ӳ��
	final physicalShelf shelf = new physicalShelf();
	final trackFactory realTrackFactory = new trackFactory();
	final List<tie> socialTie = new ArrayList<tie>();
	//Abstraction function:
	//����ʵ��һ��circularOrbit�ӿڣ����ļ��ж��벢���������
	//���ò�ͬ�����ݽṹ�洢������塢�������塢�����������������֮��Ĺ�ϵ������������������֮��Ĺ�ϵ��
	//��ʵ������������塢���������������ϵ���������ԾǨ�ȹ���
	//Representation invariant:
	//������壬��������ĵ����嶼�ǲ��ɱ�ģ�ͨ����������ݽṹ��������֮�����ϵ 
	//Safety from rep exposure:
	//�ڷ�����Ҫ���ݽṹʱ�����˷����Կ�¡
	public void checkRep()
	{
		assertTrue(physical.size()!=0);
		assertTrue(relationship.size()!=0);

	}
	
	public List<Track> getPhysical()
	{
		List<Track> tempphysical = new ArrayList<Track>();
		for(int i=0; i<physical.size(); i++)
		{
			tempphysical.add(physical.get(i));
		}
		return tempphysical;
	}
	
	
	@Override
	public List<tie> getSocialTie()
	{
		List<tie> tempSocialTie = new ArrayList<tie>();
		for(int i=0; i<socialTie.size(); i++)
		{
			tempSocialTie.add(socialTie.get(i));
		}
		return tempSocialTie;
	}
	// ΪgetDifference��������
//	@Override
//	public Map<E, Integer> getObjectGroup()
//	{
//		Map<E, Integer> temp = new HashMap<E, Integer>();
//		Iterator<E> iterator = objectGroup.keySet().iterator();
//		while(iterator.hasNext())
//		{
//			E etemp = iterator.next();
//			int itemp = objectGroup.get(etemp);//�Զ����
//			temp.put(etemp, itemp);
//		}
//		return temp;
//		
//	}
	public L getCentral()//L�ǲ��ɱ��
	{
		return central;
	}
	public Set<E> getLErelationship()// ���ع�������е�����
	{
		Iterator<E> iterator = LErelationship.iterator();
		Set<E> etemp = new HashSet<E>();
		E temp;
		while (iterator.hasNext())
		{
			temp = iterator.next();
			etemp.add(temp);
		}
		return etemp;
	}

	@Override
	public Map<Integer, Set<E>> getTrackObject()
	{
		Map<Integer, Set<E>> temp = new HashMap<Integer, Set<E>>();
		Iterator<Integer> iterator = trackObject.keySet().iterator();
		while (iterator.hasNext())
		{
			Integer etemp = iterator.next();
			Set<E> stemp = new HashSet<E>();
			temp.put(etemp, stemp);
			Iterator<E> iterator2 = trackObject.get(etemp).iterator();
			while (iterator2.hasNext())
			{
				temp.get(etemp).add(iterator2.next());
			}
		}

		return temp;
	}

	@Override
	public Map<E, Set<E>> getRelationship()
	{
		Map<E, Set<E>> temp = new HashMap<E, Set<E>>();
		Iterator<E> iterator = relationship.keySet().iterator();
		while (iterator.hasNext())
		{
			E etemp = iterator.next();// ������壬�������壬������ǲ��ɱ�ĺô�
			Set<E> stemp = new HashSet<E>();
			temp.put(etemp, stemp);
			Iterator<E> iterator2 = relationship.get(etemp).iterator();
			while (iterator2.hasNext())
			{
				temp.get(etemp).add(iterator2.next());
			}

		}
		return temp;
	}

	@Override
	public Map<E, Integer> getObjectTrack()
	{
		Map<E, Integer> temp = new HashMap<E, Integer>();
		Iterator<E> iterator = objectTrack.keySet().iterator();
		while (iterator.hasNext())
		{
			E tempe = iterator.next();
			int m = objectTrack.get(tempe);
			temp.put(tempe, m);
		}
		return temp;
	}

	@Override
	public Map<E, Double> getAngle()
	{
		Map<E, Double> temp = new HashMap<E, Double>();
		Iterator<E> iterator = angle.keySet().iterator();
		while (iterator.hasNext())
		{
			E tempe = iterator.next();// �ͻ��˴������ɾ��Ҳֻ��ɾָ�룬Ӱ�첻��ԭ����angle
			double m = angle.get(tempe);
			temp.put(tempe, m);// �Զ���װ
		}
		return temp;
	}

	@Override
	public void addTrack()// ����һ�����,��Ҫ����ȷ������뾶���������
	{
		System.out.println(trackObject.size());
		int rep = physical.size() + 1;// ����뾶 = ������+1
		Track temp = realTrackFactory.manufacture(rep);
		physical.add(temp);
		trackObject.put(physical.size() - 1, new HashSet<E>());
		System.out.println(trackObject.size());
		System.out.println("Succeed!");
	}

	@Override
	public void deleteTrack(int number)// ȥ��һ�����,���������б��еı��
	{
		if (number >= physical.size())
		{
			System.out.println("The track is out of bound!");
			return;
		}
		// physical.remove(number);//physical�ǿ��ƹ����ŵģ�ֻ�����ӹ�������ܼ��ٹ��
		System.out.println(1);
		System.out.println(trackObject.size());
		Iterator<E> iterator = trackObject.get(number).iterator();
		while (iterator.hasNext())
		{
			E temp = iterator.next();
			if(objectTrack.containsKey(temp))
			{
				objectTrack.remove(temp);
			}
//			if (relationship.containsKey(temp))
//			{
//				relationship.remove(temp);// ɾ�������ͬʱ������ϵ�����ͬ��������Ĺ�ϵҲ��Ҫ��ɾ��
//			}
			if (relationship.containsKey(temp))
			{
				Iterator<E> iterator2 = relationship.get(temp).iterator();
				while(iterator2.hasNext())
				{
					E temp1 = iterator2.next();
					if(relationship.get(temp1).contains(temp))
					{
						relationship.get(temp1).remove(temp);
					}
				}
				relationship.remove(temp);// ɾ�������������������Ĺ�ϵ
			}
			if (LErelationship.contains(temp))
			{
				LErelationship.remove(temp);
			}
		} // ���岻�ٳ����ڸù���У����ﾶ�������൱�ڳ���
		trackObject.remove(number);// ɾ������������ӳ��
		System.out.println(trackObject.size());
		System.out.println("Succeed!");
	}

	@Override
	public boolean addTrackObject(E ob, int t)// ���ض����������һ�����壨����������λ�ã�
	{
		if (t < physical.size())
		{
			if (trackObject.containsKey(t))
			{
				trackObject.get(t).add(ob);// ���ù���������ӳ��
				objectTrack.put(ob, t);// �������嵽�����ӳ��
				angle.put(ob, 0.00);// ��ʼʱ�Ƕ�Ϊ0
				//���ӹ����ʱ������ӹ�ϵ
				relationship.put(ob, new HashSet<E>());
				// addLErelationship(ob);// �������������壬���������������岻һ��ֱ����أ������罻���������㼰���ϵ��������������û�����Թ�ϵ
				System.out.println("Succeed!");
				return true;
			}
			else
			{
				System.out.println("the track has been deleted!");
				return false;
			}
		}
		else
		{
			System.out.println("the track is out of bound!");
			return false;
		}
	}

	@Override
	public boolean deleteTrackObject(E ob, int t)// ���ض������ɾ��һ�����壨����������λ�ã�
	{
		if (t < physical.size())
		{
			if (trackObject.containsKey(t))
			{
				if (!trackObject.get(t).contains(ob))
				{
					System.out.println("The object is not in orbit");
					return false;
				}
				else
				{
					//ɾ����ϵһ��Ҫɾ�ĳ��� 
					trackObject.get(t).remove(ob);// ɾ������������ӳ��
					if(objectTrack.containsKey(ob))
					{
						objectTrack.remove(ob, t);// ɾ�����嵽�����ӳ��
					}
					if (angle.containsKey(ob))
					{
						angle.remove(ob);// ɾ�����嵽�Ƕȵ�ӳ��
					}
					if (relationship.containsKey(ob))
					{
						Iterator<E> iterator = relationship.get(ob).iterator();
						while(iterator.hasNext())
						{
							E temp1 = iterator.next();
							if(relationship.get(temp1).contains(ob))
							{
								relationship.get(temp1).remove(ob);
							}
						}
						relationship.remove(ob);// ɾ�������������������Ĺ�ϵ
					}
					if (LErelationship.contains(ob))
					{
						LErelationship.remove(ob);// ɾ������������������Ĺ�ϵ
					}
//					System.out.println("Succeed!");
					return true;
				}
			}
			else
			{
				System.out.println("the track has been deleted!");
				return false;
			}
			// ob.setTrackNumber(t);// ���ù�����

		}
		else
		{
			System.out.println("the track is out of bound!");
			return false;
		}
	}

	@Override
	public void addCentralObject(L cre)// �������ĵ�����
	{
		central = cre;
	}

	@Override
	public void creatingTrackFromFiles(String name)// ���ⲿ�ļ���ȡ���ݹ�����ϵͳ����
	{
		// ����������ʵ��
	}

	@Override
	public boolean transit(E ob, int t)// ԭ�ӽṹ�еĵ��ӿ���ԾǨ(��Ҫ����ʵ��transit����)���˶�Ա����������Ҫ�õ�ԾǨ��app���������Ҫ�õ�ԾǨ
	{
		if (t < physical.size())
		{

			//ֻ�ı������ڹ���ϵ�λ�ã����漰��ϵ�ĸı�
			if (objectTrack.containsKey(ob))
			{
				int tempTrack = objectTrack.get(ob);
				trackObject.get(t).add(ob);// Ŀ������������
				trackObject.get(tempTrack).remove(ob);// ԭ���ɾ������
				objectTrack.put(ob, t);// �����Ӧ�Ĺ��ֵ�ı�
				return true;
			}
			else
			{
				System.out.println("The object is not in orbit!");
				return false;
			}
		}
		else
		{
			System.out.println("the target track is out of bound!");
			return false;
		}

	}

	// ����˶�Ա����
	// @Override
//	public boolean move(E object, double sitha)// ���ĳ��������迼�������λ�ã����ɴ�һ��λ���ƶ�����һ
//	// ��λ�ã��� object �ӵ�ǰλ���ƶ����µ� sitha �Ƕ�����Ӧ��λ��
//	{
//		for(int i=0; i<physical.size(); i++)
//		{
//			if(physical.get(i).getTrackPhysical().contains(object))
//			{
//				object.setSitha(sitha);
//				return true;
//			}
//		}
//		System.out.println("The object is not in orbit");
//		return false;
//		
//	}

//	@Override
//	public boolean addRelationshipBetweenTwoTracks(int t1, E e1, int t2, E e2)// ���������������֮��Ĺ�ϵ
//	{
//		// �ü��ϴ����б���ʵҲҪiterator����
//		if (t1 <= physical.size() && t2 <= physical.size())
//		{
//			Set<E> temp = physical.get(t1).getTrackPhysical();
//			Set<E> temp2 = physical.get(t2).getTrackPhysical();
//			if(temp.contains(e1))//������Ը�ֵ�����࣬����ת���ǰ�ȫ�ģ����÷���ʱ���õĻ�������ķ���
//			{
//				if(temp2.contains(e2))
//				{
//					Iterator<E> iterator = temp.iterator();
//					while(iterator.hasNext())
//					{
//						E tempe = iterator.next();
//						if(tempe == e1)
//						{
//							Iterator<E> iterator2 = temp2.iterator();
//							while(iterator2.hasNext())
//							{
//								E tempe2 = iterator2.next();
//								if(temp2 == e2)//==�����ϱȽϵ����ڴ��е�����
//								{
//									//������ϵ�Ĳ�����
//									e1.getRelationship().add(e2);//�˴�������temp
//									e2.getRelationship().add(e1);
//									
//								}
//							}
//						}
//					}
//					return true;//��ȷ�����������������Ӧ����ʱ���Ϳ��Է���true��
//				}
//				else 
//				{
//					System.out.println("the target track doesn't contains e2!");
//					return false;
//				}
//			}
//			else 
//			{
//				System.out.println("the initial track doesn't contains e1!");
//				return false;
//			}
//		}
//		else 
//		{
//			System.out.println("the track is out of bound!");
//			return false;
//		}
//	}
//
//	@Override
//	public boolean addRelationshipBetweenCentralAndTrack(L central, int number, E e)// ������������͹������֮��Ĺ�ϵ
//	{
//		if(number<=physical.size())
//		{
//			if(physical.get(number).getTrackPhysical().contains(e))
//			{
//				central.getRelation().add(e);//����Ҫ�ھ��������ҵ����e����Ϊ���Ƕ�e���в���
//				return true;
//			}
//			else 
//			{
//				System.out.println("There is no target object in orbit!");
//				return false;
//			}
//		}
//		else
//		{
//			System.out.println("the track is out of bound!");
//			return false;
//			
//		}
//		
//	}
	public boolean addEErelationship(E e1, E e2)
	{

		// �ڹ���Ͼ�˵�������������壬��Ϊ�������岻�ڹ����
		if (!objectTrack.containsKey(e1))
		{
			System.out.println("object1 is not in the orbit system!");
			return false;
		}
		if (!objectTrack.containsKey(e2))
		{
			System.out.println("object2 is not in the orbit system!");
			return false;
		}
		if (!relationship.get(e1).contains(e2))
		{
			relationship.get(e1).add(e2);
		}
		if (!relationship.get(e2).contains(e1))
		{
			relationship.get(e2).add(e1);
		}
		return true;
	}

	// ɾ����ϵ
	public boolean deleteEErelationship(E e1, E e2)
	{
		// �ڹ���Ͼ�˵�������������壬��Ϊ�������岻�ڹ����
		if (!objectTrack.containsKey(e1))
		{
			System.out.println("object1 is not in the orbit system!");
			return false;
		}
		if (!objectTrack.containsKey(e2))
		{
			System.out.println("object2 is not in the orbit system!");
			return false;
		}
		if (relationship.get(e1).contains(e2))
		{
			relationship.get(e1).remove(e2);
		}
		if (relationship.get(e2).contains(e1))
		{
			relationship.get(e2).remove(e1);
		}
		return true;
	}

	public boolean addLErelationship(E e2)
	{
		if (!objectTrack.containsKey(e2))
		{
			System.out.println("The object1 is not in the orbit system!");
			return false;
		}
		LErelationship.add(e2);
		return true;
	}

	public boolean deleteLErelationship(E e2)
	{
		if (!objectTrack.containsKey(e2))
		{
			System.out.println("object1 is not in the orbit system!");
			return false;
		}
		if(LErelationship.contains(e2))
		{
			LErelationship.remove(e2);
		}
		return true;
	}


//	@Override
//	public Map<String, E> getFriend()
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}

}
