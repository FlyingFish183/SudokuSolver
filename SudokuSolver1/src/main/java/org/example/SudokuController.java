package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuController {
    @FXML
    GridPane gridPane;
    @FXML
    Button solveButton;

    private int[][] puzzle;

    public void loadPuzzle(String difficulty) {
        int[][][] problems = ProblemList.getAllProblems();
        switch (difficulty) {
            case "Easy":
                puzzle = problems[0];
                break;
            case "Medium":
                puzzle = problems[1];
                break;
            case "Hard":
                puzzle = problems[2];
                break;
            case "Very Hard":
                puzzle = problems[3];
                break;
            case "Super Hard":
                puzzle = problems[4];
                break;
        }
        displayPuzzle(puzzle);
    }

    public void loadPuzzleByIndex(int index) {
        int[][][] problems = ProblemList.getAllProblems();
        if (index >= 0 && index < problems.length) {
            puzzle = problems[index];
            displayPuzzle(puzzle);
        }
    }

    private void displayPuzzle(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Construct the fx:id of the cell
                String cellId = String.format("#cell%d%d", row, col);
                TextField cell = (TextField) gridPane.lookup(cellId);
                if (cell != null) {
                    if (puzzle[row][col] != 0) {
                        cell.setText(String.valueOf(puzzle[row][col]));
                        cell.setEditable(false);
                    } else {
                        cell.setText("");
                        cell.setEditable(true);
                    }
                }
            }
        }
    }

    @FXML
    public void handleSolveButton() {
        SudokuSolver solver = new SudokuSolver(puzzle);
        int[][] solution = solver.solve();
        if (solution != null) {
            displayPuzzle(solution);
        } else {
            System.out.println("No solution exists.");
        }
    }
}