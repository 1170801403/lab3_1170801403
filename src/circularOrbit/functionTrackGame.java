//��������ֱ�Ӷ�functionTrackGame���в�������functionTrackGame��trackGame���в�����trackGame˵����ֻ��һ�����ϵͳ
package circularOrbit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import abstractFactory.trackFactory;
import physicalObject.trackE1;
import centralObject.*;

public class functionTrackGame
{
	private int trackNumber;// ���������ֻ���������ļ��ã�����ʵ�־��幦�ܵ�ʱ������physical��ʵ�ʳ���Ϊ׼
	private int playType;
	private List<trackGame> groupTrackSystem = new ArrayList<trackGame>();// �洢ÿһ�����
	private Set<trackE1> athlete = new HashSet<trackE1>();// �����洢���е�����
	private List<trackE1> athleteForRandom = new ArrayList<trackE1>();// �����ϱ�Ϊ�б����ڰ��ű�������
	private Map<trackE1, Integer> objectGroup = new HashMap<trackE1, Integer>();// ���嵽����ӳ��
	private trackFactory factory = new trackFactory();

	public int getTrackNumber()
	{
		final int temp;// �û������ٸ���
		temp = trackNumber;
		return temp;
	}

	public List<trackGame> getGroupTrackSystem()
	{
		List<trackGame> temp = new ArrayList<trackGame>();
		int size = groupTrackSystem.size();
		for (int i = 0; i < size; i++)
		{
			// trackGame etemp = new trackGame();
			trackGame etemp = groupTrackSystem.get(i);// ֻ�ܱ�֤���ÿͻ��˶�trackGame
			temp.add(etemp);
		}
		return temp;
	}

	public Set<trackE1> getAthlete()
	{
		Set<trackE1> temp = new HashSet<trackE1>();
		Iterator<trackE1> iterator = athlete.iterator();
		while (iterator.hasNext())
		{
			trackE1 etemp = iterator.next();
			temp.add(etemp);// ��������ǲ��ɱ���󣬸Ĳ���
		}
		return temp;
	}

	public void creatingTrackFromFiles(String name)// ���ⲿ�ļ���ȡ���ݹ�����ϵͳ����
	{
		String txt = new String();
		String re1 = "(Athlete)"; // Word 1
		String re2 = "(\\s+)"; // White Space 1
		String re3 = "(:)"; // Any Single Character 1
		String re4 = "(:)"; // Any Single Character 2
		String re5 = "(=)"; // Any Single Character 3
		String re6 = "(\\s+)"; // White Space 2
		String re7 = "(<)"; // Any Single Character 4
		String re8 = "((?:[a-z][a-z]+))"; // Word 2,����
		String re9 = "(,)"; // Any Single Character 5
		String re10 = "(\\d+)"; // Integer Number 1�����
		String re11 = "(,)"; // Any Single Character 6
		String re12 = "((?:[a-z][a-z]+))"; // Word 3������
		String re13 = "(,)"; // Any Single Character 7
		String re14 = "(\\d+)"; // Integer Number 2������
		String re15 = "(,)"; // Any Single Character 8
		String re16 = "(\\d{1,2}\\.\\d{2}+)"; // Float 1����ѳɼ�
		String re17 = "(>)"; // Any Single Character 9

		try
		{
			txt = new String(Files.readAllBytes(Paths.get("txt/" + name + "txt")));
		}
		catch (IOException e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10 + re11 + re12 + re13
				+ re14 + re15 + re16 + re17, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		while (m.find())
		{
			String word1 = m.group(8);// ����
			String int1 = m.group(10);// ���
			String word2 = m.group(12);// ����
			String int2 = m.group(14);// ����
			String float1 = m.group(16);// ��ѳɼ�
			trackE1 temp = factory.manufactureE(word1, Integer.parseInt(int1), word2, Integer.parseInt(int2),
					Float.parseFloat(float1));
			athlete.add(temp);// parseInt��parseFloat���ǹ��쳣��
		}

		String re18 = "(Game)"; // Word 1
		String re19 = "(\\s+)"; // White Space 1
		String re20 = "(:)"; // Any Single Character 1
		String re21 = "(:)"; // Any Single Character 2
		String re22 = "(=)"; // Any Single Character 3
		String re23 = "(\\s+)"; // White Space 2
		String re24 = "(\\d+)"; // Integer Number 1

		Pattern p1 = Pattern.compile(re18 + re19 + re20 + re21 + re22 + re23 + re24,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m1 = p1.matcher(txt);
		while (m1.find())
		{
			String int11 = m1.group(7);
			playType = Integer.parseInt(int11);// �õ���������
		}

		String re25 = "(NumOfTracks)"; // Word 1
		String re26 = "(\\s+)"; // White Space 1
		String re27 = "(:)"; // Any Single Character 1
		String re28 = "(:)"; // Any Single Character 2
		String re29 = "(=)"; // Any Single Character 3
		String re30 = "(\\s+)"; // White Space 2
		String re31 = "(\\d+)"; // Integer Number 1

		Pattern p2 = Pattern.compile(re25 + re26 + re27 + re28 + re29 + re30 + re31,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m2 = p2.matcher(txt);
		while (m2.find())
		{
			String int12 = m2.group(7);
			trackNumber = Integer.parseInt(int12);// �õ������Ŀ
		}

	}
	// ���ļ��Ĺ����У���ʼ���������˶�Ա���󣬵õ��˱������࣬�����Ŀ

	public boolean addTrack(int groupNumebr)
	{
		if (groupNumebr >= groupTrackSystem.size())
		{
			System.out.println("The group is out of bound!");
			return false;
		}
		groupTrackSystem.get(groupNumebr).addTrack();
		return true;
	}

	public boolean deleteTrack(int groupNumebr, int trackNumber)
	{
		if (groupNumebr >= groupTrackSystem.size())
		{
			System.out.println("The group is out of bound!");
			return false;
		}
		groupTrackSystem.get(groupNumebr).deleteTrack(trackNumber);
		return true;
	}

	public boolean addObject(int groupNumebr, int trackNumebr, trackE1 ob)
	{
		if (groupNumebr >= groupTrackSystem.size())
		{
			System.out.println("The group is out of bound!");
			return false;
		}
		if(!groupTrackSystem.get(groupNumebr).trackObject.containsKey(trackNumebr))
		{
			System.out.println("The track has been deleted!");
			return false;
		}
		if(groupTrackSystem.get(groupNumebr).trackObject.get(trackNumebr).size() != 0)// �涨һ�����������ֻ��һλ�˶�Ա
		{
			System.out.println("There is already an athlete in the track!");
			return false;
		}
		groupTrackSystem.get(groupNumebr).addTrackObject(ob, trackNumebr);
		return true;
	}

	public boolean deleteObject(int groupNumebr, int trackNumber)
	{
		if (groupNumebr >= groupTrackSystem.size())
		{
			System.out.println("The group is out of bound!");
			return false;
		}
		if(!groupTrackSystem.get(groupNumebr).getTrackObject().containsKey(trackNumber))
		{
			System.out.println("The track doesn't exist!");
			return false;
		}
		Iterator<trackE1> iterator = groupTrackSystem.get(groupNumebr).getTrackObject().get(trackNumber).iterator();
		while (iterator.hasNext())
		{
			trackE1 delete = iterator.next();
			groupTrackSystem.get(groupNumebr).deleteTrackObject(delete, trackNumber);
			return true;
		}

		System.out.println("the track is empty!");
		return false;

	}

	public boolean autoCompetitionA()
	{
		Random rand = new Random();
		Iterator<trackE1> iterator = athlete.iterator();
		while (iterator.hasNext()) // �������е�Ԫ��ת�Ƶ��б���
		{
			athleteForRandom.add(iterator.next());// Ӧ���Ǵ���ַ����
		}
		int total = athlete.size();
		System.out.println(trackNumber);
		int remainder = total % trackNumber;// ���һ��Ӧ�÷ֶ�����
		int group = total / trackNumber;// Ӧ�÷ֶ�����
		if (remainder == 0)
		{
			// trackE1 groupPlayer[][] = new trackE1[group][trackNumber];
			for (int i = 0; i < group; i++)// groupҲ��ͨ��physical.size()���������
			{
				trackGame temp = new trackGame();
				for (int k = 0; k < trackNumber; k++)
				{
					temp.addTrack();
				}
				groupTrackSystem.add(temp);
				for (int j = 0; j < trackNumber; j++)
				{
					int random = rand.nextInt(athleteForRandom.size());
					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
					// ���˶�Ա�������
					// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
					// addTrackObject(athleteForRandom.get(random), j);// ��������������
					// groupPlayer[i][j] = athleteForRandom.get(random);
					objectGroup.put(athleteForRandom.get(random), i);// ���˶�Ա�������
					groupTrackSystem.get(i).addTrackObject(athleteForRandom.get(random), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
					athleteForRandom.remove(random);// ��������ֻ��Զ���ǰ�ƶ�
				}
			}
		}
		else
		{

			// trackE1 groupPlayer[][] = new trackE1[group+1][trackNumber];
			for (int i = 0; i < group; i++)// �Ȱ�֮ǰ�ĵ��ζ�����
			{
				trackGame temp = new trackGame();
				for (int k = 0; k < trackNumber; k++)
				{
					temp.addTrack();
				}
				groupTrackSystem.add(temp);
				for (int j = 0; j < trackNumber; j++)
				{
					int random = rand.nextInt(athleteForRandom.size());
					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
					// Ϊ�˶�Ա�������
					// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
					// addTrackObject(athleteForRandom.get(random), j);// ��������������
					// groupPlayer[i][j] = athleteForRandom.get(random);//��֤��ͬһ���˶�Աֻ����һ�������
					objectGroup.put(athleteForRandom.get(random), i);// ���˶�Ա�������
					groupTrackSystem.get(i).addTrackObject(athleteForRandom.get(random), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
					athleteForRandom.remove(random);// ��������ֻ��Զ���ǰ�ƶ�
				}
			}
			trackGame temp = new trackGame();
			for (int m = 0; m < trackNumber; m++)
			{
				temp.addTrack();
			}
			groupTrackSystem.add(temp);// ��ʱgroupTrackSystem��size��group+1
			for (int j = 0; j < remainder; j++)
			{
				int random = rand.nextInt(athleteForRandom.size());// 0��athleteForRandom.size()-1֮��������
				// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));
				// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
				// addTrackObject(athleteForRandom.get(random), j);// ��������������
				objectGroup.put(athleteForRandom.get(random), group);// ���˶�Ա�������
				temp.addTrackObject(athleteForRandom.get(random), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
				athleteForRandom.remove(random);// ������˶�Ա����ǰ�ƶ�����֤ͬһ���˶�Աֻ�ܳ�����һ�������
			}
		}

		System.out.println("Succeed!");
		return true;
	}

	public boolean autoCompetitionB()// ���ݱ����ɼ�,��óɼ�������������
	{
		Iterator<trackE1> iterator = athlete.iterator();
		while (iterator.hasNext()) // �������е�Ԫ��ת�Ƶ��б���
		{
			athleteForRandom.add(iterator.next());// Ӧ���Ǵ���ַ����
		}
		Collections.sort(athleteForRandom, new Comparator<trackE1>()
		{
			public int compare(trackE1 p1, trackE1 p2)
			{
				// �Գɼ����н�������
				if (p1.getBest() > p2.getBest())
				{
					return -1;
				}
				if (p1.getBest() == p2.getBest())
				{
					return 0;
				}
				return 1;
			}
		});
		int total = athlete.size();
		System.out.println(trackNumber);
		int remainder = total % trackNumber;// ���һ��Ӧ�÷ֶ�����
		int group = total / trackNumber;// Ӧ�÷ֶ�����
		if (remainder == 0)
		{
			// trackE1 groupPlayer[][] = new trackE1[group][trackNumber];
			for (int i = 0; i < group; i++)// groupҲ��ͨ��physical.size()���������
			{
				trackGame temp = new trackGame();
				for (int k = 0; k < trackNumber; k++)
				{
					temp.addTrack();
				}
				groupTrackSystem.add(temp);
				int groupTrackNumber = 0;
				int finalGroupTrackNumebr = trackNumber - 1;
				for (int j = 0; j < trackNumber; j++)
				{
					// int random = rand.nextInt(athleteForRandom.size());
					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
					// ���˶�Ա�������
					// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
					// addTrackObject(athleteForRandom.get(random), j);// ��������������
					// groupPlayer[i][j] = athleteForRandom.get(random);
					int gradeNumber;
					if (j % 2 == 0)
					{
						gradeNumber = (i * trackNumber) + groupTrackNumber;
						groupTrackNumber++;
					}
					else
					{
						gradeNumber = (i * trackNumber) + finalGroupTrackNumebr;
						finalGroupTrackNumebr--;
					}
					objectGroup.put(athleteForRandom.get(gradeNumber), i);// ���˶�Ա�������
					groupTrackSystem.get(i).addTrackObject(athleteForRandom.get(gradeNumber), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
					// athleteForRandom.remove(random);// ��������ֻ��Զ���ǰ�ƶ�
				}
			}
		}
		else
		{

			// trackE1 groupPlayer[][] = new trackE1[group+1][trackNumber];
			for (int i = 0; i < group; i++)// �Ȱ�֮ǰ�ĵ��ζ�����
			{
				trackGame temp = new trackGame();
				for (int k = 0; k < trackNumber; k++)
				{
					temp.addTrack();
				}
				groupTrackSystem.add(temp);
				int groupTrackNumber = 0;
				int finalGroupTrackNumebr = trackNumber - 1;
				for (int j = 0; j < trackNumber; j++)
				{
					// int random = rand.nextInt(athleteForRandom.size());
					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
					// Ϊ�˶�Ա�������
					// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
					// addTrackObject(athleteForRandom.get(random), j);// ��������������
					// groupPlayer[i][j] = athleteForRandom.get(random);//��֤��ͬһ���˶�Աֻ����һ�������
					int gradeNumber;
					if (j % 2 == 0)
					{
						gradeNumber = (i * trackNumber) + groupTrackNumber;
						groupTrackNumber++;
					}
					else
					{
						gradeNumber = (i * trackNumber) + finalGroupTrackNumebr;
						finalGroupTrackNumebr--;
					}
					objectGroup.put(athleteForRandom.get(gradeNumber), i);// ���˶�Ա�������
					groupTrackSystem.get(i).addTrackObject(athleteForRandom.get(gradeNumber), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
					// athleteForRandom.remove(random);// ��������ֻ��Զ���ǰ�ƶ�
				}
			}
			trackGame temp = new trackGame();
			for (int m = 0; m < trackNumber; m++)
			{
				temp.addTrack();
			}
			groupTrackSystem.add(temp);
			int groupTrackNumber = 0;
			int finalGroupTrackNumebr = remainder - 1;
			for (int j = 0; j < remainder; j++)
			{
				// int random = rand.nextInt(athleteForRandom.size());//
				// 0��athleteForRandom.size()-1֮��������
				// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));
				// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
				// addTrackObject(athleteForRandom.get(random), j);// ��������������
				int gradeNumber;
				if (j % 2 == 0)
				{
					gradeNumber = (group * trackNumber) + groupTrackNumber;
					groupTrackNumber++;
				}
				else
				{
					gradeNumber = (group * trackNumber) + finalGroupTrackNumebr;
					finalGroupTrackNumebr--;
				}
				objectGroup.put(athleteForRandom.get(gradeNumber), group);// ���˶�Ա�������
				temp.addTrackObject(athleteForRandom.get(gradeNumber), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
				// athleteForRandom.remove(random);// ������˶�Ա����ǰ�ƶ�����֤ͬһ���˶�Աֻ�ܳ�����һ�������
			}
		}
//		int groupNumber = 0;
//		int i1 = 0;
//		int j = 0;
//		int j1 = trackNumber - 1;// ��ʵ�ʹ�����Ϊ׼,��һ�����֮����ô���أ����绻һ������������֮ǰ����������һ������
//		trackGame temp = new trackGame();
//		System.out.println(trackNumber);
//		for (int k = 0; k < trackNumber; k++)
//		{
//			temp.addTrack();
//		}
//		groupTrackSystem.add(temp);
//		for (int i = 0; i < athleteForRandom.size(); i++)// �óɼ�������˶�Ա������������м����,�ɼ��ɲ�ý��н�������
//		{
//			if (j > j1)
//			{
//				trackGame temp1 = new trackGame();
//				for (int k = 0; k < trackNumber; k++)
//				{
//					temp1.addTrack();
//				}
//				groupTrackSystem.add(temp1);
//				j = 0;
//				j1 = trackNumber - 1;
//				i1 = 0;
//				groupNumber++;
//			}
//			else
//			{
//				if (i1 % 2 == 0)// i1��ʾ�ڼ��μӹ��
//				{
//					objectGroup.put(athleteForRandom.get(i), groupNumber);// ���˶�Ա�������
//					groupTrackSystem.get(groupNumber).addTrackObject(athleteForRandom.get(i), j);// ��������������,��ʱ�����˽Ƕ�
//					j++;
//					i1++;
//				}
//				else
//				{
//					objectGroup.put(athleteForRandom.get(i), groupNumber);// ���˶�Ա�������
//					groupTrackSystem.get(groupNumber).addTrackObject(athleteForRandom.get(i), j1);// ��������������,��ʱ�����˽Ƕ�
//					j1--;
//					i1++;
//				}
//			}
//
//		}
		System.out.println("Succeed!");
		return true;
	}

	public boolean groupAdjust(int group1, int number1, int group2, int number2)// ������������ͬ���Ͳ�ͬ���
	{
		// ��ͬ���
//		if (player1.getGroup() == player2.getGroup())
//		{
//			if (player1.equals(player2))// �˴���Ҫ����ʵ��equals����
//			{
//				System.out.println("They are the same player");
//				return false;
//			}
//			int group1 = player1.getGroup();
//			int group2 = player2.getGroup();
//			player1.setGroup(group2);
//			player2.setGroup(group1);
//			//�Ȼ�����ٻ����
//			int track1 = player1.getTrackNumber();
//			int track2 = player2.getTrackNumber();
//			physical.get(track2);
//			deleteTrackObject(player1, track1);
//			//��̾�ž��棺
//			//Type safety: The method deleteTrackObject(E1, int) belongs to the raw type ConcreteCircularObject. References to generic type ConcreteCircularObject<L,E> 
//			 //should be parameterized
//			//���Ͱ�ȫ��:����deleteTrackObject(E1, int)����ԭʼ����ConcreteCircularObject�����õ���������ConcreteCircularObject<L,E>
//			//Ӧ���ǲ�����
//			deleteTrackObject(player2, track2);
//			addTrackObject(player1, track2);
//			addTrackObject(player2, track1);
//			return true;
//		}
//		//ͬ���
//		else
//		{
//			if (player1.equals(player2))// �˴���Ҫ����ʵ��equals����
//			{
//				System.out.println("They are the same player");
//				return false;
//			}
//		}
//		int track1 = player1.getTrackNumber();
//		int track2 = player2.getTrackNumber();
//		
//		deleteTrackObject(player1, track1);
//		deleteTrackObject(player2, track2);
//		addTrackObject(player1, track2);
//		addTrackObject(player2, track1);
////		player1.setTrackNumber(track2);
////		player2.setTrackNumber(track1);
//	    return true;
		// ͬ���
//		if (player1.equals(player2))
//		{
//			System.out.println("They are the same person!");
//			return false;
//		}
//		if (!athlete.contains(player1))
//		{
//			System.out.println("player1 isn't in the game!");
//			return false;
//		}
//		if (!athlete.contains(player2))
//		{
//			System.out.println("player2 isn't in the game!");
//			return false;
//		}
		if (group1 == group2 && number1 == number2)
		{
			System.out.println("They are the same player");
			return false;
		}
		if ((groupTrackSystem.get(group1).getTrackObject().get(number1).size() != 0)
				&& (groupTrackSystem.get(group2).getTrackObject().get(number2).size() != 0))
		{
			trackE1 f = new trackE1("a", 1, "b", 2, 4);
			trackE1 s = new trackE1("a", 2, "b", 2, 4);
			Iterator<trackE1> iterator = groupTrackSystem.get(group1).getTrackObject().get(number1).iterator();
			if (iterator.hasNext())
			{
				System.out.println("f");
				f = iterator.next();
			}
			Iterator<trackE1> iterator2 = groupTrackSystem.get(group2).getTrackObject().get(number2).iterator();
			if (iterator2.hasNext())
			{
				System.out.println("s");
				s = iterator2.next();
			}
			if (group1 == group2)
			{
//				int i = objectGroup.get(player1);
//				int track1 = groupTrackSystem.get(i).objectTrack.get(player1);
//				System.out.println("Play1's track numebr is:"+track1);
//				int track2 = groupTrackSystem.get(i).objectTrack.get(player2);
//				System.out.println("Play2's track numebr is:"+track2);
				System.out.println("number1:" + number1);
				System.out.println("number2:" + number2);
				groupTrackSystem.get(group1).transit(f, number2);// �ǶȲ���
				groupTrackSystem.get(group1).transit(s, number1);
				System.out.println("Succeed!");
				return true;
			}
			// ��ͬ���
			else
			{
//				int group1 = objectGroup.get(player1);
//				int group2 = objectGroup.get(player2);
//				objectGroup.put(player1, group2);
//				objectGroup.put(player2, group1);
//				int track1 = groupTrackSystem.get(group1).objectTrack.get(player1);
//				int track2 = groupTrackSystem.get(group2).objectTrack.get(player2);// �ǶȲ���
				groupTrackSystem.get(group1).deleteTrackObject(f, number1);
				groupTrackSystem.get(group2).deleteTrackObject(s, number2);
				groupTrackSystem.get(group1).addTrackObject(s, number1);
				groupTrackSystem.get(group2).addTrackObject(f, number2);
				// ���ʱ������transit��transitֻ�����ͬһ�����ϵͳ��������������ͬ�Ĺ��ϵͳ
//				transit(player1, track2);
//				transit(player2, track1);
				System.out.println("Succeed!");
				return true;
			}
		}
		else
		{
			System.out.println("faliure!");
			return false;
		}

	}

	// ɾ�������ɾ��ĳ��trackGame�е�ĳ��������������Ϊ��ѡ�ֳ���
//	public void deleteTrack(int t)
//	{
//		if(t>=trackNumber)
//		{
//			System.out.println("The track is out of bound!");
//		}
//	}

}
