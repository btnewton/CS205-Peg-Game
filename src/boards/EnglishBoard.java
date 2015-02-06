package boards;

import other.Coordinate;
import tests.HorizontalTest;
import tests.VerticalTest;

public class EnglishBoard extends GameBoard{

	/**
	 * Initializes the English GameBoard data structure.
	 * 
	 * @author Tylor
	 * @author Brandt
	 */
	public EnglishBoard() {
		gameBoard = new int[][] {
				{0, 0, 2, 2, 2, 0, 0},
				{0, 0, 2, 2, 2, 0, 0},
				{2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 1, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2},
				{0, 0, 2, 2, 2, 0, 0},
				{0, 0, 2, 2, 2, 0, 0}
				};
		
		tests.add(new VerticalTest());
		tests.add(new HorizontalTest());
	}

	/**
	 * drawBoard prints the game board in the console.
	 * 
	 * Each hole on the board displays information regarding its position and
	 * whether or not it is occupied.
	 * 
	 * @author Kai
	 * @author Brandt
	 */
	@Override
	public void drawBoard() {
		
		int holeCounter = 1;
		
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				int peg = gameBoard[row][col];
				holeCounter = drawPeg(peg, holeCounter);
			}
			
			System.out.print("\n");
		}
	}
	
	/**
	 * Show helpful information about commands and rules to the user.
	 * 
	 * @author Kai
	 */
	@Override
	public void showHelp() {
		System.out.println("\nGAME COMMANDS AND RULES:");
		System.out.println("press q to quit.");
		System.out.println("press h for help.");
		System.out.println("press s for stats.");
		System.out.println("Welcome to English Peg solitare, the goal of the game is to remove all pegs but one");
		System.out.println("to remove a peg, you must take select a peg, and move that peg over another into");
		System.out.println("an empty space. Think jumping in checkers. Enter the coordinate of the peg you wish");
		System.out.println("to move, and then the coordinate of the empty space you wish to move that peg into.");
		System.out.println("Moves to an empty position can be made horizontally or vertically.");
		System.out.println("The game is over when there are no longer any moves the player can make, with the");
		System.out.println("player winning if there is only one peg remaining at this time.");
	}
}
