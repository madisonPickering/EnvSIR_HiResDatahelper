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
					writer.write(thisSrc + " : " + thisDest);
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
				edges.add(edge);
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
				System.out.println("" + i + contents[i].getName());
			
			Scanner scan = new Scanner(System.in);
			int index = scan.nextInt();
			scan.close();
			return contents[index];
		}
		else //else, contents.length == 1; there is only one file
			return contents[0];
	}
}
