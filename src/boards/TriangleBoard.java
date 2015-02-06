package boards;

import java.util.Scanner;

import other.Coordinate;
import tests.AltDiagonalTest;
import tests.BoardTest;
import tests.HorizontalTest;
import tests.VerticalTest;

public class TriangleBoard extends GameBoard{

	/**
	 * Initializes the Traingle GameBoard data structure.
	 * 
	 * @author Tylor
	 * @author Brandt
	 */
	public TriangleBoard() {
		gameBoard = new int[][] {
				{2, 0, 0, 0, 0},
				{2, 2, 0, 0, 0},
				{2, 2, 2, 0, 0},
				{2, 2, 2, 2, 0},
				{2, 2, 2, 2, 2}
				};

		
		tests.add( new VerticalTest() );
		tests.add( new HorizontalTest() );
		tests.add( new AltDiagonalTest() );

		setStartingPosition();
	}

	/**
	 * Allows the user to chose which position is empty at the start of the game.
	 * 
	 * This is unique to the triangle game.
	 * 
	 * @author Brandt
	 */
	private void setStartingPosition() {
		do {
			Scanner keyboard = new Scanner(System.in); 
			
			drawBoard();
			
			System.out.print("\nPlease select a position to empty: ");
			
			String input = keyboard.nextLine();
			
			try {
				int position = Integer.parseInt(input);
				Coordinate coordinate = getCoordinate(position);

				if ( coordinate != null && isPeg(coordinate) ) {
					emptyPos(coordinate);
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid position. Please enter a number 1-15.");
			}
		}while(true);
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
			drawOffset(row);
			for (int col = 0; col < gameBoard[row].length; col++) {
				int peg = gameBoard[row][col];
				holeCounter = drawPeg(peg, holeCounter);
			}
			
			System.out.print("\n");
		}
	}
	
	/**
	 * drawOffset prints white space to create the offset necessary to make the board
	 * resemble a triangle.
	 * 
	 * The white space printed is based on the length of INVALID_GFX.
	 * 
	 * @param row determines how much white space is printed.
	 */
	private void drawOffset(int row) {
		String offset = INVALID_GFX.substring(INVALID_GFX.length()/2);
		int offsetCount = gameBoard.length - row;
		
		for (int i = 0; i < offsetCount; i++) {
			System.out.print(offset);
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
		System.out.println("Welcome to Peg solitare, the goal of the game is to remove all pegs but one");
		System.out.println("to remove a peg, you must take select a peg, and move that peg over another into");
		System.out.println("an empty space. Think jumping in checkers. Enter the coordinate of the peg you wish");
		System.out.println("to move, and then the coordinate of the empty space you wish to move that peg into.");
		System.out.println("The game is over when there are no longer any moves the player can make, with the");
		System.out.println("Player winning if there is only one peg remaining at this time.");
	}
}
