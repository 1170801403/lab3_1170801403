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

	// �����˲�����ͬ������ΪsocialTie��ֻд������
	private final Map<String, socialE1> friend = new HashMap<String, socialE1>();// ͨ�������ҵ���
	private final Map<String, Map<String, Float>> intimacyFriend = new HashMap<String, Map<String, Float>>();// �洢����˼�����ܶ�
	private final Map<String, Float> superIntimacyFriend = new HashMap<String, Float>();// �洢�������˵����ܶ�
	private final List<String> temp1 = new ArrayList<String>();// �洢�������
	private final List<String> temp2 = new ArrayList<String>();// �洢�������
	private final List<Float> temp3 = new ArrayList<Float>();// �洢�������
	private socialFactory factory = new socialFactory();//��������

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

	// ��ո���յļ���
	public void clear()
	{
		physical.clear();// physical��ʵ�ǵ�һ��Ӧ��ɾ���ģ���Ϊ��ֱ�Ӿ����˹���ı�ţ�����ı�ž�����trackObject�ܷ�����ʹ��
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

	// �����˸����еķ���
	public boolean addTrackObject(socialE1 ob, int t)// ���ض����������һ�����壨����������λ�ã�
	{
		if (t < physical.size())
		{
			if (trackObject.containsKey(t))
			{
				trackObject.get(t).add(ob);// ���ù���������ӳ��
				objectTrack.put(ob, t);// �������嵽�����ӳ��
				angle.put(ob, 0.00);// ��ʼʱ�Ƕ�Ϊ0
				// �������ʱ��Ҳ��Ҫ��friend���и���
				friend.put(ob.getName(), ob);
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

	// �����˸����еķ���
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
					i--;// ɾ��һ��Ԫ��֮�󣬺����Ԫ�ػ���ǰ�ƶ�
				}
			}
		}
//			if (relationship.containsKey(temp))
//			{
//				relationship.remove(temp);// ɾ�������ͬʱ������ϵ�����ͬ��������Ĺ�ϵҲ��Ҫ��ɾ��
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
//				relationship.remove(temp);// ɾ�������������������Ĺ�ϵ
//			}
//			if (LErelationship.contains(temp))
//			{
//				LErelationship.remove(temp);
//			}
		// } // ���岻�ٳ����ڸù���У����ﾶ�������൱�ڳ���
//		trackObject.remove(number);// ɾ������������ӳ��
		System.out.println(trackObject.size());
		clear();// ����йؼ���(�ؼ�����)
		creatingTrack();// �ؽ����
		System.out.println("Succeed!");
	}

	// �����˸����еķ���
	public boolean deleteTrackObject(socialE1 ob, int t)// ���ض������ɾ��һ�����壨����������λ�ã�
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
							i--;// ɾ��һ��Ԫ��֮�󣬲������˳�����Ϊ��ֹһ��socialTie�ﺬ����
						}
					}
					clear();// ����йؼ���(�ؼ�����)
					creatingTrack();// �ؽ����
					return true;
				}
			}
			// //ɾ����ϵһ��Ҫɾ�ĳ���
//					trackObject.get(t).remove(ob);// ɾ������������ӳ��
//					if(objectTrack.containsKey(ob))
//					{
//						objectTrack.remove(ob, t);// ɾ�����嵽�����ӳ��
//					}
//					if (angle.containsKey(ob))
//					{
//						angle.remove(ob);// ɾ�����嵽�Ƕȵ�ӳ��
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
//						relationship.remove(ob);// ɾ�������������������Ĺ�ϵ
//					}
//					if (LErelationship.contains(ob))
//					{
//						LErelationship.remove(ob);// ɾ������������������Ĺ�ϵ
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
			// ob.setTrackNumber(t);// ���ù�����

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
		if (m.find())// ֻ��findһ��
		{
			System.out.println("find central!");
			String word1 = m.group(1);
			String c1 = m.group(2);
			String c2 = m.group(3);
			String c3 = m.group(4);
			String ws2 = m.group(5);
			String c4 = m.group(6);
			String word2 = m.group(7);
			String c5 = m.group(8);// ����

			String int1 = m.group(9);
			String c6 = m.group(10);
			String w2 = m.group(11);// ����
			String c7 = m.group(12);
			String c8 = m.group(14);// �Ա�
			central = factory.manufactureL(c5, Integer.parseInt(w2), c8.charAt(0));// �������ĵ�����
		}
//		new socialL1(c5, Integer.parseInt(w2), c8.charAt(0))
		// Friend����˲�һ�������ĵ����й�ϵ
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
		while (fm.find())// ��ֹƥ��һ��
		{
			System.out.println("find friend!");
			String fword1 = fm.group(1);
			String fws1 = fm.group(2);
			String fc1 = fm.group(3);
			String fc2 = fm.group(4);
			String fc3 = fm.group(5);
			String fws2 = fm.group(6);
			String fc4 = fm.group(7);
			String falphanum1 = fm.group(8);// ����

			String fc5 = fm.group(9);
			String fint1 = fm.group(10);

			String ffc6 = fm.group(11);// ����
			String fw1 = fm.group(12);

			String fc7 = fm.group(14);// �Ա�

			socialE1 tempFriend = factory.manufactureE(falphanum1, Integer.parseInt(ffc6), fc7.charAt(0));
//					new socialE1(falphanum1, Integer.parseInt(ffc6), fc7.charAt(0));
			friend.put(falphanum1, tempFriend);// ͨ�������ҵ��ˣ���ʼ��friendӳ��
			// socialNetWork��д�˸����еķ���
			relationship.put(tempFriend, new HashSet<socialE1>());// ƥ���ͬʱ�ԡ���ϵ�����г�ʼ��
			intimacyFriend.put(falphanum1, new HashMap<String, Float>());// ��ʼ��ӳ��
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

		// �����whileѭ��ִ�����֮�󣬹��ϵͳ�ͽ���������
		// addLErelationship����������һ�����ϣ�����Ҫִ�У���addTrackObject��ִ��
		// addEErelationship��һ��ӳ�䣬ӳ���valueֵ����һ��ӳ�䣬�����ڹ������
		// superIntimacyFriend: �洢��һ�����ϵ�������������֮��Ĺ�ϵ���Լ����ܶ�
		// intimacyFriend�������ڹ�����壬�������������κι�ϵ������keyֵ������==addLErelationship��Ԫ�ص�����==����ϵ���������
		while (normalSm.find())
		{
			System.out.println("Find it!");
			String normalSalphanum1 = normalSm.group(8);// person1
			String normalSalphanum2 = normalSm.group(11);// person2
			String normalSfloat1 = normalSm.group(14);// ���ܶ�
			socialTie.add(new tie(normalSalphanum1, normalSalphanum2, Float.parseFloat(normalSfloat1)));
		}
	}
//			if ((normalSalphanum1.equals(central.getName())) || (normalSalphanum2.equals(central.getName())))
//			{
//
//				if (normalSalphanum1.equals(central.getName()))
//				{
//					// ���������һ������֮��Ĺ�ϵ�ǵ����
//					if (!(friend.containsKey(normalSalphanum2)))
//					{
//						System.out.println("System error!");
//						return;
//					}
//					socialE1 person = friend.get(normalSalphanum2);
//					if (!objectTrack.containsKey(person))
//					{
//						System.out.println("Central!");
//						addTrackObject(person, 0);// �Ѿ���objectTrack�м�����ӳ��
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//					}
//					else
//					{
//						System.out.println("Central!");
//						transit(person, 0);// �Ѿ���objectTrack�и�����ӳ��
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//					}
//					// addLErelationship(friend.get(normalSalphanum2));
//					// addTrackObject(friend.get(normalSalphanum2), 0);// ��֪���壬�Ϳ���֪�������ڵĹ��
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
//						addTrackObject(person, 0);// �Ѿ���objectTrack�м�����ӳ��
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum1, Float.parseFloat(normalSfloat1));
//					}
//					else
//					{
//						System.out.println("Central!");
//						transit(person, 0);// �Ѿ���objectTrack�и�����ӳ��
//						addLErelationship(person);
//						superIntimacyFriend.put(normalSalphanum1, Float.parseFloat(normalSfloat1));
//					}
//					// addLErelationship(friend.get(normalSalphanum1));
//					// addTrackObject(friend.get(normalSalphanum1), 0);// ��֪���壬�Ϳ���֪�������ڵĹ��
//				}
//			}
//			else
//			{
//				System.out.println("n!");
//				// ���ܶ�ӳ��
////				intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
////				intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
////				// ��ϵӳ��
////				addEErelationship(friend.get(normalSalphanum2), friend.get(normalSalphanum1));
////				System.out.println("do its!");//���ϴ���û������
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
//					// ���ܶ�ӳ��
//					intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//					intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//					// ��ϵӳ��
//					addEErelationship(person2, person1);
//
//				}
//
//				// ���������else-if����Ǵ���˫���������Ҫ���������ʱ��ɾȥһ����֧����
//				else if (objectTrack.containsKey(person1) && (!objectTrack.containsKey(person2)))
//				{
//					if ((physical.size() - 1) > objectTrack.get(person1))
//					{
//						System.out.println("EE!");
//						addTrackObject(person2, objectTrack.get(person1) + 1);
//						// ���ܶ�ӳ��
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//						// ��ϵӳ��
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
////						// ���ܶ�ӳ��
////						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
////						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
////						// ��ϵӳ��
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
//						// ���ܶ�ӳ��
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//						// ��ϵӳ��
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
////						// ���ܶ�ӳ��
////						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
////						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
////						// ��ϵӳ��
////						addEErelationship(person2, person1);
//
//					}
//				}
//				else// �������嶼���ڹ����
//				{
//					// ��ʱ������ӳ��洢��Щ�����ֵ����Ϊkeyֵ�����ظ�
//					temp1.add(normalSalphanum1);
//					temp2.add(normalSalphanum2);
//					temp3.add(Float.parseFloat(normalSfloat1));
//					System.out.println("do its!");// ���ϴ���û������
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
//				// ���ܶ�ӳ��
//				intimacyFriend.get(t1).put(t2, ft3);
//				intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//				// ��ϵӳ��
//				addEErelationship(person2, person1);
//				// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
//			}
//
//			// ���������else-if����Ǵ���˫���������Ҫ���������ʱ��ɾȥһ����֧����
//			else if (objectTrack.containsKey(person1))
//			{
//				if ((physical.size() - 1) > objectTrack.get(person1))
//				{
//
//					System.out.println("EE!");
//					addTrackObject(person2, objectTrack.get(person1) + 1);
//					// ���ܶ�ӳ��
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//					// ��ϵӳ��
//					addEErelationship(person2, person1);
//					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
//				}
//				else
//				{
//					System.out.println("EEADD!");
//					addTrack();
//					addTrackObject(person2, objectTrack.get(person1) + 1);
//					// ���ܶ�ӳ��
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//					// ��ϵӳ��
//					addEErelationship(person2, person1);
//					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
//				}
//			}
//			else if (objectTrack.containsKey(person2))
//			{
//				if ((physical.size() - 1) > objectTrack.get(person2))
//				{
//					System.out.println("EE!");
//					addTrackObject(person1, objectTrack.get(person2) + 1);
//					// ���ܶ�ӳ��
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//					// ��ϵӳ��
//					addEErelationship(person2, person1);
//					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
//				}
//				else
//				{
//					System.out.println("EEADD!");
//					addTrack();
//					addTrackObject(person1, objectTrack.get(person2) + 1);
//					// ���ܶ�ӳ��
//					intimacyFriend.get(t1).put(t2, ft3);
//					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//					// ��ϵӳ��
//					addEErelationship(person2, person1);
//					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
//				}
//			}
//			// ȫ������֮���������廹���ڹ����
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
		System.out.println("SocialTie�Ĵ�С�ǣ�" + socialTie.size());
//		Iterator<String> iterator = socialTie.keySet().iterator();
		addTrack();// ������һ������������罻������һ����û�У�Ҳ��Ĭ�Ͻ���һ�����
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
					// ���������һ������֮��Ĺ�ϵ�ǵ����
					if (!(friend.containsKey(normalSalphanum2)))
					{
						System.out.println("The friend map doesn't contains it!");
						return;
					}
					socialE1 person = friend.get(normalSalphanum2);
					if (!objectTrack.containsKey(person))
					{
						System.out.println("Central!");
						addTrackObject(person, 0);// �Ѿ���objectTrack�м�����ӳ��
						addLErelationship(person);
						superIntimacyFriend.put(normalSalphanum2, normalSfloat1);
					}
					else
					{
						System.out.println("Central!");
						transit(person, 0);// �Ѿ���objectTrack�и�����ӳ��
						addLErelationship(person);
						superIntimacyFriend.put(normalSalphanum2, normalSfloat1);
					}
					// addLErelationship(friend.get(normalSalphanum2));
					// addTrackObject(friend.get(normalSalphanum2), 0);// ��֪���壬�Ϳ���֪�������ڵĹ��
				}
				else
				{
					if (!(friend.containsKey(normalSalphanum1)))
					{
						System.out.println("The friend map doesn't contains it!");
						return;
					}
					socialE1 person = friend.get(normalSalphanum1);
					if (!objectTrack.containsKey(person))
					{
						System.out.println("Central!");
						addTrackObject(person, 0);// �Ѿ���objectTrack�м�����ӳ��
						addLErelationship(person);
						superIntimacyFriend.put(normalSalphanum1, normalSfloat1);
					}
					else
					{
						System.out.println("Central!");
						transit(person, 0);// �Ѿ���objectTrack�и�����ӳ��
						addLErelationship(person);
						superIntimacyFriend.put(normalSalphanum1, normalSfloat1);// superIntimacyFriend���������½������ϵͳ��ʱ�����㣬��Ϊ����û���ڶ��ļ���ʱ���ʼ��
					}
					// addLErelationship(friend.get(normalSalphanum1));
					// addTrackObject(friend.get(normalSalphanum1), 0);// ��֪���壬�Ϳ���֪�������ڵĹ��
				}
			}
			else
			{
				System.out.println("n!");
				// ���ܶ�ӳ��
//				intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//				intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//				// ��ϵӳ��
//				addEErelationship(friend.get(normalSalphanum2), friend.get(normalSalphanum1));
//				System.out.println("do its!");//���ϴ���û������
				if (!(friend.containsKey(normalSalphanum1) && friend.containsKey(normalSalphanum2)))
				{
					System.out.println("The friend map doesn't contains them!");
					return;
				}
				socialE1 person1 = friend.get(normalSalphanum1);
				socialE1 person2 = friend.get(normalSalphanum2);
				if (objectTrack.containsKey(person1) && objectTrack.containsKey(person2))
				{
					System.out.println("EE!");
					// ���ܶ�ӳ��
					intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, normalSfloat1);
					intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, normalSfloat1);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
					// ��ϵӳ��
					addEErelationship(person2, person1);// ���õ��Ǹ����еķ���

				}

				// ���������else-if����Ǵ���˫���������Ҫ���������ʱ��ɾȥһ����֧����
				else if (objectTrack.containsKey(person1) && (!objectTrack.containsKey(person2)))
				{
					if ((physical.size() - 1) > objectTrack.get(person1))
					{
						System.out.println("EE!");
						addTrackObject(person2, objectTrack.get(person1) + 1);
						// ���ܶ�ӳ��
						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, normalSfloat1);
						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, normalSfloat1);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
						// ��ϵӳ��
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
//						// ���ܶ�ӳ��
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//						// ��ϵӳ��
//						addEErelationship(person2, person1);

					}
				}
				else if ((objectTrack.containsKey(person2)) && (!objectTrack.containsKey(person1)))
				{
					if ((physical.size() - 1) > objectTrack.get(person2))
					{
						System.out.println("EE!");
						addTrackObject(person1, objectTrack.get(person2) + 1);
						// ���ܶ�ӳ��
						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, normalSfloat1);
						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, normalSfloat1);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
						// ��ϵӳ��
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
//						// ���ܶ�ӳ��
//						intimacyFriend.get(normalSalphanum1).put(normalSalphanum2, Float.parseFloat(normalSfloat1));
//						intimacyFriend.get(normalSalphanum2).put(normalSalphanum1, Float.parseFloat(normalSfloat1));// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
//						// ��ϵӳ��
//						addEErelationship(person2, person1);

					}
				}
				else// �������嶼���ڹ����
				{
					// ��ʱ������ӳ��洢��Щ�����ֵ����Ϊkeyֵ�����ظ�
					temp1.add(normalSalphanum1);
					temp2.add(normalSalphanum2);
					temp3.add(normalSfloat1);
					System.out.println("do its!");// ���ϴ���û������
				}

			}
		}

		// ��ʣ�ಿ�ֽ��д���
		for (int i = 0; i < temp1.size(); i++)
		{
			String t1 = temp1.get(i);
			String t2 = temp2.get(i);
			Float ft3 = temp3.get(i);
			socialE1 person1 = friend.get(t1);
			socialE1 person2 = friend.get(t2);
			if (objectTrack.containsKey(person1) && objectTrack.containsKey(person2))
			{
				System.out.println("EE!");
				// addTrackObject(friend.get(t2), objectTrack.get(t1) + 1);
				// ���ܶ�ӳ��
				intimacyFriend.get(t1).put(t2, ft3);
				intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
				// ��ϵӳ��
				addEErelationship(person2, person1);
				// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
			}

			// ���������else-if����Ǵ���˫���������Ҫ���������ʱ��ɾȥһ����֧����
			else if (objectTrack.containsKey(person1))
			{
				if ((physical.size() - 1) > objectTrack.get(person1))
				{

					System.out.println("EE!");
					addTrackObject(person2, objectTrack.get(person1) + 1);
					// ���ܶ�ӳ��
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
					// ��ϵӳ��
					addEErelationship(person2, person1);
					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
				}
				else
				{
					System.out
							.println("EEADDppppppppppppppppppppppppppppppppppppppppppppppppppppp!" + person2.getName());
					addTrack();
					addTrackObject(person2, objectTrack.get(person1) + 1);
					// ���ܶ�ӳ��
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
					// ��ϵӳ��
					addEErelationship(person2, person1);
					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
				}
			}
			else if (objectTrack.containsKey(person2))
			{
				if ((physical.size() - 1) > objectTrack.get(person2))
				{
					System.out.println("EE!");
					addTrackObject(person1, objectTrack.get(person2) + 1);
					// ���ܶ�ӳ��
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
					// ��ϵӳ��
					addEErelationship(person2, person1);
					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
				}
				else
				{
					System.out.println("EEADD!");
					addTrack();
					addTrackObject(person1, objectTrack.get(person2) + 1);
					// ���ܶ�ӳ��
					intimacyFriend.get(t1).put(t2, ft3);
					intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
					// ��ϵӳ��
					addEErelationship(person2, person1);
					// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
				}
			}
			// ȫ������֮���������廹���ڹ����
			else
			{
				System.out.println("do its again!");
			}
		}

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
						// ���ܶ�ӳ��
						intimacyFriend.get(t1).put(t2, ft3);
						intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
						// ��ϵӳ��
						addEErelationship(person2, person1);
						// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
					}
					else
					{
						System.out
								.println("EEADDppppppppppppppppppppppppppppppppppppppppppppppppppppp!" + person2.getName());
						addTrack();
						addTrackObject(person2, objectTrack.get(person1) + 1);
						// ���ܶ�ӳ��
						intimacyFriend.get(t1).put(t2, ft3);
						intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
						// ��ϵӳ��
						addEErelationship(person2, person1);
						// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
					}
				}
				else if (objectTrack.containsKey(person2)&& !objectTrack.containsKey(person1))
				{
					if ((physical.size() - 1) > objectTrack.get(person2))
					{
						System.out.println("EE!");
						addTrackObject(person1, objectTrack.get(person2) + 1);
						// ���ܶ�ӳ��
						intimacyFriend.get(t1).put(t2, ft3);
						intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
						// ��ϵӳ��
						addEErelationship(person2, person1);
						// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
					}
					else
					{
						System.out.println("EEADD!");
						addTrack();
						addTrackObject(person1, objectTrack.get(person2) + 1);
						// ���ܶ�ӳ��
						intimacyFriend.get(t1).put(t2, ft3);
						intimacyFriend.get(t2).put(t1, ft3);// �������嶼�ڹ���ϣ����üӹ���������ܶȾͿ���
						// ��ϵӳ��
						addEErelationship(person2, person1);
						// addEERelationshipֻ�����һ�μ��ɣ������ڲ�������ι�ϵ
					}
				}
				else
				{
					System.out.println("sorry!");
				}
			}
		}
		checkRep();
	}

	// �����ܶ�
	public boolean intimacy(String name)
	{
		int potential = 0;
		// ǰ��������ÿ���˵����ֶ���һ��
		List<String> queue = new ArrayList<String>();// ��Ϊ��������
		Set<String> flag = new HashSet<String>();// ����Ѿ������ʹ�����
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
			Map<String, Float> maptemp = intimacyFriend.get(tempName);// ���������ĵ��Ǹ���
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
					if (maptemp.get(temp) > 0.1 && !flag.contains(temp))// ���ܶ�����Ҫ����û�б����ʹ�
					{
						potential++;
						flag.add(temp);// �����
						queue.add(temp);// ���
					}
				}
			}
			// �����Ĺ����а����������û�
		}
		System.out.println("You can meet " + (potential) + " friends indirectly.");
		return true;
	}

	// ���ӹ�ϵ�������ڹ�����壩�����½������ϵͳ
	public boolean addRelationship(String name1, String name2, float ini)// ����˱����ڹ����
	{
		// ͨ�����Ƶõ�ʵ��
		if (!friend.containsKey(name1) || !friend.containsKey(name2))
		{
			System.out.println("They are not in the orbit");
		}
		socialTie.add(new tie(name1, name2, ini));
		clear();
		creatingTrack();
		return true;
	}


	// ɾ����ϵ�������ڹ�����壩�����µ������ϵͳ
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
