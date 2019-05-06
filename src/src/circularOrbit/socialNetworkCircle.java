package circularOrbit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.source.tree.Tree;

import APIs.CircularOrbitAPIs;
import abstractFactory.socialFactory;
import centralObject.L1;
import centralObject.socialL1;

import physicalObject.socialE1;
import physicalObject.trackE1;

public class socialNetworkCircle extends ConcreteCircularObject<socialL1, socialE1>
{
//	private  Set<String> friendName = new HashSet<String>();
	// 两个人不可能同名，因为socialTie中只写了姓名
	private final Map<String, socialE1> friend = new HashMap<String, socialE1>();// 通过名字找到人
	private final Map<String, Map<String, Float>> intimacyFriend = new HashMap<String, Map<String, Float>>();// 存储轨道人间的亲密度
	// private final Map<String, Map<String, Float>> socialTie = new HashMap<String,
	// Map<String, Float>>();// 存储socialTie中的内容，将读取文件和建立轨道分离开来
	// private final List<tie> socialTie = new ArrayList<tie>();
	private final Map<String, Float> superIntimacyFriend = new HashMap<String, Float>();// 存储与中心人的亲密度
	private final List<String> temp1 = new ArrayList<String>();// 存储例外情况
	private final List<String> temp2 = new ArrayList<String>();
	private final List<Float> temp3 = new ArrayList<Float>();
	private socialFactory factory = new socialFactory();

	//
	public Map<String, socialE1> getFriend()
	{
		Map<String, socialE1> tempFriend = new HashMap<String, socialE1>();
		Iterator<String> iterator = friend.keySet().iterator();
		while (iterator.hasNext())
		{
			String temp = iterator.next();
			tempFriend.put(temp, friend.get(temp));
		}
		return tempFriend;
	}

	// 清空该清空的集合
	public void clear()
	{
		physical.clear();// physical其实是第一个应该删除的，因为它直接决定了轨道的编号，轨道的编号决定了trackObject能否正常使用
		angle.clear();
		objectTrack.clear();
		trackObject.clear();
		LErelationship.clear();
		// relationship.clear();
		Iterator<socialE1> iterator = relationship.keySet().iterator();
		while (iterator.hasNext())
		{
			relationship.get(iterator.next()).clear();
		}
		temp1.clear();
		temp2.clear();
		temp3.clear();
		superIntimacyFriend.clear();
		Iterator<String> iterator2 = intimacyFriend.keySet().iterator();
		while (iterator2.hasNext())
		{
			intimacyFriend.get(iterator2.next()).clear();
		}
	}

	// 覆盖了父类中的方法
	public boolean addTrackObject(socialE1 ob, int t)// 向特定轨道上增加一个物体（不考虑物理位置）
	{
		if (t < physical.size())
		{
			if (trackObject.containsKey(t))
			{
				trackObject.get(t).add(ob);// 设置轨道到物体的映射
				objectTrack.put(ob, t);// 设置物体到轨道的映射
				angle.put(ob, 0.00);// 初始时角度为0
				// 加物体的时候也需要对friend进行更改
				friend.put(ob.getName(), ob);
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

	// 覆盖了父类中的方法
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
		Iterator<socialE1> iterator = trackObject.get(number).iterator();
		while (iterator.hasNext())
		{
			socialE1 temp = iterator.next();
			if (friend.containsKey(temp.getName()))
			{
				friend.remove(temp.getName());
			}
			for (int i = 0; i < socialTie.size(); i++)
			{
				if (socialTie.get(i).getName1().equals(temp.getName())
						|| socialTie.get(i).getName2().equals(temp.getName()))
				{
					socialTie.remove(i);
					i--;// 删除一个元素之后，后面的元素会往前移动
				}
			}
		}
//			if (relationship.containsKey(temp))
//			{
//				relationship.remove(temp);// 删除轨道的同时，轨道上的物体同其他物体的关系也需要被删除
//			}
//			if (relationship.containsKey(temp))
//			{
//				Iterator<socialE1> iterator2 = relationship.get(temp).iterator();
//				while (iterator2.hasNext())
//				{
//					socialE1 temp1 = iterator2.next();
//					if (relationship.get(temp1).contains(temp))
//					{
//						relationship.get(temp1).remove(temp);
//					}
//				}
//				relationship.remove(temp);// 删除该物体与其他物体间的关系
//			}
//			if (LErelationship.contains(temp))
//			{
//				LErelationship.remove(temp);
//			}
		// } // 物体不再出现在该轨道中，在田径比赛中相当于出局
//		trackObject.remove(number);// 删除轨道对物体的映射
		System.out.println(trackObject.size());
		clear();// 清空有关集合(关键步骤)
		creatingTrack();// 重建轨道
		System.out.println("Succeed!");
	}

	// 覆盖了父类中的方法
	public boolean deleteTrackObject(socialE1 ob, int t)// 向特定轨道上删除一个物体（不考虑物理位置）
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
					if (friend.containsKey(ob.getName()))
					{
						friend.remove(ob.getName());
					}
					for (int i = 0; i < socialTie.size(); i++)
					{
						if (socialTie.get(i).getName1().equals(ob.getName())
								|| socialTie.get(i).getName2().equals(ob.getName()))
						{
							socialTie.remove(i);
							i--;// 删除一个元素之后，不可以退出，因为不止一个socialTie里含有它
						}
					}
					clear();// 清空有关集合(关键步骤)
					creatingTrack();// 重建轨道
					return true;
				}
			}
			// //删除关系一定要删的彻底
//					trackObject.get(t).remove(ob);// 删除轨道到物体的映射
//					if(objectTrack.containsKey(ob))
//					{
//						objectTrack.remove(ob, t);// 删除物体到轨道的映射
//					}
//					if (angle.containsKey(ob))
//					{
//						angle.remove(ob);// 删除物体到角度的映射
//					}
//					if (friend.containsKey(ob.getName()))
//					{
//						friend.remove(ob.getName());
//					}
//					if (relationship.containsKey(ob))
//					{
//						Iterator<socialE1> iterator = relationship.get(ob).iterator();
//						while(iterator.hasNext())
//						{
//							socialE1 temp1 = iterator.next();
//							if(relationship.get(temp1).contains(ob))
//							{
//								relationship.get(temp1).remove(ob);
//							}
//						}
//						relationship.remove(ob);// 删除该物体与其他物体间的关系
//					}
//					if (LErelationship.contains(ob))
//					{
//						LErelationship.remove(ob);// 删除该物体与中心物体的关系
//					}
//					System.out.println("Succeed!");
//					return true;
//				}
//			}
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

	public void creatingTrackFromFiles(String name)
	{
		String txt = new String();
		try
		{
			txt = new String(Files.readAllBytes(Paths.get("txt/" + name + "txt")));
		}
		catch (IOException e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		String re1 = "(CentralUser)"; // Word 1
		String re2 = "(\\s*)"; // White Space 1
		String re3 = "(:)"; // Any Single Character 1
		String re4 = "(:)"; // Any Single Character 2
		String re5 = "(=)"; // Any Single Character 3
		String re6 = "(\\s*)"; // White Space 2
		String re7 = "(<)"; // Any Single Character 4
		String re8 = "((?:[a-z][a-z]*[0-9]*[a-z0-9]*))"; // Word 2
		String re9 = "(,)"; // Any Single Character 5
		String re10 = "(\\s*)";
		String re11 = "(\\d+)"; // Integer Number 1
		String re12 = "(,)"; // Any Single Character 6
		String re13 = "(\\s*)";
		String re14 = "([a-z])"; // Any Single Word Character (Not Whitespace) 2
		String re15 = "(>)"; // Any Single Character 7

		Pattern p = Pattern.compile(
				re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10 + re11 + re12 + re13 + re14 + re15,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		if (m.find())// 只会find一次
		{
			System.out.println("find central!");
			String word1 = m.group(1);
			String c1 = m.group(2);
			String c2 = m.group(3);
			String c3 = m.group(4);
			String ws2 = m.group(5);
			String c4 = m.group(6);
			String word2 = m.group(7);
			String c5 = m.group(8);// 姓名

			String int1 = m.group(9);
			String c6 = m.group(10);
			String w2 = m.group(11);// 年龄
			String c7 = m.group(12);
			String c8 = m.group(14);// 性别
			central = factory.manufactureL(c5, Integer.parseInt(w2), c8.charAt(0), 0, 0);// 构造中心点物体
		}
//		new socialL1(c5, Integer.parseInt(w2), c8.charAt(0))
		// Friend里的人不一定和中心点人有关系
		String fre1 = "(Friend)"; // Word 1
		String fre2 = "(\\s*)"; // White Space 1
		String fre3 = "(:)"; // Any Single Character 1
		String fre4 = "(:)"; // Any Single Character 2
		String fre5 = "(=)"; // Any Single Character 3
		String fre6 = "(\\s*)"; // White Space 2
		String fre7 = "(<)"; // Any Single Character 4
		String fre8 = "((?:[a-z][a-z]*[0-9]*[a-z0-9]*))"; // Alphanum 1
		String fre9 = "(,)"; // Any Single Character 5
		String fre10 = "(\\s*)"; // White Space 1
		String fre11 = "(\\d+)"; // Integer Number 1
		String fre12 = "(,)"; // Any Single Character 6
		String fre13 = "(\\s*)"; // White Space 1
		String fre14 = "([a-z])"; // Any Single Word Character (Not Whitespace) 1
		String fre15 = "(>)"; // Any Single Character 7
		Pattern fp = Pattern.compile(fre1 + fre2 + fre3 + fre4 + fre5 + fre6 + fre7 + fre8 + fre9 + fre10 + fre11
				+ fre12 + fre13 + fre14 + fre15, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher fm = fp.matcher(txt);
		System.out.println("start to find friend!");
		while (fm.find())// 不止匹配一次
		{
			System.out.println("find friend!");
			String fword1 = fm.group(1);
			String fws1 = fm.group(2);
			String fc1 = fm.group(3);
			String fc2 = fm.group(4);
			String fc3 = fm.group(5);
			String fws2 = fm.group(6);
			String fc4 = fm.group(7);
			String falphanum1 = fm.group(8);// 姓名

			String fc5 = fm.group(9);
			String fint1 = fm.group(10);

			String ffc6 = fm.group(11);// 年龄
			String fw1 = fm.group(12);

			String fc7 = fm.group(14);// 性别

			socialE1 tempFriend = factory.manufactureE(falphanum1, Integer.parseInt(ffc6), fc7.charAt(0));
//					new socialE1(falphanum1, Integer.parseInt(ffc6), fc7.charAt(0));
			friend.put(falphanum1, tempFriend);// 通过名字找到人，初始化friend映射
			// socialNetWork重写了父类中的方法
			relationship.put(tempFriend, new HashSet<socialE1>());// 匹配的同时对“关系”进行初始化
			intimacyFriend.put(falphanum1, new HashMap<String, Float>());// 初始化映射
		}

		String sre1 = "(SocialTie)"; // Word 1
		String sre2 = "(\\s*)"; // White Space 1
		String sre3 = "(:)"; // Any Single Character 1
		String sre4 = "(:)"; // Any Single Character 2
		String sre5 = "(=)"; // Any Single Character 3
		String sre6 = "(\\s*)"; // White Space 2
		String sre7 = "(<)"; // Any Single Character 4
		String sre8 = "((?:[a-z][a-z]*[0-9]*[a-z0-9]*))";
		String sre9 = "(,)"; // Any Single Character 5
		String sre10 = "(\\s*)";
		String sre11 = "((?:[a-z][a-z]*[0-9]*[a-z0-9]*))"; // Alphanum 2
		String sre12 = "(,)"; // Any Single Character 6
		String sre13 = "(\\s*)";
		String sre14 = "([01](?:\\.{1}\\d{1,3}){0,1})"; // Float 1
//		([+-]?\\d*\\.\\d+)(?![-+0-9\\.])
		String sre15 = "(>)"; // Any Single Character 7
		Pattern normalSp = Pattern.compile(sre1 + sre2 + sre3 + sre4 + sre5 + sre6 + sre7 + sre8 + sre9 + sre10 + sre11
				+ sre12 + sre13 + sre14 + sre15, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher normalSm = normalSp.matcher(txt);

		// 当这个while循环执行完毕之后，轨道系统就建立起来了
		// addLErelationship：本质上是一个集合，不需要执行，在addTrackObject里执行
		// addEErelationship：一个映射，映射的value值还是一个映射，仅限于轨道物体
		// superIntimacyFriend: 存储第一层轨道上的人与中心物体之间的关系，以及亲密度
		// intimacyFriend：仅限于轨道物体，跟中心物体无任何关系，其中key值的数量==addLErelationship中元素的数量==轨道上的物体总数
		// Friend中的每个人必定出现在某一层轨道上，Friend中每个人的名字至少在SocialTie中出现一次
//		addTrack();// 首先先建立第一层轨道
//		System.out.println("start!");
		while (normalSm.find())
		{
			System.out.println("Find it!");
			String normalSalphanum1 = normalSm.group(8);// person1
			// System.out.println(normalSalphanum1);//匹配没问题
			String normalSalphanum2 = normalSm.group(11);// person2
			// System.out.println(normalSalphanum2);
			String normalSfloat1 = normalSm.group(14);// 亲密度
			// System.out.println(normalSfloat1);
//			socialTie.put(normalSalphanum1, new HashMap<String, Float>()
//			{
//				{
//					put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//				}
//			});// 定义的时候初始化
			socialTie.add(new tie(normalSalphanum1, normalSalphanum2, Float.parseFloat(normalSfloat1)));
		}
	}
//			if ((normalSalphanum1.equals(central.getName())) || (normalSalphanum2.equals(central.getName())))
//			{
//
//				if (normalSalphanum1.equals(central.getName()))
//				{
//					// 中心人与第一层轨道人之间的关系是单向的
//					if (!(friend.containsKey(normalSalphanum2)))
//					{
//						System.out.println("System error!");
//						return;
//					}
//					socialE1 person = friend.get(normalSalphanum2);
//					if (!objectTrack.containsKey(person))
//					{
//						System.out.println("Central!");
//						addTrackObject(person, 0);// 已经往objectTrack中加入了映射
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//					}
//					else
//					{
//						System.out.println("Central!");
//						transit(person, 0);// 已经往objectTrack中更新了映射
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//					}
//					// addLErelationship(friend.get(normalSalphanum2));
//					// addTrackObject(friend.get(normalSalphanum2), 0);// 已知物体，就可以知道它所在的轨道
//				}
//				else
//				{
//					if (!(friend.containsKey(normalSalphanum1)))
//					{
//						System.out.println("System error!");
//						return;
//					}
//					socialE1 person = friend.get(normalSalphanum1);
//					if (!objectTrack.containsKey(person))
//					{
//						System.out.println("Central!");
//						addTrackObject(person, 0);// 已经往objectTrack中加入了映射
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum1, Float.parseFloat(normalSfloat1));
//					}
//					else
//					{
//						System.out.println("Central!");
//						transit(person, 0);// 已经往objectTrack中更新了映射
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum1, Float.parseFloat(normalSfloat1));
//					}
//					// addLErelationship(friend.get(normalSalphanum1));
//					// addTrackObject(friend.get(normalSalphanum1), 0);// 已知物体，就可以知道它所在的轨道
//				}
//			}
//			else
//			{
//				System.out.println("n!");
//				// 亲密度映射
////				intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
////				intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
////				// 关系映射
////				addEErelationship(friend.get(normalSalphanum2), friend.get(normalSalphanum1));
////				System.out.println("do its!");//以上代码没有问题
//				if (!(friend.containsKey(normalSalphanum1) && friend.containsKey(normalSalphanum2)))
//				{
//					System.out.println("System error!");
//					return;
//				}
//				socialE1 person1 = friend.get(normalSalphanum1);
//				socialE1 person2 = friend.get(normalSalphanum2);
//				if (objectTrack.containsKey(person1) && objectTrack.containsKey(person2))
//				{
//					System.out.println("EE!");
//					// 亲密度映射
//					intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//					intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//					// 关系映射
//					addEErelationship(person2, person1);
//
//				}
//
//				// 下面的两个else-if语句是处理双向情况，当要求处理单向情况时，删去一条分支即可
//				else if (objectTrack.containsKey(person1) && (!objectTrack.containsKey(person2)))
//				{
//					if ((physical.size() - 1) > objectTrack.get(person1))
//					{
//						System.out.println("EE!");
//						addTrackObject(person2, objectTrack.get(person1) + 1);
//						// 亲密度映射
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//						// 关系映射
//						addEErelationship(person2, person1);
//
//					}
//					else
//					{
//						temp1.add(normalSalphanum1);
//						temp2.add(normalSalphanum2);
//						temp3.add(Float.parseFloat(normalSfloat1));
////						System.out.println("EEADD!");
////						addTrack();
////						addTrackObject(person2, objectTrack.get(person1) + 1);
////						// 亲密度映射
////						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
////						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
////						// 关系映射
////						addEErelationship(person2, person1);
//
//					}
//				}
//				else if ((objectTrack.containsKey(person2)) && (!objectTrack.containsKey(person1)))
//				{
//					if ((physical.size() - 1) > objectTrack.get(person2))
//					{
//						System.out.println("EE!");
//						addTrackObject(person1, objectTrack.get(person2) + 1);
//						// 亲密度映射
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//						// 关系映射
//						addEErelationship(person2, person1);
//
//					}
//					else
//					{
//						temp1.add(normalSalphanum1);
//						temp2.add(normalSalphanum2);
//						temp3.add(Float.parseFloat(normalSfloat1));
//						// System.out.println("EEADD!");
////						addTrack();
////						addTrackObject(person1, objectTrack.get(person2) + 1);
////						// 亲密度映射
////						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
////						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
////						// 关系映射
////						addEErelationship(person2, person1);
//
//					}
//				}
//				else// 两个物体都不在轨道上
//				{
//					// 此时不能用映射存储这些例外的值，因为key值会有重复
//					temp1.add(normalSalphanum1);
//					temp2.add(normalSalphanum2);
//					temp3.add(Float.parseFloat(normalSfloat1));
//					System.out.println("do its!");// 以上代码没有问题
//				}
//
//			}
//		}
//
//		for (int i = 0; i < temp1.size(); i++)
//		{
//			String t1 = temp1.get(i);
//			String t2 = temp2.get(i);
//			Float ft3 = temp3.get(i);
//			socialE1 person1 = friend.get(t1);
//			socialE1 person2 = friend.get(t2);
//			if (objectTrack.containsKey(person1) && objectTrack.containsKey(person2))
//			{
//				System.out.println("EE!");
//				// addTrackObject(friend.get(t2), objectTrack.get(t1) + 1);
//				// 亲密度映射
//				intimacyFriend.get(t1).put(t2, ft3);
//				intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//				// 关系映射
//				addEErelationship(person2, person1);
//				// addEERelationship只需调用一次即可，函数内部会加两次关系
//			}
//
//			// 下面的两个else-if语句是处理双向情况，当要求处理单向情况时，删去一条分支即可
//			else if (objectTrack.containsKey(person1))
//			{
//				if ((physical.size() - 1) > objectTrack.get(person1))
//				{
//
//					System.out.println("EE!");
//					addTrackObject(person2, objectTrack.get(person1) + 1);
//					// 亲密度映射
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//					// 关系映射
//					addEErelationship(person2, person1);
//					// addEERelationship只需调用一次即可，函数内部会加两次关系
//				}
//				else
//				{
//					System.out.println("EEADD!");
//					addTrack();
//					addTrackObject(person2, objectTrack.get(person1) + 1);
//					// 亲密度映射
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//					// 关系映射
//					addEErelationship(person2, person1);
//					// addEERelationship只需调用一次即可，函数内部会加两次关系
//				}
//			}
//			else if (objectTrack.containsKey(person2))
//			{
//				if ((physical.size() - 1) > objectTrack.get(person2))
//				{
//					System.out.println("EE!");
//					addTrackObject(person1, objectTrack.get(person2) + 1);
//					// 亲密度映射
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//					// 关系映射
//					addEErelationship(person2, person1);
//					// addEERelationship只需调用一次即可，函数内部会加两次关系
//				}
//				else
//				{
//					System.out.println("EEADD!");
//					addTrack();
//					addTrackObject(person1, objectTrack.get(person2) + 1);
//					// 亲密度映射
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//					// 关系映射
//					addEErelationship(person2, person1);
//					// addEERelationship只需调用一次即可，函数内部会加两次关系
//				}
//			}
//			// 全部加完之后，两个物体还不在轨道上
//			else
//			{
//				System.out.println("do its again!");
//			}
//
//		}
//		System.out.println(central.getName());
//	}

	public void creatingTrack()
	{
		System.out.println("SocialTie的大小是：" + socialTie.size());
//		Iterator<String> iterator = socialTie.keySet().iterator();
		addTrack();// 建立第一条轨道，哪怕社交网络中一个人没有，也会默认建立一条轨道
		//第一次，只加第一层物体
		for (int i = 0; i < socialTie.size(); i++)
		{
			String normalSalphanum1 = socialTie.get(i).getName1();
			String normalSalphanum2 = socialTie.get(i).getName2();
			float normalSfloat1 = socialTie.get(i).getIni();
//			Iterator<String> iterator2 = socialTie.get(normalSalphanum1).keySet().iterator();
//			while(iterator2.hasNext())
//			{
//				normalSalphanum2 = iterator2.next();
//				normalSfloat1 = socialTie.get(normalSalphanum1).get(normalSalphanum2);
//			}
			if ((normalSalphanum1.equals(central.getName())) || (normalSalphanum2.equals(central.getName())))
			{

				if (normalSalphanum1.equals(central.getName()))
				{
					// 中心人与第一层轨道人之间的关系是单向的
					if (!(friend.containsKey(normalSalphanum2)))
					{
						System.out.println("The friend map doesn't contains it!");
						return;
					}
					socialE1 person = friend.get(normalSalphanum2);
					if (!objectTrack.containsKey(person))
					{
						System.out.println("Central!");
						addTrackObject(person, 0);// 已经往objectTrack中加入了映射
						addLErelationship(person);
						superIntimacyFriend.put(normalSalphanum2, normalSfloat1);
					}
					else
					{
						System.out.println("Central!");
						transit(person, 0);// 已经往objectTrack中更新了映射
						addLErelationship(person);
						superIntimacyFriend.put(normalSalphanum2, normalSfloat1);
					}
					// addLErelationship(friend.get(normalSalphanum2));
					// addTrackObject(friend.get(normalSalphanum2), 0);// 已知物体，就可以知道它所在的轨道
				}
				else
				{
					// 由轨道外的指向中心物体的关系可以被直接忽略
//					if (!(friend.containsKey(normalSalphanum1)))
//					{
//						System.out.println("The friend map doesn't contains it!");
//						return;
//					}
//					socialE1 person = friend.get(normalSalphanum1);
//					if (!objectTrack.containsKey(person))
//					{
//						System.out.println("Central!");
//						addTrackObject(person, 0);// 已经往objectTrack中加入了映射
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum1, normalSfloat1);
//					}
//					else
//					{
//						System.out.println("Central!");
//						transit(person, 0);// 已经往objectTrack中更新了映射
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum1, normalSfloat1);// superIntimacyFriend可以在重新建立轨道系统的时候清零，因为它并没有在读文件的时候初始化
//					}
					// addLErelationship(friend.get(normalSalphanum1));
					// addTrackObject(friend.get(normalSalphanum1), 0);// 已知物体，就可以知道它所在的轨道
				}
			}
			else
			{
				System.out.println("n!");
				// 亲密度映射
//				intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//				intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//				// 关系映射
//				addEErelationship(friend.get(normalSalphanum2), friend.get(normalSalphanum1));
//				System.out.println("do its!");//以上代码没有问题
				if (!(friend.containsKey(normalSalphanum1) && friend.containsKey(normalSalphanum2)))
				{
					System.out.println("The friend map doesn't contains them!");
					return;
				}
				socialE1 person1 = friend.get(normalSalphanum1);
				socialE1 person2 = friend.get(normalSalphanum2);
				if (objectTrack.containsKey(person1) && objectTrack.containsKey(person2))
				{
					if (!(objectTrack.get(person1) > objectTrack.get(person2)))
					{
						System.out.println("EE!");
						// 亲密度映射
						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, normalSfloat1);
						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, normalSfloat1);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
						// 关系映射
						addEErelationship(person2, person1);// 调用的是父类中的方法
					}

				}

				// 下面的两个else-if语句是处理双向情况，当要求处理单向情况时，删去一条分支即可
				else if (objectTrack.containsKey(person1) && (!objectTrack.containsKey(person2)))
				{
					if ((physical.size() - 1) > objectTrack.get(person1))
					{
						System.out.println("EE!");
						addTrackObject(person2, objectTrack.get(person1) + 1);
						// 亲密度映射
						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, normalSfloat1);
						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, normalSfloat1);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
						// 关系映射
						addEErelationship(person2, person1);

					}
					else
					{
						temp1.add(normalSalphanum1);
						temp2.add(normalSalphanum2);
						temp3.add(normalSfloat1);
//						System.out.println("EEADD!");
//						addTrack();
//						addTrackObject(person2, objectTrack.get(person1) + 1);
//						// 亲密度映射
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//						// 关系映射
//						addEErelationship(person2, person1);

					}
				}
				else if ((objectTrack.containsKey(person2)) && (!objectTrack.containsKey(person1)))
				{
					if ((physical.size() - 1) > objectTrack.get(person2))
					{
						System.out.println("EE!");
						addTrackObject(person1, objectTrack.get(person2) + 1);
						// 亲密度映射
						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, normalSfloat1);
						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, normalSfloat1);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
						// 关系映射
						addEErelationship(person2, person1);

					}
					else
					{
						temp1.add(normalSalphanum1);
						temp2.add(normalSalphanum2);
						temp3.add(normalSfloat1);
						// System.out.println("EEADD!");
//						addTrack();
//						addTrackObject(person1, objectTrack.get(person2) + 1);
//						// 亲密度映射
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//						// 关系映射
//						addEErelationship(person2, person1);

					}
				}
				else// 两个物体都不在轨道上
				{
					// 此时不能用映射存储这些例外的值，因为key值会有重复
					temp1.add(normalSalphanum1);
					temp2.add(normalSalphanum2);
					temp3.add(normalSfloat1);
					System.out.println("do its!");// 以上代码没有问题
				}

			}
		}

		// 对剩余部分进行处理
		for (int i = 0; i < temp1.size(); i++)
		{
			String t1 = temp1.get(i);
			String t2 = temp2.get(i);
			Float ft3 = temp3.get(i);
			socialE1 person1 = friend.get(t1);
			socialE1 person2 = friend.get(t2);
			if (objectTrack.containsKey(person1) && objectTrack.containsKey(person2))
			{
				if (!(objectTrack.get(person1) > objectTrack.get(person2)))
				{
					System.out.println("EE!");
					// addTrackObject(friend.get(t2), objectTrack.get(t1) + 1);
					// 亲密度映射
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
					// 关系映射
					addEErelationship(person2, person1);
					// addEERelationship只需调用一次即可，函数内部会加两次关系
				}
			}

			// 下面的两个else-if语句是处理双向情况，当要求处理单向情况时，删去一条分支即可
			else if (objectTrack.containsKey(person1))
			{
				if ((physical.size() - 1) > objectTrack.get(person1))
				{

					System.out.println("EE!");
					addTrackObject(person2, objectTrack.get(person1) + 1);
					// 亲密度映射
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
					// 关系映射
					addEErelationship(person2, person1);
					// addEERelationship只需调用一次即可，函数内部会加两次关系
				}
				else
				{
					System.out.println("EEADD!");
					addTrack();
					addTrackObject(person2, objectTrack.get(person1) + 1);
					// 亲密度映射
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
					// 关系映射
					addEErelationship(person2, person1);
					// addEERelationship只需调用一次即可，函数内部会加两次关系
				}
			}
			else if (objectTrack.containsKey(person2))
			{
				if ((physical.size() - 1) > objectTrack.get(person2))
				{
					System.out.println("EE!");
					addTrackObject(person1, objectTrack.get(person2) + 1);
					// 亲密度映射
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
					// 关系映射
					addEErelationship(person2, person1);
					// addEERelationship只需调用一次即可，函数内部会加两次关系
				}
				else
				{
					System.out.println("EEADD!");
					addTrack();
					addTrackObject(person1, objectTrack.get(person2) + 1);
					// 亲密度映射
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
					// 关系映射
					addEErelationship(person2, person1);
					// addEERelationship只需调用一次即可，函数内部会加两次关系
				}
			}
			// 全部加完之后，两个物体还不在轨道上
			else
			{
				System.out.println("do its again!");
			}

		}
		
		//第三次总结
		for (int i = 0; i < temp1.size(); i++)
		{
			String t1 = temp1.get(i);
			String t2 = temp2.get(i);
			Float ft3 = temp3.get(i);
			socialE1 person1 = friend.get(t1);
			socialE1 person2 = friend.get(t2);
			if (!(objectTrack.containsKey(person1) && objectTrack.containsKey(person2)))
			{
				if (objectTrack.containsKey(person1)&& !objectTrack.containsKey(person2))
				{
					if ((physical.size() - 1) > objectTrack.get(person1))
					{

						System.out.println("EE!");
						addTrackObject(person2, objectTrack.get(person1) + 1);
						// 亲密度映射
						intimacyFriend.get(t1).put(t2, ft3);
						intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
						// 关系映射
						addEErelationship(person2, person1);
						// addEERelationship只需调用一次即可，函数内部会加两次关系
					}
					else
					{
						System.out
								.println("EEADDppppppppppppppppppppppppppppppppppppppppppppppppppppp!" + person2.getName());
						addTrack();
						addTrackObject(person2, objectTrack.get(person1) + 1);
						// 亲密度映射
						intimacyFriend.get(t1).put(t2, ft3);
						intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
						// 关系映射
						addEErelationship(person2, person1);
						// addEERelationship只需调用一次即可，函数内部会加两次关系
					}
				}
				//只支持单向关系
//				else if (objectTrack.containsKey(person2)&& !objectTrack.containsKey(person1))
//				{
//					if ((physical.size() - 1) > objectTrack.get(person2))
//					{
//						System.out.println("EE!");
//						addTrackObject(person1, objectTrack.get(person2) + 1);
//						// 亲密度映射
//						intimacyFriend.get(t1).put(t2, ft3);
//						intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//						// 关系映射
//						addEErelationship(person2, person1);
//						// addEERelationship只需调用一次即可，函数内部会加两次关系
//					}
//					else
//					{
//						System.out.println("EEADD!");
//						addTrack();
//						addTrackObject(person1, objectTrack.get(person2) + 1);
//						// 亲密度映射
//						intimacyFriend.get(t1).put(t2, ft3);
//						intimacyFriend.get(t2).put(t1, ft3);// 两个物体都在轨道上，不用加轨道，加亲密度就可以
//						// 关系映射
//						addEErelationship(person2, person1);
//						// addEERelationship只需调用一次即可，函数内部会加两次关系
//					}
//				}
				else
				{
					System.out.println("sorry!");
				}
			}
		}
		checkRep();
	}

	// 求亲密度
	public boolean intimacy(String name)
	{
		int potential = 0;
		// 前提条件：每个人的名字都不一样
		List<String> queue = new ArrayList<String>();// 作为搜索队列
		Set<String> flag = new HashSet<String>();// 标记已经被访问过的人
		if (!friend.containsKey(name))
		{
			System.out.println("This person doesn't exist!");
			return false;
		}
		if (!objectTrack.containsKey(friend.get(name)))
		{
			System.out.println("This person is not in the orbital system!");
			return false;
		}
		if (objectTrack.get(friend.get(name)) != 0)
		{
			System.out.println("This person is not in the first orbit!");
			return false;
		}
		System.out.println("the size is :" + intimacyFriend.get(name).size());
		queue.add(name);
		String tempName = name;
		while (queue.size() != 0)
		{
			tempName = queue.get(0);
			queue.remove(0);
			Map<String, Float> maptemp = intimacyFriend.get(tempName);// 不包含中心的那个人
			Iterator<String> iterator = maptemp.keySet().iterator();
			while (iterator.hasNext())
			{

				String temp = iterator.next();
				System.out.println(temp);
				if (!friend.containsKey(temp))
				{
					System.out.println("This person doesn't exist!");
					return false;
				}
				if (objectTrack.get(friend.get(temp)) == 0)
				{
					;
				}
				else
				{
					System.out.println("yes!");
					if (maptemp.get(temp) > 0.1 && !flag.contains(temp))// 亲密度满足要求，且没有被访问过
					{
						potential++;
						flag.add(temp);// 做标记
						queue.add(temp);// 入队
					}
				}
			}
			// 遍历的过程中包含了中心用户
		}
		System.out.println("You can meet " + (potential) + " friends indirectly.");
		return true;
	}

	// 增加关系（仅限于轨道物体）后重新建立轨道系统
	public boolean addRelationship(String name1, String name2, float ini)// 这个人必须在轨道上
	{
		// objectTrack在增加和减少物体的时候会得到更新
//		if(objectTrack.containsKey(name1)&&objectTrack.containsKey(name2))
//		{
//			
//		}
		// 通过名称得到实例
		if (!friend.containsKey(name1) || !friend.containsKey(name2))
		{
			System.out.println("They are not in the orbit");
		}
		socialTie.add(new tie(name1, name2, ini));
		clear();
		creatingTrack();
		return true;
	}

//		if ((name1.equals(central.getName())) || (name2.equals(central.getName())))
//		{
//			if (name1.equals(central.getName()))
//			{
//				// 能出现在socialTie里面，被加到轨道上的人，一定是被加到了friend里面，反之，friend里面的人不一定都能出现在轨道上
//				if (!friend.containsKey(name2))
//				{
//					System.out.println("The second man doesn't exist!");
//					return false;
//				}
//				socialE1 temp2 = friend.get(name2);
//				if (!objectTrack.containsKey(temp2))
//				{
//					System.out.println("The second man is not in the orbital system!");
//					return false;
//				}
//				superIntimacyFriend.put(name2, ini);
//				addLErelationship(temp2);
//				if (objectTrack.get(temp2) != 0)
//				{
//					transit(temp2, 0);
//				}
//				Set<socialE1> adjust = relationship.get(temp2);
//				Iterator<socialE1> iterator = adjust.iterator();
//				while (iterator.hasNext())
//				{
//					socialE1 tempE2 = iterator.next();
//					if (objectTrack.containsKey(tempE2))
//					{
//						if (objectTrack.get(tempE2) > 1)
//						{
//							transit(tempE2, 1);// 转移到一号轨道，即第二条轨道上来
//						}
//					}
//					else
//					{
//						System.out.println("Those associated with him are not included in the map objectTrack");
//						return false;
//					}
//				}
//
//			}
//			else
//			{
//				if (!friend.containsKey(name1))
//				{
//					System.out.println("The second man doesn't exist!");
//					return false;
//				}
//				socialE1 temp1 = friend.get(name1);
//				if (!objectTrack.containsKey(temp1))
//				{
//					System.out.println("The second man is not in the orbital system!");
//					return false;
//				}
//				superIntimacyFriend.put(name1, ini);
//				addLErelationship(temp1);
//				if (objectTrack.get(temp1) != 0)
//				{
//					transit(temp1, 0);
//				}
//				Set<socialE1> adjust = relationship.get(temp1);
//				Iterator<socialE1> iterator2 = adjust.iterator();
//				while (iterator2.hasNext())
//				{
//					socialE1 tempE1 = iterator2.next();
//					if (objectTrack.containsKey(tempE1))
//					{
//						if (objectTrack.get(tempE1) > 1)// 此处必须是>1，因为该物体可能和第0号轨道上的物体之间有关系
//						{
//							transit(tempE1, 1);// 转移到一号轨道，即第二条轨道上来
//						}
//					}
//					else
//					{
//						System.out.println("Those associated with him are not included in the map objectTrack");
//						return false;
//					}
//				}
//			}
//		}
//		else
//		{
//
//			if (!friend.containsKey(name1))
//			{
//				System.out.println("The first man doesn't exist!");
//				return false;
//			}
//			if (!friend.containsKey(name2))
//			{
//				System.out.println("The second man doesn't exist!");
//				return false;
//			}
//			if (!objectTrack.containsKey(friend.get(name1)))
//			{
//				System.out.println("The first man is not in the orbital system!");
//				return false;
//			}
//			if (!objectTrack.containsKey(friend.get(name2)))
//			{
//				System.out.println("The second man is not in the orbital system!");
//				return false;
//			}
//			int track1 = objectTrack.get(friend.get(name1));
//			int track2 = objectTrack.get(friend.get(name2));
//
//			intimacyFriend.get(name1).put(name2, ini);
//			intimacyFriend.get(name2).put(name1, ini);
//
//			// 关系映射
//			addEErelationship(friend.get(name1), friend.get(name2));
//
//			if (track1 == track2)
//			{
//				;
//			}
//			else if (track1 > track2)
//			{
//				// 移动之前需要先判断是否更加接近中心点
//				if (track1 > track2 + 1)
//				{
//					transit(friend.get(name1), track2 + 1);// 牵一发而动全身
//
//				}
//				else
//				{
//					;// 此时不用移动
//				}
//
//			}
//			else if (track1 < track2)
//			{
//				if (track2 > track1 + 1)
//				{
//					transit(friend.get(name2), track1 + 1);
//				}
//				else
//				{
//					;// 此时不用移动
//				}
//			}
//		}
//		System.out.println("succeed!");
//		return true;
//	}
//
	// 删除关系（仅限于轨道物体）后重新调整轨道系统
	public boolean deleteRelationship(String name1, String name2)
	{
		if (!friend.containsKey(name1) || !friend.containsKey(name2))
		{
			System.out.println("They are not in the orbit");
		}
		boolean is = false;
		for (int i = 0; i < socialTie.size(); i++)
		{
			if ((socialTie.get(i).getName1().equals(name1) && socialTie.get(i).getName2().equals(name2))
					|| (socialTie.get(i).getName1().equals(name2) && socialTie.get(i).getName2().equals(name1)))
			{
				socialTie.remove(i);
				is = true;
				break;
			}
		}
		if (!is)
		{
			System.out.println("They have no relationship!");
			return false;
		}
		clear();
		creatingTrack();
		return true;
	}
}
//		if ((name1.equals(central.getName())) || (name2.equals(central.getName())))
//		{
//			if (name1.equals(central.getName()))
//			{
//				if (!friend.containsKey(name2))
//				{
//					System.out.println("The second man doesn't exist!");
//					return false;
//				}
//				socialE1 temp2 = friend.get(name2);
//				if (!objectTrack.containsKey(temp2))
//				{
//					System.out.println("The second man is not in the orbital system!");
//					return false;
//				}
//				// 先把关系删除，再考虑轨道的事情
//				superIntimacyFriend.remove(name2);
//				deleteLErelationship(temp2);
//			}
//			else
//			{
//
//			}
//		}
//		else
//		{
//			if (!friend.containsKey(name1))
//			{
//				System.out.println("Man one doesn't exist!");
//				return false;
//			}
//			if (!friend.containsKey(name2))
//			{
//				System.out.println("Man two doesn't exist!");
//				return false;
//			}
//			if (!objectTrack.containsKey(friend.get(name1)))
//			{
//				System.out.println("Man one is not in the orbital system!");
//				return false;
//			}
//			if (!objectTrack.containsKey(friend.get(name2)))
//			{
//				System.out.println("Man two is not in the orbital system!");
//				return false;
//			}
//			int track1 = objectTrack.get(friend.get(name1));
//			int track2 = objectTrack.get(friend.get(name2));
//
//			// 先把关系删了，再考虑轨道的事情
//			intimacyFriend.get(name1).remove(name2);
//			intimacyFriend.get(name2).remove(name1);
//			deleteEErelationship(friend.get(name1), friend.get(name2));
//
//			if (track1 == track2)
//			{
//				// 只需调用一次
//				;
//			}
//			// 2不受影响，1受影响
//			else if (track1 > track2)
//			{
//				int minTrackNumber = 1000;
//				Iterator<String> iterator = intimacyFriend.get(name1).keySet().iterator();
//				while (iterator.hasNext())
//				{
//					String temp = iterator.next();
//					if (objectTrack.get(friend.get(temp)) < minTrackNumber)
//					{
//						minTrackNumber = objectTrack.get(friend.get(temp));
//					}
//				}
//				// 唯一的关联被切断了，只能移出轨道系统
//				if (minTrackNumber == 1000)
//				{
//					deleteTrackObject(friend.get(name1), objectTrack.get(friend.get(name1)));
//				}
//				else
//				{
//					transit(friend.get(name1), minTrackNumber);
//				}
//			}
//			// 1不受影响，2受影响
//			else if (track1 < track2)
//			{
//				int minTrackNumber = -1;
//				Iterator<String> iterator = intimacyFriend.get(name2).keySet().iterator();
//				while (iterator.hasNext())
//				{
//					String temp = iterator.next();
//					if (objectTrack.get(friend.get(temp)) < minTrackNumber)
//					{
//						minTrackNumber = objectTrack.get(friend.get(temp));
//					}
//				}
//				// 唯一的关联被切断了，只能移出轨道系统
//				if (minTrackNumber == 1000)
//				{
//					deleteTrackObject(friend.get(name2), objectTrack.get(friend.get(name2)));
//				}
//				else
//				{
//					transit(friend.get(name2), minTrackNumber);
//				}
//
//			}
//		}
//		System.out.println("succeed!");
//		return true;
//	}

//	// 这个函数适用于轨道物体和中心物体
//	public int shortestLogicalDistance(String name1, String name2)
//	{
//		int distance;
//		//CircularOrbitAPIs<socialL1, socialE1> socialApi = new CircularOrbitAPIs<socialL1, socialE1>();
//		if ((name1.equals(central.getName())) || (name2.equals(central.getName())))
//		{
//			if ((name1.equals(central.getName())))
//			{
//				if (friend.containsKey(name2))
//				{
//					if (objectTrack.containsKey(friend.get(name2)))
//					{
//						distance = objectTrack.get(friend.get(name2))+1;
//					}
//					else
//					{
//						System.out.println("The name exist,but the person isn't in this orbit system!");
//						return -3;
//					}
//
//				}
//				else
//				{
//					System.out.println("The name doesn't exist!");
//					return -3;
//				}
//			}
//			else
//			{
//				if (friend.containsKey(name1))
//				{
//					if (objectTrack.containsKey(friend.get(name1)))
//					{
//						distance = objectTrack.get(friend.get(name1))+1;
//					}
//					else
//					{
//						System.out.println("The name exist,but the person isn't in this orbit system!");
//						return -3;
//					}
//
//				}
//				else
//				{
//					System.out.println("The name doesn't exist!");
//					return -3;
//				}
//			}
//		}
//		else
//		{
//
//			if (!friend.containsKey(name1))
//			{
//				System.out.println("Man one doesn't exist!");
//				return -3;
//			}
//			if (!friend.containsKey(name2))
//			{
//				System.out.println("Man two doesn't exist!");
//				return -3;
//			}
//			if (!objectTrack.containsKey(friend.get(name1)))
//			{
//				System.out.println("Man one is not in the orbital system!");
//				return -3;
//			}
//			if (!objectTrack.containsKey(friend.get(name2)))
//			{
//				System.out.println("Man two is not in the orbital system!");
//				return -3;
//			}
//			
////			if (objectTrack.get(friend.get(name1)) == objectTrack.get(friend.get(name2)))
////			{
////				distance = 0;
////			}
////			else if (objectTrack.get(friend.get(name1)) > objectTrack.get(friend.get(name2)))
////			{
////				distance = objectTrack.get(friend.get(name1)) - objectTrack.get(friend.get(name2));
////			}
////			else
////			{
////				distance = objectTrack.get(friend.get(name2)) - objectTrack.get(friend.get(name1));
////			}
//		}
//		System.out.println("The shortest logical distance is " + distance);
//		return distance;
//	}
//}

//class tie
//{
//	private final String name1;
//	private final String name2;
//	private final float ini;
//
//	public tie(String name1, String name2, float ini)
//	{
//		this.name1 = name1;
//		this.name2 = name2;
//		this.ini = ini;
//	}
//
//	public String getName1()
//	{
//		return name1;
//	}
//
//	public String getName2()
//	{
//		return name2;
//	}
//
//	public float getIni()
//	{
//		return ini;
//	}
//
//}