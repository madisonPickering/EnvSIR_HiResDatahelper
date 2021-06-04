/**Used to compare two edges (in order to sort them)
 * @author Madison Pickering
 * (Copyright 2020 Madison Pickering)
 * 
 * This file is part of EnvSIR_HiResDataHelper.

    EnvSIR_HiResDataHelper is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    EnvSIR_HiResDataHelper is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with EnvSIR_HiResDataHelper.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * 
 */
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
