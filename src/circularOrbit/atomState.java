package circularOrbit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class atomState
{
	private Map<Integer, Integer> trackObjectNumber;
//	static atomState instance = new atomState();
	public atomState(Map<Integer,Integer> trackNumber)
	{
		this.trackObjectNumber = trackNumber;
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
}
