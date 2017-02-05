package hdm.org.se2.snake02;

public class PlayerFactory {
	
	public static Snake getPlayer(String criteria)	{
		
		if (criteria.equals("Snake") )
		    return new Snake(0,0,3);
	    else if ( criteria.equals("Snake2") )
	    	return new Snake(3,0,3);
		
		return null;
	}

}
