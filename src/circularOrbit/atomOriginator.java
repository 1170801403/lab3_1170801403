package circularOrbit;

public class atomOriginator
{
	private atomState state;
	public void setState(atomState state)
	{
		this.state = state;
	}
	public atomMemento addMemento()
	{
		return new atomMemento(this.state);
	}
	public void restore(atomMemento m)
	{
		this.state = m.getState();
	}
}
