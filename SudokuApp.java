package sudoku;

public class SudokuApp {
	private static int[][] board;
	private static SudokuSolver solver;
    private static SudokuGUI gui;

	public static void main(String[] args) {
		int[][] board = {
				{0, 0, 0, 5, 8, 0, 6, 0 ,0},
				{0, 0, 0, 2, 0, 0, 4, 7 ,0},
				{0, 6, 0, 0, 0, 0, 0, 9 ,0},
				{3, 0, 0, 0, 0, 7, 0, 5 ,0},
				{2, 0, 6, 0, 5, 0, 3, 0 ,7},
				{0, 5, 0, 4, 0, 0, 0, 0 ,2},
				{0, 2, 0, 0, 0, 0, 0, 6 ,0},
				{0, 7, 8, 0, 0, 1, 0, 0 ,0},
				{0, 0, 1, 0, 3, 2, 0, 0 ,0}
		};
		solver = new SudokuSolver();
        solver.setGrid(board);
        gui = new SudokuGUI(solver);
	}
}