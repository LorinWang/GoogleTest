import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sudoku {

	public static boolean isCorret(int[][] mtrix, int N) {
		boolean isLine = true;
		boolean isCol = true;
		boolean isN = true;

		for (int i = 0; i < N * N; i++) {
			isLine = isLine & checkRow(mtrix, i, N);
		}
		if (isLine) {
			for (int i = 0; i < N * N; i++) {
				isCol = isCol & checkLine(mtrix, i, N);
			}
		}
		if (isLine && isCol) {
			for (int i = 0; i < N * N; i = i + N) {
				for (int j = 0; j < N * N; j = j + N) {
					isN = isN & checkNine(mtrix, i, j, N);
				}
			}
		}
		if (isLine && isCol && isN) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkRow(int[][] mtrix, int row, int N) {
		for (int j = 0; j < N * N; j++) {
			if (mtrix[row][j] < 1 || mtrix[row][j] > N * N) {
				return false;
			}
			for (int k = j + 1; k < N * N; k++) {
				if (mtrix[row][j] == mtrix[row][k]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkLine(int[][] mtrix, int col, int N) {
		for (int j = 0; j < N * N; j++) {
			for (int k = j + 1; k < N * N; k++) {
				if (mtrix[j][col] == mtrix[k][col]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkNine(int[][] mtrix, int row, int col, int N) {
		
		int array[]=new int[N*N];
		int k=0;
		for (int i = row; i < row + N; i++) {
			for (int j = col; j < col + N; j++) {
				array[k++]=mtrix[i][j];
			}
		}
		boolean isTrue=true;
		for (int i=0;i<N*N-1;i++)
		{
			for (int j=i+1;j<N*N;j++)
			{
				if(array[i]==array[j])
				{
					isTrue=false;
				}
			}
		}
		return isTrue;
	}

	public static void main(String[] args) {
		String inputFileName = "d:/input/A-large.in";
		String outputFileName = "d:/input/A-large_1.in";

		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);

		try {
			outputFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					outputFile));
			BufferedReader reader = new BufferedReader(
					new FileReader(inputFile));

			int T = Integer.parseInt(reader.readLine());
			for (int i = 1; i <= T; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append("Case #").append(i).append(":");

				int N = Integer.parseInt(reader.readLine());
				int[][] mtrix = new int[N * N][N * N];
				


				for (int line = 0; line < N * N; line++) {
					String strLine = reader.readLine();
					String[] lineStr = strLine.split(" ");
					for (int col = 0; col < N * N; col++) {
						mtrix[line][col] = Integer.parseInt(lineStr[col]);
					}
				}
				boolean isTrue = false;
				isTrue = isCorret(mtrix, N);
				if(isTrue)
				{
					sb.append(" Yes");
				}
				else
				{
					sb.append(" No");
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

}
