package circularOrbit;

public  class trackOrganizer
{
	private trackStrategy yes;
	public trackOrganizer(String choice)
	{
		switch (choice)
		{
			case "r":
				yes = new trackStrategyR();
				break;
			case "g":
		     	yes = new trackStrategyG();
				break;
			default:
				break;
		}
	}
	public void arrange(functionTrackGame game)
	{
		yes.strategy(game);
	}
}
