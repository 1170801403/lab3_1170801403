//mutable
package circularOrbit;

//��©�㣺�û�ɾ��һ�������������һ�����ʱ��������½��г�ʼ��(�ѽ��)
//��©�㣺ÿ�������û�м�¼����ϵ����壬��ֵ��ô����
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.L1;
import physicalObject.E1;
import physicalObject.trackE1;
import track.*;

//parameter n. ������ϵ��������
//����������п����þ����L,E
public class trackGame extends ConcreteCircularObject<L1, trackE1>// ConcreteCircularObject������Բ�д�κζ�����Ҫд�Ļ�ֻ��д<L,E>
{
//	private final int trackNumber;// ���������ֻ���������ļ��ã�����ʵ�־��幦�ܵ�ʱ������physical��ʵ�ʳ���Ϊ׼
//	private final int playType;// ��������
//	public int getTrackNumber()
//	{
//		return trackNumber;
//	}
//	public void setTrackNumber(int trackNumber)
//	{
//		this.trackNumber = trackNumber;
//	}
//	public int getPlayType()
//	{
	
//		return playType;
//	}
//	public void setPlayType(int playType)
//	{
//		this.playType = playType;
//	}
	
	
	//�������ݲ�Ӧ�ó����ڱ�������
	//private Set<trackE1> athlete = new TreeSet<trackE1>();// �����洢���е�����
	//private List<trackE1> athleteForRandom = new ArrayList<trackE1>();// �����ϱ�Ϊ�б����ڰ��ű�������
	//private Map<trackE1, Integer> objectGroup = new HashMap<trackE1, Integer>();// ���嵽����ӳ��
	//private Map<trackE1, Double> sithaTrackE1 = new HashMap<trackE1, Double>();// ���嵽�Ƕȵ�ӳ��

	
	//�������ݲ�Ӧ�ó����ڱ�������
//	public void checkRep()
//	{
//		// TODO Auto-generated method stub
//		assert (4 <= trackNumber && trackNumber <= 10);
//		if (athlete.size() % trackNumber == 0)
//		{
//			for (int i = 0; i < trackNumber; i++)
//			{
//				assert trackObject.get(i).size() == athlete.size() / trackNumber;
//			}
//		}
//		else
//		{
//			for (int i = 0; i < trackNumber-1; i++)
//			{
//				assert trackObject.get(i).size() == athlete.size() / trackNumber;
//			}
//			int j = trackNumber-1;
//			assert trackObject.get(j).size() == athlete.size() % trackNumber;
//		}
//		//����Ҫ���ͬһ���˶�Աֻ������һ�������
//	}

//	//������д�����еķ�����@Override���鷵��ֵ���������������Ƿ�һ��
//	@Override
//	public void creatingTrackFromFiles(String name)// ���ⲿ�ļ���ȡ���ݹ�����ϵͳ����
//	{
//		String txt = new String();
//		String re1 = "(Athlete)"; // Word 1
//		String re2 = "(\\s+)"; // White Space 1
//		String re3 = "(:)"; // Any Single Character 1
//		String re4 = "(:)"; // Any Single Character 2
//		String re5 = "(=)"; // Any Single Character 3
//		String re6 = "(\\s+)"; // White Space 2
//		String re7 = "(<)"; // Any Single Character 4
//		String re8 = "((?:[a-z][a-z]+))"; // Word 2,����
//		String re9 = "(,)"; // Any Single Character 5
//		String re10 = "(\\d+)"; // Integer Number 1�����
//		String re11 = "(,)"; // Any Single Character 6
//		String re12 = "((?:[a-z][a-z]+))"; // Word 3������
//		String re13 = "(,)"; // Any Single Character 7
//		String re14 = "(\\d+)"; // Integer Number 2������
//		String re15 = "(,)"; // Any Single Character 8
//		String re16 = "([+-]?\\d*\\.\\d+)(?![-+0-9\\.])"; // Float 1����ѳɼ�
//		String re17 = "(>)"; // Any Single Character 9
//
//		try
//		{
//			txt = new String(Files.readAllBytes(Paths.get("txt/"+name+"txt")));
//		}
//		catch (IOException e)
//		{
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10 + re11 + re12 + re13
//				+ re14 + re15 + re16 + re17, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//		Matcher m = p.matcher(txt);
//		while (m.find())
//		{
//			String word1 = m.group(8);// ����
//			String int1 = m.group(10);// ���
//			String word2 = m.group(12);// ����
//			String int2 = m.group(14);// ����
//			String float1 = m.group(16);// ��ѳɼ�
//			trackE1 temp = new trackE1(word1, Integer.parseInt(int1), word2, Integer.parseInt(int2),
//					Float.parseFloat(float1));
//			athlete.add(temp);// parseInt��parseFloat���ǹ��쳣��
//			//sithaTrackE1.put(temp, 0.00);// Ĭ��λ�ڽǶ�0
//			// ���ü��ϵĻ�����
//		}
//
//		String re18 = "(Game)"; // Word 1
//		String re19 = "(\\s+)"; // White Space 1
//		String re20 = "(:)"; // Any Single Character 1
//		String re21 = "(:)"; // Any Single Character 2
//		String re22 = "(=)"; // Any Single Character 3
//		String re23 = "(\\s+)"; // White Space 2
//		String re24 = "(\\d+)"; // Integer Number 1
//
//		Pattern p1 = Pattern.compile(re18 + re19 + re20 + re21 + re22 + re23 + re24,
//				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//		Matcher m1 = p1.matcher(txt);
//		while (m1.find())
//		{
//			String int11 = m1.group(7);
//			playType = Integer.parseInt(int11);// �õ���������
//		}
//
//		String re25 = "(NumOfTracks)"; // Word 1
//		String re26 = "(\\s+)"; // White Space 1
//		String re27 = "(:)"; // Any Single Character 1
//		String re28 = "(:)"; // Any Single Character 2
//		String re29 = "(=)"; // Any Single Character 3
//		String re30 = "(\\s+)"; // White Space 2
//		String re31 = "(\\d+)"; // Integer Number 1
//
//		Pattern p2 = Pattern.compile(re25 + re26 + re27 + re28 + re29 + re30 + re31,
//				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//		Matcher m2 = p2.matcher(txt);
//		while (m2.find())
//		{
//			String int12 = m2.group(7);
//			trackNumber = Integer.parseInt(int12);// �õ������Ŀ
//		}
//		for (int i = 0; i < trackNumber; i++)
//		{
//			addTrack();// ������ϵͳ
//
//		}
//	}

//	//�����еķ����Ḳ�Ǹ����еķ���
//	public void deleteTrack(int number)// ȥ��һ�����,���������б��еı��
//	{
//		physical.remove(number);
//	}

//	public boolean autoCompetitionA(Set<trackE1> athlete)// ���ű�������<=>������ŵ������
//	{
//		Random rand = new Random();
////		int randomGroup;
////		int randomTrack;
//		Iterator<trackE1> iterator = athlete.iterator();
//		while (iterator.hasNext()) // �������е�Ԫ��ת�Ƶ��б���
//		{
//			athleteForRandom.add(iterator.next());// Ӧ���Ǵ���ַ����
//		}
//		int total = athlete.size();
//		int remainder = total % physical.size();// ���һ��Ӧ�÷ֶ�����
//		int group = total / physical.size();// Ӧ�÷ֶ�����
//		if (remainder == 0)
//		{
//			// ����������ŷ�����ȱ���Ǳ���ʱ��ܳ�
////			trackE1 groupPlayer[][] = new trackE1[group][trackNumber];
////			Iterator<trackE1> iterator = athlete.iterator();
////			while(iterator.hasNext()) 
////			{
////				randomGroup = rand.nextInt(group);//[0,group-1],�����ţ���0��ʼ
////				randomTrack = rand.nextInt(trackNumber);//[0,trackNumber-1],�����ţ���0��ʼ
////				while(groupPlayer[randomGroup][randomTrack]!=null)
////				{
////					randomGroup = rand.nextInt(group);//[0,group-1],�����ţ���0��ʼ
////					randomTrack = rand.nextInt(trackNumber);//[0,trackNumber-1],�����ţ���0��ʼ
////				}	
////				
////			}
//			// trackE1 groupPlayer[][] = new trackE1[group][trackNumber];
//			for (int i = 0; i < group; i++)// groupҲ��ͨ��physical.size()���������
//			{
//				for (int j = 0; j < physical.size(); j++)
//				{
//					int random = rand.nextInt(athleteForRandom.size());
//					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
//					// ���˶�Ա�������
//					// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
//					// addTrackObject(athleteForRandom.get(random), j);// ��������������
//					// groupPlayer[i][j] = athleteForRandom.get(random);
//					objectGroup.put(athleteForRandom.get(random), i);// ���˶�Ա�������
//					addTrackObject(athleteForRandom.get(random), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
//					athleteForRandom.remove(random);// ��������ֻ��Զ���ǰ�ƶ�
//				}
//			}
//		}
//		else
//		{
//
//			// trackE1 groupPlayer[][] = new trackE1[group+1][trackNumber];
//			for (int i = 0; i < group; i++)// �Ȱ�֮ǰ�ĵ��ζ�����
//			{
//				for (int j = 0; j < physical.size(); j++)
//				{
//					int random = rand.nextInt(athleteForRandom.size());
//					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
//					// Ϊ�˶�Ա�������
//					// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
//					// addTrackObject(athleteForRandom.get(random), j);// ��������������
//					// groupPlayer[i][j] = athleteForRandom.get(random);//��֤��ͬһ���˶�Աֻ����һ�������
//					objectGroup.put(athleteForRandom.get(random), i);// ���˶�Ա�������
//					addTrackObject(athleteForRandom.get(random), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
//					athleteForRandom.remove(random);// ��������ֻ��Զ���ǰ�ƶ�
//				}
//			}
//			for (int j = 0; j < remainder; j++)
//			{
//				int random = rand.nextInt(athleteForRandom.size());
//				// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));
//				// temp.setTrackNumber(j);������䣬��������ĺ������Ѿ�ʵ���˸Ĺ���
//				// addTrackObject(athleteForRandom.get(random), j);// ��������������
//				objectGroup.put(athleteForRandom.get(random), group - 1);// ���˶�Ա�������
//				addTrackObject(athleteForRandom.get(random), j);// ���˶�Ա���ù��,��ʱ�����˽Ƕ�
//				athleteForRandom.remove(random);//��֤ͬһ���˶�Աֻ�ܳ�����һ�������
//			}
//		}
//
//		return true;
//	}

//	public boolean autoCompetitionB(Set<trackE1> athlete)// ���ݱ����ɼ�,��óɼ�������������
//	{
//		int groupNumber = 0;
//		int i1 = 0;
//		int j = 0;
//		int j1 = physical.size() - 1;// ��ʵ�ʹ�����Ϊ׼
//		for (int i = 0; i < athleteForRandom.size(); i++)// �óɼ�������˶�Ա������������м����
//		{
//			if (i1 % 2 == 0)// i1��ʾ�ڼ��μӹ��
//			{
//				objectGroup.put(athleteForRandom.get(i), groupNumber);// ���˶�Ա�������
//				addTrackObject(athleteForRandom.get(i), j);// ��������������,��ʱ�����˽Ƕ�
//				j++;
//				i1++;
//			}
//			else
//			{
//				addTrackObject(athleteForRandom.get(i), j1);// ��������������,��ʱ�����˽Ƕ�
//				j1--;
//				i1++;
//			}
//			if (j == j1)
//			{
//				j = 0;
//				j1 = physical.size() - 1;
//				i1 = 0;
//				
//				groupNumber++;
//			}
//			else
//			{
//		
//				i1++;
//			}
//		}
//		return true;
//	}

//	public boolean groupAdjust(trackE1 player1, trackE1 player2)// ������������ͬ���Ͳ�ͬ���
//	{
//		// ��ͬ���
////		if (player1.getGroup() == player2.getGroup())
////		{
////			if (player1.equals(player2))// �˴���Ҫ����ʵ��equals����
////			{
////				System.out.println("They are the same player");
////				return false;
////			}
////			int group1 = player1.getGroup();
////			int group2 = player2.getGroup();
////			player1.setGroup(group2);
////			player2.setGroup(group1);
////			//�Ȼ�����ٻ����
////			int track1 = player1.getTrackNumber();
////			int track2 = player2.getTrackNumber();
////			physical.get(track2);
////			deleteTrackObject(player1, track1);
////			//��̾�ž��棺
////			//Type safety: The method deleteTrackObject(E1, int) belongs to the raw type ConcreteCircularObject. References to generic type ConcreteCircularObject<L,E> 
////			 //should be parameterized
////			//���Ͱ�ȫ��:����deleteTrackObject(E1, int)����ԭʼ����ConcreteCircularObject�����õ���������ConcreteCircularObject<L,E>
////			//Ӧ���ǲ�����
////			deleteTrackObject(player2, track2);
////			addTrackObject(player1, track2);
////			addTrackObject(player2, track1);
////			return true;
////		}
////		//ͬ���
////		else
////		{
////			if (player1.equals(player2))// �˴���Ҫ����ʵ��equals����
////			{
////				System.out.println("They are the same player");
////				return false;
////			}
////		}
////		int track1 = player1.getTrackNumber();
////		int track2 = player2.getTrackNumber();
////		
////		deleteTrackObject(player1, track1);
////		deleteTrackObject(player2, track2);
////		addTrackObject(player1, track2);
////		addTrackObject(player2, track1);
//////		player1.setTrackNumber(track2);
//////		player2.setTrackNumber(track1);
////	    return true;
//		// ͬ���
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
//		if (objectGroup.get(player1) == objectGroup.get(player2))
//		{
//			int track1 = objectTrack.get(player1);
//			int track2 = objectTrack.get(player2);
//			transit(player1, track2);//�ǶȲ���
//			transit(player2, track1);
//			return true;
//		}
//		// ��ͬ���
//		else
//		{
//			int group1 = objectGroup.get(player1);
//			int group2 = objectGroup.get(player2);
//			objectGroup.put(player1, group2);
//			objectGroup.put(player2, group1);
//			int track1 = objectTrack.get(player1);
//			int track2 = objectTrack.get(player2);//�ǶȲ���
//			transit(player1, track2);
//			transit(player2, track1);
//			return true;
//		}
//
//	}

}
