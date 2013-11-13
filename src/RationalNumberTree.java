import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Problem B. Rational Number Tree
This contest is open for practice. You can try every problem as many times as you like, though we won't keep track of which problems you solve. Read the Quick-Start Guide to get started.
Small input
9 points	
Solve B-small
Large input
12 points	
Solve B-large
Problem

Consider an infinite complete binary tree where the root node is 1/1 and left and right childs of node p/q are p/(p+q) and (p+q)/q, respectively. This tree looks like:

         1/1
    ______|______
    |           |
   1/2         2/1
 ___|___     ___|___
 |     |     |     |
1/3   3/2   2/3   3/1
...
It is known that every positive rational number appears exactly once in this tree. A level-order traversal of the tree results in the following array:
1/1, 1/2, 2/1, 1/3, 3/2, 2/3, 3/1, ...
Please solve the following two questions:

Find the n-th element of the array, where n starts from 1. For example, for the input 2, the correct output is 1/2.
Given p/q, find its position in the array. As an example, the input 1/2 results in the output 2.
Input

The first line of the input gives the number of test cases, T. T test cases follow. Each test case consists of one line. The line contains a problem id (1 or 2) and one or two additional integers:

If the problem id is 1, then only one integer n is given, and you are expected to find the n-th element of the array.
If the problem id is 2, then two integers p and q are given, and you are expected to find the position of p/q in the array.
Output

For each test case:

If the problem id is 1, then output one line containing "Case #x: p q", where x is the case number (starting from 1), and p, q are numerator and denominator of the asked array element, respectively.
If the problem id is 2, then output one line containing "Case #x: n", where x is the case number (starting from 1), and n is the position of the given number.
Limits

1 ¡Ü T ¡Ü 100; p and q are relatively prime.

Small dataset

1 ¡Ü n, p, q ¡Ü 216-1; p/q is an element in a tree with level number ¡Ü 16.

Large dataset

1 ¡Ü n, p, q ¡Ü 264-1; p/q is an element in a tree with level number ¡Ü 64.

Sample


Input 
 	
Output 
 
4
1 2
2 1 2
1 5
2 3 2
Case #1: 1 2
Case #2: 2
Case #3: 3 2
Case #4: 5

 * @author Lorin
 *
 */

public class RationalNumberTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFileName = "d:/a.txt";
		String outputFileName = "d:/b.txt";
		
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		
		try {
			outputFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			
			int T = Integer.parseInt(reader.readLine());	
			for(int i=1;i<=T;i++)
			{
				StringBuffer sb=new StringBuffer();
				sb.append("Case #").append(i).append(":");
				
				String s=reader.readLine();
				String[] str=s.split(" ");
				if(str[0].equals("1")){
					long index=Long.parseLong(str[1]);
					Num num=Cal(index);
					sb.append(" ").append(num.p).append(" ").append(num.q);
					
					
				}
				else if(str[0].equals("2")){
					Num num=new Num();
					num.p=Long.parseLong(str[1]);
					num.q=Long.parseLong(str[2]);
					sb.append(" ").append(calIndex(num));
				}
				System.out.println(sb.toString());
				writer.write(sb.toString());
				writer.newLine();
			}
			
			
			writer.flush();
			writer.close();
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static class Num
	{
		long p;
		long q;
	}
	
	private static Num Cal(long index)
	{
		Num num=new Num();
		if(index==1)
		{
			num.p=1;
			num.q=1;
		}
		else if(index%2==0)
		{
			num.p=Cal(index/2).p;
			num.q=Cal(index/2).p+Cal(index/2).q;			
		}
		else 
		{
			num.p=Cal((index-1)/2).p+Cal((index-1)/2).q;
			num.q=Cal((index-1)/2).q;			
		}
		return num;
	}
	
	private static long calIndex(Num num){
		for (long i=1;;i++)
		{
			Num tempNum=Cal(i);
			if (tempNum.p==num.p&&tempNum.q==num.q)
			{
				return i;
			}
		}
	}
}
