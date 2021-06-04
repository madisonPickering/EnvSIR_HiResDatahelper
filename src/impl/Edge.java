/** Barebones implementation of a directed edge
 * 
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
 */
package impl;

public class Edge
{
	private int source;
	private int dest;
	
	public Edge(int source, int dest)
	{
		this.source = source;
		this.dest = dest;
	}
	
	public int getSource()
	{
		return source;
	}
	
	public int getDest()
	{
		return dest;
	}
}
