package circularOrbit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import abstractFactory.atomFactory;
import centralObject.L1;
import centralObject.atomL1;
import physicalObject.E1;
import physicalObject.atomE1;
import physicalObject.socialE1;
import track.Track;

public class atomStructure extends ConcreteCircularObject<atomL1, atomE1>
{
	// private List<Integer> trackNumber = new ArrayList<Integer>();//
	// 测不准原理，不需要知道电子在轨道上的哪个位置
	private Map<Integer, Integer> trackObjectNumber = new HashMap<Integer, Integer>();// 每个轨道上有多少个电子
    int trackNumber2 = 0;// 有多少个轨道
    private atomFactory factory = new atomFactory();
//	private atomState state;
	
	
	public void clear()
	{
		physical.clear();// physical其实是第一个应该删除的，因为它直接决定了轨道的编号，轨道的编号决定了trackObject能否正常使用
		angle.clear();
		objectTrack.clear();
		trackObject.clear();
		LErelationship.clear();
		// relationship.clear();
		Iterator<atomE1> iterator = relationship.keySet().iterator();
		while (iterator.hasNext())
		{
			relationship.get(iterator.next()).clear();
		}
	}
	
	
	
	
	
	public Map<Integer, Integer> getTrackObjectNumber()
	{
		Map<Integer, Integer> tempTrackObjectNumber = new HashMap<Integer, Integer>();
		Iterator<Integer> iterator = trackObjectNumber.keySet().iterator();
		while (iterator.hasNext())
		{
			int temp = iterator.next();
			tempTrackObjectNumber.put(temp, trackObjectNumber.get(temp));
		}
		return tempTrackObjectNumber;
	}
    public int getTrackNumber2()
	{
		int x;//传值而非传指针
		x = trackNumber2;
    	return x;
	}
	@Override
	public void creatingTrackFromFiles(String name)// 从外部文件读取数据构造轨道系统对象
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

		String fre9 = "(NumberOfTracks)"; // Word 3
		String fre10 = "(\\s+)"; // White Space 4
		String fre11 = "(:)"; // Any Single Character 4
		String fre12 = "(:)"; // Any Single Character 5
		String fre13 = "(=)"; // Any Single Character 6
		String fre14 = "(\\s+)"; // White Space 5
		String fre15 = "(\\d+)"; // Integer Number 1
		Pattern fp = Pattern.compile(fre9 + fre10 + fre11 + fre12 + fre13 + fre14 + fre15,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher fm = fp.matcher(txt);
		System.out.println(fre15);
		if (fm.find())// 顶多匹配到一次，只需要用if
		{
			trackNumber2 = Integer.parseInt(fm.group(7));
		}

		String re1 = "(ElementName)"; // Word 1
		String re2 = "(\\s+)"; // White Space 1
		String re3 = "(:)"; // Any Single Character 1
		String re4 = "(:)"; // Any Single Character 2
		String re5 = "(=)"; // Any Single Character 3
		String re6 = "(\\s+)"; // White Space 2
		String re7 = "((?:[a-z][a-z]+))"; // Word 2
		String re8 = "(\\s+)"; // White Space 3
		String re9 = "(NumberOfTracks)"; // Word 3
		String re10 = "(\\s+)"; // White Space 4
		String re11 = "(:)"; // Any Single Character 4
		String re12 = "(:)"; // Any Single Character 5
		String re13 = "(=)"; // Any Single Character 6
		String re14 = "(\\s+)"; // White Space 5
		String re15 = "(\\d+)"; // Integer Number 1
		String re16 = "(\\s+)"; // White Space 6
		String re17 = "(NumberOfElectron)"; // Word 4
		String re18 = "(\\s+)"; // White Space 7
		String re19 = "(:)"; // Any Single Character 7
		String re20 = "(:)"; // Any Single Character 8
		String re21 = "(=)"; // Any Single Character 9
		String re22 = "(\\s+)"; // White Space 8
		// re22之后出现轨道数\电子数

		String re23 = "(\\d+)"; // Integer Number 2
		String re24 = "(\\/)"; // Any Single Character 10
		// String re25="(\\d+/+d)"; // Integer Number 3
		String re25 = "(\\d+)"; // Integer Number 3
		String re26 = "(;)"; // Any Single Character 11

		String re = re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10 + re11 + re12 + re13 + re14 + re15 + re16
				+ re17 + re18 + re19 + re20 + re21 + re22;
		String numRe = re23 + re24 + re25 + re26;
		for (int i = 0; i < trackNumber2 - 1; i++)
		{
			re = re + numRe;
		}

		String re27 = "(\\d+)"; // Integer Number 10
		String re28 = "(\\/)"; // Any Single Character 18
		String re29 = "(\\d+)"; // Integer Number 11
		String finalNumRe = re27 + re28 + re29;
		re = re + finalNumRe;
		Pattern p = Pattern.compile(re, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		if (m.find())// 顶多匹配到一次，只需要用if
		{
			String word2 = m.group(7);// 原子核名称
//			central = new atomL1(word2);// 创造中心点物体
			central = factory.manufactureL(word2);
			String int1 = m.group(15);// 原子核外轨道数目
			trackNumber2 = Integer.parseInt(int1);// 确定核外轨道数目
//			for (int i = 0; i < trackNumber2; i++)// 构造轨道系统
//			{
//				addTrack();
//			}

			for (int i = 0; i < trackNumber2 - 1; i++)
			{
				String temp = m.group(22 + i * 4 + 3);
//				for(int j=0; j<Integer.parseInt(temp); j++)
//				{
//					addTrackObject(new atomE1("electrical"), i);//向轨道上加电子
//				}
				trackObjectNumber.put(i, Integer.parseInt(temp));// 通过轨道号查询该轨道上有多少个电子
			}

			// 结尾没有分号
			String temp1 = m.group(22 + (trackNumber2 - 1) * 4 + 3);
//			for (int j = 0; j < Integer.parseInt(temp1); j++)
//			{
//				addTrackObject(new atomE1("electrical"), trackNumber2 - 1);// 向最外层轨道加电子
//			}
			//其实读文件的时候就已经初始化了trackObjectNumber
			trackObjectNumber.put(trackNumber2 - 1, Integer.parseInt(temp1));
		}

	}

	public void creatingTrack(int trackNumber2,Map<Integer, Integer> RtrackObjectNumber)
	{

		for (int i = 0; i < trackNumber2; i++)// 构造轨道系统
		{
			//添加轨道
			addTrack();
		}
		for (int i = 0; i < RtrackObjectNumber.size(); i++)
		{

			int t = RtrackObjectNumber.get(i);
			for (int j = 0; j <t ; j++)
			{
				//向轨道上添加物体
				addTrackObject(factory.manufactureE("electrical"), i);// 向轨道上加电子
			}
//			new atomE1("electrical")
		}
		trackObjectNumber = RtrackObjectNumber;
		checkRep();
	}

	//涉及到具体的电子实例，之所以要重写父类中的transit方法，是为了更好的建立轨道
	public boolean electronicTransition(int source, int target)// 电子跃迁：给定源轨道、目标轨道，模拟电子跃迁，实质：源轨道上减少一个电子，目标轨道上增加一个电子；轨道编号从0开始
	{
		if (!(source < physical.size()))
		{
			System.out.println("The source orbit is out of bound!");
			return false;
		}
		if (!(target < physical.size()))
		{
			System.out.println("The target orbit is out of bound!");
			return false;
		}
		// 一切正常之后
		int t = trackObjectNumber.get(source);
		int q = trackObjectNumber.get(target);
		if (t == 0)
		{
			System.out.println("The source target doesn't has any e!");
			return false;
		}

		Iterator<atomE1> iterator = trackObject.get(source).iterator();
		atomE1 temp = new atomE1("2");
		if (iterator.hasNext())
		{
			//让它指向另一块内存
			temp = iterator.next();
		}

		// trackObject.get(source).remove(temp);
		deleteTrackObject(temp, source);
		trackObjectNumber.put(source, t - 1);

		// trackObject.get(target).add(temp);a
		addTrackObject(temp, target);
		trackObjectNumber.put(target, q + 1);
		System.out.println("succeed!");
		return true;
	}
}
