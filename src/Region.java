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
		return name;
	}
	
	public String getPop()
	{
		return pop;
	}
	
	public String getArea()
	{
		return area;
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
				int o1area = Integer.parseInt(o1.getArea());
				int o2area = Integer.parseInt(o2.getArea());
				
				if(o2area < o1area)
				{
					return -1;
				}
				
				else if(o2area > o1area)
				{
					return 1;
				}
				
				else return 0;
			}
		};
		
		 public static Comparator<Region> POP = new Comparator<Region>()
		 {
			@Override
			public int compare(Region o1, Region o2)
			{
				//convert to float because some populations are too big for ints
				float o1pop = (float) Float.parseFloat(o1.getPop());
				float o2pop = (float) Float.parseFloat(o2.getPop());
				
				if(o2pop < o1pop)
				{
					return -1;
				}
				else if(o2pop > o1pop)
				{
					return 1;
				}
				else return 0;
			}
		 };
		 
		 public static Comparator<Region> NAME = new Comparator<Region>()
		 {
			@Override
			public int compare(Region o1, Region o2)
			{
				if(o1.getName().compareTo(o2.getName()) < 0)
				{
					return -1;
				}
				else if(o1.getName().compareTo(o2.getName()) > 0)
				{
					return 1;
				}
				else return 0;
			}
		 };
		 
		 
		 
	}
}
