import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Problem

Do you know how to read the phone numbers in English? Now let me tell you.

For example, In China, the phone numbers are 11 digits, like: 15012233444. Someone divides the numbers into 3-4-4 format, i.e. 150 1223 3444. While someone divides the numbers into 3-3-5 format, i.e. 150 122 33444. Different formats lead to different ways to read these numbers:

150 1223 3444 reads one five zero one double two three three triple four.

150 122 33444 reads one five zero one double two double three triple four.

Here comes the problem:

Given a list of phone numbers and the dividing formats, output the right ways to read these numbers.

Rules:

Single numbers just read them separately.

2 successive numbers use double.

3 successive numbers use triple.

4 successive numbers use quadruple.

5 successive numbers use quintuple.

6 successive numbers use sextuple.

7 successive numbers use septuple.

8 successive numbers use octuple.

9 successive numbers use nonuple.

10 successive numbers use decuple.

More than 10 successive numbers read them all separately.

Input

The first line of the input gives the number of test cases, T. T lines|test cases follow. Each line contains a phone number N and the dividing format F, one or more positive integers separated by dashes (-), without leading zeros and whose sum always equals the number of digits in the phone number.

Output

For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is the reading sentence in English whose words are separated by a space.

Limits

1 ¡Ü T ¡Ü 100.

Small dataset

1 ¡Ü length of N ¡Ü 10.

Large dataset

1 ¡Ü length of N ¡Ü 100.

Sample


Input 
 	
3
15012233444 3-4-4
15012233444 3-3-5
12223 2-3


Output 
 
Case #1: one five zero one double two three three triple four
Case #2: one five zero one double two double three triple four
Case #3: one two double two three

 * @author Lorin
 *
 */

public class ReadPhoneNumber {

	public static void main(String[] args) {
		String inputFileName = "d:/A-large.in";
		String outputFileName = "d:/A-large-1.in";
		
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		try {
			outputFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			
			int T = Integer.parseInt(reader.readLine());
			for (int i = 1; i <= T; i++) {
				String s=reader.readLine();
				String[] temp=s.split(" ");
				String num=temp[0];
				String F=temp[1];
				//System.out.println(num);
				//System.out.println(F);
				
				String[] Fs=F.split("-");
				int firstIndex=0;
				int lastIndex=0;
				StringBuffer sb=new StringBuffer();
				sb.append("Case #");
				sb.append(i);
				sb.append(":");
				
				String sb1;
				for (int j=0;j<Fs.length;j++)
				{
					lastIndex=firstIndex+Integer.parseInt(Fs[j]);
					//System.out.println("lastindex"+lastIndex);
					String waitReadString=num.substring(firstIndex, lastIndex);
					firstIndex=lastIndex;
					//System.out.println(readString);
					sb1=readString(waitReadString);
					sb.append(sb1);
				}
				
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

	private static String readString(String waitReadString) {
		char[] charArray=waitReadString.toCharArray();
		StringBuffer sb=new StringBuffer();
		
		for (int k=0;k<charArray.length;k++)
		{
			if(k+1<charArray.length&&charArray[k]!=charArray[k+1]||k+1==charArray.length)
			{
				switch(charArray[k])
				{
					case '1':sb.append(" one");
					break;
					case '2':sb.append(" two");
					break;
					case '3':sb.append(" three");
					break;
					case '4':sb.append(" four");
					break;
					case '5':sb.append(" five");
					break;
					case '6':sb.append(" six");
					break;
					case '7':sb.append(" seven");
					break;
					case '8':sb.append(" eight");
					break;
					case '9':sb.append(" nine");
					break;
					case '0':sb.append(" zero");
					break;
				}
			}
			else
			{
				int l=1;
				int pk=k;
				while(k+1<charArray.length&&charArray[k]==charArray[k+1])
				{
					k++;
					l++;
				}
				if(l<=10){
					switch(l)
					{
						case 2:sb.append(" double");
						break;
						case 3:sb.append(" triple");
						break;
						case 4:sb.append(" quadruple");
						break;
						case 5:sb.append(" quintuple");
						break;
						case 6:sb.append(" sextuple");
						break;
						case 7:sb.append(" septuple");
						break;
						case 8:sb.append(" octuple");
						break;
						case 9:sb.append(" nonuple");
						break;
						case 10:sb.append(" decuple");
						break;					
					}
					switch(charArray[k])
					{
						case '1':sb.append(" one");
						break;
						case '2':sb.append(" two");
						break;
						case '3':sb.append(" three");
						break;
						case '4':sb.append(" four");
						break;
						case '5':sb.append(" five");
						break;
						case '6':sb.append(" six");
						break;
						case '7':sb.append(" seven");
						break;
						case '8':sb.append(" eight");
						break;
						case '9':sb.append(" nine");
						break;
						case '0':sb.append(" zero");
						break;
					}
				}
				else
				{
					for(int m=pk;m<=k;m++)
					{
						switch(charArray[m])
						{
							case '1':sb.append(" one");
							break;
							case '2':sb.append(" two");
							break;
							case '3':sb.append(" three");
							break;
							case '4':sb.append(" four");
							break;
							case '5':sb.append(" five");
							break;
							case '6':sb.append(" six");
							break;
							case '7':sb.append(" seven");
							break;
							case '8':sb.append(" eight");
							break;
							case '9':sb.append(" nine");
							break;
							case '0':sb.append(" zero");
							break;
						}
					}
					
				}
				
				
			}
		
		}
		return sb.toString();
		
	}

}
