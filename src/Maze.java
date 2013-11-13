import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Maze {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFileName = "d:/input/a.txt";
		String outputFileName = "d:/input/b.txt";

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

				String nm = reader.readLine();
				String[] nmarray = nm.split(" ");
				int N = Integer.parseInt(nmarray[0]);
				int M = Integer.parseInt(nmarray[1]);

				String SE = reader.readLine();
				String[] seArray = SE.split(" ");
				int Sx = Integer.parseInt(seArray[0]);
				int Sy = Integer.parseInt(seArray[1]);
				int Ex = Integer.parseInt(seArray[2]);
				int Ey = Integer.parseInt(seArray[3]);

				int[][] mtrix = new int[N][M];

				for (int line = 0; line < N; line++) {
					String strLine = reader.readLine();
					String[] lineStr = strLine.split(" ");
					for (int col = 0; col < M; col++) {
						mtrix[line][col] = Integer.parseInt(lineStr[col]);
					}
				}

				Stack<Integer> stackX = new Stack<Integer>();
				Stack<Integer> stackY = new Stack<Integer>();
				Stack<Integer> stackZ = new Stack<Integer>();

				boolean isFound = false;
				int maxAmount = 0;
				int minStep = 0;

				if (mtrix[Sx][Sy] < 0 || mtrix[Ex][Ey] < 0) {
					isFound = false;
				} else {
					stackX.push(Sx);
					stackY.push(Sy);
					stackZ.push(1111);
					mtrix[Sx][Sy] = -mtrix[Sx][Sy];
					goMaze(mtrix, N, M, stackX, stackY, stackZ, 0, 0, 0, 0, Ex,
							Ey);
				}

				// System.out.println(sb.toString());
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

	private static void goMaze(int[][] mtrix, int N, int M,
			Stack<Integer> stackX, Stack<Integer> stackY,
			Stack<Integer> stackZ, int step, int minStep, int amount,
			int maxAmount, int Ex, int Ey) {
		if (stackX.isEmpty() == false) {
			int x = stackX.pop();
			int y = stackY.pop();
			int z = stackZ.pop();

			step++;

			if (mtrix[x][y] < 0) {
				mtrix[x][y] = -mtrix[x][y];
			}
			amount = amount + mtrix[x][y];

			if (x == Ex && y == Ey) {
				if (minStep == 0) {
					minStep = step;
					maxAmount = amount;
				} else if (step < minStep) {
					minStep = step;
					maxAmount = amount;
				} else if (step == minStep) {
					if (amount > maxAmount) {
						maxAmount = amount;
					}
				}
				System.out.println("!" + minStep);
				System.out.println("!" + maxAmount);
			}

			else if (x - 1 >= 0 && mtrix[x - 1][y] >= 0 && (z / 1000 == 1)) {
				stackX.push(x);
				stackY.push(y);
				stackZ.push(z - 1000);

				stackX.push(x - 1);
				stackY.push(y);
				stackZ.push(1111);

				mtrix[x][y] = -mtrix[x][y];
				goMaze(mtrix, N, M, stackX, stackY, stackZ, step, minStep,
						amount, maxAmount, Ex, Ey);
			}

			else if (x + 1 < N && mtrix[x + 1][y] >= 0
					&& ((z - z / 1000 * 1000) / 100 == 1)) {
				stackX.push(x);
				stackY.push(y);
				stackZ.push(z - 100);

				stackX.push(x + 1);
				stackY.push(y);
				stackZ.push(1111);
				mtrix[x][y] = -mtrix[x][y];
				goMaze(mtrix, N, M, stackX, stackY, stackZ, step, minStep,
						amount, maxAmount, Ex, Ey);
			} else if (y - 1 >= 0 && mtrix[x][y - 1] >= 0
					&& (z/ 10%2 == 1)) {
				
				stackX.push(x);
				stackY.push(y);
				stackZ.push(z - 10);

				stackX.push(x);
				stackY.push(y - 1);
				stackZ.push(1111);

				mtrix[x][y] = -mtrix[x][y];
				goMaze(mtrix, N, M, stackX, stackY, stackZ, step, minStep,
						amount, maxAmount, Ex, Ey);
			} else if (y + 1 < M
					&& mtrix[x][y + 1] >= 0
					&& (z%10 == 1)) {
				stackX.push(x);
				stackY.push(y);
				stackZ.push(z - 1);

				stackX.push(x);
				stackY.push(y + 1);
				stackZ.push(1111);

				mtrix[x][y] = -mtrix[x][y];
				goMaze(mtrix, N, M, stackX, stackY, stackZ, step, minStep,
						amount, maxAmount, Ex, Ey);
			} else {
				amount = amount - mtrix[x][y];
				step--;
				goMaze(mtrix, N, M, stackX, stackY, stackZ, step, minStep,
						amount, maxAmount, Ex, Ey);
			}

		}
	}
}
