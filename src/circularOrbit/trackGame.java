//mutable
package circularOrbit;

//遗漏点：用户删除一条轨道，或增加一条轨道时，如何重新进行初始化(已解决)
//遗漏点：每条轨道上没有记录轨道上的物体，熵值怎么计算
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

//parameter n. 参数；系数；参量
//具体的子类中可以用具体的L,E
public class trackGame extends ConcreteCircularObject<L1, trackE1>// ConcreteCircularObject后面可以不写任何东西，要写的话只能写<L,E>
{
//	private final int trackNumber;// 轨道数量，只是用来读文件用，真正实现具体功能的时候还是以physical的实际长度为准
//	private final int playType;// 竞赛种类
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
	
	
	//以下内容不应该出现在本程序中
	//private Set<trackE1> athlete = new TreeSet<trackE1>();// 用来存储所有的物体
	//private List<trackE1> athleteForRandom = new ArrayList<trackE1>();// 将集合变为列表，便于安排比赛方案
	//private Map<trackE1, Integer> objectGroup = new HashMap<trackE1, Integer>();// 物体到组别的映射
	//private Map<trackE1, Double> sithaTrackE1 = new HashMap<trackE1, Double>();// 物体到角度的映射

	
	//以下内容不应该出现在本程序中
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
//		//不需要检查同一个运动员只出现在一组比赛中
//	}

//	//子类重写父类中的方法，@Override会检查返回值、函数名、参数是否一致
//	@Override
//	public void creatingTrackFromFiles(String name)// 从外部文件读取数据构造轨道系统对象
//	{
//		String txt = new String();
//		String re1 = "(Athlete)"; // Word 1
//		String re2 = "(\\s+)"; // White Space 1
//		String re3 = "(:)"; // Any Single Character 1
//		String re4 = "(:)"; // Any Single Character 2
//		String re5 = "(=)"; // Any Single Character 3
//		String re6 = "(\\s+)"; // White Space 2
//		String re7 = "(<)"; // Any Single Character 4
//		String re8 = "((?:[a-z][a-z]+))"; // Word 2,姓名
//		String re9 = "(,)"; // Any Single Character 5
//		String re10 = "(\\d+)"; // Integer Number 1，编号
//		String re11 = "(,)"; // Any Single Character 6
//		String re12 = "((?:[a-z][a-z]+))"; // Word 3，国籍
//		String re13 = "(,)"; // Any Single Character 7
//		String re14 = "(\\d+)"; // Integer Number 2，年龄
//		String re15 = "(,)"; // Any Single Character 8
//		String re16 = "([+-]?\\d*\\.\\d+)(?![-+0-9\\.])"; // Float 1，最佳成绩
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
//			String word1 = m.group(8);// 姓名
//			String int1 = m.group(10);// 编号
//			String word2 = m.group(12);// 国籍
//			String int2 = m.group(14);// 年龄
//			String float1 = m.group(16);// 最佳成绩
//			trackE1 temp = new trackE1(word1, Integer.parseInt(int1), word2, Integer.parseInt(int2),
//					Float.parseFloat(float1));
//			athlete.add(temp);// parseInt和parseFloat都是构造常量
//			//sithaTrackE1.put(temp, 0.00);// 默认位于角度0
//			// 利用集合的互异性
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
//			playType = Integer.parseInt(int11);// 得到比赛种类
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
//			trackNumber = Integer.parseInt(int12);// 得到轨道数目
//		}
//		for (int i = 0; i < trackNumber; i++)
//		{
//			addTrack();// 构造轨道系统
//
//		}
//	}

//	//子类中的方法会覆盖父类中的方法
//	public void deleteTrack(int number)// 去除一条轨道,输入轨道在列表中的编号
//	{
//		physical.remove(number);
//	}

//	public boolean autoCompetitionA(Set<trackE1> athlete)// 编排比赛方案<=>将物体放到轨道上
//	{
//		Random rand = new Random();
////		int randomGroup;
////		int randomTrack;
//		Iterator<trackE1> iterator = athlete.iterator();
//		while (iterator.hasNext()) // 将集合中的元素转移到列表中
//		{
//			athleteForRandom.add(iterator.next());// 应该是传地址调用
//		}
//		int total = athlete.size();
//		int remainder = total % physical.size();// 最后一组应该分多少人
//		int group = total / physical.size();// 应该分多少组
//		if (remainder == 0)
//		{
//			// 这种随机编排方案的缺点是编排时间很长
////			trackE1 groupPlayer[][] = new trackE1[group][trackNumber];
////			Iterator<trackE1> iterator = athlete.iterator();
////			while(iterator.hasNext()) 
////			{
////				randomGroup = rand.nextInt(group);//[0,group-1],分组编号，从0开始
////				randomTrack = rand.nextInt(trackNumber);//[0,trackNumber-1],轨道编号，从0开始
////				while(groupPlayer[randomGroup][randomTrack]!=null)
////				{
////					randomGroup = rand.nextInt(group);//[0,group-1],分组编号，从0开始
////					randomTrack = rand.nextInt(trackNumber);//[0,trackNumber-1],轨道编号，从0开始
////				}	
////				
////			}
//			// trackE1 groupPlayer[][] = new trackE1[group][trackNumber];
//			for (int i = 0; i < group; i++)// group也是通过physical.size()计算出来的
//			{
//				for (int j = 0; j < physical.size(); j++)
//				{
//					int random = rand.nextInt(athleteForRandom.size());
//					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
//					// 对运动员设置组别
//					// temp.setTrackNumber(j);多余语句，增加物体的函数中已经实现了改功能
//					// addTrackObject(athleteForRandom.get(random), j);// 向轨道上增加物体
//					// groupPlayer[i][j] = athleteForRandom.get(random);
//					objectGroup.put(athleteForRandom.get(random), i);// 对运动员设置组别
//					addTrackObject(athleteForRandom.get(random), j);// 对运动员设置轨道,此时设置了角度
//					athleteForRandom.remove(random);// 后面的数字会自动往前移动
//				}
//			}
//		}
//		else
//		{
//
//			// trackE1 groupPlayer[][] = new trackE1[group+1][trackNumber];
//			for (int i = 0; i < group; i++)// 先把之前的道次都排满
//			{
//				for (int j = 0; j < physical.size(); j++)
//				{
//					int random = rand.nextInt(athleteForRandom.size());
//					// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));//
//					// 为运动员设置组别
//					// temp.setTrackNumber(j);多余语句，增加物体的函数中已经实现了改功能
//					// addTrackObject(athleteForRandom.get(random), j);// 向轨道上增加物体
//					// groupPlayer[i][j] = athleteForRandom.get(random);//保证了同一个运动员只出现一组比赛中
//					objectGroup.put(athleteForRandom.get(random), i);// 对运动员设置组别
//					addTrackObject(athleteForRandom.get(random), j);// 对运动员设置轨道,此时设置了角度
//					athleteForRandom.remove(random);// 后面的数字会自动往前移动
//				}
//			}
//			for (int j = 0; j < remainder; j++)
//			{
//				int random = rand.nextInt(athleteForRandom.size());
//				// trackObject.get(physical.get(j)).add(athleteForRandom.get(random));
//				// temp.setTrackNumber(j);多余语句，增加物体的函数中已经实现了改功能
//				// addTrackObject(athleteForRandom.get(random), j);// 向轨道上增加物体
//				objectGroup.put(athleteForRandom.get(random), group - 1);// 对运动员设置组别
//				addTrackObject(athleteForRandom.get(random), j);// 对运动员设置轨道,此时设置了角度
//				athleteForRandom.remove(random);//保证同一个运动员只能出现在一组比赛中
//			}
//		}
//
//		return true;
//	}

//	public boolean autoCompetitionB(Set<trackE1> athlete)// 根据比赛成绩,最好成绩从慢到快排序
//	{
//		int groupNumber = 0;
//		int i1 = 0;
//		int j = 0;
//		int j1 = physical.size() - 1;// 以实际轨道情况为准
//		for (int i = 0; i < athleteForRandom.size(); i++)// 让成绩优秀的运动员后出场并处在中间道次
//		{
//			if (i1 % 2 == 0)// i1表示第几次加轨道
//			{
//				objectGroup.put(athleteForRandom.get(i), groupNumber);// 对运动员设置组别
//				addTrackObject(athleteForRandom.get(i), j);// 向轨道上增加物体,此时设置了角度
//				j++;
//				i1++;
//			}
//			else
//			{
//				addTrackObject(athleteForRandom.get(i), j1);// 向轨道上增加物体,此时设置了角度
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

//	public boolean groupAdjust(trackE1 player1, trackE1 player2)// 更换赛道，分同组别和不同组别
//	{
//		// 不同组别
////		if (player1.getGroup() == player2.getGroup())
////		{
////			if (player1.equals(player2))// 此处需要重新实现equals方法
////			{
////				System.out.println("They are the same player");
////				return false;
////			}
////			int group1 = player1.getGroup();
////			int group2 = player2.getGroup();
////			player1.setGroup(group2);
////			player2.setGroup(group1);
////			//先换组别，再换轨道
////			int track1 = player1.getTrackNumber();
////			int track2 = player2.getTrackNumber();
////			physical.get(track2);
////			deleteTrackObject(player1, track1);
////			//感叹号警告：
////			//Type safety: The method deleteTrackObject(E1, int) belongs to the raw type ConcreteCircularObject. References to generic type ConcreteCircularObject<L,E> 
////			 //should be parameterized
////			//类型安全性:方法deleteTrackObject(E1, int)属于原始类型ConcreteCircularObject。引用到泛型类型ConcreteCircularObject<L,E>
////			//应该是参数化
////			deleteTrackObject(player2, track2);
////			addTrackObject(player1, track2);
////			addTrackObject(player2, track1);
////			return true;
////		}
////		//同组别
////		else
////		{
////			if (player1.equals(player2))// 此处需要重新实现equals方法
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
//		// 同组别
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
//			transit(player1, track2);//角度不变
//			transit(player2, track1);
//			return true;
//		}
//		// 不同组别
//		else
//		{
//			int group1 = objectGroup.get(player1);
//			int group2 = objectGroup.get(player2);
//			objectGroup.put(player1, group2);
//			objectGroup.put(player2, group1);
//			int track1 = objectTrack.get(player1);
//			int track2 = objectTrack.get(player2);//角度不变
//			transit(player1, track2);
//			transit(player2, track1);
//			return true;
//		}
//
//	}

}
