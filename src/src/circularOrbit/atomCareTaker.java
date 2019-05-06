package circularOrbit;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class atomCareTaker
{
	private ArrayList<atomMemento> mementos = new ArrayList<atomMemento>();
	public void addMemento(atomMemento m)
	{
		mementos.add(m);
	}
	public atomMemento getMemento(int i)
	{
		if(mementos.size()-i<0)
		{
			throw new RuntimeException("Cannot rollback so many backs!");
		}
		return mementos.get(mementos.size()-i);//获得倒数某一步的状态
	}
}
