package applications;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.IntToDoubleFunction;

import circularOrbit.atomCareTaker;
import circularOrbit.atomOriginator;
import circularOrbit.atomState;
import circularOrbit.atomStructure;
import circularOrbit.functionTrackGame;
import circularOrbit.socialNetworkCircle;
import circularOrbit.trackGame;
import circularOrbit.trackOrganizer;
import physicalObject.atomE1;
import physicalObject.socialE1;
import physicalObject.trackE1;
import APIs.*;
import centralObject.L1;
import centralObject.atomL1;
import centralObject.socialL1;

public class application
{
	public static String atomcreatingTrackFromFiles()
	{
		boolean c = false;
		String size = "2";
		Scanner in2 = new Scanner(System.in);
		while (!c)
		{
			System.out.println("Please input the size of track(s,m,l):");
			size = in2.next();
			if (size.toLowerCase().equals("s") || size.toLowerCase().equals("m") || size.toLowerCase().equals("l"))
			{
				c = true;
			}
			else
			{
				System.out.println("Sorry,this size is illegal.Please input again;");
			}
		}
		if (size.equals("s"))
		{
			//System.out.println("y");
			return "AtomicStructure.";
			// System.out.println("y");
		}
		else if (size.equals("m"))
		{
			return "AtomicStructure_Medium.";
		}
		else
		{
			return "AtomicStructure_Medium.";
		}
	}

	public static String socialcreatingTrackFromFiles()
	{
		boolean c = false;
		String size = "2";
		Scanner in2 = new Scanner(System.in);
		while (!c)
		{
			System.out.println("Please input the size of track(s,m,l):");
			size = in2.next();
			if (size.toLowerCase().equals("s") || size.toLowerCase().equals("m") || size.toLowerCase().equals("l"))
			{
				c = true;
			}
			else
			{
				System.out.println("Sorry,this size is illegal.Please input again;");
			}
		}
		if (size.equals("s"))
		{
			return "SocialNetworkCircle.";
		}
		else if (size.equals("m"))
		{
			return "SocialNetworkCircle_Medium.";
		}
		else
		{
			return "SocialNetworkCircle_Larger.";
		}
	}

	public static String trackcreatingTrackFromFiles()
	{
		boolean c = false;
		String size = "2";
		Scanner in2 = new Scanner(System.in);
		while (!c)
		{
			System.out.println("Please input the size of track(s,m,l):");
			size = in2.next();
			if (size.toLowerCase().equals("s") || size.toLowerCase().equals("m") || size.toLowerCase().equals("l"))
			{
				c = true;
			}
			else
			{
				System.out.println("Sorry,this size is illegal.Please input again;");
			}
		}
		if (size.equals("s"))
		{
			return "TrackGame.";
		}
		else if (size.equals("m"))
		{
			return "TrackGame_Medium.";
		}
		else
		{
			return "TrackGame_Larger.";
		}
	}

	public static double trackEntropy(functionTrackGame track)// 计算田径轨道熵值
	{
		Scanner trackEntropyIn = new Scanner(System.in);
		int groupNumber;
		CircularOrbitAPIs<L1, trackE1> apiTrack = new CircularOrbitAPIs<L1, trackE1>();
		System.out.println(
				"Which group do you want to query for entropy" + "0-" + (track.getGroupTrackSystem().size() - 1));
		groupNumber = trackEntropyIn.nextInt();
		while ((groupNumber > track.getGroupTrackSystem().size() - 1) || (groupNumber < 0))
		{
			System.out.println("Wrong number!Please input again:");
			groupNumber = trackEntropyIn.nextInt();
		}
		double entropy = apiTrack.getObjectDistributionEntropy(track.getGroupTrackSystem().get(groupNumber));
		return entropy;
	}

	public static void trackChange(functionTrackGame track)
	{
//		Scanner changeIn = new Scanner(System.in);
//		String name1, name2;
//		int number1, number2;
//		String nationaility1, nationaility2;
//		int age1, age2;
//		double best1, best2;
//		System.out.println("Please input the two player's name,number,nationality,age,best resuts,the first player:");
//
//		System.out.println("Name:");
//		name1 = changeIn.next();
//		System.out.println("Number:");
//		number1 = changeIn.nextInt();
//		System.out.println("Nationaility:");
//		nationaility1 = changeIn.next();
//		System.out.println("Age:");
//		age1 = changeIn.nextInt();
//		System.out.println("Best results:");
//		best1 = changeIn.nextDouble();
//
//		System.out.println("The next Player:");
//
//		System.out.println("Name:");
//		name2 = changeIn.next();
//		System.out.println("Number:");
//		number2 = changeIn.nextInt();
//		System.out.println("Nationaility:");
//		nationaility2 = changeIn.next();
//		System.out.println("Age:");
//		age2 = changeIn.nextInt();
//		System.out.println("Best results:");
//		best2 = changeIn.nextDouble();
////		trackE1 real1 = new trackE1("a", 1, "b", 1, 1);
////		trackE1 real2 = new trackE1("a", 2, "b", 2, 2);
////		trackE1 real1 = new trackE1(name1, number1, nationaility1, age1, best1);
////		trackE1 real2 = new trackE1(name2, number2, nationaility2, age2, best2);
////		if (temp1.equals(temp2))
////		{
////			System.out.println("They are the same person!");
////			return;
////		}
////		trackE1 etemp1;
////		trackE1 etemp2;
//		List<trackE1> first = new ArrayList<trackE1>();
//		List<trackE1> second = new ArrayList<trackE1>();
//		Set<trackE1> temp = track.getAthlete();
//		Iterator<trackE1> iterator = temp.iterator();
//		while (iterator.hasNext())
//		{
//			trackE1 etemp = iterator.next();
//			if (etemp.getName().equals(name1) && etemp.getNumber() == number1 && etemp.getAge() == age1
//					&&etemp.getNationaility().equals(nationaility1))
//			{
//				first.add(etemp);
//				break;
//			}
//		}
//		Iterator<trackE1> iterator2 = temp.iterator();
//		while (iterator2.hasNext())
//		{
//			trackE1 etemp = iterator2.next();
//			//double用==比较会出错，因为double本身就是不准确的
//			if (etemp.getName().equals(name2) && etemp.getNumber() == number2 && etemp.getAge() == age2
//					&& etemp.getNationaility().equals(nationaility2))
//			{
//				second.add(etemp);
//				break;
//			}
//		}
//		if(first.size()!=0&&second.size()!=0)
//		{
//			System.out.println("Adjust");
//			track.groupAdjust(first.get(0), second.get(0));
//		}
		Scanner changeIn = new Scanner(System.in);
		int group1;
		int group2;
		int number1;
		int number2;
		System.out.println("Please input the two player's group number,track numebr,the first player:");
		System.out.println("group numebr:");
		group1 = changeIn.nextInt();
		System.out.println("track number:");
		number1 = changeIn.nextInt();
		System.out.println("the next player:");
		System.out.println("group numebr:");
		group2 = changeIn.nextInt();
		System.out.println("track number:");
		number2 = changeIn.nextInt();
		if(group1>(track.getGroupTrackSystem().size()-1))
		{
			System.out.println("first player's group number is out of bound!");
			return;
		}
		if(group2>(track.getGroupTrackSystem().size()-1))
		{
			System.out.println("second player's group number is out of bound!");
			return;
		}
		if(track.getGroupTrackSystem().get(group1).getTrackObject().size()-1<number1)
		{
			System.out.println("first player's track number is out of bound!");
			return;
		}
		if(track.getGroupTrackSystem().get(group1).getTrackObject().size()-1<number2)
		{
			System.out.println("second player's track number is out of bound!");
			return;
		}
		
		System.out.println("Adjust");
		track.groupAdjust(group1,number1,group2,number2);
		//number1 = changeIn.nextInt();
//		{
//			System.out.println("no Adjust");
//			return;
//		}
	}

	// System.out.println("Adjust");
//		if (track.getAthlete().contains(real1) && track.getAthlete().contains(real2))
//		{
//			System.out.println("Adjust");
//			track.groupAdjust(real1, real2);
//		}
//		else
//		{
//			System.out.println("no Adjust");
//		}

	public static void trackAddTrack(functionTrackGame track)
	{
		Scanner trackEntropyIn = new Scanner(System.in);
		int groupNumber;
		System.out.println("Which group do you want to add track:" + "0-" + (track.getGroupTrackSystem().size() - 1));
		groupNumber = trackEntropyIn.nextInt();
		while ((groupNumber > track.getGroupTrackSystem().size() - 1) || (groupNumber < 0))
		{
			System.out.println("Wrong number!Please input again:");
			groupNumber = trackEntropyIn.nextInt();
		}
		track.addTrack(groupNumber);// 操作成功会有提示，操作不成功也会有提示
	}

	public static void trackDeleteTrack(functionTrackGame track)
	{
		Scanner trackEntropyIn = new Scanner(System.in);
		int groupNumber;
		int trackNumber;
		System.out
				.println("Which group do you want to delete track:" + "0-" + (track.getGroupTrackSystem().size() - 1));
		groupNumber = trackEntropyIn.nextInt();
		while ((groupNumber > track.getGroupTrackSystem().size() - 1) || (groupNumber < 0))
		{
			System.out.println("Wrong number!Please input again:");
			groupNumber = trackEntropyIn.nextInt();
		}
		System.out.println("Please input the trackNumber:");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = trackEntropyIn.nextInt();
		track.deleteTrack(groupNumber, trackNumber);
	}

	public static void trackAddObject(functionTrackGame track)
	{
		Scanner trackEntropyIn = new Scanner(System.in);
		int groupNumber;
		int trackNumber;

		System.out.println("Which group do you want to add object:" + "0-" + (track.getGroupTrackSystem().size() - 1));
		groupNumber = trackEntropyIn.nextInt();
		while ((groupNumber > track.getGroupTrackSystem().size() - 1) || (groupNumber < 0))
		{
			System.out.println("Wrong number!Please input again:");
			groupNumber = trackEntropyIn.nextInt();
		}

		System.out.println("Please input the trackNumber:");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = trackEntropyIn.nextInt();

		System.out.println("Please input the player's name,number,nationality,age,best resuts:");
		System.out.println("Name:");
		String name1 = trackEntropyIn.next();
		System.out.println("Number:");
		int number1 = trackEntropyIn.nextInt();
		System.out.println("Nationaility:");
		String nationaility1 = trackEntropyIn.next();
		System.out.println("Age:");
		int age1 = trackEntropyIn.nextInt();
		System.out.println("Best results:");
		double best1 = trackEntropyIn.nextDouble();

		trackE1 temp1 = new trackE1(name1, number1, nationaility1, age1, best1);
		track.addObject(groupNumber, trackNumber, temp1);
	}

	public static void trackDeleteObject(functionTrackGame track)
	{
		Scanner trackEntropyIn = new Scanner(System.in);
		int groupNumber;
		int trackNumber;

		System.out
				.println("Which group do you want to delete object:" + "0-" + (track.getGroupTrackSystem().size() - 1));
		groupNumber = trackEntropyIn.nextInt();
		while ((groupNumber > track.getGroupTrackSystem().size() - 1) || (groupNumber < 0))
		{
			System.out.println("Wrong number!Please input again:");
			groupNumber = trackEntropyIn.nextInt();
		}

		System.out.println("Please input the trackNumber:");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = trackEntropyIn.nextInt();

//		System.out.println("Please input the player's name,number,nationality,age,best resuts:");
//		System.out.println("Name:");
//		String name1 = trackEntropyIn.next();
//		System.out.println("Number:");
//		int number1 = trackEntropyIn.nextInt();
//		System.out.println("Nationaility:");
//		String nationaility1 = trackEntropyIn.next();
//		System.out.println("Age:");
//		int age1 = trackEntropyIn.nextInt();
//		System.out.println("Best results:");
//		double best1 = trackEntropyIn.nextDouble();

		// trackE1 temp1 = new trackE1(name1, number1, nationaility1, age1, best1);
		track.deleteObject(groupNumber, trackNumber);
	}

	public static void trackCheck(functionTrackGame track)
	{
		boolean b;
		trackGame temp = new trackGame();
		int trackNumber = track.getTrackNumber();// 从文件中读到的轨道数
		if ((track.getAthlete().size()) % (trackNumber) == 0)
		{
			b = true;
		}
		else
		{
			b = false;
		}
		for (int i = 0; i < track.getGroupTrackSystem().size(); i++)
		{
			temp = track.getGroupTrackSystem().get(i);// 增加一条指针
			if (temp.getTrackObject().size() > 10 || temp.getTrackObject().size() < 4) // 不管用户怎么删，最后都要满足这个约束
			{
				System.out.println("group " + i + "'s track number is out of bound!");
				return;
			}
			if (b)// 原则上可以正好排满，但用户可以删除轨道，不过也只是删除这一组的轨道，对其他组无影响
			{
				if (temp.getTrackObject().size() != trackNumber)// 保证轨道数目合法
				{
					System.out.println("Group" + i + "'s trackNumber is illegal!");
					return;
				}
				for (int j = 0; j < temp.getTrackObject().size(); j++)
				{
					// 每条轨道上有且仅仅有一个运动员
					if (temp.getTrackObject().get(j).size() > 1)
					{
						System.out.println("Group" + i + " track" + j + " has more than one athlete!");
						return;
					}
					if (temp.getTrackObject().get(j).size() == 0)
					{
						System.out.println("Group" + i + " track" + j + " has no athlete!");
						return;
					}

				}
			}
			else
			{
				if (i != track.getGroupTrackSystem().size() - 1)// 保证轨道数目合法
				{
					if (temp.getTrackObject().size() != trackNumber)
					{
						System.out.println("Group" + i + "'s trackNumber is illegal!");
						return;
					}
				}
				for (int j = 0; j < temp.getTrackObject().size(); j++)
				{
					// 每条轨道上有且仅仅有一个运动员
					if (temp.getTrackObject().get(j).size() > 1)
					{
						System.out.println("Group" + i + " track" + j + " has more than one athlete!");
						return;
					}
					if (temp.getTrackObject().get(j).size() == 0)
					{
						System.out.println("Group" + i + " track" + j + " has no athlete!");
						return;
					}
				}
			}
		}
	}

	public static void trackVisualize(functionTrackGame track)// 设置为static，便于让main函数直接调用
	{
//		int group = track.getGroupTrackSystem().size();
		Scanner trackIn = new Scanner(System.in);
		int numGroup = track.getGroupTrackSystem().size();// 能提前代替就提前代替，否则会容易出现错误
		System.out.println("Please input the group number(0-" + (numGroup - 1) + "):");
		System.out.println(track.getAthlete().size());
		int groupFinal = trackIn.nextInt();
		System.out.println();
		if ((numGroup - 1) < groupFinal)
		{
			System.out.println("the group is out of bound!");
			return;
		}
		CircularOrbitHelper<atomL1, atomE1> apiAtom = new CircularOrbitHelper<atomL1, atomE1>(
				track.getGroupTrackSystem().get(groupFinal));
		apiAtom.visualize();
//		for(int i=0; i<group; i++)
//		{
//				CircularOrbitHelper<atomL1, atomE1> apiAtom = new CircularOrbitHelper<atomL1, atomE1>(
//				track.getGroupTrackSystem().get(i));
//				apiAtom.visualize();
//		}
		return;
	}

	public static void trackOrganizer1(functionTrackGame track)
	{
		trackOrganizer or = new trackOrganizer("r");
		or.arrange(track);
	}

	public static void trackOrganizer2(functionTrackGame track)
	{
		trackOrganizer or = new trackOrganizer("g");
		or.arrange(track);
	}

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

	public static void atomAddTrack(atomStructure atom)
	{
		atom.addTrack();
	}

	public static void atomDeleteTrack(atomStructure atom)
	{
		Scanner atomTrackIn = new Scanner(System.in);
		int trackNumber;
		System.out.println("Please input the trackNumber!");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = atomTrackIn.nextInt();
		atom.deleteTrack(trackNumber);
	}

	public static void atomAddObject(atomStructure atom)
	{
		Scanner atomTrackIn = new Scanner(System.in);
		int trackNumber;
		System.out.println("Please input the trackNumber!");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = atomTrackIn.nextInt();
		atom.addTrackObject(new atomE1("electronic"), trackNumber);
	}

	public static void atomDeleteObject(atomStructure atom)
	{
		Scanner atomTrackIn = new Scanner(System.in);
		atomE1 temp = null;

		int trackNumber;
		System.out.println("Please input the trackNumber!");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = atomTrackIn.nextInt();
		if (!atom.getTrackObject().containsKey(trackNumber))
		{
			System.out.println("the track has been deleted!");
			return;
		}
		Iterator<atomE1> iterator = atom.getTrackObject().get(trackNumber).iterator();
		if (iterator.hasNext())
		{
			temp = iterator.next();
		}
		atom.deleteTrackObject(temp, trackNumber);
	}

	public static double atomEntropy(atomStructure atom)// 计算原子轨道熵值
	{
		CircularOrbitAPIs<atomL1, atomE1> apiAtom = new CircularOrbitAPIs<atomL1, atomE1>();
		double entropy = apiAtom.getObjectDistributionEntropy(atom);
		return entropy;
	}

	public static void atomVisualize(atomStructure atom)// 原子轨道可视化
	{
		CircularOrbitHelper<atomL1, atomE1> apiAtom = new CircularOrbitHelper<atomL1, atomE1>(atom);
		apiAtom.visualize();
	}

	public static void atomTransit(atomStructure atom, atomCareTaker careTaker, atomOriginator originator)// 电子跃迁
	{
		Scanner trIn = new Scanner(System.in);
		System.out.println("Please input the number of the two tracks:");
		int trackNumber1 = trIn.nextInt();
		int trackNumber2 = trIn.nextInt();
		// 记录当前状态
		originator.setState(new atomState(atom.getTrackObjectNumber()));
		// 将当前状态加入备忘录列表中
		careTaker.addMemento(originator.addMemento());
		atom.electronicTransition(trackNumber1, trackNumber2);
//		//记录当前状态
//		originator.setState(new atomState(atom.getTrackObjectNumber()));
//		//将当前状态加入备忘录列表中
//		careTaker.addMemento(originator.addMemento());
	}

	public static void atomGoBack(atomStructure atom, atomCareTaker careTaker, atomOriginator originator)
	{
		Scanner backIn = new Scanner(System.in);
		System.out.println("Please input the steps:");
		int step = backIn.nextInt();
		// 恢复到之前的状态
		originator.restore(careTaker.getMemento(step));
		atom.clear();
		atom.creatingTrack(atom.getTrackNumber2(), originator.addMemento().getState().getTrackObjectNumber());
	}
//		while (!m)
//		{
	// if (!atom.electronicTransition(trackNumber1, trackNumber2))
//			{
//				System.out.println("again?(y or n)");
//				String atChoice = trIn.next();
//				if (atChoice.toLowerCase().equals("y"))
//				{
//					;
//				}
//				else
//				{
//					m = true;
//				}
//			}
//			else
//			{
//				System.out.println("transit again?(y or n)");
//				String successAtChoice = trIn.next();
//				if (successAtChoice.toLowerCase().equals("y"))
//				{
//					;
//				}
//				else
//				{
//					m = true;
//				}
//			}
//		}
//	}

	public static void socialAddTrack(socialNetworkCircle society)
	{
		society.addTrack();
		return;
	}

	public static void socialDeleteTrack(socialNetworkCircle society)// 删除一条轨道之后，关系也要删除吗
	{
		Scanner socialIn = new Scanner(System.in);
		int trackNumber;
		System.out.println("Please input the trackNumber!");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = socialIn.nextInt();
		society.deleteTrack(trackNumber);
		return;
	}

	public static void socialDeleteObject(socialNetworkCircle society)// 这个函数中不加return会错
	{
		Scanner socialIn = new Scanner(System.in);
		String name;
		int age;
		String sex1;
		char sex = 0;
		int trackNumber;
		socialE1 temp = new socialE1("a", 1, sex);
//		System.out.println("Please input the trackNumber!");// 此处不能给数字范围，因为可能是第二次delete
//		trackNumber = socialIn.nextInt();
		System.out.println("Please input the name!");
		name = socialIn.next();
		System.out.println("Please input the age!");
		age = socialIn.nextInt();
		System.out.println("Please input the sex!(M or F)");
		sex1 = socialIn.next();
		sex = sex1.charAt(0);// 输入字符中的第一个字母
		boolean is = false;
		int i = 0;
		for (i = 0; i < society.getTrackObject().size(); i++)
		{
			Iterator<socialE1> iterator = society.getTrackObject().get(i).iterator();
			while (iterator.hasNext())
			{
				// 避免重写equals方法
				temp = iterator.next();// 让temp指向内存中的另一块空间，因为socialE1是不可变的
				if (temp.getName().equals(name) && temp.getSex() == sex && temp.getAge() == age)
				{
					is = true;
					break;
				}
			}
			if (is == true)
			{
				break;
			}
		}
//		Iterator<socialE1> iterator = society.getTrackObject().get(trackNumber).iterator();
//		while (iterator.hasNext())
//		{
//			//避免重写equals方法
//			temp = iterator.next();// 让temp指向内存中的另一块空间，因为socialE1是不可变的
//			if (temp.getName().equals(name) && temp.getSex() == sex && temp.getAge() == age)
//			{
//				is = true;
//				break;
//			}
//		}
		if (!is)
		{
			System.out.println("The person is not in the track!");
			return;
		}
		society.deleteTrackObject(temp, i);
		return;
	}

	public static void socialAddObject(socialNetworkCircle society)
	{
		Scanner socialIn = new Scanner(System.in);
		String name;
		int age;
		String sex1;
		char sex = 0;
		int trackNumber;
		socialE1 temp = new socialE1("a", 1, sex);
		System.out.println("Please input the trackNumber!");// 此处不能给数字范围，因为可能是第二次delete
		trackNumber = socialIn.nextInt();
		System.out.println("Please input the name!");
		name = socialIn.next();
		System.out.println("Please input the age!");
		age = socialIn.nextInt();
		System.out.println("Please input the sex!(M or F)");
		sex1 = socialIn.next();
		sex = sex1.charAt(0);// 输入字符中的第一个字母
		socialE1 tempE = new socialE1(name, age, sex);
		society.addTrackObject(tempE, trackNumber);
	}

	public static double socialEntropy(socialNetworkCircle society)// 计算社交网络熵值
	{
		CircularOrbitAPIs<socialL1, socialE1> apiSociety = new CircularOrbitAPIs<socialL1, socialE1>();
		double entropy = apiSociety.getObjectDistributionEntropy(society);
		return entropy;
		// System.out.println("The entropy of the system is:" + entropy);
	}

	public static void socialVisualize(socialNetworkCircle society)
	{
		CircularOrbitHelper<socialL1, socialE1> apiSociety = new CircularOrbitHelper<socialL1, socialE1>(society);
		apiSociety.visualize();
	}

	public static void socialIntimacy(socialNetworkCircle society)// 计算亲密度
	{
		Scanner intimacyIn = new Scanner(System.in);
		System.out.println("Please enter a friend from layer 1");
		String first = intimacyIn.next();
		society.intimacy(first);
	}
//		boolean c = false;
//		while (!c)
//		{
//			if (!society.intimacy(first))
//			{
//				System.out.println("again?(y or n)");
//				String choice = intimacyIn.next();
//				if (choice.toLowerCase().equals("y"))
//				{
//					System.out.println("Please enter a friend from layer 1");
//					first = intimacyIn.next();
//				}
//				else
//				{
//					c = true;
//				}
//			}
//			else
//			{
//				System.out.println("get it again?(y or n)");
//				String successChoice = intimacyIn.next();
//				if (successChoice.toLowerCase().equals("y"))
//				{
//					;
//				}
//				else
//				{
//					c = true;
//				}
//			}
//		}

//	}

	public static void socialAR(socialNetworkCircle society)// 增加/删除关系后重新调整图结构
	{
		Scanner arIn = new Scanner(System.in);
//		System.out.println("Do you want to add or remove a relationship(y or n)?");
//		String yar = arIn.next();
//		if (yar.toLowerCase().equals("y"))

		// boolean rm = false;
		String name1;
		String name2;
		String ar;
		System.out.println("Please input the first man's name:");
		name1 = arIn.next();
		System.out.println("Please input the second man's name:");
		name2 = arIn.next();
		System.out.println("Add or remove(a or r)?");
		ar = arIn.next();
		while (!((ar.toLowerCase().equals("a")) || (ar.toLowerCase().equals("r"))))
		{
			System.out.println("Illegal,please input again:");
			ar = arIn.next();
		}
		if (ar.toLowerCase().equals("a"))
		{
			System.out.println("Please input their intimacy:");
			Float ini = arIn.nextFloat();
			// 在addRelationship方法中包含了addEErelationship
			society.addRelationship(name1, name2, ini);
		}
		else
		{
			society.deleteRelationship(name1, name2);
		}
	}
//			while (!rm)
//			{
//				System.out.println("Please input the first man's name:");
//				name1 = arIn.next();
//				System.out.println("Please input the second man's name:");
//				name2 = arIn.next();
//				System.out.println("Add or remove(a or r)?");
//				String ar = arIn.next();
//				if (ar.toLowerCase().equals("a"))
//				{
//					System.out.println("Please input their intimacy:");
//					Float ini = arIn.nextFloat();
//					// 在addRelationship方法中包含了addEErelationship
//					if (!society.addRelationship(name1, name2, ini))
//					{
//						System.out.println("again?(y or n)");
//						String arChoice = arIn.next();
//						if (arChoice.toLowerCase().equals("y"))
//						{
//							;
//						}
//						else
//						{
//							rm = true;
//						}
//
//					}
//					else
//					{
//						System.out.println("add or remove again?(y or n)");
//						String successARChoice = arIn.next();
//						if (successARChoice.toLowerCase().equals("y"))
//						{
//							;
//						}
//						else
//						{
//							rm = true;
//						}
//
//					}
//				}
//				else
//				{
//					// 在deleteRelationship方法中包含了deleteEErelationship
//					if (!society.deleteRelationship(name1, name2))
//					{
//						System.out.println("again?(y or n)");
//						String arChoice = arIn.next();
//						if (arChoice.toLowerCase().equals("y"))
//						{
//							;
//						}
//						else
//						{
//							rm = true;
//						}
//					}
//					else
//					{
//						System.out.println("add or remove again?(y or n)");
//						String successARChoice = arIn.next();
//						if (successARChoice.toLowerCase().equals("y"))
//						{
//							;
//						}
//						else
//						{
//							rm = true;
//						}
//
//					}
//
//				}
//			}

//	}

	// 检查合法性的时候需要运行API中的一个函数，观察两者是否相等
	// 既可以是轨道物体之间的，也可以是轨道物体与中心物体之间的
	public static int socialGetDistance(socialNetworkCircle society, String name1, String name2)// 获得最短逻辑距离,这个函数在检查合法性的时候也要用到
	{
		CircularOrbitAPIs<socialL1, socialE1> socialApi = new CircularOrbitAPIs<socialL1, socialE1>();
		Set<socialE1> tempFirst = society.getTrackObject().get(0);
		Scanner disIn = new Scanner(System.in);
//		String name1;
//		String name2;
		socialL1 central = society.getCentral();
		Map<String, socialE1> friend = society.getFriend();
//		System.out.println("Do you want to get the shortest distance between two person(y or n)?");
//		String dis;
//		dis = disIn.next();
		// boolean sum = false;
//		System.out.println("Please input the first man's name:");
//	    name1 = disIn.next();
//		System.out.println("Please input the second man's name:");
//		name2 = disIn.next();
//		society.shortestLogicalDistance(dname1, dname2);
		int distance;
		// CircularOrbitAPIs<socialL1, socialE1> socialApi = new
		// CircularOrbitAPIs<socialL1, socialE1>();
		if ((name1.equals(central.getName())) || (name2.equals(central.getName())))
		{
			distance = 10000000;
			int centralDistance = 0;
			if ((name1.equals(central.getName())))
			{
				if (!friend.containsKey(name2))
				{
					System.out.println("Man two doesn't exist!");
					return -4;
				}
				Iterator<socialE1> iterator = tempFirst.iterator();
				while (iterator.hasNext())
				{
					// socialApi.getLogicalDistance要么return一个正数，要么return一个-2
					centralDistance = socialApi.getLogicalDistance(society, friend.get(name2), iterator.next());
					if (centralDistance >= 0 && centralDistance < distance)
					{
						distance = centralDistance;
					}
				}
				return distance + 1;
			}
			else
			{
				if (!friend.containsKey(name1))
				{
					System.out.println("Man one doesn't exist!");
					return -4;
				}
				Iterator<socialE1> iterator2 = tempFirst.iterator();
				while (iterator2.hasNext())
				{
					centralDistance = socialApi.getLogicalDistance(society, friend.get(name1), iterator2.next());
					if (centralDistance >= 0 && centralDistance < distance)
					{
						distance = centralDistance;
					}
				}
				return distance + 1;
			}
		}
		else
		{

			if (!friend.containsKey(name1))
			{
				System.out.println("Man one doesn't exist!");
				return -4;
			}
			if (!friend.containsKey(name2))
			{
				System.out.println("Man two doesn't exist!");
				return -4;
			}
			distance = socialApi.getLogicalDistance(society, friend.get(name1), friend.get(name2));
			return distance;
		}
	}
//			if ((name1.equals(central.getName())))
//			{
//				if (friend.containsKey(name2))
//				{
////					if (objectTrack.containsKey(friend.get(name2)))
////					{
////						distance = objectTrack.get(friend.get(name2))+1;
////					}
////					else
////					{
////						System.out.println("The name exist,but the person isn't in this orbit system!");
////						return -3;
////					}
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

//			if (objectTrack.get(friend.get(name1)) == objectTrack.get(friend.get(name2)))
//			{
//				distance = 0;
//			}
//			else if (objectTrack.get(friend.get(name1)) > objectTrack.get(friend.get(name2)))
//			{
//				distance = objectTrack.get(friend.get(name1)) - objectTrack.get(friend.get(name2));
//			}
//			else
//			{
//				distance = objectTrack.get(friend.get(name2)) - objectTrack.get(friend.get(name1));
//			}
//		}
	// System.out.println("The shortest logical distance is " + distance);
//		return distance;
//	}
//}
//		if (dis.toLowerCase().equals("y"))
//		{
//			boolean m = false;
//			String dname1;
//			String dname2;
//			while (!m)
//			{
//				System.out.println("Please input the first man's name:");
//				dname1 = disIn.next();
//				System.out.println("Please input the second man's name:");
//				dname2 = disIn.next();
//				if (!society.shortestLogicalDistance(dname1, dname2))
//				{
//					System.out.println("again?(y or n)");
//					String disChoice = disIn.next();
//					if (disChoice.toLowerCase().equals("y"))
//					{
//						System.out.println("Please input the first man's name:");
//						dname1 = disIn.next();
//						System.out.println("Please input the second man's name:");
//						dname2 = disIn.next();
//					}
//					else
//					{
//						m = true;
//					}
//				}
//				else
//				{
//					System.out.println("get another shortest distance?(y or n)");
//					dis = disIn.next();
//					if (dis.toLowerCase().equals("y"))
//					{
//						m = false;
//					}
//					else
//					{
//						m = true;
//					}
//
//				}
//			}
//		}
//	}

	public static void socialCheck(socialNetworkCircle society)
	{
		CircularOrbitAPIs<socialL1, socialE1> socialApi = new CircularOrbitAPIs<socialL1, socialE1>();
		// Set<socialE1> temp = society.getTrackObject().get(0);
		Map<Integer, Set<socialE1>> tempTrackObject = society.getTrackObject();
		Iterator<Integer> iterator = tempTrackObject.keySet().iterator();
		int length = 0;
		boolean is = true;
		while (iterator.hasNext())// 只关心层数
		{
			int tempNumber = iterator.next();
			length++;
			Set<socialE1> tempSocialE1Set = tempTrackObject.get(tempNumber);
			Iterator<socialE1> iterator2 = tempSocialE1Set.iterator();
			while (iterator2.hasNext())
			{
				int centralDistance;
				centralDistance = socialGetDistance(society, society.getCentral().getName(),
						iterator2.next().getName());
				if (centralDistance != length)
				{
					System.out.println("Illegal system!");
					return;
				}
//					centralDistance = socialApi.getLogicalDistance(society, society.getCentral(), iterator2.next());
//					if(centralDistance>distance)
//					{
//						distance = centralDistance;
//					}
//				
//				return distance;		
			}
		}
		System.out.println("Succeed!");
		return;
	}
//				socialE1 tempSocialE1 = iterator2.next();
//				Iterator<socialE1> iterator3 = temp.iterator();
//				boolean is = false;
//				while (iterator3.hasNext())
//				{
//					socialE1 temSocialE2 = iterator3.next();
//					if (socialApi.getLogicalDistance(society, tempSocialE1, temSocialE2) + 1 == society
//							.shortestLogicalDistance(tempSocialE1.getName(), society.getCentral().getName()))
//					{
//						is = true;
//					}
//				}
//				if (!is)
//				{
//					System.out.println("Illegal distance!");
//					return;
//				}
//			}
//		}
//		System.out.println("Succeed!");
//		return;
//	}
//		Set<socialE1> leTemp = society.getLErelationship();// 获得轨道上的所有物体
//		Iterator<socialE1> iterator = leTemp.iterator();
//		while (iterator.hasNext())
//		{
//			socialE1 e = iterator.next();// 本质上都是在传指针
//			int trackNumber = society.getObjectTrack().get(e);// 物体对应的轨道的编号
//			Iterator<socialE1> iterator2 = temp.iterator();
//			boolean is = true;
//			while (iterator2.hasNext())
//			{
//				if (socialApi.getLogicalDistance(society, iterator2.next(), e) == trackNumber)
//				{
//					is = false;
//					break;
//				}
//			}
//			if (is)// 如果前一个while循环从来没有跳出过，就说明有问题
//			{
//				System.out.println("Illegal distance!");
//				return;
//			}
//
//		}
//		System.out.println("Succeed!");
//		return;

//	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String type = "1";
		boolean b = false;
		Scanner in = new Scanner(System.in);
		while (!b)
		{
			System.out.println("Please input the type of track(track,atom,social):");
			type = in.next();
			if (type.toLowerCase().equals("track") || type.toLowerCase().equals("atom")
					|| type.toLowerCase().equals("social"))
			{
				b = true;
			}
			else
			{
				System.out.println("Sorry,this type is illegal.Please input again;");
			}
		}
		if (type.toLowerCase().equals("track"))
		{
			Scanner choiceOrder = new Scanner(System.in);
			String name = trackcreatingTrackFromFiles();
			functionTrackGame track = new functionTrackGame();
			track.creatingTrackFromFiles(name);
			int order;
			String another;
			boolean again = true;
			boolean choose = true;
			boolean fina = false;
			System.out.println(
					"Now please choose the method to divede group(R indicates random,G indicates results ahead):");
			String choice = choiceOrder.next();
			while (choose)
			{
				if (choice.toLowerCase().equals("r"))
				{
					// track.autoCompetitionA();
//					trackOrganizer organizer = new trackOrganizer("r");
//					organizer.arrange();
					trackOrganizer1(track);
					fina = true;
					choose = false;
				}
				else if (choice.toLowerCase().equals("g"))
				{
					// track.autoCompetitionB();
					// trackOrganizer organizer = new trackOrganizer("g");
					// organizer.arrange();
					trackOrganizer2(track);
					fina = true;
					choose = false;
				}
				else
				{
					System.out.println("Wrong order!Input again?");
					String yes = choiceOrder.next();
					if (yes.toLowerCase().equals("n"))
					{
						choose = false;
					}
					else
					{
						System.out.println("Please input:");
						choice = choiceOrder.next();
						;
					}
				}
			}
			if (fina)
			{
				System.out.println("Now please input the number of operations:");
				System.out.println("1  visualization");
				System.out.println("2  add track");
				System.out.println("3  add object");
				System.out.println("4  delete track");
				System.out.println("5  delete object");
				System.out.println("6  entrophy");
				System.out.println("7  exchange athlete's track");// 分同一组和不同组
				System.out.println("8  check the legitimacy of the track system");
				System.out.println("0  end");
				order = choiceOrder.nextInt();
				while (!((order >= 0) && (order <= 8)))
				{
					System.out.println("Wrong order!Input again:");
					order = choiceOrder.nextInt();
				}
				while (again)
				{
					switch (order)
					{
						case 0:
							//结束操作
							again = false;
							break;
						case 1:
							// 调用可视化函数
							trackVisualize(track);
							break;
						case 2:
							// 增加轨道
							trackAddTrack(track);
							break;
						case 3:
							// 向特定轨道上增加物体
							trackAddObject(track);
							break;
						case 4:
							// 删除轨道
							trackDeleteTrack(track);
							break;
						case 5:
							// 在特定轨道上删除物体,没有给出正确提示
							trackDeleteObject(track);
							break;
						case 6:
							System.out.println("The entropy of the system is:" + trackEntropy(track));
							break;							
						case 7:
							//更换轨道
							trackChange(track);
							break;						
						case 8:
							//检查轨道系统的合法性
							trackCheck(track);
							break;
						default:
							break;
					}
					if (again)
					{
						System.out.println("operate again?(y or n)");
						another = choiceOrder.next();
						if (another.toLowerCase().equals("n"))
						{
							again = false;
						}
						else
						{
							System.out.println("Please input the order:");
							order = choiceOrder.nextInt();
							while (!((order >= 0) && (order <= 8)))
							{
								System.out.println("Wrong order!Input again:");
								order = choiceOrder.nextInt();
							}
						}
					}
				}
			}
			System.out.println("结束了");
		}

		else if (type.toLowerCase().equals("atom"))
		{
			atomCareTaker careTaker = new atomCareTaker();
			atomOriginator originator = new atomOriginator();
			String name = atomcreatingTrackFromFiles();// 没问题
			atomStructure atom = new atomStructure();// 没问题
			atom.creatingTrackFromFiles(name);
			atom.creatingTrack(atom.getTrackNumber2(), atom.getTrackObjectNumber());
			int order;
			String another;
			boolean again = true;
			System.out.println("Now please input the number of operations:");
			System.out.println("1  visualization");
			System.out.println("2  add track");
			System.out.println("3  add object");
			System.out.println("4  delete track");
			System.out.println("5  delete object");
			System.out.println("6  entrophy");
			System.out.println("7  transit of electronic");
			System.out.println("8  go back to states before");
			System.out.println("0  end");
			Scanner choiceOrder = new Scanner(System.in);
			order = choiceOrder.nextInt();
			while (!((order >= 0) && (order <= 8)))
			{
				System.out.println("Wrong order!Input again:");
				order = choiceOrder.nextInt();
			}
			while (again)
			{
				switch (order)
				{
					case 0:
						//操作结束
						again = false;
						break;
					case 1:
						// 调用可视化函数
						atomVisualize(atom);
						break;
					case 2:
						// 增加轨道
						atomAddTrack(atom);
						break;
					case 3:
						// 向特定轨道上增加物体
						atomAddObject(atom);
						break;
					case 4:
						// 删除轨道
						atomDeleteTrack(atom);
						break;
					case 5:
						// 在特定轨道上删除物体
						atomDeleteObject(atom);
						break;
					case 6:
						//求熵值
						System.out.println("The entropy of the system is:" + atomEntropy(atom));
						break;
					case 7:
						//电子跃迁
						atomTransit(atom, careTaker, originator);// 没问题
						break;
					case 8:
						//回溯
						atomGoBack(atom, careTaker, originator);// 有问题
						break;
					default:
						break;
				}
				if (again)
				{
					System.out.println("operate again?(y or n)");

					another = choiceOrder.next();
					if (another.toLowerCase().equals("n"))
					{
						again = false;
					}
					else
					{
						System.out.println("Please input the order:");
						order = choiceOrder.nextInt();
						while (!((order >= 0) && (order <= 8)))
						{
							System.out.println("Wrong order!Input again:");
							order = choiceOrder.nextInt();
						}
					}
				}
				// 可视化展示

				// (3) 用户提供必要信息，以增加新轨道、向特定轨道上增加物体；
//			addTrack();
//			addObjectToTrack();
				// (4) 用户提供必要信息，以从特定轨道上删除物体、删除整条轨道；

				// （5）计算熵值
				// System.out.println("The entropy of the system is:" + atomEntropy(atom));
				// 特有功能：电子跃迁
				// atomTransit(atom);
			}
			System.out.println("结束了");// 以上代码没有问题
		}
		else if (type.toLowerCase().equals("social"))
		{
			String name = socialcreatingTrackFromFiles();
			socialNetworkCircle society = new socialNetworkCircle();
			society.creatingTrackFromFiles(name);// 从文件构造轨道
			society.creatingTrack();
			int order;
			String another;
			boolean again = true;
			System.out.println("Now please input the order of operations:");
			System.out.println("1  visualization");
			System.out.println("2  add track");
			System.out.println("3  add object");
			System.out.println("4  delete track");
			System.out.println("5  delete object");
			System.out.println("6  entrophy");
			System.out.println("7  degree of diffusion");// 信息扩散度
			System.out.println("8  remove/add a relationship and adjust the socialNet");
			System.out.println("9  shortest logical distance between two person");
			System.out.println("10 check the legitimacy of the track system");
			System.out.println("0  end");
			Scanner choiceOrder = new Scanner(System.in);
			order = choiceOrder.nextInt();
			while (!((order >= 0) && (order <= 10)))
			{
				System.out.println("Wrong order!Input again:");
				order = choiceOrder.nextInt();
			}
			while (again)
			{
				switch (order)
				{
					case 0:
						again = false;
						break;
					case 1:
						// 调用可视化函数
						socialVisualize(society);
						break;
					case 2:
						// 增加轨道,与中心点物体没有关系的人也能出现在轨道上
						socialAddTrack(society);
						break;
					case 3:
						// 向特定轨道上增加物体,与中心点物体没有关系的人也能出现在轨道上
						socialAddObject(society);
						break;
					case 4:
						// 删除轨道,删除第一条轨道,已经重新调整图结构
						socialDeleteTrack(society);// 调整之后的结构不对(已解决)，只要删的是第一条轨道，再复杂的社交网络也退化为一个圆圈中间点一个点
						break;
					case 5:
						// 在特定轨道上删除物体,需要重新调整图结构
						socialDeleteObject(society);// 需要改进，1不应该让用户输入具体的轨道数字(已解决)2调整之后的结构不对(已解决)
						break;
					case 6:
						//求熵值
						System.out.println("The entropy of the system is:" + socialEntropy(society));
						break;
					case 7:
						//求通过第一层的某个好友能间接认识多少个好友
						socialIntimacy(society);// 有问题（已解决）
						break;
					case 8:
						//增加/删除关系后重新调整图结构
						socialAR(society);// 仅限于轨道物体间的关系增减，未将中心物体包含在内,需要重新调整图结构(已解决)
						break;
					case 9:
						String name1;
						String name2;
						Scanner disIn = new Scanner(System.in);
						System.out.println("Please input the first man's name:");
						name1 = disIn.next();
						System.out.println("Please input the second man's name:");
						name2 = disIn.next();
						System.out.println(
								"The shortest logical distance is：" + socialGetDistance(society, name1, name2));// 问题1：方法有问题(已解决)
																												// 问题2：仅限于轨道物体，未将中心物体包含在内（已改正）
						break;
					case 10:
						//检查轨道系统的合法性
						socialCheck(society);// 有问题(已解决)
					default:
						break;
				}
				if (again)
				{
					System.out.println("operate again?(y or n)");
					another = choiceOrder.next();
					if ((another.toLowerCase().equals("n")))
					{
						again = false;
					}
					else
					{
						System.out.println("Please input the order:");
						order = choiceOrder.nextInt();
						while (!((order >= 0) && (order <= 10)))
						{
							System.out.println("Wrong order!Input again:");
							order = choiceOrder.nextInt();
						}

					}
				}
			}
			System.out.println("结束了");// 只有将可视化窗口关闭，程序才会真正结束
//			// 可视化展示
//
//			// (3) 用户提供必要信息，以增加新轨道、向特定轨道上增加物体；
//
//			// (4) 用户提供必要信息，以从特定轨道上删除物体、删除整条轨道；
//
//			// （5）计算熵值
//			System.out.println("The entropy of the system is:" + sociakEntropy(society));
//			// 特有功能：计算好友扩散度
//			socialIntimacy(society);
//			// 特有功能：增加/删除一条关系
//			socialAR(society);
//			// 特有功能：计算逻辑距离
//			socialGetDistance(society);

		}
		else
		{

		}
	}

}
