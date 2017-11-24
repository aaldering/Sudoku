
public class Sudoku {
	private static final int size = 9;
	static String testInput = "800000000003600000070090200050007000000045700000100030001000068008500010090000400";
	
	static int [][] grid = new int[size][size];
	static int[] input = new int[81];
	
	public static int[][] reader() {
		String[] inputString = testInput.split(""); 
		//args[0].split(""); 
		if(testInput.matches("[0-9]+") && testInput.length() == 81) {
			System.out.println("This is the sudoku you want me to solve:");
		} else {
			System.out.println("This is not valid input. Please input a string of 81 numbers. Don't use letters :)");
			System.exit(0);
		}
		for (int i = 0; i < inputString.length; i++)
			input[i] = Integer.parseInt(inputString[i]);
		int k = 0;
		for (int i=0; i<size; i++) {
			for (int j = 0; j < size; j++) {			
				grid[i][j] =   input[k];
				k++;
			}	
		}
		
		//print starting positiions
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(grid[i][j] + " ");
				if ((j+1) % 3 == 0 && j < 8) {
					System.out.print("| ");
				}
			}
			System.out.println();
			if ((i+1) % 3 == 0 && i < 8) {
				System.out.println("- - -   - - -   - - -");
			}
		}
		return grid;
}


	public static void main(String [] args) {
		reader();
		System.out.println("");
		System.out.println("Let's solve this puppy...");
		System.out.println("");
		solver();
		System.out.println("");
		System.out.println("K thnx bye");

		
	}
	
	public static void solver() {
		double startTime = System.nanoTime();
		double endTime;
		double totalTime;
		
		if (filler(0, 0)) {
			endTime = System.nanoTime();
			System.out.println("Here is the solution: ");

			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.print(grid[i][j] + " ");
					if ((j+1) % 3 == 0 && j < 8) {
						System.out.print("| ");
					}
				}
				System.out.println();
				if ((i+1) % 3 == 0 && i < 8) {
					System.out.println("- - -   - - -   - - -");
				}
			}

			totalTime = (endTime - startTime) / 1000000000;
			System.out.printf("Solve time: %.2f seconds\n", totalTime);
		}

		else {
			System.out.println("I couldn't solve this puppy");
		}
	}

	public static boolean filler(int row, int col) 
	{	
		
		if (row == size) {
			row = 0;
			if (++col == size) {
				return true;
			}
		}

		// skip if not 0, 0 means no value and needs to be solved
		if (grid[row][col] != 0) {
			return filler(row+1, col);
		}

		else {
			for (int i = 1; i < 10; i++) {
				if (errorChecker(row, col, i)) {
					grid [row][col] = i;

			
					if (filler(row+1, col)) {
						return true;
					}
				}
			}
		}
		// if no solution, start all over again
		grid[row][col] = 0; 
		return false;
	}

	public static boolean errorChecker(int row, int col, int candidateValue) {
		// row errors check
		for (int i = 0; i < size; i++) {
			if (grid[row][i] == candidateValue) {
				return false;
			}
		}

		// columns (col) errors check
		for (int i = 0; i < size; i++) {
			if (grid[i][col] == candidateValue) {
				return false;
			}
		}

		// box errors check (typical suduko consists of 9 boxes, each 3 x 3, which also )
		int boxRow = 3*(row / 3);
		int boxCol = 3*(col / 3);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[boxRow + i][boxCol + j] == candidateValue) {
					return false;
				}
			}
		}

		return true;

	}
}