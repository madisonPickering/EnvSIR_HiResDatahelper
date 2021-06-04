/**Datahelper.java contains the main method, and
 * reads in a file specified in the format of the data collected in
 * ”A high-resolution human contact network for infectious disease transmission.” 
 * and outputs it as an adjacency list. Takes a conservative approach to adding edges:
 * if individual a has a recorded edge with b, and b does not have a recorded edge with a
 * (based on the input file), in the output file a will be recorded as having an edge with b,
 * and b will be recorded as having an edge with a.
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Datahelper 
{
	public static final String INPUT_ROOT = "input";
	public static final String OUTPUT_ROOT = "output";
	private static TreeSet<Edge> edges;
	
	public static void main(String[] args) 
	{
		edges = new TreeSet<>(new EdgeComparator());
		File input = getInputFile();
		readInput(input);
		toOutput(input.getName());
	}
	
	public static void toOutput(String inputName)
	{
		try
		{
			File output = new File(OUTPUT_ROOT + "/" + inputName + "_out");
			output.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			
			//transfer the contents of edges to the newly made output file
			int lastSrc = -1;
			Iterator<Edge> iter = edges.iterator();
			while (iter.hasNext())
			{
				Edge edge = iter.next();
				int thisSrc = edge.getSource();
				int thisDest = edge.getDest();
				System.out.println(thisSrc + ", " + thisDest);
				if (lastSrc < 0) //this is our first iter thru
				{
					lastSrc = thisSrc;
					writer.write(thisSrc + " : " + thisDest + " ");
				}
				else if (lastSrc == thisSrc)
					writer.write(thisDest + " ");
				else //lastSrc != thisSrc; start a new line
				{
					writer.write("\n");
					writer.write(thisSrc + " : " + thisDest + " ");
					lastSrc = thisSrc;
				}
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**Reads the contents of the input file into edges
	 * 
	 * @param input the input file
	 */
	public static void readInput(File input)
	{
		try
		{
			Scanner lineScanner = new Scanner(input);
			while (lineScanner.hasNextLine())
			{
				String line = lineScanner.nextLine();
				Scanner tokenScanner = new Scanner(line);
				int source = tokenScanner.nextInt();
				int dest = tokenScanner.nextInt();
				tokenScanner.close(); //dont bother w parsing the duration token
				
				Edge edge = new Edge(source, dest);
				//make it two sided
				Edge backEdge = new Edge(dest, source);
				edges.add(edge);
				edges.add(backEdge);
			}
			lineScanner.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**Returns the file to process for this run. Prompts the user to choose
	 * among multiple inputs if they exist
	 * @return the file to process for this run
	 */
	public static File getInputFile()
	{
		File root = new File(INPUT_ROOT);
		File[] contents = root.listFiles();
		if (contents.length == 0)
		{
			System.out.println("No files in input. exiting");
			return null;
		}
		//if there are multiple input files to choose from, ask which one to choose
		if (contents.length > 1)
		{
			System.out.println("Choose which of the following files? Please type"
					+ " your choice numerically (i.e, 0 for file 0)");
			
			for (int i = 0; i < contents.length; i++)
				System.out.println(i + " " + contents[i].getName());
			
			Scanner scan = new Scanner(System.in);
			int index = scan.nextInt();
			scan.close();
			return contents[index];
		}
		else //else, contents.length == 1; there is only one file
			return contents[0];
	}
}
