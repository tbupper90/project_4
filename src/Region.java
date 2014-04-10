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
	LinkedHashMap<String,PlaceOfInterest> places = new LinkedHashMap<String,PlaceOfInterest>();
	LinkedHashMap<String,PointOfInterest> points = new LinkedHashMap<String,PointOfInterest>();

	
	public Region()
	{
		
	}
	
	abstract boolean dataQC();
	
	public void addPlace(String name, PlaceOfInterest place)
	{
		places.put(name,place);
	}
	
	public void addPoint(String name, PointOfInterest point)
	{
		points.put(name,point);
	}
	
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
	
	public String toString()
	{
		return name;
		
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
