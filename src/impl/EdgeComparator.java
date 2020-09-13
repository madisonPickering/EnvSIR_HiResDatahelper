package impl;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> 
{

	/**Compares two edges, first by the source, then by the destination
	 * 
	 * @param one an edge
	 * @param two another edge
	 * @return -1 if one < two, 1 if one > two, and 0 if they are equal
	 */
	@Override
	public int compare(Edge one, Edge two)
	{
		int oneSrc = one.getSource();
		int twoSrc = two.getSource();
		
		if (oneSrc > twoSrc)
			return 1;
		else if (oneSrc < twoSrc)
			return -1;
		else //oneSrc == twoSrc
		{
			int oneDest = one.getDest();
			int twoDest = two.getDest();
			
			if (oneDest > twoDest)
				return 1;
			else if (oneDest < twoDest)
				return -1;
			else //oneDest == twoDest && oneSrc == twoSrc
				return 0;
		}
	}

}
