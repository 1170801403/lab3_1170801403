package APIs;

import java.util.HashMap;
import java.util.Map;

public class Difference<L,E>
{
	//默认情况下，对同一个包内的程序可见
	int differenceNumber;//轨道的数量差
	Map<Integer,Integer> numberTrack = new HashMap<Integer, Integer>();//第i条轨道上的数量差
	Map<Integer, String> stringTrack = new HashMap<Integer, String>();//第i条轨道上的物体差	
}
