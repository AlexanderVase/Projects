package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuGUI extends JFrame {

    public JTextField[][] grid; 
    private int[][] board;
    private SudokuSolver solver;
    private JButton solveButton;
    private JButton clearButton;
    private JButton newButton;
    private ArrayList<int[][]> sudokuList; 
    private int currentSudokuIndex;

    public SudokuGUI(SudokuSolver solver) {
        this.grid = new JTextField[9][9];
        this.solver = solver;
        board = solver.getGrid();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        newButton = new JButton("New soduko");
        sudokuList = new ArrayList<>();
        currentSudokuIndex = 0;

        readSudokusFromFile();

        setUp();
        setUpListeners();
        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void setUp() {
        setLayout(new BorderLayout());
        JPanel grids = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new JTextField();
                if (board[i][j] != 0) {
                    grid[i][j].setText(String.valueOf(board[i][j]));
                    grid[i][j].setEditable(false);
                }
                grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                if (i < 3 && j < 3 || i < 3 && j > 5 || (2 < i && i < 6) && (2 < j && j < 6) || i > 5 && j < 3 || i > 5 && j > 5) {
                    grid[i][j].setBackground(Color.ORANGE);
                }
                grids.add(grid[i][j]);
            }
        }
        add(grids, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(newButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Sudoku Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setUpListeners() {
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (readGrid()) {  
                    if (!solver.solve()) {  
                        JOptionPane.showMessageDialog(SudokuGUI.this, "Unsolvable sudoku!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    updateGrid();  
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solver.clearAll();
                updateGrid();
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateNewSudoku();
                updateGrid();
            }
        });
    }

    private boolean readGrid() {
        boolean invalidInput = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String cellValue = grid[i][j].getText().trim();
                
                try {
                    int value;
                    if (cellValue.isEmpty()) {
                        value = 0; 
                    } else {
                        value = Integer.parseInt(cellValue);
                    }
                    
                    if (value > 0 && value <= 9 || cellValue.isEmpty()) {
                        board[i][j] = value;
                    } else {
                        board[i][j] = 0;
                        grid[i][j].setText("");
                        invalidInput = true;
                    }
                } catch (NumberFormatException ex) {
                    grid[i][j].setText("");
                    board[i][j] = 0;    
                    invalidInput = true;
                }
            }
        }
        if (invalidInput) {
            JOptionPane.showMessageDialog(this, "Invalid input detected. Please enter numbers between 1 and 9.", "Input Error", JOptionPane.ERROR_MESSAGE);
            invalidInput = false; 
            return false;
        } else {
            solver.setGrid(board);
            return true;
        }
    }

    private void updateGrid() {
        board = solver.getGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    grid[i][j].setText("");
                } else {
                    grid[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
    }

    private void generateNewSudoku() {
        if (currentSudokuIndex < sudokuList.size()) {
            board = sudokuList.get(currentSudokuIndex);
            currentSudokuIndex++;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != 0) {
                        grid[i][j].setEditable(false);
                    } else {
                        grid[i][j].setEditable(true); // Sätt rutorna till redigerbara igen
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No more sudokus available.", "Error", JOptionPane.ERROR_MESSAGE);
            currentSudokuIndex = 0;
        }
        solver.setGrid(board);
        updateGrid();
    }

    private void readSudokusFromFile() {
        SudokuFileReader reader = null;
        try {
            reader = new SudokuFileReader("/Users/alexandervase/Desktop/edaa01-workspace-vscode/Sudoku/src/sudoku/sudokuTextFile.txt");
            while (reader.hasMoreSudokus()) {
                sudokuList.add(reader.getNextSudoku());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file: SudokuTextFile");
           // System.exit(1);
        }
    }

    private class SudokuFileReader {
        private Scanner scanner;
    
        public SudokuFileReader(String filename) throws FileNotFoundException {
            this.scanner = new Scanner(new File(filename));
        }
    
        public boolean hasMoreSudokus() {
            return scanner.hasNextLine();
        } 
        public int[][] getNextSudoku() {
            int[][] sudoku = new int[9][9];
            for (int i = 0; i < 9; i++) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) { // Om raden är tom, hoppa över den
                    i--; // Minska räknaren för att inte hoppa över en rad i sudokumatrisen
                    continue;
                }
                String[] numbers = line.split(" ");
                for (int j = 0; j < 9; j++) {
                    if (!numbers[j].isEmpty()) { // Kontrollera om strängen är tom innan konvertering
                        sudoku[i][j] = Integer.parseInt(numbers[j]);
                    } else {
                        sudoku[i][j] = 0; // Om strängen är tom, sätt värdet till 0
                    }
                }
            }
            return sudoku;
        }
    }
}
