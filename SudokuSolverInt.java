package sudoku;


public interface SudokuSolverInt {
	/**
	 * Solves the sudoku.
	 * 
	 * @return true if the sudoku is solveable
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void set(int row, int col, int digit);
	
	/**
	 * Returns number from specific row and column
	 */
	int get(int row, int col);

	/**
	 * Clears digit on specific row and column
	 */
	void clear(int row, int col);

	/**
	 * Clears all digits in the sudoku.
	 */
	void clearAll();

	/**
	 * Checks that the digit in the box row, col follows the sudoku rules.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 * @return true if the digit in the box row, col follows the sudoku rules.
	 */
	boolean isValid(int row, int col);

	/**
	 * To be done
	 */
	boolean isAllValid();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setGrid(int[][] m);

	/**
	 * To be done
	 */
	int[][] getGrid();
}