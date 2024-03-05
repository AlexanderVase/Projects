package sudoku;

import java.util.Arrays;

public class SudokuSolver implements SudokuSolverInt {
	private int[][] grid;
	
	public SudokuSolver() {
		grid = new int[9][9];
	}

	@Override
	public boolean solve() {
		for (int r = 0; r < 9; r++) {
	        for (int c = 0; c < 9; c++) {
	            if (grid[r][c] != 0 && !isValid(r, c, grid[r][c])) {
	                return false; //False om start grid är impossible
	            }
	        }
	    }
		return solve(0, 0);
	}
	private boolean solve(int row, int col) {
		if(row == 9) {	//Basfall när vi gått igenom alla rader och pusslet är löst
			return true;	
		}
		int nextCol = (col + 1) % 9;		//Flyttar kolumnen till första platsen igen efter att vi nått 9
		int nextRow = row;
		if(nextCol == 0) {					//Om vi blivit klara med en hel rad bli col 0 och vi hoppar ner en rad
			nextRow += 1;
		}
		if(grid[row][col] != 0) {
			return solve(nextRow, nextCol);
		}
		for(int digit = 1; digit <= 9; digit++) {
			if(isValid(row, col, digit)) {
				grid[row][col] = digit;
				if(solve(nextRow, nextCol)) {	//Rekursivt lös för nästa ruta
					return true;
				}
				grid[row][col] = 0; //Backtrackar genom att nolla rutan och gå tillbaka
			}
		}
		return false;
	}

	@Override
	public void set(int row, int col, int digit) {
		if(row >= 9 || col >= 9 || row < 0 || col < 0) {		//Kontrollerar att vi inte är utanför sudokot
			throw new IllegalArgumentException("Outside of grid");
		}
		if(digit > 9 || digit < 0) {									//Kontrollerar att man inte försöker sätta in värde utanför mängden
			throw new IllegalArgumentException("Digit must be in 1-9 range");
		}
		grid[row][col] = digit;			//Sätter värdet i specifik plats
	}

	@Override
	public int get(int row, int col) {
		return grid[row][col];			//Hämtar värde för specifik plats
	}

	@Override
	public void clear(int row, int col) {
		set(row, col, 0);					//Nollar värde för specifik plats
	}

	@Override
	public void clearAll() {			//Går igenom hela matrisen och nollar allt
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				grid[r][c] = 0;
			}
		}
	}

	@Override
	public boolean isValid(int row, int col) {
		int digit = grid[row][col];
		return isValid(row, col, digit);
	}
	private boolean isValid(int row, int col, int digit) {
		for(int c = 0; c < 9; c++) {		//Kontrollerar om digit finns i raden
			if(grid[row][c] == digit && c != col) {
				return false;
			}
		}
		for(int r = 0; r < 9; r++) {		//Kontrollerar om digit finns i kolumnen
			if(grid[r][col] == digit && r != row) {
				return false;
			}
		}											//Kontrollerar om digit finns i 3x3 rutan
		int boxRow = row - row % 3; 	//Hittar radnummret för första rutan
		int boxCol = col - col % 3;		//Hittar kolumnnumret för första rutan
		for(int r = boxRow; r < boxRow + 3; r++) {
			for(int c = boxCol; c < boxCol + 3; c++) {
				if(grid[r][c] == digit && r != row && c != col) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isAllValid() {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				if(grid[r][c] != 0 && !isValid(r, c)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void setGrid(int[][] m) {
		if(m.length != 9 || m[0].length != 9) {
			throw new IllegalArgumentException("Grid must be 9x9");
		}
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				if(m[r][c] < 0 || m[r][c] > 9) {
					throw new IllegalArgumentException("Grid can only contain numbers between 1-9");
				}
			}
		}
		this.grid = m;
	}

	@Override
	public int[][] getGrid() {
		int[][] temp = new int[9][9];
		for(int i = 0; i < 9; i++) {
			temp[i] = Arrays.copyOf(grid[i], grid[i].length);
		}
		return temp;
	}

}