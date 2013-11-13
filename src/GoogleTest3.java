import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Lorin Date: 13-9-12 Time: 上午1:28 Problem
 * 
 * Moist has a hobby -- collecting figure skating trading cards. His card
 * collection has been growing, and it is now too large to keep in one
 * disorganized pile. Moist needs to sort the cards in alphabetical order, so
 * that he can find the cards that he wants on short notice whenever it is
 * necessary.
 * 
 * The problem is -- Moist can't actually pick up the cards because they keep
 * sliding out his hands, and the sweat causes permanent damage. Some of the
 * cards are rather expensive, mind you. To facilitate the sorting, Moist has
 * convinced Dr. Horrible to build him a sorting robot. However, in his rather
 * horrible style, Dr. Horrible has decided to make the sorting robot charge
 * Moist a fee of $1 whenever it has to move a trading card during the sorting
 * process.
 * 
 * Moist has figured out that the robot's sorting mechanism is very primitive.
 * It scans the deck of cards from top to bottom. Whenever it finds a card that
 * is lexicographically smaller than the previous card, it moves that card to
 * its correct place in the stack above. This operation costs $1, and the robot
 * resumes scanning down towards the bottom of the deck, moving cards one by one
 * until the entire deck is sorted in lexicographical order from top to bottom.
 * 
 * As wet luck would have it, Moist is almost broke, but keeping his trading
 * cards in order is the only remaining joy in his miserable life. He needs to
 * know how much it would cost him to use the robot to sort his deck of cards.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each one starts with a line containing a single integer, N. The next
 * N lines each contain the name of a figure skater, in order from the top of
 * the deck to the bottom.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the number of dollars it would cost
 * Moist to use the robot to sort his deck of trading cards.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100. Each name will consist of only letters and the space character.
 * Each name will contain at most 100 characters. No name with start or end with
 * a space. No name will appear more than once in the same test case.
 * Lexicographically, the space character comes first, then come the upper case
 * letters, then the lower case letters.
 * 
 * Small dataset
 * 
 * 1 ≤ N ≤ 10.
 * 
 * Large dataset
 * 
 * 1 ≤ N ≤ 100.
 * 
 * Sample
 * 
 * 
 * Input
 * 
 * Output
 * 
 * 2 2 Oksana Baiul Michelle Kwan 3 Elvis Stojko Evgeni Plushenko Kristi
 * Yamaguchi Case #1: 1 Case #2: 0
 */
public class GoogleTest3 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// 文件处理
		String inputFileName = "d:/a.txt";
		String outputFileName = "d:/b.txt";
		
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		outputFile.createNewFile();

		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		// BufferedReader reader=new BufferedReader(new
		// InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(reader.readLine());
			String[] names = new String[N];
			int money = 0;
			for (int j = 0; j < N; j++) {
				names[j] = reader.readLine();
			}
			money = GoogleTest3.StringSort(names);
			StringBuffer sb=new StringBuffer();
			sb.append("Case #");
			sb.append(i);
			sb.append(": ");
			sb.append(money);
			//writer.write("Case #" + i + ":" + " " + money);
			System.out.println(sb.toString());
			writer.write(sb.toString());
			writer.newLine();
			//System.out.println("Case #" + i + ":" + " " + money);
		}
		writer.flush();
		writer.close();
		reader.close();
	}

	private static int StringSort(String[] str) {
		int money = 0;
		for (int j = 1; j < str.length; j++) {
			if (str[j].compareTo(str[j - 1]) < 0) {
				money++;
				String temp = str[j];
				boolean isFind = false;
				for (int k = j - 1; k >= 0; k--) {
					if (temp.compareTo(str[k]) < 0) {
						str[k + 1] = str[k];
					} else {
						isFind = true;
						str[k + 1] = temp;
					}
				}
				if (!isFind) {
					str[0] = temp;
				}
			}
		}
		return money;
	}

}
