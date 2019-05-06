package track;

public class trackFactory
{
	public Track manufacture(int rep)
	{
		return new Track(rep);
	}
}
