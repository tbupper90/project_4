import java.io.Serializable;
import java.util.*;


abstract class Region implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5959620826424242964L;
	
	protected String name;
	protected String pop;
	protected String area;
	

	
	public String getName()
	{
		return "";
	}
	
	public String getPop()
	{
		return "";
	}
	
	public String getArea()
	{
		return "";
	}
	
	/**
	 * Comparators for the Region Class
	 *
	 */
	public static class Comparators
	{
		public static Comparator<Region> AREA = new Comparator<Region>()
		{
			@Override
			public int compare(Region o1, Region o2)
			{
				return 0;
			}
		};
		
		 public static Comparator<Region> POP = new Comparator<Region>()
		 {
			@Override
			public int compare(Region o1, Region o2)
			{
				//convert to float because some populations are too big for ints
				return 0;
			}
		 };
		 
		 public static Comparator<Region> NAME = new Comparator<Region>()
		 {
			@Override
			public int compare(Region o1, Region o2)
			{
				return 0;
			}
		 };
		 
		 
		 
	}
}
