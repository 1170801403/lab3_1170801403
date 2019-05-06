package circularOrbit;

import track.*;
import centralObject.L1;

import static org.junit.Assert.assertTrue;

import java.util.*;

import track.*;
import physicalObject.*;
import centralObject.*;

//接口中的泛型是object的，ConcreteCircularObject中的泛型是有限定的，ConcreteCircularObject子类中的泛型也是有限定的
//如果接口和ConcreteCircularObject中的泛型都是<L extends L1,E extends E1>，就会出错
public class ConcreteCircularObject<L ,E > implements circularOrbit<L, E>// 接口后面必须加尖括号，否则会错，而且不能加extends，但接口本身的定义里可以加extends
{
	L central;// 默认状态，同一个包内可见，没有get函数，对于客户端代码来讲反而更加安全
	final List<Track> physical = new ArrayList<Track>();// 列表编号表示轨道层号，轨道对象中包含物体E,未把Track当成占位符
	final Map<Integer, Set<E>> trackObject = new HashMap<Integer, Set<E>>();// 轨道映射物体的列表
	final Map<E, Integer> objectTrack = new HashMap<E, Integer>();// 物体映射轨道的列表
	final Map<E, Set<E>> relationship = new HashMap<E, Set<E>>();// 存储轨道物体之间的关系
	final Set<E> LErelationship = new HashSet<E>();// 存储中心点物体和轨道物体之间的关系，在社交网络中是指第一层人
	final Map<E, Double> angle = new HashMap<E, Double>();// 存储每个物体的角度
	// final Map<E, Integer> objectGroup = new HashMap<E, Integer>();// 物体到组别的映射
	final physicalShelf shelf = new physicalShelf();
	final trackFactory realTrackFactory = new trackFactory();
	final List<tie> socialTie = new ArrayList<tie>();
	//Abstraction function:
	//具体实现一个circularOrbit接口，从文件中读入并建立轨道，
	//采用不同的数据结构存储轨道物体、中心物体、轨道物体与中心物体之间的关系、中心物体与轨道物体之间的关系，
	//并实现增减轨道物体、增减轨道、增减关系、轨道物体跃迁等功能
	//Representation invariant:
	//轨道物体，轨道，中心点物体都是不可变的，通过具体的数据结构建立它们之间的联系 
	//Safety from rep exposure:
	//在返回重要数据结构时，做了防御性克隆
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
	// 为getDifference函数服务
//	@Override
//	public Map<E, Integer> getObjectGroup()
//	{
//		Map<E, Integer> temp = new HashMap<E, Integer>();
//		Iterator<E> iterator = objectGroup.keySet().iterator();
//		while(iterator.hasNext())
//		{
//			E etemp = iterator.next();
//			int itemp = objectGroup.get(etemp);//自动拆包
//			temp.put(etemp, itemp);
//		}
//		return temp;
//		
//	}
	public L getCentral()//L是不可变的
	{
		return central;
	}
	public Set<E> getLErelationship()// 返回轨道上所有的物体
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
			E etemp = iterator.next();// 轨道物体，中心物体，轨道都是不可变的好处
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
			E tempe = iterator.next();// 客户端代码就算删，也只能删指针，影响不到原来的angle
			double m = angle.get(tempe);
			temp.put(tempe, m);// 自动包装
		}
		return temp;
	}

	@Override
	public void addTrack()// 增加一条轨道,需要首先确定轨道半径，必须大于
	{
		System.out.println(trackObject.size());
		int rep = physical.size() + 1;// 轨道半径 = 轨道编号+1
		Track temp = realTrackFactory.manufacture(rep);
		physical.add(temp);
		trackObject.put(physical.size() - 1, new HashSet<E>());
		System.out.println(trackObject.size());
		System.out.println("Succeed!");
	}

	@Override
	public void deleteTrack(int number)// 去除一条轨道,输入轨道在列表中的编号
	{
		if (number >= physical.size())
		{
			System.out.println("The track is out of bound!");
			return;
		}
		// physical.remove(number);//physical是控制轨道编号的，只能增加轨道，不能减少轨道
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
//				relationship.remove(temp);// 删除轨道的同时，轨道上的物体同其他物体的关系也需要被删除
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
				relationship.remove(temp);// 删除该物体与其他物体间的关系
			}
			if (LErelationship.contains(temp))
			{
				LErelationship.remove(temp);
			}
		} // 物体不再出现在该轨道中，在田径比赛中相当于出局
		trackObject.remove(number);// 删除轨道对物体的映射
		System.out.println(trackObject.size());
		System.out.println("Succeed!");
	}

	@Override
	public boolean addTrackObject(E ob, int t)// 向特定轨道上增加一个物体（不考虑物理位置）
	{
		if (t < physical.size())
		{
			if (trackObject.containsKey(t))
			{
				trackObject.get(t).add(ob);// 设置轨道到物体的映射
				objectTrack.put(ob, t);// 设置物体到轨道的映射
				angle.put(ob, 0.00);// 初始时角度为0
				//增加轨道的时候就增加关系
				relationship.put(ob, new HashSet<E>());
				// addLErelationship(ob);// 向轨道上增加物体，该物体与中心物体不一定直接相关，例如社交网络中两层及以上的人与中心物体就没有明显关系
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
	public boolean deleteTrackObject(E ob, int t)// 向特定轨道上删除一个物体（不考虑物理位置）
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
					//删除关系一定要删的彻底 
					trackObject.get(t).remove(ob);// 删除轨道到物体的映射
					if(objectTrack.containsKey(ob))
					{
						objectTrack.remove(ob, t);// 删除物体到轨道的映射
					}
					if (angle.containsKey(ob))
					{
						angle.remove(ob);// 删除物体到角度的映射
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
						relationship.remove(ob);// 删除该物体与其他物体间的关系
					}
					if (LErelationship.contains(ob))
					{
						LErelationship.remove(ob);// 删除该物体与中心物体的关系
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
			// ob.setTrackNumber(t);// 设置轨道编号

		}
		else
		{
			System.out.println("the track is out of bound!");
			return false;
		}
	}

	@Override
	public void addCentralObject(L cre)// 增加中心点物体
	{
		central = cre;
	}

	@Override
	public void creatingTrackFromFiles(String name)// 从外部文件读取数据构造轨道系统对象
	{
		// 留到子类中实现
	}

	@Override
	public boolean transit(E ob, int t)// 原子结构中的电子可以跃迁(需要重新实现transit方法)，运动员更换赛道需要用到跃迁，app更换轨道需要用到跃迁
	{
		if (t < physical.size())
		{

			//只改变物体在轨道上的位置，不涉及关系的改变
			if (objectTrack.containsKey(ob))
			{
				int tempTrack = objectTrack.get(ob);
				trackObject.get(t).add(ob);// 目标轨道增加物体
				trackObject.get(tempTrack).remove(ob);// 原轨道删除物体
				objectTrack.put(ob, t);// 物体对应的轨道值改变
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

	// 针对运动员对象
	// @Override
//	public boolean move(E object, double sitha)// 如果某轨道物体需考虑其绝对位置，并可从一个位置移动到另一
//	// 个位置，将 object 从当前位置移动到新的 sitha 角度所对应的位置
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
//	public boolean addRelationshipBetweenTwoTracks(int t1, E e1, int t2, E e2)// 增加两个轨道物体之间的关系
//	{
//		// 用集合代替列表，其实也要iterator遍历
//		if (t1 <= physical.size() && t2 <= physical.size())
//		{
//			Set<E> temp = physical.get(t1).getTrackPhysical();
//			Set<E> temp2 = physical.get(t2).getTrackPhysical();
//			if(temp.contains(e1))//子类可以赋值给父类，向上转型是安全的，调用方法时调用的还是子类的方法
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
//								if(temp2 == e2)//==本质上比较的是内存中的内容
//								{
//									//进行联系的操作；
//									e1.getRelationship().add(e2);//此处不能用temp
//									e2.getRelationship().add(e1);
//									
//								}
//							}
//						}
//					}
//					return true;//当确定了两轨道都含有相应物体时，就可以返回true了
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
//	public boolean addRelationshipBetweenCentralAndTrack(L central, int number, E e)// 增加中心物体和轨道物体之间的关系
//	{
//		if(number<=physical.size())
//		{
//			if(physical.get(number).getTrackPhysical().contains(e))
//			{
//				central.getRelation().add(e);//不需要在具体轨道上找到这个e，因为不是对e进行操作
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

		// 在轨道上就说明不是中心物体，因为中心物体不在轨道上
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

	// 删除关系
	public boolean deleteEErelationship(E e1, E e2)
	{
		// 在轨道上就说明不是中心物体，因为中心物体不在轨道上
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
