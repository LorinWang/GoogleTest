import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
/**
 * Problem C. Sorting
Confused? Read the quick-start guide.
Small input
5 points	
You have solved this input set.
Large input
8 points	
Redownload C-large.in
Your submission was received. You can still resubmit for 6min 04sec.
Only your last submission counts. View your last submission.
Problem

Alex and Bob are brothers and they both enjoy reading very much. They have widely different tastes on books so they keep their own books separately. However, their father thinks it is good to promote exchanges if they can put their books together. Thus he has bought an one-row bookshelf for them today and put all his sons' books on it in random order. He labeled each position of the bookshelf the owner of the corresponding book ('Alex' or 'Bob').

Unfortunately, Alex and Bob went outside and didn't know what their father did. When they were back, they came to realize the problem: they usually arranged their books in their own orders, but the books seem to be in a great mess on the bookshelf now. They have to sort them right now!!

Each book has its own worth, which is represented by an integer. Books with odd values of worth belong to Alex and the books with even values of worth belong to Bob. Alex has a habit of sorting his books from the left to the right in an increasing order of worths, while Bob prefers to sort his books from the left to the right in a decreasing order of worths.

At the same time, they do not want to change the positions of the labels, so that after they have finished sorting the books according their rules, each book's owner's name should match with the label in its position.

Here comes the problem. A sequence of N values s0, s1, ..., sN-1 is given, which indicates the worths of the books from the left to the right on the bookshelf currently. Please help the brothers to find out the sequence of worths after sorting such that it satisfies the above description.

Input

The first line of input contains a single integer T, the number of test cases. Each test case starts with a line containing an integer N, the number of books on the bookshelf. The next line contains N integers separated by spaces, representing s0, s1, ..., sN-1, which are the worths of the books.

Output

For each test case, output one line containing "Case #X: ", followed by t0, t1, ..., tN-1 in order, and separated by spaces. X is the test case number (starting from 1) and t0, t1, ..., tN-1 forms the resulting sequence of worths of the books from the left to the right.

Limits

1 �� T �� 30.

Small dataset

1 �� N �� 100
-100 �� si �� 100

Large dataset

1 �� N �� 1000
-1000 �� si �� 1000

Sample


Input 
 	
Output 
 
2
5
5 2 4 3 1
7
-5 -12 87 2 88 20 11
Case #1: 1 4 2 3 5
Case #2: -5 88 11 20 2 -12 87

 * @author Lorin
 *
 */

public class Sort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFileName = "d:/C-large.in";
		String outputFileName = "d:/C-large1.in";
		
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		
		try {
			outputFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			
			int T = Integer.parseInt(reader.readLine());
			for (int i = 1; i <= T; i++) {
				String s=reader.readLine();
				int books=Integer.parseInt(s);
				
				String t=reader.readLine();
				String[] nums=t.split(" ");
				
				int[] array=new int[books];
				int[] alexArray=new int[books];
				int[] bobArray=new int[books];
				int[] resultArray=new int[books];
				
				ArrayList<Integer> list=new ArrayList<Integer>();
				
				for(int j=0;j<books;j++)
				{
					array[j]=Integer.parseInt(nums[j]);
				}
				
				for(int j=0;j<books;j++)
				{
					if (array[j]%2==0)
					{
						alexArray[j]=Integer.MAX_VALUE;
						bobArray[j]=array[j];
					}
					else
					{
						list.add(j);
						alexArray[j]=array[j];
						bobArray[j]=Integer.MAX_VALUE;
					}
				}
			
				Arrays.sort(alexArray);
				Arrays.sort(bobArray);
				Stack<Integer> stack=new Stack<Integer>();
				for (int x=0;x<bobArray.length;x++)
				{
					if(bobArray[x]!=Integer.MAX_VALUE)
					{
						stack.push(bobArray[x]);
					}
				}
				int alex=0;
				for (int y=0;y<books;y++)
				{
					if(list.contains(y))
					{
						resultArray[y]=alexArray[alex];
						alex++;
					}
					
					else
					{
						resultArray[y]=stack.pop();

					}					
				}
				StringBuffer sc=new StringBuffer();
				sc.append("Case #");
				sc.append(i);
				sc.append(":");
				for(int z=0;z<resultArray.length;z++)
				{
					sc.append(" "+resultArray[z]);
				}
				
				writer.write(sc.toString());
				writer.newLine();
				
				/*System.out.println(java.util.Arrays.toString(alexArray));
				System.out.println(java.util.Arrays.toString(bobArray));*/
				
			}
			writer.flush();
			writer.close();
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	

	}

}
