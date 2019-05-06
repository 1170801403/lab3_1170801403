package circularOrbit;

public class atomMemento
{
	private atomState state;
	public atomMemento(atomState state)
	{
		this.state = state;
	}
	public atomState getState()
	{
		return this.state;
	}
}
