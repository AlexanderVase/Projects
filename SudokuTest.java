package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {
	private static SudokuSolver solver;

	@BeforeEach
	void setUp() throws Exception {
		solver = new SudokuSolver();
	}

	@AfterEach
	void tearDown() throws Exception {
		solver = null;
	}
	@Test
	void solveEmpty() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0}
		};
		solver.setGrid(grid);
		assertTrue(solver.solve());
	}
	@Test
	void solvePossibleFig1() {
		int[][] grid = {
				{0, 0, 8, 0, 0, 9, 0, 6 ,2},
				{0, 0, 0, 0, 0, 0, 0, 0 ,5},
				{1, 0, 2, 5, 0, 0, 0, 0 ,0},
				{0, 0, 0, 2, 1, 0, 0, 9 ,0},
				{0, 5, 0, 0, 0, 0, 6, 0 ,0},
				{6, 0, 0, 0, 0, 0, 0, 2 ,8},
				{4, 1, 0, 6, 0, 8, 0, 0 ,0},
				{8, 6, 0, 0, 3, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0}
		};
		solver.setGrid(grid);
		assertTrue(solver.solve());
	}
	@Test
	void solvePossibleAndAllValid() {
		int[][] grid = {
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
		solver.setGrid(grid);
		assertTrue(solver.isAllValid());
		assertTrue(solver.solve());
	}
	@Test
	void solveWrong() {
		int[][] grid = {
				{1, 1, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{1, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0}
		};
		solver.setGrid(grid);
		assertFalse(solver.solve());
	}
	@Test
	void solveImpossible() {
		int[][] grid = {
				{1, 2, 3, 0, 0, 0, 0, 0 ,0},
				{4, 5, 6, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 7, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0}
		};
		solver.setGrid(grid);
		assertFalse(solver.solve());
	}
	@Test
	void clear() {
		int[][] grid = {
				{1, 1, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{1, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0}
		};
		solver.setGrid(grid);		
		solver.clearAll();
		int[][] clearedGrid = solver.getGrid();
	    for (int row = 0; row < clearedGrid.length; row++) {
	        for (int col = 0; col < clearedGrid[row].length; col++) {
	            assertEquals(0, clearedGrid[row][col]);
	        }
	    }
	}
	@Test
	void setGetClearAndValid() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0},
				{0, 0, 0, 0, 0, 0, 0, 0 ,0}
		};
		solver.setGrid(grid);
		solver.set(2, 4, 8);
		solver.isValid(2, 4);
		solver.get(2, 4);
		assertEquals(8, grid[2][4]);
		solver.clear(2, 4);
		assertEquals(0, grid[2][4]);
	}
	
}